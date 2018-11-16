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

import javax.xml.crypto.Data;

import static java.lang.Math.pow;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

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
		double correlationSum = 0;				//Final output. Running total.
		HashMap<String, Double> wordFreq1 = normalize(counts1);
		HashMap<String, Double> wordFreq2 = normalize(counts2);

		// Compare the frequencies of words found in both documents and add them to the correlationSum
		for ( String word : wordFreq1.keySet()){
			if (wordFreq2.containsKey(word)){
				correlationSum += pow((wordFreq1.get(word) - wordFreq2.get(word)),2);
				// System.out.println(word + "\t" + wordFreq1.get(word) + " - " + wordFreq2.get(word) + " = " + pow((wordFreq1.get(word) - wordFreq2.get(word)),2));
			}
		}
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(4);

		System.out.println("The more similiar the document, the closer the Correlation metric will be to 0.\n"
				+ "Correlation Metric: " + nf.format(correlationSum) /*((aveUnique1+aveUnique2)/2))*/);

	}

	private static HashMap<String, Double> normalize(DataCount<String>[] counts){
		// We need the total number of words
		// Total number of words will be the sum of all counts of every word in the document
		int totalNumWords = 0;		//Total num of words for doc
		for (DataCount<String> c: counts){
			totalNumWords+=c.count;
		}
		System.out.println("Total number of words: " + totalNumWords);

		// We need to find the max and min number of occurrences for normalization
		// Because the data is sorted, the first number is the max, the last is the min
		double maxRatio = (double)counts[0].count/totalNumWords;
		double minRatio = (double)counts[counts.length - 1].count/totalNumWords;

		// A frequency variable we'll need for Normalizing
		double freq = 0;
		// A HashMap to store the frequencies in
		HashMap<String, Double> myMap = new HashMap<>();

		// Normalizing the data:
		// X(new) = [X - X(min)]/[X(max)-X(min)]
		for (DataCount<String> c: counts){
			freq = (double)c.count/totalNumWords;
			if (freq < 0.01 && freq > 0.0001){
				freq = ((freq - minRatio)/(maxRatio - minRatio));
				//System.out.println(c.data + ": " + freq);
				myMap.put(c.data, freq);
			}
		}
		return myMap;
	}

	private DataCount<String>[] trim(DataCount<String>[] counts){
		Map<String, Double> wordFreq = new HashMap<>();
		int maxOccurrences = 0;
		int minOccurrences = 0;
		int totalNumWords = 0;
		double freq = 0;

		for (DataCount<String> c: counts){
			if (maxOccurrences == 0) {
				maxOccurrences = c.count;
			}
			minOccurrences = c.count;
			totalNumWords += c.count;
		}


		for (DataCount<String> c : counts){
			freq = c.count/(double)totalNumWords;
			if (freq < 0.01 && freq > 0.0001) {
				wordFreq.put(c.data, freq);
			}
		}

		return counts;
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
