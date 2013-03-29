package InterfacePractice;


//-------------------------------------------------------------
//2012-03-02 1130 Created. FORSTEZT
//2012-03-02 1220 Tested and working. FORSTEZT
//-------------------------------------------------------------
public class Song implements Comparable<Song> {

	private String title;
	private Artist artist;
	private int runningTime;

	public Song(String songTitle, Artist songArtist, int songTime) {
		title = songTitle;
		artist = songArtist;
		runningTime = songTime;
	}

	public String toString() {
		return title + "\t" + "(" + runningTime + ")" + "\t" + artist.toString();
	}

	public boolean equals(Object otherSong) {
		return (this.title.equals(((Song)otherSong).title))
				&& (this.artist.equals(((Song)otherSong).artist));
	}

	public int compareTo(Song otherSong) {
		int result = this.title.compareTo(otherSong.title);
		if (result == 0) {
			result = this.artist.compareTo(otherSong.artist);
		}
		return result;
	}
	
	public int getRunningTime() {
		return runningTime;
	}
}
