package edu.uwec.forstezt;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		for (int n = 1; n <= 100; n++) {

			ArrayList<Integer> factors = new ArrayList<Integer>();
			factors.addAll(factorsOf(n));

			if (factors.size() == 1) {
				System.out.println(n + " is prime");
			} else {
				System.out.println("             " + n + " = " + factors);
			}

		}
	} // End of main

	// ----------------------------------------------
	// Wrapper function that calls getFactorsOf
	// in order to initialize its "divisor"
	// parameter to 2. This function simply returns
	// the ArrayList that is returned by function
	// getFactorsOf.
	// ----------------------------------------------
	static ArrayList<Integer> factorsOf(int n) {

		return getFactorsOf(n, 2);

	} // factorsOf

	// -----------------------------------------------
	// This is the RECURSIVE function that you write.
	// Fill in its body and test it.
	// -----------------------------------------------

	static ArrayList<Integer> getFactorsOf(int n, int divisor) {
		// YOUR CODE GOES HERE.
		ArrayList<Integer> primeFactors = new ArrayList<Integer>();
		boolean isPrime = true;

		// if n is 1, it is already prime
		if (n == 1) {
			primeFactors.add(n);
			return primeFactors;
		}

		if (divisor == n) {
			primeFactors.add(n);
			return primeFactors;
		}

		if (n % divisor == 0) { // divisor is a factor of n

				primeFactors.add(divisor); // add divisor to the the ArrayList
				primeFactors.addAll(getFactorsOf(n / divisor, 2)); // continue
																	// to find
																	// prime
																	// factors
				return primeFactors;

		} else { // divisor is not a factor of n
			return getFactorsOf(n, divisor + 1);
		}

	} // End of getFactorsOf

} // End of class Main

