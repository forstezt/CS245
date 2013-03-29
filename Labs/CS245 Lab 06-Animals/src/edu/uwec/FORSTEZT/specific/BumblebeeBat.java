package edu.uwec.FORSTEZT.specific;

import edu.uwec.FORSTEZT.general.Mammal;

//-------------------------------------------------------
//Define class BumblebeeBat here as a subclass of Mammal.
//-------------------------------------------------------

//-------------------------------------------------------------
//2012-03-09 1120 Created. FORSTEZT
//2012-03-09 0130 Tested and working. FORSTEZT
//-------------------------------------------------------------
public class BumblebeeBat extends Mammal {

	// A lot of errors below will disappear
	// when you fill in the class header line
	// above correctly!

	// ------------------------------------------
	// BumblebeeBat
	// Genus/Species: Craseonycteris thonglongyai
	// Location: Central Thailand
	// Diet: insects
	// Size range: 2.5cm - 3.3cm
	// Weight range: 0.002kg - 0.003kg
	// Description: Bat with a pig-like snout; arguably the smallest
	// mammal in the world.
	// Abilities: flap flap flap...
	// ------------------------------------------

	// -------------------------------
	// Construct a BumblebeeBat here.
	// -------------------------------
	public BumblebeeBat(int animalID, String name) {

		this.setAnimalID(animalID);
		this.setAnimalName(name);

		// Initialize bounds of the species size.
		// This will get you started.
		this.setMinHeight(2.5);
		this.setMaxHeight(3.3);

		this.setMinWeight(0.002);
		this.setMaxWeight(0.003);

		this.setGenusSpecies("Craseonycteris thonglongyai");
		this.setLocation("Central Thailand");
		this.setDiet("insects");

		// Create it at its smallest size.
		this.setHeightCM(this.getMinHeight());
		this.setWeightKG(this.getMinWeight());

	}

	// -------------------------------------------------------------
	// Describe a BumbleBeeBat here. As part of the description,
	// include general Mammal description info from the superclass.
	// (See simulation output.)
	// -------------------------------------------------------------
	@Override
	public String description() {

		return super.description()
				+ "BumblebeeBat: A bat with a pig-like snout. It is arguably the smallest mammal in the world.\n~~~~~~~~~~~~~~~~~~~~~~~~~~\n";

	}

	// --------------------
	// Just going batty...
	// --------------------
	public void move() {
		System.out.println("Flap flap flap...");
	}

}
