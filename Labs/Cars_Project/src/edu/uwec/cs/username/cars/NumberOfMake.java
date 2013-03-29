package edu.uwec.cs.username.cars;

public class NumberOfMake {

	private String nameOfMake;
	private int numberOfMake;
	
	public NumberOfMake(String make, int number) {
		nameOfMake = make;
		numberOfMake = number;
		
	}
	
	public int getNumberOfMake() {
		return numberOfMake;
	}
	
	public String getNameOfMake() {
		return nameOfMake;
	}
}
