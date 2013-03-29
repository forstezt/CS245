package edu.uwec.cs245.events;

import java.util.ArrayList;
import java.util.Collections;

import acm.graphics.*;
import edu.uwec.cs245.RandomNumbers.MyRandomClass;

public class Deck_Class {

	// ---------------------------------
	// STATIC CONSTANTS
	// Used via Deck_Class.CONSTANTNAME
	// ---------------------------------
	public static final String WITH_JOKERS_STRING = "with_jokers";
	public static final String WITHOUT_JOKERS_STRING = "without_jokers";

	// -------------------------------
	// STATIC CONSTANTS AND VARIABLES
	// Used via Deck_Class.varName
	// -------------------------------
	// --------------------------
	// Sizes of a standard deck.
	// --------------------------
	public static final int TOTAL_CARDS_NO_JOKERS = 26;
	public static final int TOTAL_CARDS_WITH_JOKERS = 28;
	public static final int ROWS_DEALT = 4;
	public static final int COLS_DEALT = 7;

	// ------------------------------------
	// STATIC METHODS
	// Called via Deck_Class.methodName();
	// ------------------------------------

	// No static methods yet.

	// -------------------
	// INSTANCE VARIABLES
	// -------------------
	// -------------------------------------------------------
	// The main data structure of this class: the card deck.
	// -------------------------------------------------------
	private ArrayList<Card_Class> cardArray = new ArrayList<Card_Class>();

	// ---------------------------------
	// The jokers, which may or may
	// not be added into the deck,
	// as specified in the constructor.
	// ---------------------------------
	String withOrWithoutJokers = WITH_JOKERS_STRING;
	public Card_Class joker_black;
	public Card_Class joker_red;

	// ------------------------------------------------
	// CONSTRUCTORS
	//
	// Pass Deck_Class.WITH_JOKERS_STRING as the value
	// of parameter withOrWithoutJokers to construct a
	// deck that includes jokers; otherwise specify
	// Deck_Class.WITHOUT_JOKERS_STRING.
	// ------------------------------------------------
	public Deck_Class(String withOrWithoutJokers) {

		this.withOrWithoutJokers = withOrWithoutJokers;
		this.loadCardsIntoDeck(withOrWithoutJokers);
	}

	// ---------------------------------
	// INSTANCE METHODS
	// Called via objName.methodName();
	// ---------------------------------

	// -----------------------------------------------------
	// shuffleDeck shuffles the cards in this deck.
	//
	// This is how the deck is shuffled in one pass:
	// Swap a random card with card 1.
	// Swap a random card with card 2.
	// ...etc...
	// Swap a random card with card N.
	//
	// Parameter:
	// nrOfShufflesToDo
	// The number of shuffles to do if more than one
	// is desired. But just one is sufficient.
	//
	// 2012-03-30 1045 Created. FORSTEZT
	// 2012-03-30 1500 Tested and working. FORSTEZT
	// -----------------------------------------------------
	public void shuffleDeck(int nrOfShufflesToDo) {
		System.out.println("shuffleDeck() is executing.\n");
		Card_Class cardToSwapWith;
		for (int shuffles = 1; shuffles <= nrOfShufflesToDo; shuffles++) {

			for (Card_Class card : cardArray) {
				int randomIndex = MyRandomClass.randomInt(0,
						cardArray.size() - 1);
				cardToSwapWith = cardArray.get(randomIndex);
				cardArray.set(randomIndex, card);
				cardArray.set(cardArray.indexOf(card), cardToSwapWith);
			}

		} // end for (nrOfShufflesToDo shuffles)

		// System.out.println("shuffleDeck: After shuffling, the deck is:" +
		// this);

		System.out.println("shuffleDeck is returning.");
	} // End of shuffleDeck method
		// ----------------------------------------------------------------------
		// loadCardsIntoDeck loads the card images that are in the project
		// folder
		// "card_images" into this object's deck ArrayList and instance
		// variables
		// for jokers and card backs.
		//
		// NOTE: Cards are loaded into the deck via their names:
		// 0.jpg - 12.jpg are DIAMONDS: ace, 2, ..., jack, queen, king
		// 13.jpg - 25.jpg are SPADES: ace, 2, ..., jack, queen, king
		// 26.jpg - 38.jpg are HEARTS: ace, 2, ..., jack, queen, king
		// 39.jpg - 51.jpg are CLUBS: ace, 2, ..., jack, queen, king
		// joker_black.jpg (the black joker)
		// joker_red.jpg (the red joker)
		// back_vert_blue.jpg (the blue card back; vertical orientation)
		//
		// 2012-03-16 0216 Created. RICHAG
		// 2012-03-16 1806 Tested and working. RICHAG
		// 2012-03-20 0500 Now card filename numbers start at 0, not 1. RICHAG
		// 2012-03-20 0527 Retested and working. RICHAG
		// ----------------------------------------------------------------------

	public void loadCardsIntoDeck(String withOrWithoutJokers) {
		System.out
				.println("loadCardsIntoDeck is executing.  withOrWithoutJokers = "
						+ withOrWithoutJokers + "\n");

		String cardFaceImagePath; // card_images/1.jpg, cardimages/2.jpg, etc.
		String suitName; // diamonds, spades, hearts, clubs.
		int rankNr; // 1 (ace), 2, ... 11 (jack), 12 (queen), 13 (king).

		// ----------------------------------------
		// Load the regular cards (without jokers)
		// into the deck, setting their properties
		// as we do so. Jokers are added after
		// this loop if doing so was specified.
		// ----------------------------------------
		for (int cardIndex = 0; cardIndex < TOTAL_CARDS_NO_JOKERS; cardIndex++) {

			// ----------------------------------
			// Compute this card's suit and rank
			// based on its card number. Card
			// filenames are the numbers 0 thru
			// 54. All the diamonds are 0-12,
			// the spades are 13-25, etc.
			// ----------------------------------
			int cardNr = cardIndex + 1; // Helps compute rankNr.

			if (cardIndex < 13) {
				suitName = "diamonds";
				rankNr = cardNr;
			} else if (cardIndex < 26) {
				suitName = "spades";
				rankNr = cardNr - 13;
			} else if (cardIndex < 39) {
				suitName = "hearts";
				rankNr = cardNr - 26;
			} else {
				suitName = "clubs";
				rankNr = cardNr - 39;
			}

			// ----------
			// DEBUGGING
			// ----------
			// System.out.println("In loadCardsIntoDeck:  cardIndex = " +
			// cardIndex
			// + ", rank = " + rankNr
			// + ", suit = " + suitName + ".");

			// ------------------------
			// Compute the card paths.
			// ------------------------
			cardFaceImagePath = "card_images/" + cardIndex + ".jpg";

			// -----------------------------------------
			// Create a new card and add it to the deck.
			// -----------------------------------------
			Card_Class newCard = new Card_Class(cardFaceImagePath,
					Card_Class.BLUE_CARD_BACK_PATH, suitName, rankNr);
			this.cardArray.add(newCard);

			// ----------
			// DEBUGGING
			// ----------
			// System.out.println("In loadCardsIntoDeck:  The deck after adding card index "
			// + cardIndex + ":\n" + this);

		} // End for

		// -------------------------------
		// Load the two joker card images.
		// Give jokers the highest ranks.
		// -------------------------------
		this.joker_black = new Card_Class(Card_Class.BLACK_JOKER_PATH,
				Card_Class.BLUE_CARD_BACK_PATH, Card_Class.BLACK_SUIT_NAME,
				Card_Class.JOKER_RANK_NR);

		this.joker_red = new Card_Class(Card_Class.RED_JOKER_PATH,
				Card_Class.BLUE_CARD_BACK_PATH, Card_Class.RED_SUIT_NAME,
				Card_Class.JOKER_RANK_NR);

		// ----------------------------------------------------
		// If the jokers are to be added into the deck, do so.
		// ----------------------------------------------------
		if (withOrWithoutJokers.equals(WITH_JOKERS_STRING)) {
			cardArray.add(this.joker_black);

			// System.out.println("In loadCardsIntoDeck:  After adding THE BLACK JOKER "
			// + cardArray.size() + ", deck = " + this);

			cardArray.add(this.joker_red);

			// System.out.println("In loadCardsIntoDeck:  After adding THE RED JOKER "
			// + cardArray.size() + ", deck = " + this);
		}

		// ----------
		// DEBUGGING
		// ----------
		// System.out.println("\nThe deck currently holds " + cardArray.size() +
		// " cards.");

		System.out.println("loadCardsIntoDeck is returning.");
	} // end of loadCardsIntoDeck method

	// ---------------------------------------------------
	// dealCard deals the specified card onto the canvas.
	// It's recorded as being in the specified pile, and
	// is dealt face_up or face_down as indicated at the
	// GPoint parameter cardLoc.
	//
	// Example:
	// dealCard(canvas, cardNr, "table",
	// Card_Class.FACE_DOWN, cardLoc);
	//
	// 2012-03-18 0248 Created. RICHAG
	// 2012-03-20 1130 Tested and working. FORSTEZT
	// ---------------------------------------------------
	public void dealCard(GCanvas canvas, int cardIndex, String currentPile,
			String faceUpOrDown, GPoint cardLoc) {
		// System.out.println("Deck_Class.dealCard() is executing:\n"
		// + "  cardNr = " + cardIndex + "\n"
		// + "  currentPile = " + currentPile + "\n"
		// + "  faceUpOrDown = " + faceUpOrDown + "\n"
		// + "  cardLoc = " + cardLoc + "\n");

		// ----------------------------------------------
		// Set the specified metaproperties of the card.
		// ----------------------------------------------

		// --- YOUR CODE GOES HERE ---
		cardArray.get(cardIndex).setCurrentPile(currentPile);
		cardArray.get(cardIndex).setCardLoc(cardLoc);
		cardArray.get(cardIndex).setFaceUpOrDown(faceUpOrDown);

		// ---------------------------------------------
		// Add either its card face or card back OImage
		// to the canvas, depending on the value of the
		// card's faceUpOrDown property.
		// ---------------------------------------------
		if (faceUpOrDown.equals(Card_Class.FACE_UP)) {
			canvas.add(cardArray.get(cardIndex).getCardFaceOImage(), cardLoc);
		} else if (faceUpOrDown.equals(Card_Class.FACE_DOWN)) {
			canvas.add(cardArray.get(cardIndex).getCardBackOImage(), cardLoc);
		}

		// System.out.println("Deck_Class.dealCard() is returning.\n");
	} // End of dealCard method

	// --------------------------------------------
	// Test_Deck_Class
	//
	// Example:
	// Deck_Class.Test();
	//
	// 2012-13-17 1232 Created. RICHAG
	// 2012-13-17 2046 Tested and working. RICHAG
	// --------------------------------------------
	public static void Test() {
		System.out.println("Deck_Class.Test() is starting.\n");

		// System.out.println("Deck_Class.Test():  Random(currentTimeMillis()) = "
		// + System.currentTimeMillis());

		// ------------------------------------------
		// Create a deck WITHOUT jokers and test it.
		// ------------------------------------------
		// Deck_Class deckNoJokers = new Deck_Class(WITHOUT_JOKERS_STRING);
		// System.out.println("\nInitial deck WITHOUT jokers: \n" +
		// deckNoJokers);

		// System.out.println("Deck_Class.Test():  Shuffling the deck...");
		// deckNoJokers.shuffleDeck();

		// System.out.println("\nDeck_Class.Test():  Shuffled deck WITHOUT jokers: \n"
		// + deckNoJokers);

		// ---------------------------------------
		// Create a deck WITH jokers and test it.
		// ---------------------------------------
		Deck_Class deckWithJokers = new Deck_Class(WITH_JOKERS_STRING);
		System.out.println("\nInitial deck WITH jokers: \n" + deckWithJokers);

		System.out.println("Deck_Class.Test():  Shuffling the deck...");
		deckWithJokers.shuffleDeck(1);

		System.out
				.println("\nDeck_Class.Test():  Shuffled deck WITH jokers: \n"
						+ deckWithJokers);

		// --------------
		// Done testing.
		// --------------
		System.out.println("Deck_Class.Test() is finished.");
	} // End of Deck_Class.Test() method

	// ----------------------------------------------
	// toString()
	//
	// This toString displays a deck as a vertical
	// list of card info lines.
	//
	// 2012-03-17 1458 Created. RICHAG
	// 2012-03-17 1806 Tested and working. RICHAG
	// 2012-03-17 2030 Now displays the array index
	// in square brackets. RICHAG
	// 2012-03-17 2040 Retested and working. RICHAG
	// ----------------------------------------------
	@Override
	public String toString() {

		String verticalCardList = "\n  The deck currently holds "
				+ cardArray.size() + " cards:\n";

		for (int index = 0; index < cardArray.size(); index++) {

			verticalCardList = verticalCardList + "    ["
					+ (index < 10 ? " " : "") + index + "] "
					+ cardArray.get(index);
		}

		return verticalCardList;

	} // End of toString method

} // End of Deck_Class
