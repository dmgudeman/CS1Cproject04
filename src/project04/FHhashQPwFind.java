package project04;

import java.util.ArrayList;

public class FHhashQPwFind<KeyType, E extends Comparable<KeyType>> extends
      FHhashQP<E> 
{
  
   public ArrayList<KeyType> keys = new ArrayList<>();
   private String name;
   private SongsInGenre songsInGenre;
   
   public FHhashQPwFind(String key, SongsInGenre sig)
   {
      this.setName(key);
      this.setSongsInGenre(sig);
   }
   
   public FHhashQPwFind()
   {
      this.setName(null);     
   }

   // from instructions
   // THE GOAL is that given a HASHED KEY we can pull out the data
   public E find(KeyType key)
   {  
      try
      {   
         boolean temp = false;
 
         temp = mArray[findPosKey(key) ].state == 0 ;

      } catch (Exception e)
      {
         System.out.println("Object with Key: " + key + " is not found.");
      }
    return mArray[findPosKey(key) ].data;
   }

   // from instructions
   // similar to the method insert(E x) in parent class
   // First it adds the key to the list of keys if it hasn't been seen
   // before.
   // Then, it calls the parent class insert (E x)
   public boolean insert(KeyType k, E x)
   {
      if (!keys.contains(k))
      {
         keys.add(k);
      }
      this.insert(x);
      return true;

   }

   public void setKeys(ArrayList<KeyType> keys)
   {
      this.keys = keys;
   }

   // from instructions
   // Accessor method for this list of keys
   ArrayList<KeyType> getKeys()
   {
      return keys;
   }

   // from instructions
   // which provides a trivial modification to myHash() which uses the
   // key rather than the object to hash
   protected int myHashKey(KeyType key)
   {
      int hashVal;

      hashVal = key.hashCode() % mTableSize;
      if (hashVal < 0)
         hashVal += mTableSize;

      return hashVal;
   }
  

   // from instructions
   // which provides a trivial modification to findPos() which uses the
   // key rather than the object to get a position
   protected int findPosKey(KeyType key)
   {
      int kthOddNum = 1;
      int index = myHashKey(key);

      
      while (mArray[index].state != EMPTY && mArray[index].data.equals(key))
      {

         index += kthOddNum; // k squared = (k-1) squared + kth odd #
         kthOddNum += 2; // compute next odd #
         if (index >= mTableSize)
            index -= mTableSize;
      }
      return index;
   }

   public SongsInGenre getSongsInGenre()
   {
      return songsInGenre;
   }

   public void setSongsInGenre(SongsInGenre songsInGenre)
   {
      this.songsInGenre = songsInGenre;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

}
