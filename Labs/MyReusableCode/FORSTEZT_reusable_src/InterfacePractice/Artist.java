package InterfacePractice;

import java.util.ArrayList;
import java.util.Random;


//-------------------------------------------------------------
//2012-03-02 1130 Created. FORSTEZT
//2012-03-02 1220 Tested and working. FORSTEZT
//-------------------------------------------------------------
public class Artist implements Comparable<Artist> {

	private String lastName;
	private String firstName;
	private ArrayList<Song> songs;

	public Artist(String last, String first) {
		firstName = first;
		lastName = last;
		songs = new ArrayList<Song>();
	}

	public void addSong(Song songName) {
		songs.add(songName);
	}

	public String toString() {
		return (firstName + " " + lastName);
	}

	public boolean equals(Object otherArtist) {
		return (this.firstName.equals(((Artist)otherArtist).firstName))
				&& (this.lastName.equals(((Artist)otherArtist).lastName));
	}

	public int compareTo(Artist otherArtist) {
		int result = this.lastName.compareTo(otherArtist.lastName);
		if (result == 0) {
			result = this.firstName.compareTo(otherArtist.firstName);
		}
		return result;
	}
	
	public static void testCompareTo() {
		System.out.println("Beginning the test of .compareTo...");
		
		System.out.println("Comparing artists Ryan Miller and Ryan Miller...");
		Artist a1 = new Artist("Miller", "Ryan");
		Artist a2 = new Artist("Miller", "Ryan");
		System.out.println("The result is..." + a1.compareTo(a2));
		
		System.out.println("Comparing artists Ryan Miller and Ryan Willer...");
		a1 = new Artist("Miller", "Ryan");
		a2 = new Artist("Willer", "Ryan");
		System.out.println("The result is..." + a1.compareTo(a2));
		
		System.out.println("Comparing artists Ryan Miller and Ryan Ciller...");
		a1 = new Artist("Miller", "Ryan");
		a2 = new Artist("Ciller", "Ryan");
		System.out.println("The result is..." + a1.compareTo(a2));
		
		System.out.println("Test successful... Test complete.");
	}
	
	public static void testEquals() {
		System.out.println("Beginning the test of .equals...");
		System.out.println("Is Ryan Miller equal to Ryan Miller?");
		Artist a1 = new Artist("Miller", "Ryan");
		Artist a2 = new Artist("Miller", "Ryan");
		if (!a1.equals(a2)) {
			System.err.println("They should be equal, but aren't!");
			return;
		} else {
			System.out.println("Yes!");
		}
		
		System.out.println("Is Ryan Miller equal to Ryan Willer?");
		a1 = new Artist("Miller", "Ryan");
		a2 = new Artist("Willer", "Ryan");
		if (a1.equals(a2)) {
			System.err.println("They should be unequal, but aren't!");
			return;
		} else {
			System.out.println("No!");
		}
		System.out.println("Test successful... Test complete.");
	}
	
	public static void testAddSong() {
		System.out.println("Beginning the test of .addSong...");
		Artist a1 = new Artist("Miller", "Ryan");
		System.out.println("The number of songs associated with this artist is:  " + a1.songs.size());
		Song s1 = new Song("Amsterdam", a1, 200);
		a1.addSong(s1);
		if (a1.songs.size() == 1) {
			System.out.println("The number of songs associated with this artist is:  " + a1.songs.size());
		} else {
			System.err.println("There aren't the right amount of songs!");
			return;
		}

		System.out.println("Test successful... Test complete.");

	}
}
