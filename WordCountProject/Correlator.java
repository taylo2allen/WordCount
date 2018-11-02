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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.pow;

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

        // correlation function goes here
        /*remove words with normalized frequencies above %1 and %.01
         *for every word in both take difference between frequencies
         *square the difference and add the result to a running sum
         *final result is difference metric. corresponds to the square
         *of Euclidean distance between 2 vectors in the space of shared
         *words in the doc. this assumes words not appearing in both
         *don't affect correlation.
         */
        double correlationSum = 0;
        //int correlationCount1 = 0;
        //int correlationCount2 = 0;
        int totalNumWords1 = 0;
        int totalNumWords2 = 0;
        double maxOccurrences = 0.0;
        double minOccurrences = 0.0;
        double aveUnique1 = 0.0;        
        double aveUnique2 = 0.0;
        
        for (DataCount<String> c: counts1){
        	if (maxOccurrences == 0) {
        		maxOccurrences = c.count;
        	}
        	minOccurrences = c.count;
            totalNumWords1 += c.count;
        }
        aveUnique1=(maxOccurrences+minOccurrences)/totalNumWords1;
        
        for (DataCount<String> c: counts2){
        	if (maxOccurrences == 0) {
        		maxOccurrences = c.count;
        	}
        	minOccurrences = c.count;
            totalNumWords2 += c.count;
        }
        aveUnique2=(maxOccurrences+minOccurrences)/totalNumWords2;

        double freq = 0;
        Map<String, Double> wordFreq1 = new HashMap<>();
        Map<String, Double> wordFreq2 = new HashMap<>();
                
        for (DataCount<String> c : counts1){
            //correlationCount1++;
            freq = c.count/(double)totalNumWords1;
            
            if (freq < 0.01 && freq > 0.0001){
            	
                wordFreq1.put(c.data, (c.count/(double)aveUnique1));
                //System.out.println(c.data + "\t\t\t" + wordFreq1.get(c.data));
            }
            /*take max number of occurrences and add the minimum number of occurrences
            *and divide by the number of unique words
            *this give the average number of occurrences for any word in the document 
            *for each word divide its number of occurrence by the average number of
            *occurrences. This gives the uniqueness of that word for the particular
            *document.         
            *
            *aveUnique=(max+min) / totUnique;
            *divide number of occurrences for each by the average uniqueness.
            */   
        }
        for (DataCount<String> c : counts2){
            //correlationCount2++;
            freq = c.count/(double)totalNumWords2;
            if (freq < 0.01 && freq > 0.0001) {
                wordFreq2.put(c.data, (c.count/(double)aveUnique2));
                //System.out.println(c.data + "\t\t\t" + wordFreq2.get(c.data));
            }
        }

        for ( String word : wordFreq1.keySet()){
                if (wordFreq2.containsKey(word)){
                    correlationSum += pow((wordFreq1.get(word) - wordFreq2.get(word)),2);
                   // System.out.println(word + "\t" + wordFreq1.get(word) + " - " + wordFreq2.get(word) + " = " + pow((wordFreq1.get(word) - wordFreq2.get(word)),2));
                }
        }
        System.out.println("The more similiar the document, the closer the Correlation metric will be to 0.\n"
        		+ "Correlation Metric: " +/* correlationSum/*/((aveUnique1+aveUnique2)/2));
       
        //normalize frequencies
        //remove top%1


        /*double topCutOff1 = Math.ceil((correlationCount1 * 0.01));
        double bottomCutOff1 = Math.ceil((correlationCount1 * 0.0001));

        double topCutOff2 = Math.ceil((correlationCount2 * 0.01));
        double bottomCutOff2 = Math.ceil((correlationCount2 * 0.0001));*/

        /*System.out.println(" the count1 is: " + correlationCount1 + "\t\tthe count2 is: " + correlationCount2 + "\n" +
                           " the topCutOff1 is : " + topCutOff1 + "\tthe topCutOff2 is : " + topCutOff2 + "\n" +
                           " the bottomCutOff1 is : " + bottomCutOff1 + "\tthe bottomCutOff2 is : " + bottomCutOff2);
        */
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

    private static <E extends Comparable<? super E>> void sortByDescendingCount(DataCount<E>[] counts){
    HeapSort sortObject = new HeapSort();

        //HeapSort Algorithm brought to you by Sam's Sanity and the letters "ahhhhhhhhhhhhhhh!"
        sortObject.heapSort(counts);
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
