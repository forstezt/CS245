package InterfacePractice;

import java.util.ArrayList;
import java.util.Collections;


//-------------------------------------------------------------
// 2012-03-02 1130 Created. FORSTEZT
// 2012-03-02 1220 Tested and working. FORSTEZT
// ------------------------------------------------------------
public class Playlist {
	private String name;
	private ArrayList<Song> songs;
	
	public Playlist(String playlistName) {
		name = playlistName;
		songs = new ArrayList<Song>();
	}
	
	public void addSong(Song song) {
		songs.add(song);
	}
	
	public boolean contains(Song song) {
		return songs.contains(song);
	}
	
	public void sortBySong() {
		if (songs.isEmpty()) {
			System.out.println("You have no songs in your playlist.");
		}
		Collections.sort(songs);
	}
	
	public int getTotalPlayTime() {
		int time = 0;
		for (Song song : songs) {
			time += song.getRunningTime();
		}
		
		return time;
	}
	
	public String toString() {
		String string = name + ": \n";
		for (Song song : songs) {
			string += (song.toString() + "\n");
		}
		
		return string;
	}
}
