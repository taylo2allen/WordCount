// Project:      WordCountProject
// Module:       WordCount.java
// Contributors: Taylor Allen, Sam Hendryx, Andrew Cash
// Date:         10/19/18
/* Purpose:      The commandline form for WordCount will be as follows:java WordCount [ -b | -a | -h ] [ -frequency | -num_unique ] <filename>
                  •-b    Use an Unbalanced BST to implement the DataCounter
                  •-a    Use an AVL Tree
                  •-h    Use a Hashtable
                  •-frequency     Print all the word/frequency pairs, ordered by frequency, and then by thewords in lexicographic order
                  •-num_unique     Print the number of unique words in the document. This is the totalnumber of distinct (different)
                  words in the document. Words that appear more than onceare only counted as a single word for this statistic.
 */



import java.io.IOException;

/**
 * An executable that counts the words in a files and prints out the counts in
 * descending order. You will need to modify this file.
 */
public class WordCount {
    private static void countWords(String file1, String file2, boolean bUnique) {
        DataCounter<String> counter = new BinarySearchTree<String>();

        try {
            FileWordReader reader = new FileWordReader(file1);
            String word = reader.nextWord();
            while (word != null) {
                counter.incCount(word);
                word = reader.nextWord();
            }
        } catch (IOException e) {
            System.err.println("Error processing " + file1 + e);
            System.exit(1);
        }

        DataCount<String>[] counts = counter.getCounts();
        sortByDescendingCount(counts);
        // sortByUniqueCount(counts);

        // bUnique = False
        // bUnique = True

        int uniqueCount = 0;

        if (bUnique) {
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
    private static <E extends Comparable<? super E>> void sortByUniqueCount(DataCount<E>[] counts) {
        for (int i = 1; i < counts.length; i++) {
            DataCount<E> x = counts[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (counts[j].count <= x.count) {
                    break;
                }
                counts[j + 1] = counts[j];
            }
            counts[j + 1] = x;
        }
    }

    private static <E extends Comparable<? super E>> void sortByDescendingCount(
            DataCount<E>[] counts) {
        int n = counts.length - 1;      //last element of array
        int i = counts.length / 2 - 1;  //parent key
        int j;                          //child key
        DataCount temp;                 //temporary counts holder
        DataCount<E>[] outCounts = new DataCount[counts.length];       //a new DataCount array to be a sorted counts array

        //bubble up the largest values of counts.count
        while (i >= 0){
            j = (i * 2) + 1;
            //if j < n, then there should be one more spot after j. (we won't go past the end of the array)
            if (j < n){
                //compare outCounts[j] and outCounts[j+1]. All we want is the index that is largest.
                if (counts[j].count < counts[j+1].count){
                    //j+1 is the largest index. increment j.
                    j++;
                }
            }
            //now compare i and j and move the index with the largest count to i
            if (counts[j].count > counts[i].count){
                temp = counts[i];
                counts[i] = counts[j];
                counts[j] = temp;
            }
            i--;
        }

        //settle to the bottom
        //set n to the last element in the array. decrement down to the 0th element and stop.
        for (n = counts.length - 1; n >= 0; n--){
            //pop off root (largest) and move last element to root. remove last element.
            outCounts[(outCounts.length - 1) - n] = counts[0];
            counts[0] = counts[n];
            counts[n] = null;
            //n--;
            //trickle down the smaller value
            //start at root and check if children are larger
            i = 0;                  //parent
            while ((i * 2) + 1 <= n - 1){
                j = (i * 2) + 1;    //child
                //if j < n then we still have space before falling off "end of array" to compare j and j+1
                if (j < n - 1){
                    //compare j and j+1 for largest
                    if (counts[j].count < counts[j+1].count){
                        //j+1 is larger, so increment j
                        j++;
                    }
                }
                //compare i and j for largest and swap the largest into i
                if (counts[i].count < counts[j].count){
                    temp = counts[i];
                    counts[i] = counts[j];
                    counts[j] = temp;
                    //this child j changed. make i = j and keep moving down.
                    i = j;
                } else {
                    break;
                }
            }
        }
        for (int k = 0; k < outCounts.length; k++) {
            counts[k] = outCounts[k];
        }

//        for (int i = 1; i < counts.length; i++) {
//            DataCount<E> x = counts[i];
//            int j;
//            for (j = i - 1; j >= 0; j--) {
//                if (counts[j].count >= x.count) {
//                    break;
//                }
//                counts[j + 1] = counts[j];
//            }
//            counts[j + 1] = x;
//        }

    }

    public static void main(String[] args) {
        Switch(args);
    }

    private static void Switch(String[] args) {
        try {
            if(args[0].equals("-a")){
                // System.out.println("flag -a");
                if (args[1].equals("-frequency")) {
                    // countWords(args[2],null);
                }
                else if (args[1].equals("-num_unique")) {
                    // 2797 unique words
                    // countWords(args[2],null);
                }
                else {throw new Exception("Invalid argument in arg1 must be [ -frequency | -num_unique ].");}
            }
            else if(args[0].equals("-b")){
                if (args[1].equals("-frequency")) {
                    countWords(args[2],null,false);
                }
                else if (args[1].equals("-num_unique")) {
                    // 2797 unique words
                    countWords(args[2],null,true);
                }
                else {throw new Exception("Invalid argument in arg1 must be [ -frequency | -num_unique ].");}
            }
            else if(args[0].equals("-h")){
                // System.out.println("flag -h");
                if (args[1].equals("-frequency")) {
                    countWords(args[2],null,false);
                }
                else if (args[1].equals("-num_unique")) {
                    // 2797 unique words
                    // countWords(args[2],null);
                    countWords(args[2],null,true);
                }
                else {throw new Exception("Invalid argument in arg1 must be [ -frequency | -num_unique ].");}
            }
            else {throw new Exception("Invalid argument in arg0 must be [ -b | -a | -h ].");}

            // if ((!args[1].equals("-frequency"))||(!args[1].equals("-num_unique"))){
            //     throw new Exception("Invalid argument in arg1 must be [ -frequency | -num_unique ].");
            // }
        }
        catch(Exception e){
            System.out.println("Error : " + e.getMessage());
        }
    }
}
