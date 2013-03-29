package edu.uwec.forstezt;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		AnimalTree tree = new AnimalTree();
		String answer;

		System.out.println("Welcome to the game of Animal!");
		System.out
				.println("In this game, you will think of an amimal, and I will try to guess it.");
		System.out
				.println("Please answer all questions with \"y\" or \"n\" for \"yes\" and \"no\" respectively.");
		System.out
				.println("you may also enter \"l\", and I will list all of the animals in my database.");

		while (true) {
			
			
			
			System.out.println("The wrapper has ended...");
			
			
			
			System.out.println("Are you thinking of an animal?");
			answer = in.next().toLowerCase();
			System.out.println("");
			
			if (answer.equals("n")) {
				break;
			} else if (answer.equals("y")) {
//				tree.playGameWrapper();
				tree.playGame();
			} else if (answer.equals("l")) {
				System.out.println("Here are all of the animals that I know of!");
				System.out.println(tree.toString());
				System.out.println("");
			} else {
				System.out.println("I'm sorry, I don't understand that kind of complex language...");
			}

		}
		
		System.out.println("Thank you for playing ANIMAL!  Goodbye!");
	}
}
