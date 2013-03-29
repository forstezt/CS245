package edu.uwec.forstezt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//-----------------------------------------
// This class implements a spelling checker
// that uses a hash table to hold the list
// of dictionary words for fast lookups.
//-----------------------------------------
public class Spelling_Checker_Class {
	
	private CS245_Linked_List dictionaryHashTable;
//	private Hash_Table_Class dictionaryHashTable;

	//------------
	// CONSTRUCTOR
	//------------
	public Spelling_Checker_Class(int dictionaryCapacity, String dictionaryFilename) {
		System.out.println("\nThe Spelling_Checker_Class constructor is executing with dictionaryCapacity = "
				+ dictionaryCapacity + ", dictionaryFilename = \"" + dictionaryFilename + "\".");
		
		dictionaryHashTable = new CS245_Linked_List(dictionaryCapacity);
//		dictionaryHashTable = new Hash_Table_Class(dictionaryCapacity);
		Load_Dictionary_Into_Hash_Table(dictionaryFilename);
		
		System.out.println("The Spelling_Checker_Class constructor is finished.\n");
}
	
	//--------------------------------------------------------
	// checkSpelling reads the specified text file, breaks it
	// up into an array of words, then checks whether each
	// word is in the hash table.  Of course, for this to work,
	// the hash table must already be preloaded with a list of
	// correctly spelled words as the keys of the key/value
	// pairs.
	//
	// Each word NOT in the spelling dictionary is displayed
	// in the console as misspelled.  At the end, the total
	// number of words checked and the number of misspelled
	// words is also displayed in the console.
	//
	// 2012-04-13 1230 Created.  RICHAG
	// 2012-04-13 0351 Tested and working.  RICHAG
	//---------------------------------------------------------
	public void checkSpelling(String checkSpellingFilename) {
		System.out.println("\ncheckSpelling(\""
				+ checkSpellingFilename
				+ "\") is executing.");

		String checkSpellingString = readFileIntoString(checkSpellingFilename);
		String[] checkSpellingArray = stringToWordArray(checkSpellingString);

		int totalWords = checkSpellingArray.length;
		
		System.out.println("\ncheckSpelling:  About to check the spelling of "
				+ totalWords
				+ " words read from file \""
				+ checkSpellingFilename
				+ "\"...");

		String thisWordToCheck;
		int totalMisspellingsFound = 0;

		for (int wordIndex = 0; wordIndex < totalWords; wordIndex++) {

			// -----------------------------------
			// Create a key/value pair to insert.
			// -----------------------------------

			thisWordToCheck = checkSpellingArray[wordIndex];

//			System.out.println("\ncheckSpelling:  Checking thisWord #" + wordIndex
//					+ ": \"" + thisWordToCheck + "\".");

			if (!dictionaryHashTable.containsKey(thisWordToCheck)) {
				System.out.println("Misspelled word #"
						+ totalMisspellingsFound
						+ ": \"" + thisWordToCheck + "\"");
				//dictionaryHashTable.suggestions(thisWordToCheck);

				totalMisspellingsFound++;
			}

		} // end for
		
		//------
		// Done.
		//------
		System.out.println("\ncheckSpelling(\""
				+ checkSpellingFilename
				+ "\") is finished.  "
				+ totalWords + " words checked; "
				+ totalMisspellingsFound + " misspellings found.");
	
	} // End of checkSpelling()
	
	
	
	// ----------------------------------------------------------
	// Load_Dictionary_Into_Hash_Table loads a list of dictionary
	// words into the spellingDictionary hash table. Each word is
	// the key; the value of a key is the line number it appeared
	// on in the dictionary data file.
	//
	// 2012-04-13 1253 Created.  RICHAG
	// 2012-04-13 0351 Tested and working.  RICHAG
	// ----------------------------------------------------------
	private void Load_Dictionary_Into_Hash_Table(String dictionaryFilename) {

		System.out.println("\nLoad_Dictionary_Into_Hash_Table is executing.");

		try {
			BufferedReader inputFile = new BufferedReader(new FileReader(
					dictionaryFilename));

			String dictionaryWord;
			int lineNr = 0;
			int totalInsertionCollisions;
			
			while (true) {
				
				//-------------------------------------------
				// Read one line of the dictionary wordlist,
				// which contains a single dictionary word
				// or null when we read the end-of-file mark.
				//-------------------------------------------
				dictionaryWord = inputFile.readLine();

				//------------------------------------------
				// If the line is null, we've hit the end of
				// the wordlist file, so exit this loop.
				//------------------------------------------
				if (dictionaryWord == null) {
					System.out.println("\nLoad_Dictionary_Into_Hash_Table:  Done reading; exiting read loop.");
					break;
				}

				// ------------------------------------------
				// Load a key/value pair into the hash table.
				// The key is one word of the wordlist, and
				// its value is null.
				// ------------------------------------------
		
				//-------------------------------------
				// Display how many key/value pairs
				// have been inserted every 1000 lines.
				//-------------------------------------
//				lineNr++;
//				if ((lineNr % 1000) == 0) {
//					totalInsertionCollisions = dictionaryHashTable.collisionsThisHashTable();
//					System.out.println("Reading line " + lineNr
//							+ ":  total insertion collisions = "
//							+ totalInsertionCollisions);
//				}

				dictionaryHashTable.insertKeyValuePair(dictionaryWord, lineNr);
//				System.out.println(dictionaryWord + " has been added.");
			}
		} catch (IOException e) {
//			System.out.println("Error reading dictionary file \"" + dictionaryFilename + "\":  " + e);
			e.printStackTrace();
		} // end try

//		System.out.println("\nLoad_Dictionary_Into_Hash_Table is finished.");
		
		//Debug.println("\nLoad_Dictionary_Into_Hash_Table is finished.  The hash table is:"
		//		+ dictionaryHashTable);

	} // End Load_Dictionary_Into_Hash_Table

	
	
	// -----------------------------------------------------------------------
	// stringToWordArray breaks a given string into an array of single words.
	//
	// The string is split into words that lie on either side of a group
	// of one or more non-alphabetic characters. So the resulting "words"
	// will only be uppercase or lowercase letters with no internal
	// punctuation. If a splitter pattern occurs at the end, it doesn't
	// cause an empty word to be created at the end.
	//
	// Example: If the str is the string "Well...you've changed."
	// The splitters are "...", the apostrophe in "you've", and the final
	// period. So the returned array of words would be:
	//
	// [0] Well
	// [1] you
	// [2] ve
	// [3] changed (the final period doesn't cause an empty word at [4]).
	//
	// regex is a "regular expression" that defines the "delimiter" strings
	// that lie between what we will consider "words".
	// ^a-zA-Z is a pattern that means "NOT(a lowercase or uppercase letter)
	// The trailing + means "match one or more of the preceding pattern".
	// A leading ^ in front of the whole pattern means "match the following
	// pattern only at the start of a string."
	//
	// Test code:
	//
	// for (String s : getWords("Well...you've changed.")) {
	// System.out.println("String = #" + s + "#\n");
	// }
	//
	// for (String s : getWords("The Odyssey by Homer")) {
	// System.out.println("String = #" + s + "#\n");
	// }
	//
	// 2011-11-12 ???? Created. RICHAG
	// 2011-11-12 0351 Tested and working. RICHAG
	// -----------------------------------------------------------------------
	private static String[] stringToWordArray(String str) {

		// -------------------------------------------------------
		// If the string starts with one or more nonletters,
		// delete those leading nonletters so that splitting
		// the string on groups of nonletters doesn't create an
		// empty word at the very start of the string.
		//
		// The first ^ is a pattern that matches "the start of
		// the string". The rest of the pattern matches one or
		// more nonletters. So this first regular expression
		// will match one or more nonletters at the start of the
		// string, then replaceFirst will replace that matched
		// group of nonletters with the empty string, which
		// deletes it.
		// -------------------------------------------------------
		String leading_delimiter_removal_regex = "^[^a-zA-Z]+";
		str = str.replaceFirst(leading_delimiter_removal_regex, "");

		// --------------------------------------------------
		// Now the string doesn't start with any nonletters.
		// Use the remaining groups of nonletters to split
		// the string into an array of words consisting of
		// letters only. That means contractions like
		// "didn't" will be split into the two words "didn"
		// and "t", but we're going to allow that.
		// --------------------------------------------------
		String word_splitter_regex = "[^a-zA-Z]+";
		return str.split(word_splitter_regex);
		
	} // End of stringToWordArray

	
	// ------------------------------------------------------------
	// readFileIntoString reads any text file into a single string.
	//
	// 2012-04-13 0114 Created.  RICHAG
	// 2012-04-13 0351 Tested and working.  RICHAG
	// ------------------------------------------------------------
	private static String readFileIntoString(String fileName) {
		
		String result = "";
		
		try { // Handle file input errors, if any.
			
			BufferedReader inputFile = new BufferedReader(new FileReader(fileName));
			String line;
			
			//--------------------------------------------
			// Read the file until EOF (a null line),
			// catenating each line ono the result string.
			//--------------------------------------------
			while (true) {
				
				line = inputFile.readLine();
				
				if (line == null) break;
				
				//-------------------------------------
				// Catenate the line and an end-of-line
				// mark onto the result string.
				//-------------------------------------
				result = result + (line + "\n");
			}
			
		} catch (IOException e) {
			System.out.println("Error reading dictionary file \"" + fileName + "\":  " + e);
			e.printStackTrace();
		} // try
		
		return result;
		
	} // End of readFileIntoString()
	
	
	
	//-----------------------------------------------------------------------
	// Spelling_Checker_Class.test() tests the methods of this class.
	//
	// In the example call below,
	//
	//   10000 is the capacity of the hash table to be constructed, which
	//   presumably is greater than the number of words in the file named
	//   "dictionary.txt".
	//
	//   "dictionary.txt" holds the correctly spelled list of words.
	//
	//   "checkme.txt" holds the text that is to be spelling-checked.
	// 
	//   Spelling_Checker_Class.test(10000, "dictionary.txt", "checkme.txt");
	//
	// 2012-04-13 0119 Created.  RICHAG
	// 2012-04-13 0351 Tested and working.  RICHAG
	//-----------------------------------------------------------------------
	public static void test(int hashTableCapacity,
			String dictionaryFilename,
			String checkSpellingFilename) {
		
//		System.out.println("Spelling_Checker_Class.test() is executing.\n");
		
		// --------------------------------------------------
		// Construct a spelling checker for wordlistfood.txt.
		// The file testwordlist.txt has 10 food words in it,
		// such as apple, banana, etc.
		// --------------------------------------------------	
//		System.out.println("Spelling_Checker_Class.test():  Constructing a new hash_table of size "
//						+ hashTableCapacity + " for the dictionary filename \""
//						+ dictionaryFilename + "\"...");
		
		Spelling_Checker_Class spellingChecker =
				new Spelling_Checker_Class(hashTableCapacity, dictionaryFilename);
		
//		System.out.println("Spelling_Checker_Class.test():"
//				+ " Done constructing a hash table for dictionary file "
//				+ dictionaryFilename + "\".");

		// ---------------------------------------------------
		// Use the newly constructed spelling checker to check
		// the spelling of words in the file "phoodze.txt".
		// ---------------------------------------------------
		
//		System.out.println("Spelling_Checker_Class.test():  Checking the spelling of words in file "
//				+ checkSpellingFilename + "...");
		
		spellingChecker.checkSpelling(checkSpellingFilename);
		
//		System.out.println("\nSpelling_Checker_Class.test():"
//				+ "  Done checking the spelling of all of the words in file \""
//				+ checkSpellingFilename + "\".");
		
		//------
		// Done.
		//------
//		System.out.println("Spelling_Checker_Class.test() is finished.\n");

	} // End of Spelling_Checker_Class.test()
	
	
} // End of Spelling_Checker_Class

