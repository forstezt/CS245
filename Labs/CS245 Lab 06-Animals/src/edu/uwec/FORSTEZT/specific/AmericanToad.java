package edu.uwec.FORSTEZT.specific;

import edu.uwec.FORSTEZT.general.Amphibian;

//----------------------------------------------------------
//Define class AmericanToad here as a subclass of Amphibian.
//----------------------------------------------------------

//-------------------------------------------------------------
//2012-03-09 1120 Created. FORSTEZT
//2012-03-09 0130 Tested and working. FORSTEZT
//-------------------------------------------------------------
public class AmericanToad extends Amphibian {

	// A lot of errors below will disappear
	// when you fill in the class header line
	// above correctly!

	// ------------------------------------------
	// AmericanToad
	// Genus/Species: Bufo americanus
	// Location: Northeastern United States and Canada
	// Diet: insects
	// Size range: 3.0cm - 9.0cm
	// Weight range: 0.15kg - 0.6kg
	// Description: A highly camouflaged land amphibian
	// with stocky hind legs.
	// Abilities: Hop hop hop...
	// ------------------------------------------

	// --------------------------------
	// Construct an AmericanToad here.
	// --------------------------------
	public AmericanToad(int animalID, String name) {

		this.setAnimalID(animalID);
		this.setAnimalName(name);

		// Initialize bounds of the species size.
		// This will get you started.
		this.setMinHeight(3.0);
		this.setMaxHeight(9.0);

		this.setMinWeight(0.15);
		this.setMaxWeight(0.6);

		this.setGenusSpecies("Bufo americanus");
		this.setLocation("Northeastern United States and Canada");
		this.setDiet("insects");

		// Create it at its smallest size.
		this.setHeightCM(this.getMinHeight());
		this.setWeightKG(this.getMinWeight());

	}

	// ----------------------------------------------------------
	// We override the call to amphibian's method here, since we
	// want to display more-specific toad information. But
	// we can still get useful information from that method,
	// so call super.description() to get that and use it as part
	// of the description here. (See simulation output.)
	// ----------------------------------------------------------
	public String description() {
		return super.description()
				+ "American Toad: Its mottled skin makes it highly camouflaged. It also has stocky hind legs and a sticky tongue.\n~~~~~~~~~~~~~~~~~~~~~~~~~~\n";

	}

	// -------------------------------
	// A toad leads a very hoppy life.
	// -------------------------------
	public void move() {
		System.out.println("Hop hop hop...");
	}

}
