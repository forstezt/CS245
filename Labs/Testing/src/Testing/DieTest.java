package Testing;

import Dice.Dice_Class;
import Dice.Die_Class;
import Dice.Die_Side_Class;

public class DieTest {
	public static void main(String[] args) {
		Die_Side_Class side1 = new Die_Side_Class(1, 1.0, "1");
		Die_Side_Class side2 = new Die_Side_Class(2, 2.0, "2");
		Die_Side_Class side3 = new Die_Side_Class(3, 3.0, "3");
		Die_Side_Class side4 = new Die_Side_Class(4, 4.0, "4");
		Die_Side_Class side5 = new Die_Side_Class(5, 5.0, "5");
		Die_Side_Class side6 = new Die_Side_Class(6, 6.0, "6");
		Die_Side_Class side7 = new Die_Side_Class(7, 7.0, "7");

		
		Die_Class die1 = new Die_Class();
		die1.addSide(side1);
		die1.addSide(side2);
		die1.addSide(side3);
		die1.addSide(side4);
		die1.addSide(side5);
		die1.addSide(side6);
		die1.addSide(side7);
		
		Die_Class die2 = new Die_Class();
		die2.addSide(side1);
		die2.addSide(side2);
		die2.addSide(side3);
		die2.addSide(side4);
		die2.addSide(side5);
		die2.addSide(side6);
		die2.addSide(side7);
		
		Die_Class die3 = new Die_Class();
		die3.addSide(side1);
		die3.addSide(side2);
		die3.addSide(side3);
		die3.addSide(side4);
		die3.addSide(side5);
		die3.addSide(side6);
		die3.addSide(side7);

		
		Dice_Class dice = new Dice_Class();
		dice.addDie(die1);
		dice.addDie(die2);
		dice.addDie(die3);

		
		dice.roll();
		System.out.println(dice.getIntSum());
		dice.displayDiceAsStrings();
	}
	}
}
