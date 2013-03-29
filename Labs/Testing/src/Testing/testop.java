package Testing;

import java.util.Scanner;

public class testop {
	public static void main(String args[]) {
		System.out.println("Standard output Stream message");
		System.err.println("Standard error stream message");
		
		
		
		Scanner in = new Scanner(System.in);
		System.out.println("Name:  ");
		String name = in.next();
		System.out.println("Description:  ");
		String description = in.nextLine();
		System.out.println("Gender:  ");
		String gender = in.next();
		
		
		int i = 0;
		Integer I = (Integer)i;
		
		System.out.println(name + description + gender);
	}
}
