package edu.uwec.cs.username.cars;

public class Car {

	//-------------------------
	// The properties of a car.
	//-------------------------
	private String make;
	private String model;
	private int    year;
	private String color;
	private double price;
	
	//---------------------------------
	// Constructor Car builds a default
	// car that has no properties.
	//
	// @param NONE
	//---------------------------------
	public Car() {
		// No code needed here.
	}
	
	//-----------------------------------------------
	// Create your property-value getters and setters
	// below.  Here are a pair to get you started.
	//-----------------------------------------------
	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}


	//----------------------------------------
	// printCar prints all the data for a Car.
	// This method is complete and it works.
	//----------------------------------------
	public void printCar() {
		
		System.out.println();
		System.out.println("Make:  "  + make );
		System.out.println("Model: "  + model);
		System.out.println("Year:  "  + year );
		System.out.println("Color: "  + color);
		System.out.println("Price: $" + price);
	}
}
