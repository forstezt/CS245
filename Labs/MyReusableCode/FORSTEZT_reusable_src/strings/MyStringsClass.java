package strings;

import java.util.Random;
import java.util.Scanner;

public class MyStringsClass {

	public static String scramble(String string) {

		Random generator = new Random();
		int j;
		String scrambledString = "";
		String usedIndexes = "";

		for (int i = 0; i < string.length(); i++) {
			j = generator.nextInt(string.length());
			while (usedIndexes.contains("" + j)) {
				j = generator.nextInt(string.length());
			}
			scrambledString += string.charAt(j);
			usedIndexes += j;

		}

		return scrambledString;
	}

	// ------------------------------------------------------------
	// askUserForString displays a prompt and waits for the user
	// to enter a string. The string must be on one line (that
	// is, it can't contain line breaks.)
	//
	// That user-entered string is returned. If the user enters
	// an empty string (no text), that's okay; that empty string
	// is returned just like any other string. So it's not
	// treated as a special case.
	//
	// Parameters:
	// prompt: A String to be displayed as a prompt. It will
	// normally ask the user to enter some data string.
	//
	// Returned:
	// entered_string: The string entered by the user.
	//
	// Example:
	// String name =
	// MyStringClass.askUserForString("What's your name?");
	//
	// 2012-02-17 1112 Created. FORSTEZT
	// 2012-02-17 1120 Tested and working. FORSTEZT
	// ------------------------------------------------------------

	public static String askUserForString(String prompt) {
		Scanner in = new Scanner(System.in);
		System.out.println(prompt);
		String entered_string = in.nextLine();
		return entered_string;
	}

	public static void Test_askUserForString() {
		Scanner in = new Scanner(System.in);
		System.out.println("Test_askUserForString:  The test has started.");
		String prompt = "Enter a string (\"quit\" to quit):  ";

		String entered_string;
		while (true) {
			entered_string = MyStringsClass.askUserForString(prompt);
			if (entered_string.equals("quit")) {
				break;
			}
			System.out.println("The entered string has a length of "
					+ entered_string.length() + ": " + "\"" + entered_string
					+ "\"");
		}

		System.out.println("Test_askUserForString:  The test has finished.");
	}
}
