package project04;

/**
 * One object of class MillionSongDataSubset parses a JSON data set and
 * stores each entry in an array.
 * 
 * @author CS1C, Foothill College, DavidGudeman
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import cs1c.SongEntry;

public class FoothillTunesStore implements Iterable<SongEntry>
{
   private static final boolean ENABLE_DATA_OUTPUT = false;

   ArrayList<SongEntry> tunes;
   ArrayList<String> genres = new ArrayList<>();
   private SongEntry[] arrayOfSongs;
//   private JSONArray allSongs;
   FHhashQPwFind<String, SongsInGenre> genreTable = new FHhashQPwFind<String, SongsInGenre>();
  // FHhashQPwFind<String, SongsInGenre> songsInStore = new FHhashQPwFind<String, SongsInGenre>();

   public FoothillTunesStore(String jsonFileName)
   {
      ArrayList<SongEntry> songEntryList = new ArrayList<>();
      String jsonFilePath = jsonFileName;
      JSONParser jsonParser = new JSONParser();

      try
      {
         // --------------------
         // parse the JSON file
         FileReader fileReader = new FileReader(jsonFilePath);
         JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);
         JSONArray allSongs = (JSONArray) jsonObject.get("songs");
         System.out.println("Parsing JSON file...");
         // MillionSongDataSubset dataSet = new MillionSongDataSubset(allSongs);

         // --------------------
         // create an array of all the JSON objects
         arrayOfSongs = new SongEntry[allSongs.size()];

         tunes = new ArrayList<>();
         Iterator<?> iterator = allSongs.iterator();
         int counter = 0;
         while (iterator.hasNext() && counter < allSongs.size())
         {
            JSONObject currentJson = (JSONObject) iterator.next();
            String title = currentJson.get("title").toString();
            int duration = (int) Double.parseDouble(currentJson.get("duration")
                  .toString());
            String artist = currentJson.get("artist_name").toString();
            String genre = currentJson.get("genre").toString();
            String id = currentJson.get("song_id").toString();
            SongEntry currentSong = new SongEntry(title, duration, artist,
                  genre, id);  
            if (!genres.contains(genre))
            {
               genres.add(genre);
            }
           
            tunes.add(currentSong);
            arrayOfSongs[counter++] = currentSong;
         }
         makeListOfSongs(arrayOfSongs, songEntryList);
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
      } catch (IOException e)
      {
         e.printStackTrace();
      } catch (ParseException e)
      {
         e.printStackTrace();
      }
   }

   /**
    * returns the array of song entries
    */
   public void makeListOfSongs(SongEntry[] entries,
         ArrayList<SongEntry> songEntryList)
   {
      for (int i = 0; i < entries.length; i++)
      {
         songEntryList.add(entries[i]);
      }
   }

   /**
    * displays the array of song entries
    */
   public void printAllSongs()
   {
      for (SongEntry song : arrayOfSongs)
         System.out.println(song);
   }

   public void printArrayListTunes()
   {
      int counter = 1;
      for (SongEntry s : tunes)
      {
         System.out.println();
         System.out.println("Song number " + counter);
         System.out.println(s.getArtistName());
         System.out.println(s.getDuration());
         System.out.println(s.getGenre());
         System.out.println(s.getTitle());
         counter++;
      }
   }

   public void printSubListTunes(ArrayList<SongEntry> song)
   {
      System.out.println("Number of found songs: " + song.size());
      for (SongEntry s : song)
      {
         System.out.println(s.toString());
      }
   }

   public SongEntry findSongByTitle(String title)
   {
      ArrayList<SongEntry> foundTitles = new ArrayList<>();
      SongEntry song = null;
      try
      {
         for (SongEntry s : tunes)
         {
            if (s.getTitle().equals(title))
            {
               song = s;
               foundTitles.add(s);
            }
         }
         printSubListTunes(foundTitles);
         return song;
      } catch (Exception e)
      {
         System.out.println("Title not found");
         return null;
      }
   }

   public void printSong(SongEntry song)
   {
      System.out.println(song.getTitle());
   }

   public ArrayList<SongEntry> getTitles()
   {
      ArrayList<SongEntry> titles = new ArrayList<>();
      for (SongEntry s : tunes)
      {
         titles.add(s);
      }
      System.out.println("titles is ArrayList<String> of size: "
            + titles.size());
      return titles;
   }

   public Object getFirstNTitles(int numSongsToBuy, boolean enableRandomPurchase)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * Sorts the songs in the store by genre
    */
   public FHhashQPwFind<String, SongsInGenre> groupSongsByGenre()
   {      
     for (SongEntry se : tunes)
     {  
      
        if (!genreTable.keys.contains(se.getGenre()))
        {
            SongsInGenre  sig = new SongsInGenre(se.getGenre());
            genreTable.keys.add(se.getGenre());
            genreTable.insert(sig);
        }
//        System.out.println(se.getGenre());
//        System.out.println(genreTable.find(se.getGenre()));
        try {
        genreTable.find(se.getGenre()).getGenreList().add(se);
//        System.out.println(se.getTitle());
//        System.out.println(se.getGenre() + genreTable.find(se.getGenre()).getGenreList().size());
        }catch (Exception e)
        {}
     }
     return genreTable;
   }

   
   public void printNumOfSongsInEachGenre()
   {
     
      for (String genre : genreTable.getKeys())
      {
         System.out.print(genre + " has :");
         try {
        // System.out.println(genreTable.find(genre));
         System.out.println(genreTable.find(genre).getGenreList().size());
         } catch (Exception e)
         {
            System.out.println("ho there");
         }
      }

   }

   public ArrayList<SongEntry> buySongByTitle(String title)
   {
      ArrayList<SongEntry> searchResult = new ArrayList<>();
      for (SongEntry se : tunes)
      {
         if (se.getTitle().equals(title))
         {
            searchResult.add(se);
         }
      }
      return searchResult;
   }

   public void getSongsByGenre()
   {
     
      for (String genre : genreTable.getKeys())
      {
         System.out.println(genre + "HHHHHHHHHHHHHHHHHHH");
         try {
         System.out.println(genreTable.find(genre));
         System.out.println(genreTable.find(genre).getGenreList().size());
         } catch (Exception e)
         {
            System.out.println("ho there");
         }
      }
      
    
   }
   @Override
   public Iterator iterator()
   {
      // TODO Auto-generated method stub
      return null;
   }
}
