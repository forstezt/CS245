package InterfacePractice;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Create some artists
				Artist a1 = new Artist("Miller", "Ryan");		// Guster
				Artist a2 = new Artist("Flowers", "Brandon");	// The Killers
				Artist a3 = new Artist("Aubert", "Brian");		// Silversun Pickups
				
				// Create some songs and attach them to the artists
				// note all song times are made up and some songs are 
				// made up covers to help illustrate the compateTo() functionality
				Song s1 = new Song("Amsterdam", a1, 200); // live version
				a1.addSong(s1);
				
				Song s2 = new Song("One Man Wrecking Machine", a1, 300);
				a1.addSong(s2);
				
				Song s3 = new Song("On Top", a2, 200);
				a2.addSong(s3);
				
				Song s4 = new Song("Substitution", a3, 200);
				a3.addSong(s4);
				
				Song s5 = new Song("On the Ocean", a1, 100);
				a1.addSong(s5);
				
				Song s6 = new Song("Jenny Was a Friend of Mine", a2, 500);
				a1.addSong(s6);
				
				Song s7 = new Song("Amsterdam", a1, 140); // album version
				a1.addSong(s7);
				
				Song s8 = new Song("Dustland Fairytale", a2, 100);
				a1.addSong(s8);
				
				Song s9 = new Song("Mr. Brightside", a2, 150);
				a1.addSong(s9);
				
				Song s10 = new Song("Mr. Brightside", a3, 150);
				a1.addSong(s10);
				
				// Create a playlist
				Playlist pl1 = new Playlist("MyPlaylist");
				pl1.addSong(s1);
				pl1.addSong(s2);
				pl1.addSong(s3);
				pl1.addSong(s4);
				pl1.addSong(s5);
				pl1.addSong(s6);
				pl1.addSong(s7);
				pl1.addSong(s8);
				pl1.addSong(s9);
				pl1.addSong(s10);



				// Display the original playlist
				System.out.println(pl1);
				
				// Ask some questions
				System.out.println(pl1.contains(s2));
				System.out.println(pl1.contains(new Song("One Man Wrecking Machine", a1, 300)));
				System.out.println(pl1.contains(new Song("Dustland Fairytale", new Artist("Flowers", "Brandon"), 100)));
				
				System.out.println("Total Running Time: " + pl1.getTotalPlayTime() + "\n");
				
				// Sort the playlist and display the result
				pl1.sortBySong();
				System.out.println(pl1);
				
				Artist.testCompareTo();
				Artist.testAddSong();
				Artist.testEquals();
	}

}
