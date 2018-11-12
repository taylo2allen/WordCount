// Project:      WordCountProject
// Module:       Correlator.java
// Contributors: Taylor Allen, Sam Hendryx, Andrew Cash
// Date:         11/6/18
/* Purpose:      The command line form for Correlator will be as follows:java Correlator -h [-frequency | -unique] <filename2>
*/

import static java.lang.Math.pow;

public class HashTable implements DataCounter<String>{
    protected int size;                   //Size of data elements NOT the size of the array
    //public int key;                     //Ket for hashtable (created by hashcode)
    public DataCount<String>[] table;			//Array of DataCount<String>. Should hold our Word/Count pairs

//    public String data;
//    public int count;

    /** {@inheritDoc} */
    @Override
    public DataCount<String>[] getCounts(){
    @SuppressWarnings("unchecked")
    DataCount<String>[] counts = new DataCount[size];
    //Go through HashTable and create an Array of DataCount<String>[]
    return counts;
    }

    /** {@inheritDoc} */
    @Override
    public int getSize(){
        return size;
    }

    /** {@inheritDoc} */
    @Override
    public void incCount(String data){
    int key = 0;
        //Get HashCode of String "data". HashCode(data) -> key
        key = myHash(data, getSize());
        //Check if table[key] == null
        if (table[key] == null){
            //Prepare insert
            //Is the load factor > 1/2
            if ((double)(getSize()/table.length) >= .5){
                //YES - double the size of the array to the next prime number
                DataCount<String>[] temp = new DataCount[size.length * 2];
                for(DataCount c : counts){
                    if(c != null){
                        key = c.myHash(c.word, temp.length);
                        temp[myHash] = c;
                    }
                }
            }

        }

        /*
        class WordCounterMap
            Entry[] entrys = new Entry[1]

            void add(String s)
                int hash = hash(s, entrys.length)
                if(entrys[hash] == null{
                    Entry[] temp = new Entry[entry.length * 2]
                    for(Entry e : entrys){
                        if(e != null)
                            int hash = hash(e.word, temp.length)
                            temp[hash] = e;
                    entrys = temp;
                    hash = hash(s, entrys.length)
                while(true)
                    if(entrys[hash] != null)
                        if(entrys[hash].word.equals(s))
                            entrys[hash].count++
                            break
                    else
                        entrys[hash] = new Entry(s)
                    hash++
                    hash = hash mod entrys.length

            int getCount(String s)
                int hash = hash(s, length)
                if(entrys[hash] == null)
                    return 0
                while(true)
                    if(entrys[hash].word.equals(s))
                        entrys[hash].count++
                        break
                    hash++
                    hash = hash mod entrys.length


            class Entry
                int count
                String word

                Entry(String s)
                    this.word = s
                    count = 1
         */



    //Check to see if "key" is empty.
      //If Key is empty, then the word isn't in the table.
      //Is load factor > 1/2?
        //YES - resize array (double the siza and find the next prime)
          //Rehash ALL previously stored data.
        //NO - Do nothing special
        //Put the word into the table
        //Increment size (size++;) (we need to keep track of how many unique entries there are for the output
    //If the key is NOT empty, (Will a WHILE loop work here?)
      //Is the data in this location the same word?
        //YES - increment the data count
          //Break out of loop
        //NO - Increment the key
          //Loop again
    }

    private static boolean isPrime(int n){
        if( n == 2 || n == 3 )
        return true;

        if( n == 1 || n % 2 == 0 )
        return false;

        for( int i = 3; i * i <= n; i += 2 )
        if( n % i == 0 )
            return false;

        return true;
    }

    private int myHash(String word, int hashSize){
        /* word is the word we are hashing */
        /* hashSize is the current size of the array */
        int result = 0;
        int count = 0;
        for (char c : word.toCharArray()) {
            result += c * pow(256,count);
            count++;
            count = count % 5;
        }

        return (result % hashSize);
    }
}
