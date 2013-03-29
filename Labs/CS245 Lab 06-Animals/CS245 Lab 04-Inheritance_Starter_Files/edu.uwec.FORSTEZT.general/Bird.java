package edu.uwec.EMAILNAME.general;

//This is also an abstract class.

// PUT YOUR CLASS DEFINITION FOR BIRD HERE
public abstract class Bird {

	// We can fully implement the reproduce() method at this level
	// because all birds reproduce similarly enough
	public void reproduce() {
		System.out.println("*" + this.getAnimalName() + " lays some shelled eggs in a nest...*");
	}
	// We can put part of a description for an animal at this level.
	// We will use it in a subclass.
	public String description() {
		return "Bird: A feathered, warm-blooded animal with wings and a beak. ";
	}
	
}
