package musicproject01;
import java.util.ArrayList;
import java.util.Scanner;

public class MusicLibraryApp {
    public static void main(String[] args) {
        MusicLibrary library = new MusicLibrary();
        library.loadDataFromDatabase();

        Scanner scanner = new Scanner(System.in);

        while (true) {
         System.out.println("\n--- Music Library Management System ---");
         System.out.println("1. Add Music Entry");
         System.out.println("2. Remove Music Entry");
         System.out.println("3. Search Music Entry");
         System.out.println("4. Update Music Entry");
         System.out.println("5. Display Music Library");
         System.out.println("6. Save Data to File");
         System.out.println("0. Exit");

         System.out.print("Enter your choice: ");
         int choice = scanner.nextInt();
         scanner.nextLine(); // Consume the newline character

         switch (choice) {
             case 1:
                 System.out.print("Enter title: ");
                 String title = scanner.nextLine();
                 System.out.print("Enter artist: ");
                 String artist = scanner.nextLine();
                 System.out.print("Enter album: ");
                 String album = scanner.nextLine();
                 System.out.print("Enter release year: ");
                 int releaseYear = scanner.nextInt();
                 scanner.nextLine(); // Consume the newline character
                 System.out.print("Enter genre: ");
                 String genre = scanner.nextLine();

                 MusicEntry entry = new MusicEntry(title, artist, album, releaseYear, genre);
                 library.addMusicEntry(entry);
                 break;

             case 2:
            	    System.out.print("Enter the ID of the music entry to remove: ");
            	    int idToRemove = scanner.nextInt();
            	    scanner.nextLine(); // Consume the newline character

            	    // Find the entry by ID and remove it
            	    for (MusicEntry e : library.getMusicEntries()) {
            	        if (e.getId() == idToRemove) {
            	            library.removeMusicEntry(e);
            	            System.out.println("Music entry removed.");
            	            break;
            	        }
            	    }
            	    break;


             case 3:
                 System.out.print("Enter a title to search: ");
                 String searchTitle = scanner.nextLine();
                 ArrayList<MusicEntry> searchResults = library.searchByTitle(searchTitle);
                 if (!searchResults.isEmpty()) {
                     System.out.println("Search results:");
                     for (MusicEntry resultEntry : searchResults) {
                         System.out.println(resultEntry.getTitle() + " - " + resultEntry.getArtist() + " - " + resultEntry.getAlbum());
                     }
                 } else {
                     System.out.println("No matching entries found.");
                 }
                 break;

             case 4:
                 // Implement update music entry
                 break;

             case 5:
                 System.out.println("--- Music Library ---");
                 library.displayMusicLibrary();
                 break;

             case 6:
                 library.saveDataToFile();
                 break;

             case 0:
                 library.saveDataToFile(); // Save data before exiting
                 System.out.println("Exiting the Music Library Management System. Goodbye!");
                 System.exit(0);

             default:
                 System.out.println("Invalid choice. Please try again.");
         }
     }
 }
}
