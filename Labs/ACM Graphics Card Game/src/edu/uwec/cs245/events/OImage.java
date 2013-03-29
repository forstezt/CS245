package edu.uwec.cs245.events;

import acm.graphics.GImage;

//----------------------------------------------------
// An OwnedGImage is a GImage that is embedded within
// a larger object (in this game, it's embedded within
// a Card_Class object), so it holds a reference to
// that owning object so that it's possible, given the
// GImage, to find the object that it's embedded in.
//
// That's handy, for example, if the GImage is clicked
// on a canvas and we need to find the card object
// that the image depicts to get at the card properties,
// not the GImage's properties.
//
// 2012-03-19 0110 Created.  RICHAG
// 2012-03-30 0502 Tested and working.  RICHAG
//------------------------------------------------------
public class OImage extends GImage {
	
	//--------------------
	// Instance variables.
	//--------------------
	//--------------------------
	// A reference to the object
	// that owns this GImage.
	//--------------------------
	private Object ownerObj = null;

	//-------------
	// CONSTRUCTORS
	//-------------
	//--------------------------------------------
	// OwnedGImage CONSTRUCTOR.
	//
	// 2012-03-19 0123 Created.  RICHAG
	// 2012-03-30 0503 Tested and working.  RICHAG
	//--------------------------------------------
	public OImage(String imagePath, Object ownerObj) {
		
		//-----------------------------
		// Construct the GImage itself.
		//-----------------------------
		super(imagePath);
		
		//--------------------------
		// Store a ref to the object
		// that owns this GImage.
		//--------------------------
		this.ownerObj = ownerObj;
		
		//System.out.println("Owned_GImage.Constructor: This owned GImage has been created: " + this);
	}

	public Object getOwnerObj() {
		return this.ownerObj;
	}
	
	
	//-----------------------------------------------
	// cloneOImage returns a clone of a given OImage.
	//
	// 2012-03-19 1705 Created.  RICHAG
	// 2012-03-30 0503 Tested and working.  RICHAG
	//-----------------------------------------------
	public static OImage cloneOImage(OImage originalOImage) {
		
		//------------------------------------
		// Get the card that owns this OImage.
		//------------------------------------
		Object ownerObj = originalOImage.getOwnerObj();
		Card_Class originalCard = (Card_Class) ownerObj;
		
		//---------------------------------------------
		// Get the card's face-up or face-down image,
		// depending on the card's current orientation.
		//---------------------------------------------
		String imagePath;
		if (originalCard.getFaceUpOrDown().equals(Card_Class.FACE_UP)) {
			imagePath = originalCard.getCardFaceImagePath();
		} else {
			imagePath = originalCard.getCardBackImagePath();
		}
		
		//--------------------------------
		// Construct and return the clone.
		//--------------------------------
		OImage clone = new OImage(imagePath, ownerObj);
		return clone;
	}

	@Override
	public String toString() {
		Card_Class ownerCard = (Card_Class) this.ownerObj;
		return "\nOImage [" + super.toString() + "\nownerObj=" + ownerCard + "]";
	}
	
	
} // End of OImage class
