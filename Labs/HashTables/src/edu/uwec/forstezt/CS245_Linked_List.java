package edu.uwec.forstezt;

public class CS245_Linked_List {

	private LLNodeHoldingDataKeyString[] hashTable;
	private int[] collisions;

	CS245_Linked_List(int capacity) {
		hashTable = new LLNodeHoldingDataKeyString[capacity];
		collisions = new int[capacity];
		for (int i = 0; i < capacity; i++) {
			hashTable[i] = new LLNodeHoldingDataKeyString(new KeyValuePair(
					null, null));
		}

		for (int i = 0; i < capacity; i++) {
			hashTable[i].setNextnode(new LLNodeHoldingDataKeyString(
					new KeyValuePair(null, null)));
		}
	}

	private int capacity() {
		return hashTable.length;
	}

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
		int bucketIndex = hash(dataKeyString.toLowerCase());
		LLNodeHoldingDataKeyString currentNode = hashTable[bucketIndex];
		do {
			currentNode = currentNode.getNextnode();
			if (currentNode.getKeyValuePair() != null) {
				// check if current node key is alphabetically higher than
				// desired key
				if (this.comesFirstAlphabetically(dataKeyString, currentNode
						.getKeyValuePair().getKey())) {
					return false;
				}
				if (currentNode.getKeyValuePair().getKey() != null) {
					if (currentNode.getKeyValuePair().getKey()
							.equalsIgnoreCase(dataKeyString)) {
						return true;
					}
				}
			}
		} while (currentNode.getNextnode() != null);

		return false;
	}

	public Object getValue(String dataKeyString) {
		if (dataKeyString == null) {
			System.err.println("The dataKeyString must have a value.");
			return null;
		}

		int bucketIndex = hash(dataKeyString);
		LLNodeHoldingDataKeyString currentNode = hashTable[bucketIndex];
		do {
			currentNode = currentNode.getNextnode();
			if (currentNode.getKeyValuePair() != null) {
				if (currentNode.getKeyValuePair().getKey()
						.equalsIgnoreCase(dataKeyString)) {
					return currentNode.getKeyValuePair().getValue();
				}
			}
		} while (currentNode.getNextnode() != null);
		return null;
	}

	public void insertKeyValuePair(String dataKeyString, Object valueObj) {
		if (dataKeyString == null) {
			System.err.println("The dataKeyString must have a value.");
			return;
		}
		// if the hash table does not contain the key...
		if (!this.containsKey(dataKeyString)) {
			LLNodeHoldingDataKeyString nextNode = new LLNodeHoldingDataKeyString(
					new KeyValuePair(dataKeyString, valueObj));
			int bucketIndex = hash(dataKeyString);
			LLNodeHoldingDataKeyString currentNode = hashTable[bucketIndex];
			// iterate through the nodes until you reach the last node
			do {
				currentNode = currentNode.getNextnode();
				if (currentNode.getNextnode() == null) {
					break;
				}
			} while (!this.comesFirstAlphabetically(dataKeyString, currentNode
					.getKeyValuePair().getKey()));
			// append the current node to the end
			currentNode.setNextnode(nextNode);
			collisions[bucketIndex]++;
		} else { // the hash table does contain the key...
			int bucketIndex = hash(dataKeyString);
			LLNodeHoldingDataKeyString currentNode = hashTable[bucketIndex];
			do {
				currentNode = currentNode.getNextnode();
				if (currentNode.getKeyValuePair() != null) {
					if (currentNode.getKeyValuePair().getKey() != null) {
						if (currentNode.getKeyValuePair().getKey()
								.equalsIgnoreCase(dataKeyString)) {
							// Reset the value of the KeyValuePair already in
							// the table
							currentNode.getKeyValuePair().setValue(valueObj);
						}
					}
				}
			} while (currentNode.getNextnode() != null);
		}

	}

	public int pairCount() {
		int count = 0;
		for (int i = 0; i < collisions.length; i++) {
			count += collisions[i];
		}
		return count;
	}

	public int collissionsThisKey(String dataKeyString) {
		if (collisions[hash(dataKeyString)] > 0) {
			return collisions[hash(dataKeyString)];
		}
		return 0;
	}

	public int collisionsThisHashTable() {
		int count = 0;
		int dummyNodesToSubtract = 0;
		for (int i = 0; i < collisions.length; i++) {
			count += (collisions[i]);
			if (collisions[i] > 0) {
				dummyNodesToSubtract++;
			}
		}

		return (count - dummyNodesToSubtract);
	}

	public String toString() {
		String string = "[";
		LLNodeHoldingDataKeyString currentNode;
		for (int i = 0; i < hashTable.length; i++) {
			currentNode = hashTable[i];
			do {
				currentNode = currentNode.getNextnode();
				if (currentNode.getKeyValuePair() != null) {
					string += (currentNode.getKeyValuePair().toString() + ", ");
				}
			} while (currentNode.getNextnode() != null);
			string += "]\n";
		}
		return string;
	}

	public void clear() {
		hashTable = new LLNodeHoldingDataKeyString[hashTable.length];
	}

	public boolean isEmpty() {
		for (int i = 0; i < collisions.length; i++) {
			if (collisions[i] != 0) {
				return false;
			}
		}
		return true;
	}

	private boolean comesFirstAlphabetically(String key1, String key2) {
		char[] chars1 = key1.toCharArray();
		char[] chars2 = key1.toCharArray();

		for (int i = 0; i < chars1.length; i++) {
			if (chars1[i] < chars2[i]) {
				return true;
			}
		}

		return false;

	}
}
