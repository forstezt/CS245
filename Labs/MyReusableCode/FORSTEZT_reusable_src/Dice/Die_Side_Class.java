package Dice;

public class Die_Side_Class {

	private int intValue;
	private double realValue;
	private String string_image;
	private String numeric_image;
	private Object digital_image;

	// ------------------------------------------
	// Getters and Setters
	// ------------------------------------------

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public double getRealValue() {
		return realValue;
	}

	public void setRealValue(double realValue) {
		this.realValue = realValue;
	}

	public String getString_image() {
		return string_image;
	}

	public void setString_image(String string_image) {
		this.string_image = string_image;
	}

	public String getNumeric_image() {
		return numeric_image;
	}

	public void setNumeric_image(String numeric_image) {
		this.numeric_image = numeric_image;
	}

	public Object getDigital_image() {
		return digital_image;
	}

	public void setDigital_image(Object digital_image) {
		this.digital_image = digital_image;
	}

	// -----------------------------------------------------
	// Constructors
	// -----------------------------------------------------

	//-------------------------------------------------------------
	// 2012-02-24 1112 Created. FORSTEZT
	// 2012-03-02 1018 Tested and working. FORSTEZT
	// ------------------------------------------------------------
	public Die_Side_Class() {
		intValue = 1;
		realValue = 1.0;
		string_image = "---------\n|       |\n|   O   |\n|       |\n---------";
	}

	//-------------------------------------------------------------
	// 2012-02-24 1112 Created. FORSTEZT
	// 2012-03-02 1018 Tested and working. FORSTEZT
	// ------------------------------------------------------------
	public Die_Side_Class(int integerValue, double doubleValue,
			String numericImage) {
		if (integerValue < 0) {
			System.out.println("Please enter a valid side number.");
			return;
		}
		intValue = integerValue;
		realValue = doubleValue;
		numeric_image = numericImage;

		if (integerValue == 1) {
			string_image = "---------\n|       |\n|   O   |\n|       |\n---------";
		} else if (integerValue == 2) {
			string_image = "---------\n|       |\n| O   O |\n|       |\n---------";

		} else if (integerValue == 3) {
			string_image = "---------\n| O   O |\n|       |\n|   O   |\n---------";

		} else if (integerValue == 4) {
			string_image = "---------\n| O   O |\n|       |\n| O   O |\n---------";

		} else if (integerValue == 5) {
			string_image = "---------\n| O   O |\n|   O   |\n| O   O |\n---------";

		} else if (integerValue == 6) {
			string_image = "---------\n| O   O |\n| O   O |\n| O   O |\n---------";

		} else {
			string_image = "---------\n|       |\n|   " + integerValue
					+ "   |\n|       |\n---------";

		}

	}

}
