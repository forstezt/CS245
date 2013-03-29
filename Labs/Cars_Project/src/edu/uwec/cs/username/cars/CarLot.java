package edu.uwec.cs.username.cars;

import java.io.*;
import java.util.Scanner;

//-----------------------------------------------------
// Class CarLot stores all the cars in a given car lot
// in a private array named CarList that can hold a
// maximum of 50 cars.  The array may not be completely
// full of data, so a separate variable keeps track of
// how many cars are actually in the CarList array.
//
// Each element of the array is an instance of the Car
// class.
//
// Write public methods to examine the CarList array
// and produce the desired inventory reports.
//-----------------------------------------------------
public class CarLot {

	// -----------------------------------------
	// Define and initialize the carList array.
	// -----------------------------------------
	private final int MAX_CARS_IN_LOT = 50;
	Car[] carList = new Car[MAX_CARS_IN_LOT];
	int totalCarsInStock = 0; // The actual number of cars in carList.

	// ------------------------------------------
	// This constructor constructs a CarLot
	// object and fills it with data from
	// the data file whose name is dataFileName.
	//
	// NOTE: This works; there is no need to
	// modify it.
	// ------------------------------------------
	public CarLot(String dataFileName) {
		loadFileData(dataFileName);
	}

	// -------------------------------------------------
	// printAllCars prints all the cars in this CarLot.
	//
	// @param which_lot: a string that says which
	// car lot this report is about, such as
	// "dealership" or "junkers". The string is
	// simply displayed in the report heading.
	// -------------------------------------------------
	public void printAllCars(String which_lot) {

		System.out.println("All cars in the " + which_lot + " lot:");

		// Your code goes here.
		int totalCarsPrinted = 0;

		for (int i = 0; i < totalCarsInStock; i++) {
			if (carList[i] != null) {
				carList[i].printCar();
				totalCarsPrinted = totalCarsPrinted + 1;
			}
		}

		System.out.println("\n" + totalCarsPrinted + " cars printed.\n");

	} // End of method printAllCars

	// -------------------------------------------------
	// printAllCars prints all the cars in this CarLot.
	// -------------------------------------------------
	public void printRedCars() {

		System.out.println("\nAll red cars:");

		// Your code goes here.
		int totalCarsPrinted = 0;
		String mostPopularColor = "red";

		for (int i = 0; i < totalCarsInStock; i++) {

			if ((carList[i] != null)
					&& (carList[i].getColor().toLowerCase()
							.contains(mostPopularColor))) {
				carList[i].printCar();
				totalCarsPrinted = totalCarsPrinted + 1;
			}
		}

		System.out.println("\n" + totalCarsPrinted + " cars printed.\n");

	}// End of method printRedCars

	// --------------------------------------------
	// Do the same below by creating the following
	// methods yourself:
	// printFordsAfter1997()
	// printCheapExpensiveCars()
	// --------------------------------------------

	// Your remaining methods go here.

	public void printFordsAfter1997() {

		System.out.println("\nAll Fords after 1997:");

		// Your code goes here.
		int totalCarsPrinted = 0;
		String desiredMake = "Ford";
		
		for (int i = 0; i < totalCarsInStock; i++) {
			if ((carList[i] != null) && (carList[i].getYear() > 1997) && (carList[i].getMake().equalsIgnoreCase(desiredMake))) {
				carList[i].printCar();
				totalCarsPrinted = totalCarsPrinted + 1;
			}
		}

		System.out.println("\n" + totalCarsPrinted + " cars printed.\n");
	}

	public void printLeastAndMostExpensiveCars() {

		System.out.println("\nThe least and most expensive cars:");

		// Your code goes here.
		int totalCarsPrinted = 0;
		Car leastExpensiveCar = carList[0];
		Car mostExpensiveCar = carList[0];

		for (int i = 1; i < totalCarsInStock; i++) {
			if (carList[i] != null) {
				if (carList[i].getPrice() <= leastExpensiveCar.getPrice()) {
					leastExpensiveCar = carList[i];

				}
			}
		}

		for (int i = 0; i < totalCarsInStock; i++) {
			if (carList[i] != null) {
				if (carList[i].getPrice() == leastExpensiveCar.getPrice()) {
					carList[i].printCar();
					totalCarsPrinted++;
				}
			}
		}

		for (int i = 1; i < totalCarsInStock; i++) {
			if (carList[i] != null) {
				if (carList[i].getPrice() >= mostExpensiveCar.getPrice()) {
					mostExpensiveCar = carList[i];

				}
			}
		}

		for (int i = 0; i < totalCarsInStock; i++) {
			if (carList[i] != null) {
				if (carList[i].getPrice() == mostExpensiveCar.getPrice()) {
					carList[i].printCar();
					totalCarsPrinted++;

				}
			}
		}

		System.out.println("\n" + totalCarsPrinted + " cars printed.\n");
	}

	public void printLowStockMake() {

		System.out.println("\nThe Make currently with the fewest cars:");

		// --------------------------------
		// Add up the counts of ALL makes
		// and store them in a new array.
		// --------------------------------
		
		// Your code goes here.
		NumberOfMake[] makeNumbers = new NumberOfMake[totalCarsInStock];
		
		String makesAccountedFor = "";
		for (int i = 0; i < totalCarsInStock; i++) {
			if (carList[i] != null) {
				if (!makesAccountedFor.contains(carList[i].getMake())) {
					makesAccountedFor += (carList[i].getMake() + " ");
				}
			}
		}
		String currentMake;
		int numberOfCarsOfCurrentMake;
		Scanner in = new Scanner(makesAccountedFor);
		int makeNumbersIndex = 0;
		while (in.hasNext()) {
			numberOfCarsOfCurrentMake = 0;
			currentMake = in.next();
			for (int i = 0; i < totalCarsInStock; i++) {
				if (carList[i] != null) {
					if (carList[i].getMake().equals(currentMake)) {
						numberOfCarsOfCurrentMake++;
					}
				}
			}
			makeNumbers[makeNumbersIndex] = new NumberOfMake(currentMake, numberOfCarsOfCurrentMake);
			makeNumbersIndex++;
		}
		// ------------------------------------------
		// Determine the make with the fewest cars.
		// ------------------------------------------

		// Your code goes here.
		NumberOfMake lowStockMake = makeNumbers[0];
		for (int i = 1; i < makeNumbers.length; i++) {
			if (makeNumbers[i] != null) {
				if (makeNumbers[i].getNumberOfMake() <= lowStockMake.getNumberOfMake()) {
					lowStockMake = makeNumbers[i];
				}
			}
		}
		// -------------------------------------
		// Now print all the cars of that make.
		// -------------------------------------

		// Your code goes here.
		int totalCarsPrinted = 0;
		
		for (int i = 0; i < totalCarsInStock; i++) {
			if (carList[i] != null) {
				if (carList[i].getMake().toLowerCase().equals(lowStockMake.getNameOfMake().toLowerCase())) {
					carList[i].printCar();
					totalCarsPrinted++;
				}
			}
		}
		
		System.out.println("\n" + totalCarsPrinted + " cars printed.\n");

	} // End of method printLowStockMake

	// -----------------------------------------------------------------------
	// This method is given completely, and it works. Do not modify it.
	//
	// loadFileData loads and returns a CarList object with car data that has
	// been read from a specified data file.
	//
	// The data format is pipe-delimited data (that is, the data separator is
	// a "|" (vertical bar) character, usually called a "pipe". The fields of
	// car data appear in this order on each line of the file:
	//
	// Make|Model|Year|Color|Price
	//
	// ...like this:
	//
	// Honda|Civic|2001|Red|4999.99
	//
	// We use the String method ".split" to split the pipe-delimited data
	// into separate String elements, which it stores in an array. We
	// place that data in an array named "separated_property_strings",
	// like this:
	//
	// String[] separated_property_strings = null;
	// ...read a line of data into "line" here...
	// separated_property_strings = line.split("\\|");
	//
	// Once the line of data is split up into the separated_property_strings
	// array (the strings are the properties of a car), we then create a
	// new Car object and store that data into it, converting any numeric
	// properties of the car (year, price) from string form to numeric form.
	// Finally, we append that Car object to the CarLists array and count it,
	// so that we know exactly how many cars we have stored in the CarList
	// array.
	// -----------------------------------------------------------------------
	private void loadFileData(String filename) {

		System.out.println("Loading cars from file \"" + filename + "\"...");

		File f = new File(filename); // The file of data to be loaded.

		// ----------------------------------------------------------
		// Array "separated_property_strings" will hold the separated
		// strings of car data after the "split" function splits them
		// using their separating "pipe" delimiters.
		//
		// String "line" will hold the current line of text that has
		// been read from the data file.
		// ----------------------------------------------------------
		String[] separated_property_strings = null;
		String line = null;

		// ----------------------------------------
		// Load the data file, being careful to
		// catch and report any errors that occur.
		// ----------------------------------------
		try {
			// --------------------------------
			// Create a BufferedReader to read
			// the data file line by line.
			// --------------------------------
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			while (true) { // This is a central-exit loop.

				// ---------------------------------------------------
				// Try to read a line of text. If the resulting line
				// is null, then we've read all the data, so we break
				// out of this loop.
				// ---------------------------------------------------
				line = br.readLine(); // Read a line of data.

				if (line == null) {
					break; // Exit if there's no more data.
				}

				// ----------------------------------------
				// We have a line of data. Split the line
				// into an array of strings based on the
				// delimiter between data values, which in
				// this case is a vertical bar character,
				// also called a pipe: ("|")
				// ----------------------------------------
				separated_property_strings = line.split("\\|");

				// ----------------------------------------
				// Now we have the current car's properties
				// separated into strings in the elements
				// of separated_property_strings, so we
				// create a Car object to hold those
				// properties:
				//
				// make : String
				// model : String
				// year : integer
				// color : String
				// price : Double
				// ----------------------------------------
				Car currentCar = new Car();

				// ----------------------------------------
				// Transfer the separated property strings
				// into the Car object, converting the
				// properties that should be numeric to
				// their appropriate numeric form.
				// ----------------------------------------
				currentCar.setMake(separated_property_strings[0]);
				currentCar.setModel(separated_property_strings[1]);
				currentCar.setYear(Integer
						.parseInt(separated_property_strings[2]));
				currentCar.setColor(separated_property_strings[3]);
				currentCar.setPrice(Double
						.parseDouble(separated_property_strings[4]));

				// currentCar.printCar(); // FOR DEBUGGING

				// -----------------------------------------------------
				// The car object now holds all of its properties, so
				// we use totalCarsInStock as the index into carList
				// where we store the currentCar. We then postincrement
				// totalCarsInStock so that it indicates how many
				// cars are now in the carList array.
				//
				// In case there are somehow more than 50 cars in the
				// data file, which would be trouble, we skip any cars
				// that exist past the 50th one so that we don't try to
				// insert a car into the 51st element of carList,
				// because that element doesn't exist!
				// -----------------------------------------------------
				if (totalCarsInStock < MAX_CARS_IN_LOT) {
					carList[totalCarsInStock++] = currentCar;
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		System.out.println(totalCarsInStock + " cars were loaded from file \""
				+ filename + "\".\n");
	}

} // End of class CarLot