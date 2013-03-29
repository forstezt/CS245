package edu.uwec.forstezt;

public class LLNodeHoldingDataKeyString {
	private KeyValuePair word; // A value to be stored, so we have some data in the list.
	private LLNodeHoldingDataKeyString nextnode; // Forward link to the next node in the list.

	// ------------------------------------------------
	// LLNodeHoldingDataKeyString Contstructor creates a new
	// LLNodeHoldingDataKeyString, stores the specified
	// integer in it, and sets its nextnode to null
	// (not strictly necessary, but done for emphasis).
	// -------------------------------------------------
	public LLNodeHoldingDataKeyString(KeyValuePair word) {
		this.word = word;
		this.nextnode = null;
	}

	// ----------------------------
	// public GETTER for nextnode.
	// ----------------------------
	public LLNodeHoldingDataKeyString getNextnode() {
		return nextnode;
	}

	// ----------------------------
	// public SETTER for nextnode.
	// ----------------------------
	public void setNextnode(LLNodeHoldingDataKeyString nextnode) {
		this.nextnode = nextnode;
	}

	// ----------------------------
	// public GETTER for intvalue.
	// ----------------------------
	public KeyValuePair getKeyValuePair() {
		return word;
	}

	// ---------------------------
	// public SETTER for intvalue.
	// ---------------------------
	public void setKeyValuePair(KeyValuePair word) {
		this.word = word;
	}

	
	//------------------------------------------------------
	// listToString() returns a string representation of the
	// entire linked list of LLNodeHoldingDataKeyString nodes starting
	// at list_head, ending with the node whose nextnode ref
	// is null.
	//------------------------------------------------------
	public String listToString() {
			
			String result = ""; // The string to be returned.
				
				//-------------------------------
				// Get a ref to the current node.
				//-------------------------------
				LLNodeHoldingDataKeyString thisnode = this;  // "this" is the current node.
				
				//----------------------------------
				// Walk down the linked list, adding
				// each node's toString string to
				// the result until the last
				// nextnode link is null.
				//----------------------------------
				do {
					// ------------------------------------------------
					// Append thisnode's string rep to the result, then
					// advance it to refer to the next node, if any.
					// ------------------------------------------------
					result = result + thisnode.toString();
					thisnode = thisnode.getNextnode();
				}	
				while (thisnode != null);
			
			// -------------------------
			// Done.  Return the string.
			// -------------------------
			return result;
			
		} // listToString
		
	
	@Override
	public String toString() {
		return "[" + word.toString() + ",nextnode="
				+ (nextnode == null ? "null]" : "non-null]");

	}
}
