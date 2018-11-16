// Project:      WordCountProject
// Module:       Correlator.java
// Contributors: Taylor Allen, Sam Hendryx, Andrew Cash
// Date:         11/6/18
/* Purpose:      The command line form for Correlator will be as follows:java Correlator -h [-frequency | -unique] <filename2>
*/

import java.util.Arrays;

import static java.lang.Math.pow;

public class HashTable implements DataCounter<String>{
    private static final int DEFAULT_TABLE_SIZE = 10;
    private HashEntry<String> [ ] array; // The array of elements
    private int occupied;                 // The number of occupied cells
    protected int size;                   //Size of data elements NOT the size of the array
    //public int key;                     //Ket for hashtable (created by hashcode)
    //public DataCount<String>[] table;			//Array of DataCount<String>. Should hold our Word/Count pairs

//    public String data;
//    public int count;

    /**
     * Construct the hash table.
     */
    public HashTable( ){
        this( DEFAULT_TABLE_SIZE );
    }

    /**
     * Construct the hash table.
     * @param size the approximate initial size.
     */
    public HashTable( int size ){
        allocateArray( size );
        doClear( );
    }

    /**
     * Insert into the hash table. If the item is
     * already present, do nothing.
     * @param x the item to insert.
     */
    public boolean insert( String x )
    {
        // Insert x as active
        int currentPos = findPos( x );
        if( isActive( currentPos ) ){
            array[ currentPos ].count++;
            return false;
        }

        if( array[ currentPos ] == null )
            ++occupied;
        array[ currentPos ] = new HashEntry<>( x, true );
        size++;

        // Rehash; see Section 5.5
        if( occupied > array.length / 2 )
            rehash( );

        return true;
    }

    private boolean insert(String x, int count){
        // Insert x into array with it's current count
        int currentPos = findPos( x );
        if( isActive( currentPos ) ){
            System.out.println("Oops! A collision we were NOT expecting!");
            return false;
        }
        if( array[ currentPos ] == null )
            ++occupied;
        array[ currentPos ] = new HashEntry<>(x, count, true);
        size++;

        return true;
    }

    /**
     * Expand the hash table.
     */
    private void rehash( )
    {
        HashEntry<String> [ ] oldArray = array;

        // Create a new double-sized, empty table
        allocateArray( 2 * oldArray.length );
        occupied = 0;
        size = 0;

        // Copy table over
        for( HashEntry<String> entry : oldArray )
            if( entry != null && entry.isActive )
                insert( entry.element, entry.count );
    }

    /**
     * Method that performs quadratic probing resolution.
     * @param x the item to search for.
     * @return the position where the search terminates.
     */
    private int findPos( String x )
    {
        int offset = 1;
        int currentPos = myhash( x );

        while( array[ currentPos ] != null &&
                !array[ currentPos ].element.equals( x ) )
        {
            currentPos += offset;  // Compute ith probe
            offset += 2;
            if( currentPos >= array.length )
                currentPos -= array.length;
        }

        return currentPos;
    }

    /**
     * Get current size.
     * @return the size.
     */
    public int size( )
    {
        return size;
    }

    /**
     * Get length of internal table.
     * @return the size.
     */
    public int capacity( )
    {
        return array.length;
    }

    /**
     * Return true if currentPos exists and is active.
     * @param currentPos the result of a call to findPos.
     * @return true if currentPos is active.
     */
    private boolean isActive( int currentPos )
    {
        return array[ currentPos ] != null && array[ currentPos ].isActive;
    }

    private void doClear( )
    {
        occupied = 0;
        for( int i = 0; i < array.length; i++ )
            array[ i ] = null;
    }

    private int myhash( String x )
    {
        int hashVal = myHashCode(x, capacity());

        hashVal %= array.length;
        if( hashVal < 0 )
            hashVal += array.length;

        return hashVal;
    }

    private static class HashEntry<String>
    {
        public String  element;   // the element
        public int count;         // the count of the word
        public boolean isActive;  // false if marked deleted

        public HashEntry( String e )
        {
            this( e, true );
        }

        public HashEntry( String e, boolean i )
        {
            element  = e;
            count = 1;
            isActive = i;
        }

        public HashEntry(String e, int c, boolean i){
            element = e;
            count = c;
            isActive = i;
        }
    }

    /**
     * Internal method to allocate array.
     * @param arraySize the size of the array.
     */
    private void allocateArray( int arraySize )
    {
        array = new HashEntry[ nextPrime( arraySize ) ];
    }

    /**
     * Internal method to find a prime number at least as large as n.
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    private static int nextPrime( int n )
    {
        if( n % 2 == 0 )
            n++;

        for( ; !isPrime( n ); n += 2 )
            ;

        return n;
    }

    /**
     * Internal method to test if a number is prime.
     * Not an efficient algorithm.
     * @param n the number to test.
     * @return the result of the test.
     */
    private static boolean isPrime( int n )
    {
        if( n == 2 || n == 3 )
            return true;

        if( n == 1 || n % 2 == 0 )
            return false;

        for( int i = 3; i * i <= n; i += 2 )
            if( n % i == 0 )
                return false;

        return true;
    }

    /** {@inheritDoc} */
    @Override
    public DataCount<String>[] getCounts() {
        @SuppressWarnings("unchecked")
        DataCount<String>[] counts = new DataCount[size];
        //Go through HashTable and create an Array of DataCount<String>[]
        int i = 0;
        for (HashEntry<String> entry : array) {
            if (entry != null && entry.isActive) {
                counts[i] = new DataCount<>(entry.element, entry.count);
                //System.out.println(entry.element + " " + entry.count + "\n");
                i++;
            }
        }

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
        insert(data);
    }

    private int myHashCode(String word, int hashSize){
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

    /**
     * Dump the contents of the tree to a String (provided for debugging and
     * unit testing purposes).
     *
     * @return a textual representation of the tree.
     */
    protected String dump() {
        String out = "";
        if (array != null){
            for (int i = 0; i < array.length; i++){
                if (array[i] == null){
                    out += "[.,.] ";
                }else {
                    out += "[" + array[i].element + "," + array[i].count + "] ";
                }
            }
            return out;
        }
        return "<empty tree>";
    }
}
