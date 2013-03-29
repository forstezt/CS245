package edu.uwec.FORSTEZT.main;

import edu.uwec.FORSTEZT.general.Animal;
import edu.uwec.FORSTEZT.specific.*;

public class Main {

	/********************************************************
	 * This main program creates and VERY briefly simulates
	 * the lives of three animals.  Calls are made to methods
	 * at all levels of the Animal hierarchy.
	 * 
	 * Please do NOT change any part of the main program; it
	 * works as-is.
	 * 
	 * @param args
	 *******************************************************/
	public static void main(String[] args) {
		// The purpose of this lab is to demonstrate
		// the concept of Java inheritance using animals.
		//
		// Develop the animal class first, then the Amphibian,
		// Bird, and Mammal classes.
		
		simulateAnimals();
	}
	
	static void simulateAnimals() {
		
		System.out.println("\n--- Start of simulation ---\n");

		//------------------------------------
		// Create and simulate Tammy the Toad.
		//------------------------------------
		AmericanToad tammy = new AmericanToad(1, "Tammy");
		
		// Abstract method made concrete in the AmericanToad class
		System.out.println(tammy.description()); 
		
		// Calls a method found in Amphibian only.
		tammy.reproduce(); 
		
		// Calls a method found in Animal only.
		tammy.eat(); 
		
		// Unique child class method - other non-toad animals can't do this.
		tammy.move(); 
		tammy.eat();  // Hopping around makes Tammy hungry again.
		
		//----------------------------------------------
		// Create and simulate Billy the Bat (a Mammal).
		//----------------------------------------------
		BumblebeeBat billy = new BumblebeeBat(2, "Billy");
		System.out.println(billy.description());
		billy.reproduce();
		billy.eat();
		billy.move(); // Flight of the bumblebee bat.
		billy.eat();
		
		//-------------------------------------------
		// Create and simulate Eric the Emu (a bird).
		//-------------------------------------------
		Emu eric = new Emu(3, "Eric");
		System.out.println(eric.description());
		eric.reproduce();
		eric.eat();
		eric.move(); // An emu-lation.
		eric.eat();
		
		System.out.println("\n--- End of simulation ---\n");

		
		
		Animal.testEatAndGrow();
	} // End of simulateAnimals

} // End of main
