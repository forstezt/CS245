package Dice;

import java.util.ArrayList;

public class Dice_Class {

	private ArrayList<Die_Class> dieArray;

	public Dice_Class() {
		dieArray = new ArrayList<Die_Class>();
	}

	public void addDie(Die_Class dieObj) {
		dieArray.add(dieObj);
	}

	public int dieCount() {
		return dieArray.size();
	}

	//-------------------------------------------------------------
	// 2012-02-24 1112 Created. FORSTEZT
	// 2012-03-02 1018 Tested and working. FORSTEZT
	// ------------------------------------------------------------
	public void roll() {
		if (dieArray.size() > 0) {
			for (Die_Class die : dieArray) {
				die.roll();
			}
		} else {
			System.out
					.println("You must add a die to your collection before you can roll.");
		}
	}

	//-------------------------------------------------------------
	// 2012-02-24 1112 Created. FORSTEZT
	// 2012-03-02 1018 Tested and working. FORSTEZT
	// ------------------------------------------------------------
	public int getIntSum() {
		int sum = 0;

		for (Die_Class die : dieArray) {
			ArrayList<Die_Side_Class> sides = die.getSides();
			int topSideIndex = die.getTopSideIndex();
			sum += sides.get(topSideIndex).getIntValue();
		}

		return sum;
	}

	//-------------------------------------------------------------
	// 2012-02-24 1112 Created. FORSTEZT
	// 2012-03-02 1018 Tested and working. FORSTEZT
	// ------------------------------------------------------------
	public double getRealSum() {
		double sum = this.getIntSum();
		return sum;
	}

	
	public void displayDiceAsImages() {
		this.displayDiceAsStrings();
	}

	public void displayDiceAsStrings() {
		for (Die_Class die : dieArray) {
			ArrayList<Die_Side_Class> sides = die.getSides();
			int topSideIndex = die.getTopSideIndex();
			System.out.println(sides.get(topSideIndex).getString_image());
		}
	}

	//-------------------------------------------------------------
	// 2012-02-24 1112 Created. FORSTEZT
	// 2012-03-02 1018 Tested and working. FORSTEZT
	// ------------------------------------------------------------
	public void displayDiceAsIntValues() {
		for (Die_Class die : dieArray) {
			ArrayList<Die_Side_Class> sides = die.getSides();
			int topSideIndex = die.getTopSideIndex();
			System.out.println(sides.get(topSideIndex).getIntValue());
		}
	}

	//-------------------------------------------------------------
	// 2012-02-24 1112 Created. FORSTEZT
	// 2012-03-02 1018 Tested and working. FORSTEZT
	// ------------------------------------------------------------
	public void displayDiceAsRealValues() {
		for (Die_Class die : dieArray) {
			ArrayList<Die_Side_Class> sides = die.getSides();
			int topSideIndex = die.getTopSideIndex();
			System.out.println(sides.get(topSideIndex).getRealValue());
		}
	}
}
