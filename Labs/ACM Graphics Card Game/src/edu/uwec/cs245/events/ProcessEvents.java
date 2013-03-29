//---------------------------------------------------------
// Program:  ProcessEvents
// Author:   YOUR NAME GOES HERE (and your EMAILNAME here).
// What it does:  This is the game Concentration Solitaire.
// Course:  CS245
//
// 2012-??-?? ???? Created.  YOUR EMAILNAME HERE
// 2012-??-?? ???? Tested and working.  YOUR EMAILNAME HERE
//---------------------------------------------------------

package edu.uwec.cs245.events;

//DO THE RIGHT IMPORTS.
import java.awt.Point;
import java.awt.event.MouseEvent;

import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class ProcessEvents extends GraphicsProgram {
	
	//-------------------------------------
	// This sets the window size to exactly
	// the size of the grid of cards dealt
	// in the game of Concentration.
	//
	// FOR DEBUGGING:  100 pixels has been
	// added to the height to make room for
	// a debugging label to be displayed.
	// You can remove the "100 + " and
	// remove or comment out the label code
	// once the game is working.
	//
	// DO NOT MOVE OR DELETE THIS CODE.
	//-------------------------------------
	public static final int APPLICATION_WIDTH  = Deck_Class.COLS_DEALT * Card_Class.CARD_WIDTH_PIXELS;
	public static final int APPLICATION_HEIGHT = Deck_Class.ROWS_DEALT * Card_Class.CARD_HEIGHT_PIXELS;
	
	//--------------------
	// Instance variables.
	//--------------------
	// -------------------------------------
	// Get the current GCanvas object
	// so we can pass it to the game object,
	// which needs it in order to draw
	// the card image objects onto it.
	// -------------------------------------
	private GCanvas canvas = this.getGCanvas();
	
	//----------------------------------------
	// Construct a game object that implements
	// the game Concentration.  Pass it the
	// canvas so the game can draw cards on
	// the canvas.  (The canvas is private to
	// this ProcessEvents class.)
	//----------------------------------------
	Concentration_Class concentrationGame = new Concentration_Class(canvas);

	//------------------------------------
	// A label object that's created and
	// put on the canvas for debugging.
	// To see it, you'll need to adjust
	// the screen height above so it's
	// larger than needed to hold the
	// grid of cards.
	//------------------------------------
	GLabel debuggingLabel = new GLabel("Debugging info will appear here.", 100, 435);
	
	
	//------------------
	// Instance methods.
	//------------------
	// --------------------------------------------
	// init() prepares the program to listen for
	// events.  It also places a GLabel object on
	// the canvas that will display what mouse
	// events are being processed by the program
	// for debugging purposes.  The label can be
	// removed once the program is working.
	//
	// 2012-03-15 0529 Created. RICHAG
	// 2012-03-20 1600 Tested and working. FORSTEZT
	// --------------------------------------------
	public void init() {

		//-----------------------------------------
		// Set up and display the debugging GLabel.
		//-----------------------------------------
		//canvas.add(debuggingLabel);
		//----------------------------
		// Tell the concentrationGame
		// object to start a new game.
		//----------------------------
		
		concentrationGame.startANewGame();
		//--------------------------------
		// Do you hear any squeaky things?
		//--------------------------------
		
		// --- YOUR CODE GOES HERE ---
		addMouseListeners();
		
	} // end of init() method

	
	
	//--------------------------------------------
	// mouseReleased
	//
	// 2012-03-15 0905 Created. RICHAG
	// 2012-03-20 1600 Tested and working. FORSTEZT
	//--------------------------------------------
	public void mouseReleased(MouseEvent e) {
		System.out.println("mouseReleased");
		int x = e.getX();
		int y = e.getY();
		GPoint currPoint = new GPoint(x, y);
		
		//------------------------------
		// Compute a name for the object
		// that we can display in a
		// debug message.
		//------------------------------
		String currTargetName = nameOfGObjectAtPoint(currPoint);

		//this.debuggingLabel.setLabel("mousePressed on " + currTargetName + " at "
		//		+ currPoint.getX() + ", " + currPoint.getY() + ".");

		//--------------------------------
		// If the canvas or the label was
		// clicked, just ignore the click.
		//--------------------------------

		GObject currGObjectPressed = canvas.getElementAt(currPoint);
		if (currGObjectPressed == null) return;  // The canvas was clicked.
		if (currGObjectPressed == debuggingLabel) return; //The label was clicked
		
		//---------------------------
		// Handle the clicked object.
		//---------------------------
		
		// --- YOUR CODE GOES HERE ---
		try {
			concentrationGame.handleCardClick(canvas.getElementAt(currPoint));
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


	} // end of mouseReleased



	// ------------------------------------------
	// nameOfGObjectAtPoint returns the name of
	// the on-screen GObject that is currently at
	// the specified point.  This string is
	// displayed in the debugging label.
	//
	// 2012-03-15 0529 Created. RICHAG
	// 2012-03-15 0905 Tested and working. RICHAG
	// ------------------------------------------
	private String nameOfGObjectAtPoint(GPoint p) {

		// --------------------
		// Ask for the element
		// (object) at point p.
		// ---------------------
		Object gobj = getElementAt(p.getX(), p.getY());

		//-------------------------------------------------
		// Determine what was clicked.  Yes, we really need
		// to use == here to compare object ADDRESSES.
		//
		// "instanceof" is a boolean operator that returns
		// true if a specified object is an instance of a
		// specified class.  Assuming all of the GImages in
		// this program are cards, we can use it to
		// identify card image objects.
		//-------------------------------------------------
		if (gobj == this.debuggingLabel) return "the label";
		if (gobj instanceof GImage) return "the card";
		return "the canvas";  // It's none of the above.

	} // end of method nameOfObjectAtPoint
	
	
	//---------------------------------------
	// main
	//
	// This main method starts asynchronous
	// event processing and calls the init()
	// and run() methods if they exist.
	//
	// NOTE:  DO NOT MODIFY OR DELETE THIS
	//        METHOD.
	//---------------------------------------
	public static void main(String[] args) {
		new ProcessEvents().start(args);

	}

} // end of class ProcessEvents
