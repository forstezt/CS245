package edu.uwec.cs245.events;

import acm.graphics.*;

//-------------------------------------------------------------
// Card_Class is the template for a single card object.
// A Deck_Class is a template for a deck of Card_Class objects.
//-------------------------------------------------------------
public class Card_Class {

	//----------------------------------
	// STATIC CONSTANTS
	// Used via Card_Class.CONSTANT_NAME
	//----------------------------------
	public final static int    CARD_WIDTH_PIXELS  = 71;
	public final static int    CARD_HEIGHT_PIXELS = 96;
	public final static String FACE_UP            = "face_up";
	public final static String FACE_DOWN          = "face_down";
	
	//--------------------------------------
	// Named constants for card back colors.
	//--------------------------------------
	public final static String BACK_COLOR = "blue";
	public final static String BACK_COLOR_NICKNAME = "blueback"; // Used in debugging messages.

	//------------
	// Card ranks.
	//------------
	public final static int NONCARD_RANK_NR  = 0;  // Used for card backs and blank cards.
	public final static int LOW_ACE_RANK_NR  = 1;  // Useful for looping over ranks 1-14.
	public final static int DEUCE_RANK_NR    = 2;  // Useful for looping over ranks 2-15.
	public final static int HIGH_ACE_RANK_NR = 14; // Sometimes ace is higher-ranked than king. 
	public final static int JOKER_RANK_NR    = 15; // Joker is normally ranked highest.
	
	public final static String NONCARD_RANK_NAME = "no_rank";  // Used for blank cards and card backs.
	
	public final static String[] rankNames = {NONCARD_RANK_NAME, "ace", "two", "three", "four",
		"five",	"six", "seven", "eight", "nine", "ten", "jack", "queen", "king", "ace", "joker"};
	
	//------------
	// Card suits.
	//------------
	public final static int NONCARD_SUIT_NR = 0; // Used for card backs and blank cards.
	public final static int INITIAL_SUIT_NR = 1; // Useful for looping over all suits (1-4).
	public final static int FINAL_SUIT_NR   = 4; // Useful for looping over all suits (1-4).
	
	public final static String BLACK_SUIT_NAME   = "black";
	public final static String RED_SUIT_NAME     = "red";
	public final static String NONCARD_SUIT_NAME = "no_suit";  // Used for blank cards and card backs.
	
	public final static String[] suitNames  = {NONCARD_SUIT_NAME, "diamonds", "spades", "hearts", "clubs",
		                                       RED_SUIT_NAME, BLACK_SUIT_NAME};

	//------------
	// Card paths.
	//------------
	public final static String BLUE_CARD_BACK_PATH  = "card_images/back_vert_blue.jpg";
	public final static String BLACK_JOKER_PATH     = "card_images/joker_black.jpg";
	public final static String RED_JOKER_PATH       = "card_images/joker_red.jpg";

	//----------------------------
	// STATIC VARIABLES
	// Used via Card_Class.varName
	//----------------------------
	
	// No static variables yet.

	//------------------------------------
	// STATIC METHODS
	// Called via Deck_Class.methodName();
	//------------------------------------

	// No static methods yet.
	
	//---------------------------
	// PRIVATE INSTANCE VARIABLES
	//---------------------------
	//----------
	// Card data
	//----------
	private OImage cardFaceOImage    = null;                // The face's OImage, which contains its image.
	private String cardFaceImagePath = "no_cardImagePath";  // The path to the card face's JPEG image.
	private OImage cardBackOImage    = null;                // The face's OImage, which contains its image.
	private String cardBackImagePath = BLUE_CARD_BACK_PATH; // The path to the blue card back's JPEG image.

	private int    rankNr    = NONCARD_RANK_NR;      // 0 = noncard, 1=ace, 2, 3, ..., 12=queen, 13=king.
	private String rankName  = NONCARD_RANK_NAME;    // "ace", "two", ..., "jack", "queen", "king".
	private String suitName  = NONCARD_SUIT_NAME;    // Usually "diamonds", "spades", "hearts", or "clubs".
	private String backColor = BACK_COLOR;           // The color of the currently used card back.
	
	//--------------
	// Card metadata
	//--------------
	private GPoint cardLoc;                        // The canvas location of the card's upper left corner.
	private String currentPile  = "the deck";      // Or "the table", a pile name ("the stock"), etc.
	private String faceUpOrDown = FACE_UP;         // Indicates whether a card is FACE_UP or FACE_DOWN.
	
	//-------------
	// CONSTRUCTORS
	//-------------
	//--------------------------------------------
	// Card_Class CONSTRUCTOR for card fronts.
	//
	// 2012-03-17 1630 Created.  RICHAG
	// 2012-03-17 1806 Tested and working.  RICHAG
	//--------------------------------------------
	public Card_Class(String cardFaceImagePath, String cardBackImagePath,
			String suitName, int rankNr) {
		
		this.cardFaceImagePath = cardFaceImagePath;
		this.cardFaceOImage    = new OImage(this.cardFaceImagePath, this);
		this.cardBackImagePath = cardBackImagePath;
		this.cardBackOImage    = new OImage(BLUE_CARD_BACK_PATH, this);
		
		this.suitName  = suitName;
		this.rankNr    = rankNr;
		this.rankName  = rankNames[rankNr];
		this.backColor = BACK_COLOR_NICKNAME;
		
		//System.out.println("Card_Class.Constructor created: " + this); // FOR DEBUGGING.
	}

	//---------------------------------
	// INSTANCE METHODS
	// Called via objName.methodName();
	//---------------------------------

	//---------------------
	// Getters and Setters.
	//---------------------
	public String getCardFaceImagePath() {
		return cardFaceImagePath;
	}
	
	public String getCardBackImagePath() {
		return cardBackImagePath;
	}
	
	public OImage getCardFaceOImage() {
		return cardFaceOImage;
	}
	
	public OImage getCardBackOImage() {
		return cardBackOImage;
	}
	
	public int getRankNr() {
		return rankNr;
	}

	public void setRankNr(int rankNr) {
		this.rankNr = rankNr;
	}

	public String getRankName() {
		return rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	public String getSuitName() {
		return suitName;
	}

	public void setSuitName(String suitName) {
		this.suitName = suitName;
	}

	public GPoint getCardLoc() {
		return cardLoc;
	}

	public void setCardLoc(GPoint cardLoc) {
		this.cardLoc = cardLoc;
	}

	public String getCurrentPile() {
		return currentPile;
	}

	public void setCurrentPile(String currentPile) {
		this.currentPile = currentPile;
	}

	public String getFaceUpOrDown() {
		return faceUpOrDown;
	}

	public void setFaceUpOrDown(String FaceUpOrDown) {
		this.faceUpOrDown = FaceUpOrDown;
	}

	//--------------------------------------------
	// toString() displays card info on a single
	// line terminated with a NEWLINE.
	//
	// 2012-03-17 1247 Created.  RICHAG
	// 2012-03-17 1658 Tested and working.  RICHAG
	//--------------------------------------------
	@Override
	public String toString() {
		
		return this.backColor
				+ " "    + this.rankName
				+ " of " + this.suitName
				+ ", "   + (this.faceUpOrDown)
				+ " in " + this.currentPile
				+ "\n";
		
	} // End of toString method

} // End of Card_Class