package edu.uwec.cs.username.cars;

public class Main {

	/*******************************************************
	 * @author
	 ******************************************************/
	public static void main(String[] args) {
		
		//-------------------------------------------
		// Construct an initial CarLot object named
		// currentCars.  The CarLot constructor loads
		// the new CarLot with the car data from the
		// specified data file.
		//-------------------------------------------
		CarLot currentCars = new CarLot("cars.txt");
		
		//-----------------------------------------------------
		// This main program sends messages to the currentCars
		// object, invoking the methods in it that produce the
		// specified reports.  (Of course, you need to write
		// those methods!  Those methods are methods within the
		// CarLot class, of course, not methods within this
		// Main class.  So write them in that separate CarLot
		// class within this project.
		//
		// As you write each method, test it thoroughly.  It's
		// best to test them one at a time, not all at once.
		//-----------------------------------------------------
		
		// ------------------------------------------------
		// Task 1: Print all the cars currently in the lot.
		// ------------------------------------------------
		currentCars.printAllCars("current");
	
		// ----------------------------------------------
		// Task 2: Print all of the red cars.
		// This includes any car with "red" in its color,
		// such as "Red and White", or "Light Red".
		// ----------------------------------------------
		currentCars.printRedCars();
		
		// --------------------------------------------
		// Task 3: Print all Ford cars made after 1997.
		// --------------------------------------------
		currentCars.printFordsAfter1997();
		
		// ---------------------------------------
		// Task 4: Print both the cheapest and the
		//         most expensive cars in stock.
		// ---------------------------------------
		currentCars.printLeastAndMostExpensiveCars();
	
		// -------------------------------------------------------
		// Task 5:  Print all cars having the Make with the fewest
		// cars in stock.  For example, if there are 8 Fords,
		// 3 Chevys, and 5 Toyotas, print all of the Chevys.
		//
		// This task is the most challenging, because it involves
		// performing some subtasks in order to determine which
		// make of car should be printed.
		// -------------------------------------------------------
		currentCars.printLowStockMake();

		// ----------------------------------------------------------
		// Task 6:  Print all the junkers.
		//
		// Do this the easy way, in two steps:
		//
		// 6a:  Construct a new CarLot object named "junkers" that's
		//      loaded with the car data from the file "junkers.txt".
		//-----------------------------------------------------------

		// Write your code HERE that constructs a "junkers" object,
		// filling the object with data from the "junkers.txt" file.
		CarLot junkers = new CarLot("junkers.txt");
		
		// ----------------------------------------------------------
		// 6b:  Now print all the junkers by sending a message to the
		//      junkers object to do so using a method that a CarLot
		//      already has, because you wrote it earlier.
		//-----------------------------------------------------------

		// Write your code HERE that tells the junkers object to print
		// all of the junkers.
		junkers.printAllCars("current");


	} // End of function "main"

} // End of class "Main"