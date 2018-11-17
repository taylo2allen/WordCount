// Project:      WordCountProject
// Module:       WordCount.java
// Contributors: Taylor Allen, Sam Hendryx, Andrew Cash
// Date:         10/19/18
/* Purpose:      The commandline form for WordCount will be as follows:java WordCount [ -b | -a | -h ] [ -frequency | -num_unique ] <filename>
                  -b    Use an Unbalanced BST to implement the DataCounter
                  -a    Use an AVL Tree
                    -h    Use a Hashtable
                    -frequency     Print all the word/frequency pairs, ordered by frequency, and then by the words in lexicographic order
                    -num_unique     Print the number of unique words in the document. This is the total number of distinct (different)
                  words in the document. Words that appear more than once are only counted as a single word for this statistic.
 */

import java.io.IOException;

/**
 * An executable that counts the words in a files and prints out the counts in
 * descending order. You will need to modify this file.
 */
public class WordCount {
	private static void countWords(DataCounter<String> counter,String file, boolean bUnique){
		try {
			FileWordReader reader = new FileWordReader(file);
			String word = reader.nextWord();
			while (word != null){
				counter.incCount(word);
				word = reader.nextWord();
			}
		} catch (IOException e){
			System.err.println("\nError processing the file \"" + file + "\"\nException: " + e + "\n");
			System.exit(1);
		}

		DataCount<String>[] counts = counter.getCounts();
		sortByDescendingCount(counts);

		int uniqueCount = 0;

		if (bUnique){
			for (DataCount<String> c : counts)
				uniqueCount++;

			System.out.println("\n\n" + uniqueCount + " Unique Words Found in \'" + file + "'.\n\n");
		}
		else{
			for (DataCount<String> c : counts)
				System.out.println(c.count + " \t" + c.data);
		}
	}

	private static <E extends Comparable<? super E>> void sortByDescendingCount(DataCount<E>[] counts){
		HeapSort sortObject = new HeapSort();
		//Sort using Heapsort
		sortObject.heapSort(counts);
	}

	public static void main(String[] args){
		WordCountSwitch(args);
	}

	private static void WordCountSwitch(String[] args){
		try {
			if (args[0].equals("-a")){
				DataCounter<String> counter = new AVLTree<>();
				if (args[1].equals("-frequency")){
					countWords(counter,args[2],false);
				}
				else if (args[1].equals("-num_unique")){
					countWords(counter,args[2],true);
				}
				else {throw new Exception("Invalid argument in arg1 must be [ -frequency | -num_unique ].");}
			}
			else if (args[0].equals("-b")){
				DataCounter<String> counter = new BinarySearchTree<String>();
				if (args[1].equals("-frequency")){
					countWords(counter,args[2],false);
				}
				else if (args[1].equals("-num_unique")){
					countWords(counter,args[2],true);
				}
				else {throw new Exception("Invalid argument in arg1 must be [ -frequency | -num_unique ].");}
			}
			else if (args[0].equals("-h")){
				DataCounter<String> counter = new HashTable();
				if (args[1].equals("-frequency")){
					countWords(counter,args[2],false);
				}
				else if (args[1].equals("-num_unique")){
					countWords(counter,args[2],true);
				}
				else {throw new Exception("Invalid argument in arg1 must be [ -frequency | -num_unique ].");}
			}
			else if (args[2].equals(null)){throw new Exception("Invalid argument in arg2 must enter a filename.");}
			else {throw new Exception("Invalid argument in arg0 must be [ -b | -a | -h ].");}
		}
		catch(Exception e){
			System.out.println("\nArgument Error : " + e.getMessage() + "\n");
		}
	}
}
