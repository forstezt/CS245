package edu.uwec.forstezt;

import java.util.ArrayList;
import java.util.Scanner;

public class Hash_Table_Class {

	private KeyValuePair[] hashTable;

	Hash_Table_Class(int capacity) {
		hashTable = new KeyValuePair[capacity];

	}

	// private int hash(String dataKeyString) {
	// char[] stringCharacters = dataKeyString.toCharArray();
	// int hash = 0;
	// for (int i = 0; i < stringCharacters.length; i++) {
	// hash += stringCharacters[i];
	// }
	// return (hash % hashTable.length);
	// }

	// -----------------------------------------------------
	// Hash()
	//
	// Hash computes and returns an initial hash value for
	// a given key string. The value returned will lie in
	// the range 0 through (capacity - 1) inclusive.
	//
	// This hash value spreads keys better for very large
	// amounts of data to be inserted, rather than simply
	// adding the characters of the dataKeyString together
	// modulo the hash table capacity. The "magic number"
	// 7411 that appears in the formula is a prime number
	// that each character value is multiplied by in order
	// to spread the keys out over a larger range of values.
	//
	// Taking the remainder after dividing by hashTableCapacity
	// is done on each addition so that the sum doesn't grow so
	// large that it overflows the maximum size of an int.
	//
	// 2011-11-09 1453 Created. RICHAG
	// 2012-04-12 0230 Tested and working. RICHAG
	// -----------------------------------------------------
	private int hash(String dataKeyString) {
		// System.out.println("Hash(\"" + dataKeyString + "\") is executing.");

		Integer hashValue = 0;
		int charValue;
		int hashTableCapacity = this.capacity();
		dataKeyString = dataKeyString.toLowerCase();

		for (int charNr = 0; charNr < dataKeyString.length(); charNr++) {
			charValue = (int) dataKeyString.charAt(charNr);
			hashValue = (hashValue + ((charValue * 7411) * (charNr * charNr)))
					% hashTableCapacity;
		}

		// System.out.println("Hash(\"" + dataKeyString +
		// "\") returning:  Hash(\""
		// + dataKeyString + "\") = " + hashValue + ".");

		return hashValue;

	} // End of Hash()

	public boolean containsKey(String dataKeyString) {
		if (dataKeyString == null) {
			System.err.println("The dataKeyString must have a value.");
			return false;
		} else {
			int originalIndex = this.hash(dataKeyString);
			int index = originalIndex;
			while (true) {
				if ((hashTable[index % hashTable.length] == null)) {
					return false;
				}
				if (hashTable[index % hashTable.length].getKey()
						.equalsIgnoreCase(dataKeyString)) {
					return true;
				}

				index++;
				if ((index % hashTable.length) == originalIndex) {
					return false;
				}
			}
		}
	}

	public Object getValue(String dataKeyString) {
		if (dataKeyString == null) {
			System.err.println("The dataKeyString must have a value.");
			return null;
		}

		boolean exists = this.containsKey(dataKeyString);
		if (!exists) {
			return null;
		} else {
			return hashTable[this.hash(dataKeyString)].getValue();
		}
	}

	public void insertKeyValuePair(String dataKeyString, Object valueObj) {
		if (dataKeyString == null) {
			System.err.println("The dataKeyString must have a value.");
			return;
		}

		int originalInsertionPoint = this.hash(dataKeyString);
		int insertionPoint = originalInsertionPoint;
		while (true) {
			if (hashTable[insertionPoint % hashTable.length] == null) {
				hashTable[insertionPoint % hashTable.length] = new KeyValuePair(
						dataKeyString, valueObj);
				break;
			}
			insertionPoint++;
			if ((insertionPoint % hashTable.length) == originalInsertionPoint) {
				System.out.println("There is no room left in the table.");
				break;
			}
		}

	}

	public int pairCount() {
		int count = 0;
		for (int i = 0; i < hashTable.length; i++) {
			if (!(hashTable[i].getValue() == null)) {
				count++;
			}
		}

		return count;
	}

	public int capacity() {
		return hashTable.length;
	}

	public int collissionsThisKey(String dataKeyString) {
		if (dataKeyString == null) {
			System.err.println("The dataKeyString must have a value.");
			return 0;
		}
		int dataKeyStringHash = this.hash(dataKeyString);
		int collisionCount = 0;
		for (int i = 0; i < hashTable.length; i++) {
			if (!(hashTable[i] == null)) {
				if (this.hash(hashTable[i].getKey()) == dataKeyStringHash) {
					collisionCount++;
				}
			}
		}

		return collisionCount;
	}

	public int collisionsThisHashTable() {
		int collisions = 0;
		for (int i = 0; i < hashTable.length; i++) {
			if (!(hashTable[i] == null)) {
				collisions += this.collissionsThisKey(hashTable[i].getKey());
			}
		}

		return collisions;
	}

	public String toString() {
		String braceString = "{";
		for (int i = 0; i < hashTable.length - 1; i++) {
			if (hashTable[i] != null) {
				braceString += (hashTable[i].getKey());
				if (hashTable[i].getValue() != null) {
					braceString += ("/" + hashTable[i].getValue().toString());
				} else {
					braceString += ("/null");
				}
				braceString += ", ";
			} else {
				braceString += "null, ";
			}
		}
		if (hashTable[hashTable.length - 1] != null) {
			braceString += (hashTable[hashTable.length - 1].getKey() + "}");
			if (hashTable[hashTable.length - 1].getValue() != null) {
				braceString += ("/"
						+ hashTable[hashTable.length - 1].getValue().toString() + "}");
			}
		} else {
			braceString += "null}";
		}

		String bracketString = "[";
		for (int i = 0; i < hashTable.length - 1; i++) {
			if (hashTable[i] != null) {
				bracketString += (hashTable[i].getKey());
				if (hashTable[i].getValue() != null) {
					bracketString += ("/" + hashTable[i].getValue().toString());
				}
				bracketString += ", ";
			} else {
				bracketString += "null, ";
			}
		}
		if (hashTable[hashTable.length - 1] != null) {
			bracketString += (hashTable[hashTable.length - 1].getKey() + "]");
			if (hashTable[hashTable.length - 1].getValue() != null) {
				bracketString += ("/"
						+ hashTable[hashTable.length - 1].getValue().toString() + "]");
			}
		} else {
			bracketString += "null]";
		}
		return braceString + "\n" + bracketString;
	}

	public void clear() {
		hashTable = new KeyValuePair[hashTable.length];
	}

	public boolean isEmpty() {
		for (int i = 0; i < hashTable.length; i++) {
			if (!(hashTable[i] == null)) {
				return false;
			}
		}

		return true;
	}

	public void suggestions(String dataKeyString) {
		Scanner in = new Scanner(System.in);

		boolean areThereAnyPossibleWords1 = this.differentLetter(dataKeyString);
		boolean areThereAnyPossibleWords2 = this.missingLetter(dataKeyString);
		boolean areThereAnyPossibleWords3 = this.addedLetter(dataKeyString);
		boolean areThereAnyPossibleWords4 = this.swappedLetters(dataKeyString);

		if (!(areThereAnyPossibleWords1 || areThereAnyPossibleWords2
				|| areThereAnyPossibleWords3 || areThereAnyPossibleWords4)) {
			System.out.println("No suggestions available.");
		} else {
			System.out
					.println("Add word to dictionary? (answer in the form \"yes\" or \"no\").");
			String changedWord = in.nextLine();
			if (changedWord.equalsIgnoreCase("yes")) {
				this.insertKeyValuePair(dataKeyString, null);
			}
		}
	}

	private boolean differentLetter(String dataKeyString) {
		System.out.println("Did you mean...");
		char[] letters = dataKeyString.toCharArray();
		char originalLetter;
		boolean areThereAnyPossibleWords = false;
		String possibleWord = "";
		for (int i = 0; i < letters.length; i++) {
			originalLetter = letters[i];
			for (int j = 97; j < 123; j++) {
				letters[i] = (char) j;
				// System.out.println("letter to be switched in:  " +
				// letters[i]);
				for (char letter : letters) {
					possibleWord += letter;
				}
				// System.out.println("possibleWord:  " + possibleWord);
				if (this.containsKey(possibleWord)) {
					System.out.println("\t" + possibleWord + "?");
					areThereAnyPossibleWords = true;
				}
				possibleWord = "";
			}
			letters[i] = originalLetter;
		}
		return areThereAnyPossibleWords;
	}

	private boolean missingLetter(String dataKeyString) {
		ArrayList<Character> letters = new ArrayList<Character>();
		String possibleWord = "";
		boolean areThereAnyPossibleWords = false;
		for (int i = 0; i < letters.size(); i++) {
			for (int j = 97; j < 123; j++) {
				letters.add(i, (Character) ((char) j));
				// System.out.println("letter to be inserted:  " + letters[i]);
				for (Character letter : letters) {
					possibleWord += letter;
				}
				// System.out.println("possibleWord:  " + possibleWord);
				if (this.containsKey(possibleWord)) {
					System.out.println("\t" + possibleWord + "?");
					areThereAnyPossibleWords = true;
				}
				possibleWord = "";
			}
			letters.remove(i);
		}
		return areThereAnyPossibleWords;

	}

	private boolean addedLetter(String dataKeyString) {
		ArrayList<Character> letters = new ArrayList<Character>();
		String possibleWord = "";
		Character addedLetter;
		boolean areThereAnyPossibleWords = false;
		for (int i = 0; i < letters.size(); i++) {
			addedLetter = letters.get(i);
			letters.remove(i);
			// System.out.println("letter to be inserted:  " + letters[i]);
			for (Character letter : letters) {
				possibleWord += letter;
			}
			// System.out.println("possibleWord:  " + possibleWord);
			if (this.containsKey(possibleWord)) {
				System.out.println("\t" + possibleWord + "?");
				areThereAnyPossibleWords = true;
			}
			possibleWord = "";

			letters.add(i, addedLetter);
		}
		return areThereAnyPossibleWords;
	}

	private boolean swappedLetters(String dataKeyString) {
		ArrayList<Character> letters = new ArrayList<Character>();
		String possibleWord = "";
		Character intermediateStep;
		boolean areThereAnyPossibleWords = false;
		for (int i = 0; i < letters.size() - 1; i++) {
			intermediateStep = letters.get(i);
			letters.set(i, letters.get(i + 1));
			letters.set(i + 1, intermediateStep);
			// System.out.println("letter to be inserted:  " + letters[i]);
			for (Character letter : letters) {
				possibleWord += letter;
			}
			// System.out.println("possibleWord:  " + possibleWord);
			if (this.containsKey(possibleWord)) {
				System.out.println("\t" + possibleWord + "?");
				areThereAnyPossibleWords = true;
			}
			possibleWord = "";

			intermediateStep = letters.get(i);
			letters.set(i, letters.get(i + 1));
			letters.set(i + 1, intermediateStep);
		}
		return areThereAnyPossibleWords;
	}

}
