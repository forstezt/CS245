package edu.uwec.EMAILNAME.general;

//-----------------------------------------------------
// Amphibian:  A subclass of Animal.
//
// Define Amphibian here, along with a reproduce method
// that displays the animal's name and describes and
// how it reproduces (see simulation output).
//
// Also define a "description" method that describes an
// amphibian in general terms (see simulation output).
//-----------------------------------------------------

// PUT YOUR CLASS HEADING FOR AMPHIBIAN HERE.
public class Amphibian extends Animal {

	// We can fully implement the reproduce() method at this
	// level, because all birds reproduce similarly enough.
	public void reproduce() {
		System.out.println("*" + this.getAnimalName() + 
				" lays or fertilizes some shell-less eggs in water...*");
	}
	// We can get part of a description for an animal here.
	// We will use it in a child class.
	public String description() {
		return "Amphibian: a hairless, cold-blooded animal that undergoes metamorphosis from a juvenile aquatic form. ";
	}
}
