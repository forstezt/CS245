package edu.uwec.EMAILNAME.specific;

import edu.uwec.EMAILNAME.general.Amphibian;

//----------------------------------------------------------
//Define class AmericanToad here as a subclass of Amphibian.
//----------------------------------------------------------

//...THE CLASS HEADER LINE FOR AmericanToad GOES HERE...

//A lot of errors below will disappear
//when you fill in the class header line
//above correctly!

	//------------------------------------------
	// AmericanToad
	// Genus/Species: Bufo americanus
	// Location: Northeastern United States and Canada
	// Diet: insects
	// Size range: 3.0cm - 9.0cm
	// Weight range: 0.15kg - 0.6kg
	// Description: A highly camouflaged land amphibian
	// with stocky hind legs.
	// Abilities: Hop hop hop...
	//------------------------------------------

	// --------------------------------
	// Construct an AmericanToad here.
	// --------------------------------
	public AmericanToad(int animalID, String name) {

		this.setAnimalID(animalID);
		this.setAnimalName(name);
		
		// Initialize bounds of the species size.
		// This will get you started.
		this.setMinHeight(3.0);
		//...more goes here...
		
		// Create it at its smallest size.
		this.setHeightCM(this.getMinHeight());
		//...more goes here...

	}

	// ----------------------------------------------------------
	// We override the call to amphibian's method here, since we
	// want to display more-specific toad information. But
	// we can still get useful information from that method,
	// so call super.description() to get that and use it as part
	// of the description here.  (See simulation output.)
	// ----------------------------------------------------------
	public String description() {

		//...more goes here...

	}

	// -------------------------------
	// A toad leads a very hoppy life.
	// -------------------------------
	public void move() {
		System.out.println("Hop hop hop...");
	}

}
