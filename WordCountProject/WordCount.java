// Project:      WordCountProject
// Module:       WordCount.java
// Contributors: Taylor Allen, Sam Hendryx, Andrew Cash
// Date:         10/19/18
/* Purpose:      The commandline form for WordCount will be as follows:java WordCount [ -b | -a | -h ] [ -frequency | -num_unique ] <filename>
                  • -b    Use an Unbalanced BST to implement the DataCounter
                  • -a    Use an AVL Tree
                  • -h    Use a Hashtable
                  • -frequency     Print all the word/frequency pairs, ordered by frequency, and then by thewords in lexicographic order
                  • -num_unique     Print the number of unique words in the document. This is the totalnumber of distinct (different)
                  words in the document. Words that appear more than onceare only counted as a single word for this statistic.
 */

import java.io.IOException;

/**
 * An executable that counts the words in a files and prints out the counts in
 * descending order. You will need to modify this file.
 */
public class WordCount {
    private static void countWords(String file1, String file2, boolean bUnique){
        DataCounter<String> counter = new BinarySearchTree<String>();

        try {
            FileWordReader reader = new FileWordReader(file);
            String word = reader.nextWord();
            while (word != null){
                counter.incCount(word);
                word = reader.nextWord();
            }
        } catch (IOException e){
            System.err.println("\nError processing the file \"" + file1 + "\" Exception: " + e + "\n");
            System.exit(1);
        }

        DataCount<String>[] counts = counter.getCounts();
        sortByDescendingCount(counts);

        // bUnique = False = run the -frequency flag
        // bUnique = True = run the -num_unique flag

        int uniqueCount = 0;

        if (bUnique){
            for (DataCount<String> c : counts)
                uniqueCount++;

            System.out.println("\n\n" + uniqueCount + " Unique Words Found in \'" + file1 + "'.\n\n");
        }
        else{
            for (DataCount<String> c : counts)
                System.out.println(c.count + " \t" + c.data);
        }
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
  
    private static <E extends Comparable<? super E>> void sortByDescendingCount(
            DataCount<E>[] counts) {
        HeapSort sortObject = new HeapSort();

        //HeapSort Algorithm brought to you by Sam's Sanity and the letters "ahhhhhhhhhhhhhhh!"
        sortObject.heapSort(counts);
    }
    private static <E extends Comparable<? super E>> void sortByDescendingCount(DataCount<E>[] counts){
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
    }

    public static void main(String[] args){
        Switch(args);
    }

    private static void Switch(String[] args){
        try {
            if (args[0].equals("-a")){
                if (args[1].equals("-frequency")){
                    // countWords(args[2],null,false);
                }
                else if (args[1].equals("-num_unique")){
                    // countWords(args[2],null,true);
                }
                else {throw new Exception("Invalid argument in arg1 must be [ -frequency | -num_unique ].");}
            }
            else if (args[0].equals("-b")){
                if (args[1].equals("-frequency")){
                    countWords(args[2],null,false);
                }
                else if (args[1].equals("-num_unique")){
                    countWords(args[2],null,true);
                }
                else {throw new Exception("Invalid argument in arg1 must be [ -frequency | -num_unique ].");}
            }
            else if (args[0].equals("-h")){
                if (args[1].equals("-frequency")){
                    // countWords(args[2],null,false);
                }
                else if (args[1].equals("-num_unique")){
                    // countWords(args[2],null,true);
                }
                else {throw new Exception("Invalid argument in arg1 must be [ -frequency | -num_unique ].");}
            }
            else if (args[2].equals(null)){throw new Exception("Invalid argument in arg2 must enter a filename.");}
            else {throw new Exception("Invalid argument in arg0 must be [ -b | -a | -h ].");}
        }
        catch(Exception e){
            System.out.println("\nArgument Error : " + e.getMessage() + "\n");
        }
        countWords(args[0]);
    }
}