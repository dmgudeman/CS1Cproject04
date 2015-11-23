#CS1C Assignment 04
##Hashing

This project explores hashing. It continues to develope the FoothillTunes
Store.  A HashEntry class is used to wrap the SongEntry class to provide
it with a state attribute to allow for the data to be empty, active or deleted.
This class is wrapped in two ways.

First the CompInt class allows the toString(), compareTo(), equals() and 
hashCode() methods to be overriden. The Java hashcode method is used to create
a hash of the ID that is owned by the SongEntry class. With that hash the HashEntry
containing-SongEntry is placed in the hashTable class FHhashQPwFind.

The second wrapper class utilizes the string from the genre attribute of the
SongEntry class to populate an arrayList that is held as an attribute of the 
hashTable class. The songEntries are held in arrayLists that are specific
to each SongsInGenre instantiation of the same name.

One of the challenges of this was keeping all the classes straight. One of the
things that I learned in this exercise is to turn to the use of Exceptions
to allow the program to run so that I could trouble shoot it. As an example
I was calling the groupByGenre function three times and it crashed. By using
exceptions and a paired down input json file I was able to see that.

