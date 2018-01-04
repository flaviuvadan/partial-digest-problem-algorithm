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
	
	//need a pair class to store pairs of value n and d
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
	
	//scanner for input, L to keep track of provided elements, X is the solution list
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
	
	//main method to run everything 
	public static void main(String[] args){
		
	}
}
	
	
	