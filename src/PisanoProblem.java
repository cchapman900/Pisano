/*
 * Chris Chapman
 * BMCCACM Pisano Challenge Problem
 */


/* *********************************************************************
 * The main initial problem was getting faulty results as a result of
 * using both int and long which were both too small to contain the
 * Fibonacci numbers needed to calculate modulus out to ~1,000,000. 
 * BigInteger seems to be necessary to get those high values. 
 * ******************************************************************* */
import java.math.BigInteger;
import java.util.Scanner;




public class PisanoProblem {
	
	
	/* ****************************************
	 * getInputFromUser()
	 * 
	 * Get input from user:
	 * Takes P number of data sets and stores each 
	 * modulus M for its period to be calculated.
	 ****************************************** */
	public static int[] getInputFromUser(){
		
		Scanner scanner = new Scanner(System.in);
			
		int P;							//Number of data sets
		int[] input;					//Array of M values
		
		System.out.println("Please enter number of data sets: ");
		P = scanner.nextInt();
		
		input = new int[P]; 			//create array of P values for each M
		
		for (int i = 1; i <= P; i++) { 	//Fill the array with M values
			
			int M;						//Value of modulus
			
			System.out.println("Please enter M value " + i + " of " + P + ": ");
			M = scanner.nextInt();
			
			input[i-1] = M;				//Add value of modulus to array of moduli
		}
		
		return input;			
	}
	
	
	
	
	/* *******************************************
	 * intToBigInt(int i)
	 * 
	 * Utility method to convert int to BigInteger
	 ******************************************* */
	public static BigInteger intToBigInt(int i){
		return new BigInteger(Integer.toString(i)); //BigInteger only takes Strings
	}
	
	
	
	
	
	/* ******************************************
	 * getPisano(BigInteger modulus)
	 * 
	 * Create the Fibonacci series and calculate 
	 * the Pisano Period of the given modulus
	 ******************************************** */
	public static BigInteger getPisano(BigInteger modulus){
		
		//Convenience variables so you don't have to keep using intToBigInt.
		BigInteger one = intToBigInt(1);
		BigInteger zero = intToBigInt(0);
		
		//Create the first two values of the Fibonacci Sequence: 1, 1, ...
		BigInteger fib_N = one;			//the Nth value in the Fibonacci sequence
		BigInteger fib_NPlusOne= one;	//the Nth+1 value in the sequence
		
		
		/* ***********************************************
		 * Increment the N value of the Fibonacci Sequence
		 * and calculate the modulus.
		 * 
		 * Note: For BigInteger, must use mod() instead of %
		 ************************************************* */
		for (BigInteger i = intToBigInt(2); i.compareTo(zero) != -1; i = i.add(one)) { // i.compareTo(zero) != -1 will return if i is negative. Theoretically this is an infinite loop if the period is not found. Might not be the best way to do it(?)
			
			BigInteger mod1 = fib_N.mod(modulus);
			BigInteger mod2 = fib_NPlusOne.mod(modulus);
			
			/* ************************************************
			 * Every Pisano period ends with the values 1 and 0
			 * Once these values are found, return the index at
			 * which they are in the Fibonacci series.
			 ************************************************ */
			if (mod1.equals(one) && mod2.equals(zero)){
				
				return i;	//Return index of the end of the Pisano Period.
				
			} else {		//If the period doesn't end here...
				
				//...increment N in Fibonnacci series.
				BigInteger temp = fib_N.add(fib_NPlusOne);
				fib_N = fib_NPlusOne;
				fib_NPlusOne= temp;
				
			}
		}
		
		//Return -1 if it can't find the period. 
		return new BigInteger("-1");
	}
	
	

	/* ***********
	 * Main Method
	 *********** */
	public static void main(String[] args) {
		
		
		int[] userInput = getInputFromUser();
		
		
		for (int i = 1; i <= userInput.length; i++) {
			
			BigInteger result = getPisano(intToBigInt(userInput[i-1]));

			if (result.equals("-1")) {
			
				System.out.println("Oops, there was a problem. Couldn't find result.");
			
			} else {
			
				System.out.println(i + " " + result);
		
			}
		} 
	}
	
	
	
}
