package edu.uwec.EMAILNAME.general;

//this is also an abstract class - one does not need to implement 
//inherited abstract methods yet... but it can if you want it to. 

// PUT YOUR CLASS DEFINITION FOR MAMMAL HERE.
public abstract class Mammal{

	// We can fully implement the reproduce() method at this level
	// because all mammals reproduce similarly enough.
	public void reproduce() {
		System.out.println("*" + this.getAnimalName() 
							+ " mates to later produce live young...*");
	}
	// We can put part of a description for an animal at this level.
	// We will use it in a child class.
	public String description() {
		return "Mammal: a hairy, warm-blooded animal with specialized teeth. ";
	}

}
