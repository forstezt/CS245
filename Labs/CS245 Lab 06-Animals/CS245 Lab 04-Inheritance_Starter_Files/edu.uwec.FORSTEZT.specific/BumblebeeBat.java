package edu.uwec.EMAILNAME.specific;

import edu.uwec.EMAILNAME.general.Mammal;

//-------------------------------------------------------
//Define class BumblebeeBat here as a subclass of Mammal.
//-------------------------------------------------------

//...THE CLASS HEADER LINE for BumblebeeBat GOES HERE...

//A lot of errors below will disappear
//when you fill in the class header line
//above correctly!

	//------------------------------------------
	// BumblebeeBat
	// Genus/Species: Craseonycteris thonglongyai
	// Location: Central Thailand
	// Diet: insects
	// Size range: 2.5cm - 3.3cm
	// Weight range: 0.002kg - 0.003kg
	// Description: Bat with a pig-like snout; arguably the smallest
	// mammal in the world.
	// Abilities: flap flap flap...
	//------------------------------------------

	// -------------------------------
	// Construct a BumblebeeBat here.
	// -------------------------------
	public BumblebeeBat(int animalID, String name) {

		this.setAnimalID(animalID);
		this.setAnimalName(name);

		// Initialize the bounds of the species size.
		// This will get you started.
		this.setMinHeight(2.5);
		// ...more goes here...

		// Create it at its smallest size.
		this.setHeightCM(this.getMinHeight());
		// ...more goes here...
		
	}

	//-------------------------------------------------------------
	// Describe a BumbleBeeBat here.  As part of the description,
	// include general Mammal description info from the superclass.
	// (See simulation output.)
	//-------------------------------------------------------------
	@Override
	public String description() {
		//...more goes here...
	}

	//--------------------
	// Just going batty...
	//--------------------
	public void move() {
		System.out.println("Flap flap flap...");
	}

}
