package edu.uwec.forstezt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	// //
	// ----------------------------------------------------------------------------
	// // Reads a text file and returns a String array of single words.
	// // @PARAM file is the name of the text file.
	// // NOTE: Do not hard-code the name of any data file into any
	// // of these methods!
	// //
	// ----------------------------------------------------------------------------
	// public static String[] getWordsFromFile(String file) {
	// return getWords(readFile(file));
	// }
	//
	// //
	// -----------------------------------------------------------------------
	// // getWords breaks a given string into an array of single words.
	// // @PARAM: str is the string to be split into words.
	// //
	// // The string is split into words that lie on either side of a group
	// // of one or more non-alphabetic characters. So the resulting "words"
	// // will only be uppercase or lowercase letters with no internal
	// // punctuation. If a splitter pattern occurs at the end, it doesn't
	// // cause an empty word to be created at the end.
	// //
	// // Example: If the str is the string "Well...you've changed."
	// // The splitters are "...", the apostrophe in "you've", and the final
	// // period. So the returned array of words would be:
	// //
	// // [0] Well
	// // [1] you
	// // [2] ve
	// // [3] changed (the final period doesn't cause an empty word at [4]).
	// //
	// // regex is a "regular expression" that defines the "delimiter" strings
	// // that lie between what we will consider "words".
	// // ^a-zA-Z is a pattern that means "NOT(a lowercase or uppercase letter)
	// // The trailing + means "match one or more of the preceding pattern".
	// // A leading ^ in front of the whole pattern means "match the following
	// // pattern only at the start of a string."
	// //
	// -----------------------------------------------------------------------
	// public static String[] getWords(String str) {
	//
	// // -------------------------------------------------------
	// // If the string starts with one or more nonletters,
	// // delete those leading nonletters so that splitting
	// // the string on groups of nonletters doesn't create an
	// // empty word at the very start of the string.
	// //
	// // The first ^ is a pattern that matches "the start of
	// // the string". The rest of the pattern matches one or
	// // more nonletters. So this first regular expression
	// // will match one or more nonletters at the start of the
	// // string, then replaceFirst will replace that matched
	// // group of nonletters with the empty string, which
	// // deletes it.
	// // -------------------------------------------------------
	// String leading_delimiter_removal_regex = "^[^a-zA-Z]+";
	// str = str.replaceFirst(leading_delimiter_removal_regex, "");
	//
	// // --------------------------------------------------
	// // Now the string doesn't start with any nonletters.
	// // Use the remaining groups of nonletters to split
	// // the string into an array of words consisting of
	// // letters only. That means contractions like
	// // "didn't" will be split into the two words "didn"
	// // and "t", but we're going to allow that.
	// // --------------------------------------------------
	// String word_splitter_regex = "[^a-zA-Z]+";
	// return str.split(word_splitter_regex);
	// }
	//
	// // --------------------------------------------------
	// // Reads a text file into a single string.
	// // @PARAM: filename is the name of the text file
	// // --------------------------------------------------
	// public static String readFile(String filename) {
	// String result = "";
	// try { // Need to handle possible file errors.
	// BufferedReader inputFile = new BufferedReader(new FileReader(
	// filename));
	// String line;
	// // Read file until EOF (a null line).
	// while (true) {
	// line = inputFile.readLine();
	// if (line == null)
	// break;
	// result += line + "\n";
	// }
	// } catch (IOException e) {
	// System.out.println("File error: " + e);
	// } // try
	// return result;
	// } // readFile

	// public static void main(String[] args) {
	//
	// Scanner in = new Scanner(System.in);
	// System.out
	// .println("Please enter the filename of a dictionary to use...");
	// String dictionaryFilename = in.nextLine();
	//
	// System.out
	// .println("Please enter the filname of a text to spellcheck...");
	// String textFilename = in.nextLine();
	//
	// String[] words;
	// words = getWordsFromFile(textFilename);
	// Hash_Table_Class hashTable = new Hash_Table_Class(71);
	// // System.out.println(words.length);
	//
	// try {
	// BufferedReader inputFile = new BufferedReader(new FileReader(
	// dictionaryFilename));
	// String line;
	// while (true) {
	// line = inputFile.readLine();
	//
	// if (line == null) {
	// break; // Done reading; exit this loop.
	// }
	// // YOUR CODE GOES HERE: Insert line into your hash table here.
	// if (!hashTable.containsKey(line)) {
	// hashTable.insertKeyValuePair(line, null);
	// // System.out.println(line + " has been added...");
	// }
	// }
	// } catch (IOException e) {
	// System.out.println("File error: " + e);
	// } // end try
	//
	// System.out.println("");
	// System.out.println("");
	// System.out
	// .println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	// System.out.println("Printing all words in " + textFilename
	// + " that aren't in the dictionary...");
	// System.out.println("");
	// for (String word : words) {
	// boolean isInDictionary = hashTable.containsKey(word);
	// if (!isInDictionary) {
	// System.out.println("");
	// System.out.println("> " + word + " is misspelled");
	// System.out.println("");
	// hashTable.suggestions(word);
	// System.out.println("");
	// }
	// }
	// System.out
	// .println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	//
	// System.out.println("");
	// System.out
	// .println("Document successfully checked, all errors reported.");
	//
	// // System.out.println("");
	// // System.out.println("Printing out the hashTable...");
	// // System.out.println(hashTable.toString());
	// // System.out.println("hashTable printed successfully.");
	//
	// }
	// }

	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------

	// -------------------------------------------------
	// This is the test driver code for this hash table
	// implementation, which uses Linear Probing to
	// resolve collisions.
	//
	// Uncomment one test at a time to test your hash
	// table classes. Once you are certain your hash
	// table is working, run the spelling checker tests
	// one at a time to verify that your hash table
	// works when used within a real application.
	//
	// 2011-11-12 1159 Created. RICHAG
	// 2011-11-13 0451 Tested. RICHAG
	// -------------------------------------------------

	public static void main(String[] args) {
		System.out.println("MAIN:  The main program is executing.");

		// Hash_Table_Entry_Class.test(); // This test should test methods in
		// your Hash_Table_Entry class.
		// Hash_Table_Class.test(); // This test should test methods in your
		// Hash_Table_Class.

		// Spelling_Checker_Class.test(15, "wordlistfood.txt", "phoodze.txt");
		// Easy to check for correctness.

		// Spelling_Checker_Class.test(8, "wordlistfood.txt", "phoodze.txt");
		// Table too small; what happens?

		// Spelling_Checker_Class.test(150, "wordlist100.txt",
		// "jabberwocky.txt");
		// Shows clumping.

		// Spelling_Checker_Class.test(315000, "wordlist.txt",
		// "jabberwocky.txt");
		// Needs a good hash function!

		// Spelling_Checker_Class.test(71, "thing.txt",
		// "shortjabber.txt");

		// Spelling_Checker_Class.test(315000, "wordlist.txt", "odyssey.txt");
		// // An entire book to check!
///////////////////////////////////////////////////////////////////////////////////////////////////////////
//		 Spelling_Checker_Class.test(50, "wordlistuppercase.txt",
//		 "odysseytitle.txt"); // Check case-handling.
//		 Spelling_Checker_Class.test(50, "wordlistlowercase.txt",
//		 "odysseytitle.txt"); // Check case-handling.

//		 Spelling_Checker_Class.test(15, "wordlistfood.txt", "phoodze.txt"); // Easy
																			// to
																			// check
																			// for
																			// correctness.
//		 Spelling_Checker_Class.test(8, "wordlistfood.txt", "phoodze.txt"); //
		// Table too small; what happens?
//		 Spelling_Checker_Class.test(150, "wordlist100.txt",
//		 "jabberwocky.txt"); // Shows clumping.
//		 Spelling_Checker_Class.test(315000, "wordlist.txt",
//		 "jabberwocky.txt"); // Needs a good hash function!
//		 Spelling_Checker_Class.test(315000, "wordlist.txt", "odyssey.txt");
		// // An entire book to check!

		// System.out.println("\nMAIN:  The main program is finished.");
		System.exit(0);

	} // End of main() method

	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------

	// public static void main(String[] args) {
	//
	//
	// System.out.println("MAIN:  This test program has begun.\n");
	// Debug.turnOnMessages();
	//
	// //-------------------------------------------------
	// // Tests to verify that the debugging methods work.
	// //-------------------------------------------------
	// // Debug.println("This is a test debugging message.");
	// // Debug.turnOffMessages();
	// // Debug.println("This message should NOT be seen.");
	// // Debug.turnOnMessages();
	// // Debug.println("This message SHOULD be seen.");
	//
	// CS245_Linked_List int_chain = new CS245_Linked_List(50);
	//
	// //---------------------------------------------
	// // Tests to verify that append and remove work.
	// //---------------------------------------------
	// int_chain.append(10);
	// int_chain.remove(10);
	// int_chain.remove(10);
	//
	// int_chain.append(20);
	// int_chain.append(30);
	//
	// int_chain.remove(20);
	// int_chain.remove(10);
	// int_chain.remove(32); // Not in the list.
	// int_chain.remove(30);
	// int_chain.remove(30); // No longer in the list.
	//
	// System.out.println("\nMAIN:  The final linked list is:  " + int_chain);
	// System.out.println("\nThis test program has ended.\n");
	// }
	//
} // End of Main class
