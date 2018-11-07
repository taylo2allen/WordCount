// Project:      WordCountProject
// Module:       Correlator.java
// Contributors: Taylor Allen, Sam Hendryx, Andrew Cash
// Date:         11/6/18
/* Purpose:      The command line form for Correlator will be as follows:java Correlator -h [-frequency | -unique] <filename2>
*/

public class HashTable implements DataCounter<String>{
    protected int size;							//Size of data elements NOT the size of the array
    public int key;								//Ket for hashtable (created by hashcode)
    public DataCount<String> table[];			//Array of DataCount<String>. Should hold our Word/Count pairs

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
		//Get HashCode of String "data". HashCode(data) -> key
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
}
