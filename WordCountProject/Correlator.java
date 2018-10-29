// Project:      WordCountProject
// Module:       Correlator.java
// Contributors: Taylor Allen, Sam Hendryx, Andrew Cash
// Date:         10/19/18
/* Purpose:      The commandline form for Correlator will be as follows:java Correlator [ -b | -a | -h ] <filename1> <filename2>
                 	-b    Use an Unbalanced BST to implement the DataCounter
                 	-a    Use an AVL Tree
                 	-h    Use a Hashtable
                 Uses a function to correlate two documents
 */

import java.io.IOException;

/**
 * An executable that counts the words in a files and prints out the counts in
 * descending order. You will need to modify this file.
 */
public class Correlator {
    private static void countWords(String file1, String file2){
        DataCounter<String> counter1 = new BinarySearchTree<String>();
        
        //check first file
        try {
            FileWordReader reader = new FileWordReader(file1);
            String word1 = reader.nextWord();
            while ((word1 != null) && !file2.equals(null)){
                counter1.incCount(word1);
                word1 = reader.nextWord();
            }
        } catch (IOException e){
            System.err.println("\nError processing the file \"" + file1 + "\" Exception: " + e + "\n");
            System.exit(1);
        }
        //check second file
        DataCounter<String> counter2 = new BinarySearchTree<String>();

        try {
            FileWordReader reader = new FileWordReader(file2);
            String word2 = reader.nextWord();
            while ((word2 != null) && (!file2.equals(null))){
                counter2.incCount(word2);
                word2 = reader.nextWord();
            }
        } catch (IOException e){
            System.err.println("\nError processing the file \"" + file2 + "\" Exception: " + e + "\n");
            System.exit(1);
        }

        DataCount<String>[] counts1 = counter1.getCounts();
        DataCount<String>[] counts2 = counter2.getCounts();

        sortByDescendingCount(counts1);
        sortByDescendingCount(counts2);
        
        int correlationCount1 = 0;
        int correlationCount2 = 0;
        
        for (DataCount<String> c : counts1)
            correlationCount1++;
        
        for (DataCount<String> c : counts2)
            correlationCount2++;
        
        System.out.println(" the count1 is: " + correlationCount1 + " the count2 is: " + correlationCount2);
       // correlation function goes here
        
        
    }

    /**
     * TODO Replace this comment with your own.
     *
     * Sort the count array in descending order of count. If two elements have
     * the same count, they should be in alphabetical order (for Strings, that
     * is. In general, use the compareTo method for the DataCount.data field).
     *
     * This code uses insertion sort. You should modify it to use a heap sort
     * sorting algorithm. NOTE: the current code assumes the array starts in
     * alphabetical order! You'll need to make your code deal with unsorted
     * arrays.
     *
     * The generic parameter syntax here is new, but it just defines E as a
     * generic parameter for this method, and constrains E to be Comparable. You
     * shouldn't have to change it.
     *
     * @param counts array to be sorted.
     */

   /* private static <E extends Comparable<? super E>> void sortByUniqueCount(DataCount<E>[] counts){
        for (int i = 1; i < counts.length; i++){
            DataCount<E> x = counts[i];
            int j;
            for (j = i - 1; j >= 0; j--){
                if (counts[j].count <= x.count){
                    break;
                }
                counts[j + 1] = counts[j];
            }
            counts[j + 1] = x;
        }
    }*/

    private static <E extends Comparable<? super E>> void sortByDescendingCount(DataCount<E>[] counts){
    HeapSort sortObject = new HeapSort();

        //HeapSort Algorithm brought to you by Sam's Sanity and the letters "ahhhhhhhhhhhhhhh!"
        sortObject.heapSort(counts);

        /**********************************
        //			Original Code
        for (int i = 1; i < counts.length; i++){
                DataCount<E> x = counts[i];
                int j;
                for (j = i - 1; j >= 0; j--){
                    if (counts[j].count >= x.count){
                        break;
                    }
                    counts[j + 1] = counts[j];
                }
                counts[j + 1] = x;
            }
        //			End Original Code
        **********************************/
    }

    public static void main(String[] args){
        CorrelatorSwitch(args);
    }
    private static void CorrelatorSwitch(String[] args){
        try {
            if (args[0].equals("-a")){
                if (!args[1].equals(null) && !args[2].equals(null)){
                    countWords(args[1],args[2]);
                }
                else {throw new Exception("Invalid argument in arg1 & arg2 must be valid filenames.");}
            }
            else if (args[0].equals("-b")){
                if (!args[1].equals(null) && !args[2].equals(null)){
                    countWords(args[1],args[2]);
                }
                else {throw new Exception("Invalid argument in arg1 & arg2 must be valid filenames.");}
            }
            else if (args[0].equals("-h")){
                if (!args[1].equals(null) && !args[2].equals(null)){
                    countWords(args[1],args[2]);
                }
                else {throw new Exception("Invalid argument in arg1 & arg2 must be valid filenames.");}
            }
            else {throw new Exception("Invalid argument in arg0 must be [ -b | -a | -h ].");}
        }
        catch(Exception e){
            System.out.println("\nArgument Error : " + e.getMessage() + "\n");
        }
    }
}
