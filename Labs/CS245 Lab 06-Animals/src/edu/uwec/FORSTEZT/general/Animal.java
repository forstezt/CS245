package edu.uwec.FORSTEZT.general;

import edu.uwec.FORSTEZT.specific.BumblebeeBat;

//--------------------------------------------------------
// Define the Animal class here.
// 
// Note how code is reused between classes - 
// all animals have a physical size, diet, loc, name, etc,
// so we need only put the most-general variables here, in
// one location.  Then all subclasses -inherit- these
// variables and methods, or if they're abstract, are
// forced to implement them.
//--------------------------------------------------------




//-------------------------------------------------------------
//2012-03-09 1120 Created. FORSTEZT
//2012-03-09 0130 Tested and working. FORSTEZT
//-------------------------------------------------------------
public abstract class Animal {

// NOTE:  Lots of errors below will disappear when
// you write the correct Animal class header above!

	//--------------------------------------------------
	// Instance variables.  These will be initialized in
	// the constructor of a subclass, not here.  Here
	// are a few to get you started.
	//--------------------------------------------------
	private int animalID = 0; // To identify unique animals
	private String animalName = "";
	private String genusSpecies = "";
	private String diet = "";
	private double maxHeight;
	private double minHeight;
	private double maxWeight;
	private double minWeight;
	private double heightCM;
	private double weightKG;
	private String location;
	
	//--------------------------------------
	// NOTE:  This Animal class is abstract,
	// so it has no constructors.
	//--------------------------------------
	
	//---------------------------
	// All animals gotta eat!
	// And eating makes 'em grow.
	//---------------------------
	public void eat() {
		System.out.println(this.animalName + " ate some " + diet + "... ");
		this.grow();
	}

	//----------------------------------
	// Here's how they grow (let's say).
	//----------------------------------
	private void grow() {
		boolean it_grew = false;
		
		// Grow 10 to 20 percent of its capacity.
		double heightIncrement = (this.maxHeight - this.minHeight)
								 / ((Math.random() * 5) + 5);
		double weightIncrement = (this.maxWeight - this.minWeight) 
								 / ((Math.random() * 5) + 5);
		// Increment size and weight.
		if ((this.heightCM < maxHeight)) {
			this.heightCM = this.heightCM + heightIncrement;
			it_grew = true;
		}
		if (this.weightKG < maxWeight) {
			this.weightKG = this.weightKG + weightIncrement;
			it_grew = true;
		}
		
		//------------------------------
		// Indicate how much it grew, or
		// or that it's fully grown.
		//------------------------------
		if (it_grew) {
			System.out.println(this.animalName + " grew!");
			System.out.println(this.animalName + " is now " + this.heightCM + "cm tall and "
								+ this.weightKG + "kg in mass.");
		} else {
			System.out.println(this.animalName + " is fully grown.");
		}
	}
	
	//-----------------------------------------------------------
	// ABSTRACT METHODS
	//
	// Abstract methods get implemented in child classes.
	// Each type of animal behaves very differently, so it's best
	// that we force each subclass to implement these for each
	// specific type of animal.
	//-----------------------------------------------------------
	public abstract void reproduce();
	
	//------------------------------------------------------
	// Each type of animal will have a very different
	// description, so it's best to make this abstract, too.
	//------------------------------------------------------
	public String description() {
		return "~~~~~~~~~~~~~~~~~~~~~~~~~~\nAnimal Name: "
				+ this.getAnimalName()
				+ "\nID: "
				+ this.getAnimalID()
				+ "\nGenus/Species: "
				+ this.getGenusSpecies()
				+ "\nLocation: "
				+ this.getLocation()
				+ "\nDiet: "
				+ this.getDiet()
				+ "\nHeight: "
				+ this.getHeightCM()
				+ "cm\nWeight: "
				+ this.getWeightKG()
				+ "kg\n";
	}
	
	//-----------------------------------------------
	// Animals move differently, too, but they all do
	// move, so move() is another abstract method.
	//-----------------------------------------------
	public abstract void move();
	
	//---------------------------------------------------------------
	// All animals share these common general properties:  they all
	// have a physical size, they live somewhere, eat something, etc.
	// So the getters and setters go here, not in the subclasses.
	//
	// These public getters and setters can be invoked in subclasses
	// using "this.methodName()" because they're all inherited.
	//
	//    Example:  this.setWeightKG(0.4);
	//---------------------------------------------------------------
	public double getWeightKG() {
		return weightKG;
	}
	public void setWeightKG(double weight) {
		this.weightKG = weight;
	}
	public double getHeightCM() {
		return heightCM;
	}
	public void setHeightCM(double heightCM) {
		this.heightCM = heightCM;
	}
	public int getAnimalID() {
		return animalID;
	}
	public void setAnimalID(int animalID) {
		this.animalID = animalID;
	}
	public String getAnimalName() {
		return animalName;
	}
	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}
	public String getGenusSpecies() {
		return genusSpecies;
	}
	public void setGenusSpecies(String genusSpecies) {
		this.genusSpecies = genusSpecies;
	}
	public String getDiet() {
		return diet;
	}
	public void setDiet(String diet) {
		this.diet = diet;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getMinHeight() {
		return minHeight;
	}
	public void setMinHeight(double minHeight) {
		this.minHeight = minHeight;
	}
	public double getMaxHeight() {
		return maxHeight;
	}
	public void setMaxHeight(double maxHeight) {
		this.maxHeight = maxHeight;
	}
	public double getMinWeight() {
		return minWeight;
	}
	public void setMinWeight(double minWeight) {
		this.minWeight = minWeight;
	}
	public double getMaxWeight() {
		return maxWeight;
	}
	public void setMaxWeight(double maxWeight) {
		this.maxWeight = maxWeight;
	}
	
	
	
	
	
	public static void testEatAndGrow() {
		System.out.println("Starting test of the .eat() and .grow() methods...");
		System.out.println("Creating a new BumblebeeBat named Billy...");
		BumblebeeBat billy = new BumblebeeBat(2, "Billy");
		System.out.println("Billy is eating...");
		
		if (billy.getHeightCM() >= billy.getMaxHeight() || billy.getWeightKG() >= billy.getMaxWeight()) {
			System.err.println("Hopefully billy has reached his maximum height or weight... otherwise you may have a problem!");
		}
		
		try {
			billy.eat();
		} catch (Exception error) {
			System.err.println("Your math is wrong!");
			return;
		}
		
		System.out.println("Test successful and complete!");
	}
	

}
