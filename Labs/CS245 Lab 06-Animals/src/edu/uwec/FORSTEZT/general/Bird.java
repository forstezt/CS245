package edu.uwec.FORSTEZT.general;

//This is also an abstract class.



//-------------------------------------------------------------
//2012-03-09 1120 Created. FORSTEZT
//2012-03-09 0130 Tested and working. FORSTEZT
//-------------------------------------------------------------
public abstract class Bird extends Animal{

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
