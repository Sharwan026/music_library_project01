package musicproject01;
import java.sql.*;
import java.util.ArrayList;

public class MusicLibrary {
    private ArrayList<MusicEntry> musicEntries;
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/music_library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "sharukkrish";

    public MusicLibrary() {
        musicEntries = new ArrayList<>();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    }

    public void addMusicEntry(MusicEntry entry) {
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(
                     "INSERT INTO music_entries (title, artist, album, release_year, genre) VALUES (?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, entry.getTitle());
            pstmt.setString(2, entry.getArtist());
            pstmt.setString(3, entry.getAlbum());
            pstmt.setInt(4, entry.getReleaseYear());
            pstmt.setString(5, entry.getGenre());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entry.setId(generatedKeys.getInt(1));
                }
            }

            musicEntries.add(entry);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeMusicEntry(MusicEntry entry) {
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement("DELETE FROM music_entries WHERE id = ?")) {

            pstmt.setInt(1, entry.getId());
            pstmt.executeUpdate();

            musicEntries.remove(entry);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MusicEntry> searchByTitle(String title) {
        ArrayList<MusicEntry> result = new ArrayList<>();
        for (MusicEntry entry : musicEntries) {
            if (entry.getTitle().equalsIgnoreCase(title)) {
                result.add(entry);
            }
        }
        return result;
    }

    // Implement other search methods (e.g., by artist, album, genre, release year)

    public void updateMusicEntry(MusicEntry oldEntry, MusicEntry newEntry) {
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(
                     "UPDATE music_entries SET title = ?, artist = ?, album = ?, release_year = ?, genre = ? WHERE id = ?")) {

            pstmt.setString(1, newEntry.getTitle());
            pstmt.setString(2, newEntry.getArtist());
            pstmt.setString(3, newEntry.getAlbum());
            pstmt.setInt(4, newEntry.getReleaseYear());
            pstmt.setString(5, newEntry.getGenre());
            pstmt.setInt(6, oldEntry.getId());

            pstmt.executeUpdate();

            int index = musicEntries.indexOf(oldEntry);
            musicEntries.set(index, newEntry);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayMusicLibrary() {
        for (MusicEntry entry : musicEntries) {
            System.out.println(entry);
        }
    }

    public static int saveDataToFile() {
        return MusicEntry.getTotalEntries();
    }

    public void loadDataFromDatabase() {
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM music_entries")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                String album = rs.getString("album");
                int releaseYear = rs.getInt("release_year");
                String genre = rs.getString("genre");

                MusicEntry entry = new MusicEntry(title, artist, album, releaseYear, genre);
                entry.setId(id); // Set the ID retrieved from the database
                musicEntries.add(entry);
            }

            System.out.println("Data loaded from the database.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	
	}

	
	

	
}
