package edu.uwec.cs245.RandomNumbers;

import java.util.Random;

public class MyRandomClass {

	// -----------------------------------------------------
	// randomInt returns an integer in the range min_int
	// through max_int inclusive. min_int must be less
	// than max_int. For now, if they aren't, that error
	// is NOT checked and reported.
	//
	// TBD: Handle the case when min_int > max_int.
	//
	// Parameters:
	//   min_int: The minimum integer that will be returned.
	//   max_int: The maximum integer that will be returned.
	//
	// Returned:
	//   random_int_in_range:
	//   An integer in the range min_int through max_int
	//   inclusive.
	//
	// 2012-02-16 0026 Created. RICHAG
	// 2012-02-16 0100 Tested and working, but does not
	// check that min_int <= max_int. RICHAG
	// ----------------------------------------------------
	public static int randomInt(int min_int, int max_int) {

		int random_int_in_range; // The value to be returned.

		// ---------------------------------------
		// Create a Java random number generator.
		// ---------------------------------------
		Random randGen = new Random();

		int raw_random_int = randGen.nextInt((max_int - min_int) + 1);
		//System.out.println("In Random_Int:  raw_random_int = "
		//+ raw_random_int);

		random_int_in_range = raw_random_int + min_int;

		//System.out.println("In Random_Int:  random_int_in_range = "
		//+ random_int_in_range);

		return random_int_in_range;

	} // End of randomInt

	// ---------------------------------------------------------
	// Test_randomInt tests the Random_Int function by calling
	// it 100 times to generate 100 random numbers. It checks
	// that each random number generated is in the specified
	// range; if it's not, an error is reported. Otherwise it
	// reports that all numbers fell in the specified range.
	//
	// Parameters:
	// min_int: The minimum integer to be generated.
	// max_int: The maximum integer to be generated.
	//
	// Returned: Nothing; this is a void function.
	//
	// Example:
	//   Test_Random_Int(10, 20);
	//
	// 2012-02-16 0037 Created. RICHAG
	// 2012-02-16 0100 Tested and working. RICHAG
	// -------------------------------------------------------
	public static void Test_randomInt(int min_int, int max_int) {

		System.out.println("Test_randomInt has started.");
		System.out.println("Test_randomInt:  min_int = " +  min_int
				+ ", max_int = " + max_int + ".\n");

		final int TOTAL_TESTS = 1000;
		int failures = 0;
		int test_value;

		for (int test_nr = 1; test_nr <= TOTAL_TESTS; test_nr++) {

			test_value = randomInt(min_int, max_int);

			if (test_value < min_int) {
				System.out.println("TEST " + test_nr
						+ " FAILED:  The test value of " + test_value
						+ " is below the range of " + min_int + " thru "
						+ max_int + ".");
				failures = failures + 1;
			} else if (test_value > max_int) {
				System.out.println("TEST " + test_nr
						+ " FAILED:  The test value of " + test_value
						+ " is above the range of " + min_int + " thru "
						+ max_int + ".");
				failures = failures + 1;
			}
		}

		System.out.println();
		
		if (failures == 0) {
			System.out.println("TEST RUN SUCCEEDED:  " + TOTAL_TESTS
					+ " numbers were generated within the specified range of "
					+ min_int + " thru " + max_int + ".");
		} else {
			System.out.println("TEST RUN FAILED:  Out of " + TOTAL_TESTS
					+ " numbers generated, " + failures
					+ " were outside the specified range of " + min_int
					+ " thru " + max_int + ".");
		}

		System.out.println("Test_randomInt has finished.");

	} // End of Test_randomInt
	
} // End of class MyRandomClass

