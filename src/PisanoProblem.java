/*
 * Chris Chapman
 * BMCCACM Pisano Challenge Problem
 * 2/20/14
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
	 * Utility method to convert int to BigInteger
	 ******************************************* */
	public static BigInteger intToBigInt(int i){
		return new BigInteger(Integer.toString(i)); //BigInteger only takes Strings
	}
	
	
	
	
	
	//create Fibonacci Set and calculate modulus period in same method
	public static BigInteger getPisano(BigInteger modulus){
		
		BigInteger one = new BigInteger("1");
		BigInteger zero = new BigInteger("0");
		
		BigInteger fibonacci_N_Minus_One = one;
		BigInteger fibonacci_N = one;
		
		BigInteger mod1;
		BigInteger mod2;
		
		
		for (BigInteger i = new BigInteger("2"); i != zero; i = i.add(one)) {
			mod1 = fibonacci_N_Minus_One.mod(modulus);
			mod2 = fibonacci_N.mod(modulus);
			
			if (mod1.equals(one) && mod2.equals(zero))
			{
				return i;
			} else {
				BigInteger temp = fibonacci_N_Minus_One.add(fibonacci_N);
				
				fibonacci_N_Minus_One = fibonacci_N;
				fibonacci_N = temp;
				
			}
		}
		
		return new BigInteger("-1");
	}
	
	

	/* ************
	 * Main Method
	 ************ */
	public static void main(String[] args) {
		
		//BigInteger mod = new BigInteger("987654");
		
		int[] userInput = getInputFromUser();
		
		for (int i = 1; i <= userInput.length; i++) {
			BigInteger result = getPisano(intToBigInt(userInput[i-1]));

			if (result.equals("-1")) {
				System.out.println("Oops, there was a problem. Couldn't find result.");
			} else {
				System.out.println(i + " " + result);
			}
		}
		
		//BigInteger result = getPisano(mod);
		 

	}

}
