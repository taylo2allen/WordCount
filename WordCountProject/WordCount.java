import java.io.IOException;

/**
 * An executable that counts the words in a files and prints out the counts in
 * descending order. You will need to modify this file.
 */
public class WordCount {

    private static void countWords(String file) {
        DataCounter<String> counter = new BinarySearchTree<String>();

        try {
            FileWordReader reader = new FileWordReader(file);
            String word = reader.nextWord();
            while (word != null) {
                counter.incCount(word);
                word = reader.nextWord();
            }
        } catch (IOException e) {
            System.err.println("Error processing " + file + e);
            System.exit(1);
        }

        DataCount<String>[] counts = counter.getCounts();
        sortByDescendingCount(counts);
        for (DataCount<String> c : counts)
            System.out.println(c.count + " \t" + c.data);
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
        for (int i = 1; i < counts.length; i++) {
            DataCount<E> x = counts[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (counts[j].count >= x.count) {
                    break;
                }
                counts[j + 1] = counts[j];
            }
            counts[j + 1] = x;
        }
    }

    public static void main(String[] args) {
      /*  if (args.length != 1) {
            System.err.println("Usage: filename of document to analyze");
            System.exit(1);
        }*/
        // try {
        //   // switch(args[0]){
        //   //   case "-a":
        //   //     System.out.println("flag a thrown");
        //   //     break;
        //   //   case "-b":
        //   //     System.out.println("flag b thrown");
        //   //     break;
        //   //   case "-h":
        //   //     System.out.println("flag h thrown");
        //   //   default:
        //   //     // System.out.println("none of the above");
        //   //       throw new Exception("Invalid argument in arg0 must be [ -b | -a | -h ].");
        //   //     // break;
        //   // }

        //         if(args[0] == "-a"){
        //             System.out.println("flag a thrown");
        //         }
        //         if(args[0] == "-b"){
        //             System.out.println("flag b thrown");
        //         }
        //         if(args[0] == "-h"){
        //             System.out.println("flag h thrown");
        //         }
        //         else {throw new Exception("Invalid argument in arg0 must be [ -b | -a | -h ].");}

        //         if (args[1] == "-frequency") {
        //             System.out.println("frequency");
        //         }
        //         else if (args[1] == "-num_unique") {
        //             System.out.println("unique");
        //         }
        //         else {throw new Exception("Invalid argument in arg1 must be [ -frequency | -num_unique ].");}

        //   // switch(args[1]){
        //   //   case "-frequency":
        //   //     System.out.println("frequency");
        //   //     break;
        //   //   case "-num_unique":
        //   //     System.out.println("unique");
        //   //     break;
        //   //   default:

        //   //     // System.out.println("none of the above");
        //   //       throw new Exception("Invalid argument in arg1 must be [ -frequency | -num_unique ].");
        //   //     // break;
        //   // }
        // }
        // catch(Exception e){
        //     System.out.println("Error : " + e.getMessage());
        // }
    }
           //countWords(args[0]);

    private static void Switch(String string) {
        // TODO Auto-generated method stub

        try {
          // switch(args[0]){
          //   case "-a":
          //     System.out.println("flag a thrown");
          //     break;
          //   case "-b":
          //     System.out.println("flag b thrown");
          //     break;
          //   case "-h":
          //     System.out.println("flag h thrown");
          //   default:
          //     // System.out.println("none of the above");
          //       throw new Exception("Invalid argument in arg0 must be [ -b | -a | -h ].");
          //     // break;
          // }

                if(args[0] == "-a"){
                    System.out.println("flag a thrown");
                }
                if(args[0] == "-b"){
                    System.out.println("flag b thrown");
                }
                if(args[0] == "-h"){
                    System.out.println("flag h thrown");
                }
                else {throw new Exception("Invalid argument in arg0 must be [ -b | -a | -h ].");}

                if (args[1] == "-frequency") {
                    System.out.println("frequency");
                }
                else if (args[1] == "-num_unique") {
                    System.out.println("unique");
                }
                else {throw new Exception("Invalid argument in arg1 must be [ -frequency | -num_unique ].");}

          // switch(args[1]){
          //   case "-frequency":
          //     System.out.println("frequency");
          //     break;
          //   case "-num_unique":
          //     System.out.println("unique");
          //     break;
          //   default:

          //     // System.out.println("none of the above");
          //       throw new Exception("Invalid argument in arg1 must be [ -frequency | -num_unique ].");
          //     // break;
          // }
        }
        catch(Exception e){
            System.out.println("Error : " + e.getMessage());
        }

        // catch(Exception arg0_error) {
        //     System.out.println("Invalid argument in arg0 must be [ -b | -a | -h ].");
        // }
        // catch(Exception arg1_error) {
        //     System.out.println("Invalid argument in arg1 must be [ -frequency | -num_unique ].");
        // }

    }
}
