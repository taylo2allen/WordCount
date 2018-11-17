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

import static java.lang.Math.pow;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;

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
			System.err.println("\nError processing the file \"" + file1 + "\" \nException: " + e + "\n");
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
			System.err.println("\nError processing the file \"" + file2 + "\" \nException: " + e + "\n");
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
		HashMap<String, Double> wordFreq1 = normalize(counts1, file1);
		HashMap<String, Double> wordFreq2 = normalize(counts2, file2);

		// Compare the frequencies of words found in both documents and add them to the correlationSum
		for ( String word : wordFreq1.keySet()){
			if (wordFreq2.containsKey(word)){
				correlationSum += pow((wordFreq1.get(word) - wordFreq2.get(word)),2);
			}
		}
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(4);
		double out = 100 - (correlationSum*100);

		System.out.println("The closer to 100%, the more similar the two documents are.\n"
				+ "Correlation Percentage: " + nf.format(out) + "%");

	}

	private static HashMap<String, Double> normalize(DataCount<String>[] counts, String file){
		// We need the total number of words
		// Total number of words will be the sum of all counts of every word in the document
		int totalNumWords = 0;		//Total num of words for doc
		for (DataCount<String> c: counts){
			totalNumWords+=c.count;
		}
		System.out.println("Total number of words in " + file + ": " + totalNumWords);

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
				myMap.put(c.data, freq);
			}
		}
		return myMap;
	}

	/**
	 * Creates a new HeapSort object and uses it to sort the data.
	 *
	 * @param counts array to be sorted.
	 */

	private static <E extends Comparable<? super E>> void sortByDescendingCount(DataCount<E>[] counts){
		HeapSort sortObject = new HeapSort();
		//Sort using Heapsort
		sortObject.heapSort(counts);
	}

	public static void main(String[] args){
		correlatorSwitch(args);
	}

	private static void correlatorSwitch(String[] args){
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
