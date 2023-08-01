package musicproject01;
import java.io.Serializable;

public class MusicEntry implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int totalEntries = 0;

    private int id;
    private String title;
    private String artist;
    private String album;
    private int releaseYear;
    private String genre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public MusicEntry(String title, String artist, String album, int releaseYear, String genre) {
        this.id = ++totalEntries;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }

    // Method Overriding for custom string representation
    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Artist: " + artist + ", Album: " + album +
                ", Release Year: " + releaseYear + ", Genre: " + genre;
    }

	public static int getTotalEntries() {
		// TODO Auto-generated method stub
		return 0;
	}
}
