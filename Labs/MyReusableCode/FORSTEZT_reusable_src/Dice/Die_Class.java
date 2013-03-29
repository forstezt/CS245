package Dice;

import java.util.ArrayList;
import java.util.Random;

import edu.uwec.cs245.RandomNumbers.MyRandomClass;

public class Die_Class {

	private ArrayList<Die_Side_Class> sides;
	private int topSideIndex;
	
	public ArrayList<Die_Side_Class> getSides() {
		return sides;
	}

	public void setSides(ArrayList<Die_Side_Class> sides) {
		this.sides = sides;
	}

	public int getTopSideIndex() {
		return topSideIndex;
	}

	public void setTopSideIndex(int topSideIndex) {
		this.topSideIndex = topSideIndex;
	}

	public Die_Class() {
		sides = new ArrayList<Die_Side_Class>();
	}

	//-------------------------------------------------------------
	// 2012-02-24 1112 Created. FORSTEZT
	// 2012-03-02 1018 Tested and working. FORSTEZT
	// ------------------------------------------------------------
	public void addSide(Die_Side_Class dieSideObj) {
		sides.add(dieSideObj);
	}

	//-------------------------------------------------------------
	// 2012-02-24 1112 Created. FORSTEZT
	// 2012-03-02 1018 Tested and working. FORSTEZT
	// ------------------------------------------------------------
	public void roll() {
		if (sides.size() >= 2) {
			topSideIndex = MyRandomClass.randomInt(0, (sides.size() - 1));
			
		} else {
			System.out
					.println("You must first add at least 2 sides to the die.");
		}
	}

	//-------------------------------------------------------------
	// 2012-02-24 1112 Created. FORSTEZT
	// 2012-03-02 1018 Tested and working. FORSTEZT
	// ------------------------------------------------------------
	public Die_Side_Class getTopSide() {
		return sides.get(topSideIndex);
	}

	public void displayTopSideAsImage() {
		this.displayTopSideAsString();
	}

	//-------------------------------------------------------------
	// 2012-02-24 1112 Created. FORSTEZT
	// 2012-03-02 1018 Tested and working. FORSTEZT
	// ------------------------------------------------------------
	public void displayTopSideAsString() {
		System.out.println(sides.get(topSideIndex).getString_image());
	}

	//-------------------------------------------------------------
	// 2012-02-24 1112 Created. FORSTEZT
	// 2012-03-02 1018 Tested and working. FORSTEZT
	// ------------------------------------------------------------
	public void displayTopSideIntValue() {
		System.out.println(sides.get(topSideIndex).getIntValue());
	}

	//-------------------------------------------------------------
	// 2012-02-24 1112 Created. FORSTEZT
	// 2012-03-02 1018 Tested and working. FORSTEZT
	// ------------------------------------------------------------
	public void displayTopSideRealValue() {
		System.out.println(sides.get(topSideIndex).getRealValue());
	}
}
