import static edu.uwec.FORSTEZT.ImprovedJava.ij.*;

public class TestImprovedJava {

	public static void main(String[] args) {

		println(NEWLINE + "This test program is running.");

		if (NOT(2 + 2 == 5)) {

			print("Line 1:  The world says, " + QUOTE + "Hello!"
					+ QUOTE);

			print(NEWLINE + "Line 2:  This is on line 2!" + NEWLINE);

			println("Line 3:  The world says, " + Quoted("Goodbye!"));
			println();
			
			println("Test complete.");

		}

	} // End of main method

}
