package project04;

/**
 * Reads an input file that contains the users selections.
 * Creates an object of type FoothillTunesStore which parses the JSON file via
 * an object of type MillionSongSubset in class cs1c.
 *
 * @author Foothill College, [YOUR NAME HERE]
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import cs1c.SongEntry;

public class MyTunes
{
   private static final int QUIT = 0;
   private static final int HELP_MENU = 1;
   private static final int LIST_SONG_TITLES = 2;
   private static final int LIST_SONGS_BY_GENRE = 3;
   private static final int BUY_SONG_TITLE = 4;
   private static final int CREATE_RANDOM_PLAYLIST = 5;
   private static final int ADD_SONG_TO_JUKEBOX = 6;
   private static final int ADD_VIP_SONG_TO_JUKEBOX = 7;
   private static final int GET_PURCHASED_IDs = 8;
   private static final int FIND_SONG_BY_ID = 9; /* new feature for assignment 4 */
   private static final int FIND_GENRE = 10; /* new feature for assignment 4 */

   private static final boolean ENABLE_DEBUG_SELECTION = false;

   /*
    * Once a user purchases a song, they can make various selections such as add
    * the song to their play list, play the song, etc. We'll gradually give the
    * user more options.
    */
   private ArrayList<SongEntry> purchasedTunes;

   /*
    * A hash table called that keeps track of all the purchased songs.
    */
   private FHhashQPwFind<Integer, SongCompInt> tableOfSongIDs = new FHhashQPwFind<>(
         null, null);
  

   /*
    * Where all songs from MillionSongSubset are stored.
    */
   private static FoothillTunesStore theStore;

   /*
    * Show all songs that have been purchased.
    */
   public void showLibrary()
   {
      System.out.println(purchasedTunes);
   }

   /*
    * Selections user can make.
    */
   public static void printMenu()
   {
      System.out.println("\nMenu:");
      System.out.println("0. Quit");
      System.out.println("1. Output this menu");
      System.out.println("2. Show all song titles");
      System.out.println("3. Show all songs by genre ");
      System.out.println("4. Buy songs by title");
      System.out.println("5. Create a playlist");
      System.out.println("6. Play my song title (regular priority)");
      System.out.println("7. Play my song title first (high priority)");
      System.out.println("9. Find a song by id");
      System.out.println("10. Find a song by genre");
      System.out.println();
   }

   /*
    * Reads the tested selection file.
    */
   public static ArrayList<String> readTestFile(String filename)
   {
      ArrayList<String> selections = new ArrayList<String>();
      try
      {
         // --------------------
         // parse the test file
         FileReader fileReader = new FileReader(filename);

         BufferedReader br = new BufferedReader(fileReader);
         String line;
         while ((line = br.readLine()) != null)
         {
            selections.add(line);
         }
         fileReader.close();
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
      } catch (IOException e)
      {
         e.printStackTrace();
      }
      return selections;
   }

   /*
    * Tests selections.
    */
   public static void main(String[] args)
   {
      final String jsonFilePath = "resources/music_genre_subset_wIDs.json";
      final String tunesTestFilePath = "resources/test_tunes.txt";
      final String testJsonPath = "resources/test.json";

      theStore = new FoothillTunesStore(jsonFilePath);
     
      ArrayList<SongEntry> storeTitles = theStore.getTitles();
      System.out.println("Welcome! We have over " + storeTitles.size()
            + " in FoothillTunes Jukebox!");

      MyTunes.printMenu();

      MyTunes personalTunes = new MyTunes();

      ArrayList<String> linesInFile = MyTunes.readTestFile(tunesTestFilePath);
      int selection = -1;
      long startTime, estimatedTime;

      for (int i = 0; i < linesInFile.size() && selection != QUIT; /*
                                                                    * no need to
                                                                    * increment
                                                                    * here
                                                                    */)
      {
         String line = linesInFile.get(i++);
         String[] tokens = line.split(" ");
         if (line.contains("selection"))
            selection = Integer.parseInt(tokens[1]);
         else
         {
            // invalid selection format
            System.out.printf("WARNING: Invalid selection %d at line %d\n",
                  selection, i);
            continue;
         }

         if (ENABLE_DEBUG_SELECTION)
            System.out.println("\nselected option:" + selection);
         switch (selection)
         {
         case QUIT:
            break;
         case HELP_MENU:
            MyTunes.printMenu();
            break;
         case LIST_SONG_TITLES:
            // System.out.println("Number of titles purchased = "
            // + personalTunes.getPurchasedTunes().size());
            personalTunes.showLibrary();
            break;
         case LIST_SONGS_BY_GENRE:
            // NOTE: method groupSongsByGenre modified to store songs in a genre
            // using a hash table
            theStore.groupSongsByGenre();

            // NOTE: For debugging. Will result in duplicate output.
             theStore.printNumOfSongsInEachGenre();

            break;
         case BUY_SONG_TITLE:
            String title = linesInFile.get(i++);
            if (ENABLE_DEBUG_SELECTION)
               System.out.println("selected song title: " + title);

            // implement searching for songs by title
            ArrayList<SongEntry> searchResult = theStore.buySongByTitle(title);

            

            if (ENABLE_DEBUG_SELECTION)
            {
               System.out.println("Found " + searchResult.size() + " song(s):");
               System.out.println(searchResult);
            }

            // to simplify implementation we will assume first result is what
            // user wants
            // personalTunes.addSongs(searchResult.get(0));

            personalTunes.addIDToTable(searchResult.get(0));

            break;

         case CREATE_RANDOM_PLAYLIST:
            /* content from previous project */
            break;
         case ADD_SONG_TO_JUKEBOX:
            /* content from previous project */
            break;
         case ADD_VIP_SONG_TO_JUKEBOX:
            /* content from previous project */
            break;

          case GET_PURCHASED_IDs:
          ArrayList<Integer> idKeys = personalTunes.getTableOfSongIDs()
          .getKeys();
         
          System.out.println("\n All purchased songs by ID:");
          for(Integer x : personalTunes.tableOfSongIDs.keys)
          {
             System.out.print(x + " ");          
             personalTunes.tableOfSongIDs.find(x);
             System.out.println(personalTunes.tableOfSongIDs.find(x));
          }
         
          break;
         
         case FIND_SONG_BY_ID:
         

            int requestedID = Integer.parseInt(linesInFile.get(i++));
            if (ENABLE_DEBUG_SELECTION)
               System.out.println("selected song id: " + requestedID);

            try
            {
               if (personalTunes.getTableOfSongIDs().find(requestedID) != null)
                  System.out.println("line # " + (i)
                        + " requested song ID " + requestedID + " found.");
               else
               {
                  System.out.println("line # " + (i - 1)
                        + " requested song ID " + requestedID + " NOT found.");
               }
            } catch (NoSuchElementException e)
            {
               System.out.println("line # " + (i - 1) + " requested song ID "
                     + requestedID + " NOT found.");
            }

            break;
         
          case FIND_GENRE:
          
          break;
          default:
          System.out.println("ERROR : invalid selection.");
          MyTunes.printMenu();
          break;
         } // switch
      } // for lines in the file
   } // main method

   private FHhashQPwFind<Integer, SongCompInt> getTableOfSongIDs()
   {

      return this.tableOfSongIDs;
   }

   private void addIDToTable(SongEntry songEntry)
   {
      SongCompInt sc = new SongCompInt(songEntry);
     // tableOfSongIDs.insert(sc.hashCode(), sc);
      
      tableOfSongIDs.insert(songEntry.getID(), sc);
   }

   private void addSongs(SongEntry songEntry)
   {
      purchasedTunes.add(songEntry);

   }

}
