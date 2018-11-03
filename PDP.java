package partial-digest-problem-algorithm;

import java.util.*;

/*
* A Java implementation of the partial digest problem backtracking algorithm.
*
* Instructions: compile using javac partial_digest.java
* Input format: <num elements>
*		<num1>
*		<num2>
*		...
*		<num n>
* Output format: X: [elements]
*
*/
public class PDP {

    /**
     * Keep track of input
     */
    private Scanner scan;

    /**
     * Keep track of provided elements
     */
    private LinkedList<Integer> L;

    /**
     * Keep track of pairs
     */
    private Stack<Pair> S;

    /**
     * The solution list
     */
    public LinkedList<Integer> X;

    PDP() {
        scan = new Scanner(System.in);
        L = new LinkedList<>();
        X = new LinkedList<>();
        S = new Stack<>();
    }

    /**
     * Scan input and add given digest fratments to L
     */
    public void readFragments() {
        int num_el = scan.nextInt();
        int next;

        for (int i = 0; i < num_el; i++) {
            next = scan.nextInt();
            this.L.add(next);
        }
    }

    /**
     * Return the set created by the delta function
     *
     * @param el - current fragment
     * @return the new set
     */
    public LinkedList<Integer> deltaSet(int el) {
        LinkedList<Integer> new_list = new LinkedList<>();

        for (int solution : X) {
            if (solution != el) {
                new_list.add(Math.abs(el - solution));
            }
        }

        return new_list;
    }

    //Return true if the set returned by delta is a subset of L

    /**
     * Tell whether the set returned by delta is a subset of L
     *
     * @param el - current fragment
     * @param X  - the set
     * @return true if a subset, false otherwise
     */
    public boolean delta(int el, LinkedList<Integer> X) {
        LinkedList<Integer> new_list = this.deltaSet(el);

        for (int element : new_list) {
            if (!this.L.contains(element)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Return max value from L (a naive, linear, implementation)
     *
     * @return max value from L
     */
    public int maxL() {
        int max = L.getFirst();

        for (Integer element : L) {
            if (element > max) {
                max = element;
            }
        }

        return max;
    }

    public void partial_digest() {
        Integer y_max = this.maxL();
        L.remove(y_max);
        X.add(0);
        X.add(y_max);

        //Keep track of backtracking steps
        Integer d = 0;

        while (!L.isEmpty()) {

            //Largest element of L
            Integer y = this.maxL();

            //Placement on left hand side
            if (delta(y, X) && d == 0) {
                for (Integer i : this.deltaSet(y)) {
                    L.remove(i);
                }

                X.add(y);
                S.push(new pair(y, 0));

                //Placement on the right hand side
            } else if (delta(y_max - y, X) && d <= 1) {
                for (Integer i : deltaSet(y_max - y)) {
                    L.remove(i);
                }

                X.add(y_max - y);
                S.push(new pair(y_max - y, 1));
                d = 0;
            } else if (!S.isEmpty()) {
                //Recall last position from stack
                pair y_pop = S.pop();
                d = y_pop.d + 1;

                //Reconstruct previous distance set
                LinkedList<Integer> temp = deltaSet(y_pop.n);
                for (Integer i : temp) {
                    L.add(i);
                }

                //Reconstruct previous position set
                X.remove(new Integer(y_pop.n));

                //Impossible backtracking
            } else {
                S.clear();
                System.out.println("No feasible solution.");
                break;
            }
        }

        //Output feasible solution
        Collections.sort(X);
        return X;
    }

    //main method to run everything
    public static void main(String[] args) {
        pdp PDP_instance = new PDP();
        PDP_instance.readFragments();
        partial_digest = PDP_instance.partial_digest()
        System.out.println("X: " + partial_digest.toString() + " is a solution.");
    }
}

	
	
