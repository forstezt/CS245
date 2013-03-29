package edu.uwec.forstezt;

import java.util.Scanner;

public class AnimalTree {

	TNode root;
	private String treeString = "";

	public AnimalTree() {
		root = new TNode("Does it live underwater?");
		root.setLeftPointer(new TNode("ant"));
		root.setRightPointer(new TNode("oyster"));
	}

	
	private void addAnimalToTree(String animal, String question, String answer, TNode currentNode) {
		
		if (answer.equals("y")) {
			currentNode.setRightPointer(new TNode(animal));
			currentNode.setLeftPointer(new TNode(currentNode.getValue()));
		} else if (answer.equals("n")) {
			currentNode.setLeftPointer(new TNode(animal));
			currentNode.setRightPointer(new TNode(currentNode.getValue()));
		}
		
		currentNode.setValue(question);
	}
	
	
	public String toString() {
		treeString = "";
		this.buildTreeString(root);
		return treeString;
	}

	private void buildTreeString(TNode node) {

		if (!(node.hasRightChild() || node.hasLeftChild())) {
			treeString += (node.getValue() + "\n");
		}
		if (node.hasLeftChild()) {
			buildTreeString(node.getLeftPointer());
		}
		if (node.hasRightChild()) {
			buildTreeString(node.getRightPointer());
		}
	}
	
	
	
	
	
	
//	public void playGameWrapper() {
//		
//		
//		TNode currentNode = root;
//		Scanner in = new Scanner(System.in);
//		String answer = "";
//		String question = "";
//		String animal = "";
//		
//		playGame(currentNode, in, answer, question, animal);
//		
//		
//
//		
//	}
//	
//	
//	public void playGame(TNode currentNode, Scanner in, String answer, String question, String animal) {
//
//		
//		if ((currentNode.hasLeftChild() || currentNode.hasRightChild())) {
//			
//			System.out.println(currentNode.getValue());
//			answer = in.next().toLowerCase();
//			
//			if (answer.equals("y")) {
//
//				playGame(currentNode.getRightPointer(), in, answer, question, animal);
//
//			} else if (answer.equals("n")) {
//
//				playGame(currentNode.getLeftPointer(), in, answer, question, animal);
//
//			}
//		}
//		
//		System.out.println("Is it a(n) " + currentNode.getValue());
//		answer = in.next().toLowerCase();
//		
//		if (answer.equals("y")) {
//			
//			System.out.println("I guessed it!  I win!");
//			System.out.println("Let's play again!");
//			
//		} else if (answer.equals("n")) {
//
//			System.out.println("I don't know your animal.  You win!");
//			System.out
//					.println("Please teach me your animal!  What animal were you thinking of?");
//			animal = in.next().toLowerCase();
//			in.nextLine();
//			System.out.println("Please enter a yes-or-no question that distinguishes a(n) " + animal + " from a(n) " + currentNode.getValue() + ":");
//			question = in.nextLine();
//			System.out.println("What's the correct answer if it's a(n) " + animal + "?");
//			answer = in.next().toLowerCase();
//			
//			this.addAnimalToTree(animal, question, answer, currentNode);
//			
//			System.out.println("Ok, let's play again!");
//			
//		}
//		
//		
//
//	}
	
	
	
	public void playGame() {
		TNode currentNode = root;
		Scanner in = new Scanner(System.in);
		String answer;
		String question;
		String animal;

		while ((currentNode.hasLeftChild() || currentNode.hasRightChild())) {

			System.out.println(currentNode.getValue());
			answer = in.next().toLowerCase();

			if (answer.equals("y")) {

				currentNode = currentNode.getRightPointer();

			} else if (answer.equals("n")) {

				currentNode = currentNode.getLeftPointer();

			}
		}

		System.out.println("Is it a(n) " + currentNode.getValue());
		answer = in.next().toLowerCase();

		if (answer.equals("y")) {

			System.out.println("I guessed it!  I win!");
			System.out.println("Let's play again!");

		} else if (answer.equals("n")) {

			System.out.println("I don't know your animal.  You win!");
			System.out
					.println("Please teach me your animal!  What animal were you thinking of?");
			animal = in.next().toLowerCase();
			in.nextLine();
			System.out.println("Please enter a yes-or-no question that distinguishes a(n) " + animal + " from a(n) " + currentNode.getValue() + ":");
			question = in.nextLine();
			System.out.println("What's the correct answer if it's a(n) " + animal + "?");
			answer = in.next().toLowerCase();
			
			this.addAnimalToTree(animal, question, answer, currentNode);
			
			System.out.println("Ok, let's play again!");
		}
	}
}
