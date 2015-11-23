package project04;
import java.util.ArrayList;

import cs1c.SongEntry;


public class SongsInGenre implements Comparable<String>
{
   protected String genreName = "";
   protected ArrayList<SongEntry> songsInGenreList;
   protected HashEntry<String> he;
   
   /**
    * Constructor
    * @param genreName
    * @param genreList
    * DG
    */
   public SongsInGenre(String genre)
   {
      super();
      this.genreName = genre;
      this.songsInGenreList = new ArrayList<>();
      he = new HashEntry<String>(genre, FHhashQP.ACTIVE);
   }
   
   // we'll use compareTo() to implement our find on key
   public int compareTo(String key)
   {
      return this.getGenreName().compareTo(key);
   }
   
   public boolean equals (SongsInGenre rhs)
   {
      return this.getGenreName().equals(rhs.getGenreName());
   }
   
   public void addSong(SongEntry e)
   {
      songsInGenreList.add(e);
   }

   public String getGenreName()
   {
      return genreName;
   }

   public void setGenreName(String genreName)
   {
      this.genreName = genreName;
   }

   public ArrayList<SongEntry> getGenreList()
   {
      return songsInGenreList;
   }

   public void setGenreList(ArrayList<SongEntry> genreList)
   {
      this.songsInGenreList = genreList;
   }
   
   public int hashCode()
   {
      return this.getGenreName().hashCode();
   }
   

  
}
