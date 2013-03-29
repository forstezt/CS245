package edu.uwec.forstezt;

public class TNode {
	
	private String question;
	private TNode leftPointer;
	private TNode rightPointer;
	
	public TNode(String question) {
		this.question = question;
	}
	
	public String getValue() {
		return this.question;
	}
	
	public void setValue(String question) {
		this.question = question;
	}
	
	public void setLeftPointer(TNode leftNode) {
		this.leftPointer = leftNode;
	}
	
	public TNode getLeftPointer() {
		return this.leftPointer;
	}
	
	public void setRightPointer(TNode rightNode) {
		this.rightPointer = rightNode;
	}
	
	public TNode getRightPointer() {
		return this.rightPointer;
	}
	
	public boolean hasLeftChild() {
		if (this.leftPointer == null) {
			return false;
		}
		return true;
	}
	
	public boolean hasRightChild() {
		if (this.rightPointer == null) {
			return false;
		}
		return true;
	}
}
