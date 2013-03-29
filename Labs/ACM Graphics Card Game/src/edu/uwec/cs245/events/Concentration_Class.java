package edu.uwec.cs245.events;

import java.util.Scanner;

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GPoint;

public class Concentration_Class {

	// -----------------------------------
	// STATIC CONSTANTS
	// Referenced via Deck_Class.CONSTANTNAME
	// -----------------------------------
	static final int MILISECONDS_TO_PAUSE = 2000;

	// ----------------------------------
	// STATIC VARIABLES
	// Referenced via Deck_Class.varName
	// ----------------------------------

	// No static variables yet.

	// ------------------------------------
	// STATIC METHODS
	// Called via Deck_Class.methodName();
	// ------------------------------------

	// No static methods yet.

	// -------------------
	// INSTANCE VARIABLES
	// -------------------
	// ---------------
	// The card deck.
	// ---------------
	GCanvas canvas;
	Deck_Class deck = new Deck_Class(Deck_Class.WITH_JOKERS_STRING);

	// ----------------------------------------
	// Track how many cards have been selected
	// (up to two) and which cards they are.
	// ----------------------------------------
	int nrOfCardsNowSelected = 0; // Two cards maximum; indexes are 0 and 1.

	Card_Class selectedCard1;
	Card_Class selectedCard2;

	OImage cardOImage1; // The first card OImage shown face-up.
	OImage cardOImage2; // The second card OImage shown face-up.

	int nrOfCardsOnGameBoard = 0; // Cards are removed in pairs on each match.

	// -------------
	// CONSTRUCTORS
	// -------------
	public Concentration_Class(GCanvas canvas) {
		this.canvas = canvas;
	}

	// ---------------------------------
	// INSTANCE METHODS
	// Called via objName.methodName();
	// ---------------------------------

	// ---------------------------------------------
	// startANewGame sets up a new game by getting
	// the player names, then dealing the cards and
	// displaying the player names, scores, and the
	// game status on the screen. Then this
	// method terminates; the game proceeds via
	// trapping and processing mouse events until
	// the game is over and a human user says to
	// quit.
	//
	// Example:
	// deck.startANewGame();
	//
	// 2012-03-20 1100 Created. FORSTEZT
	// 2012-03-20 1100 Tested and working. FORSTEZT
	// ---------------------------------------------
	public void startANewGame() {
		System.out
				.println("Concentration_Class.startANewGame() is executing.\n");

		// ---------------------------------------
		// Deal the cards onto the game table,
		// then terminate and wait for the player
		// to click a card.
		// ---------------------------------------

		// --- YOUR CODE GOES HERE ---
		this.dealConcentration();

		System.out
				.println("Concentration_Class.startANewGame() is returning.\n");
		return;

	} // End of startTheGame method

	// ---------------------------------------------------
	// handleCardClick handles the game processing needed
	// when the current player clicks a card back on the
	// game board (the canvas).
	//
	// The card is turned over and displayed face-up, and
	// we increment the number of cards selected on this
	// turn.
	//
	// If this is the first card the player has clicked
	// on this turn, we leave it visible, but store
	// which card this is so we can flip it back over
	// later. Then we terminate. The player will then
	// click a second card to try to match this first
	// card's rank.
	//
	// But if this is the SECOND card the player has
	// clicked, we check whether its rank (not its suit)
	// matches the first selected card's rank.
	//
	// If not, we pause long enough for the player
	// to try to memorize the cards and their positions,
	// then flip over both cards to be face-down again.
	// The cards must be repainted after they're flipped
	// in order to display the change on-screen while
	// this method is still executing.
	//
	// Then we terminate so the player can take the next
	// turn.
	//
	// But if the two selected cards do match, both
	// cards are removed from the canvas.
	//
	// Then we check whether all the cards have been
	// removed from the game board. If not, we deal
	// another game. Otherwise the game is over and we
	// terminate the entire program.
	//
	// Example:
	// handleCardClick(clickedGObject);
	//
	// 2012-03-20 1600 Created. FORSTEZT
	// 2012-03-20 1830 Tested and working. FORSTEZT
	// ---------------------------------------------------
	public void handleCardClick(GObject clickedGObject) throws InterruptedException {
		System.out
				.println("Concentration_Class.handleCardClick() is executing.");

		GLabel cardInfo;
		// ---------------------------------------
		// Cast the clicked GObject (which is
		// really an OImage) to its OImage class.
		// ---------------------------------------
		OImage clickedCardOImage = (OImage) clickedGObject;

		if (nrOfCardsNowSelected == 0) {
			//cardOImage1 = (OImage)clickedGObject;

			selectedCard1 = (Card_Class)(clickedCardOImage.getOwnerObj());
			cardOImage1 = this.flipCardOver(clickedCardOImage);
			System.out.println("first card");
			nrOfCardsNowSelected++;
			return;

		} else if (nrOfCardsNowSelected == 1){
			//cardOImage2 = (OImage)clickedGObject;
			
			selectedCard2 = (Card_Class)(clickedCardOImage.getOwnerObj());		
			cardOImage2 = this.flipCardOver(clickedCardOImage);
			
			if (selectedCard2 == selectedCard1) {
				nrOfCardsNowSelected = 0;
				return;
			}
			
			if (selectedCard2.getRankNr() != selectedCard1.getRankNr()) {
				Thread.sleep(MILISECONDS_TO_PAUSE);
				cardOImage1 = this.flipCardOver(cardOImage1);
				cardOImage2 = this.flipCardOver(cardOImage2);
				nrOfCardsNowSelected = 0;
				return;
			}
			
			nrOfCardsNowSelected = 0;
		}
	
		// --------------------------------
		// If we got this far, the ranks of
		// the two cards showing DO match.
		// Remove them from the game board.
		// --------------------------------
		System.out.println("Pausing, then removing the two matching cards.");

		 //pauseSeconds(SECONDS_TO_PAUSE);
		 Thread.sleep(MILISECONDS_TO_PAUSE);
		 canvas.remove(cardOImage1);
		 canvas.remove(cardOImage2);
		 nrOfCardsOnGameBoard -= 2;

		// ------------------------------
		// Update the canvas so that the
		// two removed cards vanish from
		// the screen NOW.
		// ------------------------------
		 canvas.paint(canvas.getGraphics());

		// --- THEN MORE CODE GOES HERE ---
		if (nrOfCardsOnGameBoard == 0) {
			this.startANewGame();
		}

		System.out
				.println("Concentration_Class.handleCardClick() is returning.\n");
	} // End of handleCardClick method

	// -----------------------------------------
	// dealConcentration deals out the cards
	// for the game of Concentration. For this
	// lab, the two jokers are included.
	//
	// Example:
	// this.Deal_Concentration();
	//
	// 2012-03-20 1130 Created. FORSTEZT
	// 2012-03-20 1630 Tested and working. FORSTEZT
	// -----------------------------------------
	public void dealConcentration() {
		System.out.println("dealConcentration() is executing.\n");

		// --- YOUR CODE GOES HERE ---
		deck.shuffleDeck(1);
		
		for (int i = 0; i < deck.ROWS_DEALT; i++) {
			for (int j = 0; j < deck.COLS_DEALT; j++) {
				deck.dealCard(canvas, i * (deck.COLS_DEALT) + j, "table",
						Card_Class.FACE_DOWN, new GPoint(j
								* Card_Class.CARD_WIDTH_PIXELS, i
								* Card_Class.CARD_HEIGHT_PIXELS));
			}
		}
		
		nrOfCardsOnGameBoard = deck.ROWS_DEALT * deck.COLS_DEALT;
		
		System.out.println("dealConcentration() is returning.\n");
	} // End of dealConcentration method

	// --------------------------------------------------------
	// flipCardOver turns over a card clicked on the canvas,
	// regardless of what its current orientation is. If it's
	// face up, this method turns it face down, and vice-versa.
	// The OImage left on the canvas is returned.
	//
	// Example:
	// flipCardOver(cardOImage);
	//
	// 2012-03-20 1700 Created. FORSTEZT
	// 2012-03-20 1750 Tested and working. FORSTEZT
	// --------------------------------------------------------
	public OImage flipCardOver(OImage clickedOImage) {
		System.out.println("flipCardOver is executing.");

		// ----------------------------------------------------
		// Get a ref to the card that owns the clicked OImage.
		// ----------------------------------------------------

		// --- SOME CODE GOES HERE ---
		Card_Class clickedCard = (Card_Class) clickedOImage.getOwnerObj();

		// --------------------------------------------------
		// Invert the card and get its corresponding OImage.
		// --------------------------------------------------

		// --- MORE CODE GOES HERE ---
		OImage flippedCardOImage;
		if (clickedCard.getFaceUpOrDown().equals(clickedCard.FACE_DOWN)) {
			clickedCard.setFaceUpOrDown(clickedCard.FACE_UP);
			flippedCardOImage = clickedCard.getCardFaceOImage();
		} else {
			clickedCard.setFaceUpOrDown(clickedCard.FACE_DOWN);
			flippedCardOImage = clickedCard.getCardBackOImage();
		}

		// ------------------------------------------
		// Out with the old OImage, in with the new,
		// at the clicked card's same card location.
		// ------------------------------------------
		canvas.remove(clickedOImage);
		canvas.add(flippedCardOImage, clickedCard.getCardLoc());

		// -----------------------------------
		// Paint the flipped card so that the
		// card's image is updated on-screen
		// RIGHT NOW.
		// -----------------------------------
		flippedCardOImage.paint(canvas.getGraphics());

		// System.out.println("flipCardOver is returning.  The card is now "
		// + currCard);

		return flippedCardOImage;

	} // End of flipCardOver() method

	// ---------------------------------------------
	// doConcentrationTest tests the methods
	// in this Concentration_Class by calling
	// them and printing the necessary info
	// to show that they're working properly.
	//
	// NOTE: This is a static method, so qualify
	// its name with the class name, not an object
	// name, when you call it.
	//
	// Where would be a good place to call this
	// method to run your test? HINT: NOT in the
	// method named "main"!
	//
	// Example:
	// Concentration_Class.doConcentrationTest();
	//
	// 2012-03-21 1440 Created. FORSTEZT
	// 2012-03-21 1500 Tested and working. FORSTEZT
	// ---------------------------------------------
	public static void doConcentrationTest(GCanvas canvas) throws InterruptedException {
		System.out.println("Concentration_Class.Test() is starting.\n");

		// -------------------------------------------
		// Test your Concentration game methods here.
		// -------------------------------------------

		// --- YOUR TEST CODE GOES HERE ---
		
		// --------------
		// Done testing.
		// --------------
		System.out.println("Concentration_Class.Test() is finished.");
	} // End of Concentration_Class.Test() method

} // End of Concentration_Class
