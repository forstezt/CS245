package edu.uwec.EMAILNAME.specific;

import edu.uwec.EMAILNAME.general.Bird;

//---------------------------------------------
// Define class EMU here as a subclass of Bird.
//---------------------------------------------

// ...THE CLASS HEADER LINE GOES HERE...

// A lot of errors below will disappear
// when you fill in the class header line
// above correctly!

	//------------------------------------------
	// Emu
	// Genus/Species: Dromaius novaehollandiae
	// Location: Mainland Australia
	// Diet: plants, insects
	// Size range: 150cm - 200cm
	// Weight range: 18.0kg - 55.0kg
	// Description: A very large flightless bird
	// capable of running at speeds over 30 mph.
	// Abilities: Runs around...
	//------------------------------------------
	
	//-----------------------
	// Construct an Emu here.
	//-----------------------
	public Emu(int animalID, String name) {
		
		this.setAnimalID(animalID);
		this.setAnimalName(name);
		
		// Initialize bounds of the species size.
		// This will get you started.
		this.setMinHeight(150);
		//...more goes here...
		
		// Create it at its smallest size.
		this.setHeightCM(this.getMinHeight());
		//...more goes here...
		
	}
	
	//-----------------------------------------------------------
	// Describe an Emu here.  As part of the description,
	// include general Bird description info from its superclass.
	// (See simulation output.)
	//-----------------------------------------------------------
	@Override
	public String description() {
		//...more goes here...
	}
	
	//--------------------------
	// What an Emu does for fun.
	//--------------------------
	public void move() {
		System.out.println("Runs around at high speed...");
	}
}
