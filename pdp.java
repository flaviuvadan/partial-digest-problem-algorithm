import java.util.*;

/*
* A simple Java implementation of the partial digest problem backtracking algorithm.
*
* Instructions: compile using javac pdp.java
* Input format: <num elements>
*				<num1>
*				<num2>
*				...
*				<num n>
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
	Scanner 			scan;
	LinkedList<Integer> L;
	LinkedList<Integer> X;
	Stack<pair>			S;
	
	//constructor to initialize a pdp class
	pdp(){
		scan = new Scanner(System.in);
		L	 = new LinkedList<>();
		X 	 = new LinkedList<>();
		S	 = new Stack<>();
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
	
	//main method to run everything 
	public static void main(String[] args){
		
	}
}
	
	
	