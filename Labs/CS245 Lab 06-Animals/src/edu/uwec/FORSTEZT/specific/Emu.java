package edu.uwec.FORSTEZT.specific;

import edu.uwec.FORSTEZT.general.Bird;

//---------------------------------------------
// Define class EMU here as a subclass of Bird.
//---------------------------------------------

//-------------------------------------------------------------
//2012-03-09 1120 Created. FORSTEZT
//2012-03-09 0130 Tested and working. FORSTEZT
//-------------------------------------------------------------
public class Emu extends Bird {

	// A lot of errors below will disappear
	// when you fill in the class header line
	// above correctly!

	// ------------------------------------------
	// Emu
	// Genus/Species: Dromaius novaehollandiae
	// Location: Mainland Australia
	// Diet: plants, insects
	// Size range: 150cm - 200cm
	// Weight range: 18.0kg - 55.0kg
	// Description: A very large flightless bird
	// capable of running at speeds over 30 mph.
	// Abilities: Runs around...
	// ------------------------------------------

	// -----------------------
	// Construct an Emu here.
	// -----------------------
	public Emu(int animalID, String name) {

		this.setAnimalID(animalID);
		this.setAnimalName(name);

		// Initialize bounds of the species size.
		// This will get you started.
		this.setMinHeight(150);
		this.setMaxHeight(200);

		this.setMinWeight(18.0);
		this.setMaxWeight(55.0);

		this.setGenusSpecies("Dromaius novaehollandiae");
		this.setLocation("Mainland Australia");
		this.setDiet("plants, insects");

		// Create it at its smallest size.
		this.setHeightCM(this.getMinHeight());
		this.setWeightKG(this.getMinWeight());

	}

	// -----------------------------------------------------------
	// Describe an Emu here. As part of the description,
	// include general Bird description info from its superclass.
	// (See simulation output.)
	// -----------------------------------------------------------
	@Override
	public String description() {
		return super.description()
				+ "Emu: A flightless bird.  The second largest bird in the world, it is capable of running at speeds over 30 mph.\n~~~~~~~~~~~~~~~~~~~~~~~~~~\n";

	}

	// --------------------------
	// What an Emu does for fun.
	// --------------------------
	public void move() {
		System.out.println("Runs around at high speed...");
	}
}
