import java.util.*;

/*
* A simple Java implementation of the partial digest problem backtracking algorithm.
*
* Instructions: compile using javac pdp.java
* Input format: <num elements>
*		<num1>
*		<num2>
*		...
*		<num n>
* Output format: X: [elements]
*
*/

public class pdp{
	
	//Need a pair class to store pairs of value n and d;
	//this should not cause problems, the class is small
	public class pair{
		final int n;
		final int d;
		
		//constructor to initialize values
		pair(int num, int dval){
			this.n = num;
			this.d = dval;
		}
	}
	
	//Scanner for input, L to keep track of provided elements, X is the solution list
	//and S is the stack keeping track of pairs
	Scanner 	    scan;
	LinkedList<Integer> L;
	LinkedList<Integer> X;
	Stack<pair>	    S;
	
	//constructor to initialize a pdp class
	pdp(){
		scan = new Scanner(System.in);
		L    = new LinkedList<>();
		X    = new LinkedList<>();
		S    = new Stack<>();
	}
	
	//Scan input and add given digest fragments to L
	public void readFragments(){
		int num_el = scan.nextInt();
		int next;
		
		for(int i = 0; i < num_el; i++){
			next = scan.nextInt();
			this.L.add(next);
		}
	}
	
	//Return the set create by the delta function
	//el - the current fragment passed to the delta function
	public LinkedList<Integer> deltaSet(int el){
		LinkedList<Integer> new_list = new LinkedList<>();
		
		//walk through X and build new_list
		for(int i : X){
			if(i != el){
				new_list.add(Math.abs(el - i));
			}
		}
		
		return new_list;
	}
	
	//Return true if the set returned by delta is a subset of L
	public boolean delta(int el, LinkedList<Integer> X){
		//build a new list that's the result of the delta function
		LinkedList<Integer> new_list = this.deltaSet(el);
		
		//check for subset
		for(int i : new_list){
			if(!this.L.contains(i)){
				return false;
			}
		}
		
		//a subset
		return true;
	}
	
	//Returns max value from L. Implemented in a naivë way.
	public int maxL(){
		int max = L.getFirst();
		
		for(Integer i : L){
			if(i > max){
				max = i;
			}
		}
		
		return max;
	}
	
	public void pdp(){
		Integer y_max = this.maxL();
		L.remove(y_max);
		X.add(0);
		X.add(y_max);
		
		//Keep track of backtracking steps
		Integer d = 0;
		
		while(!L.isEmpty()){
			
			//Largest element of L
			Integer y = this.maxL(); 
			
			//Placement on left hand side
			if(delta(y, X) && d == 0){
				for(Integer i : this.deltaSet(y)){
					L.remove(i);
				}
				
				X.add(y);
				S.push(new pair(y, 0));
				
			//Placement on the right hand side
			} else if(delta(y_max - y, X) && d <= 1){
				for(Integer i : deltaSet(y_max - y)){
					L.remove(i);
				}
				
				X.add(y_max - y);
				S.push(new pair(y_max - y, 1));
				d = 0;
			} else if(!S.isEmpty()){
				//Recall last position from stack
				pair y_pop = S.pop();
				d = y_pop.d + 1;
				
				//Reconstruct previous distance set
				LinkedList<Integer> temp = deltaSet(y_pop.n);
				for(Integer i : temp){
					L.add(i);
				}
				
				//Reconstruct previous position set
				X.remove(new Integer(y_pop.n));
				
			//Impossible backtracking 
			} else{
				S.clear();
				System.out.println("No feasible solution.");
				break;
			}
		}
		
		//Output feasible solution
		Collections.sort(X);
		System.out.println("X: " + X.toString() + " is a solution.");
	}
	
	//main method to run everything 
	public static void main(String[] args){
		pdp pdp_instance = new pdp();
		pdp_instance.readFragments();
		pdp_instance.pdp();
	}
}
	
	
	
