package project04;

import cs1c.SongEntry;

/**
 * Required by instructions pattern after Employee in the modules. Week 7 (Part
 * 1) Find() in Hash Tables
 * 
 * @author davidgudeman
 * @param <E>
 *
 */
public class SongCompInt implements Comparable<Integer>
{

   public HashEntry<SongEntry> he;
  

   public SongCompInt(SongEntry e)
   {
      
      he = new HashEntry<>(e, FHhashQP.ACTIVE);
   }

   public String toString()
   {
      return he.data.toString();
   }

   // we'll use compareTo() to implement our find on key
   public int compareTo(Integer key)
   {
      return he.data.getID() - key;
   }

   // let equals() preserve the equals() provided by embedded data
   public boolean equals(SongCompInt rhs)
   {
      return he.data.equals(rhs.he.data);
   }

   public int hashCode()
   {
     
      return he.data.getID();
   }

}
