import static org.junit.jupiter.api.Assertions.*;

class TestHeapSort {
    public static void main(String[] args) {
        boolean dumpall = false, notest = false;

        if (args.length > 1
                || (args.length == 1 && args[0].compareTo("dump") != 0 && args[0]
                .compareTo("notest") != 0)) {
            System.err.println("Arguments: [dump] to dump all output");
            System.err.println("           [notest] to skip tests");
            System.err.println("No arguments just tests output");
            return;
        }

        if (args.length == 1) {
            dumpall = true;
            if (args[0].compareTo("notest") == 0)
                notest = true;
        }

        String[][] tests = {
                { "two", "three", "six", "three", "four", "three", "six", "four", "four", "five", "five", "four", "five", "one", "five", "six", "five", "six", "two", "six", "six" },
                { "9", "8", "6", "9", "9", "5", "7", "6", "3", "4", "8", "8", "6", "1", "6", "1"},
                };

        String[][] expected = {
                {
                        "six,6 five,5 four,4 three,3 two,2 one,1 " },
                {
                        "6,4 8,3 9,3 1,2 3,1 4,1 5,1 7,1 " },
                };

        boolean error = false;
        for (int i = 0; i < tests.length; i++) {
            AVLTree<String> tree = new AVLTree<String>();
            for (int j = 0; j < tests[i].length; j++) {
                tree.incCount(tests[i][j]);
            }

            DataCount<String>[] cnt = tree.getCounts();
            HeapSort sortObject = new HeapSort();
            sortObject.heapSort(cnt);
            String out = "";
            if (cnt != null && cnt.length > 0)
                for (int j = 0; j < cnt.length; j++)
                    out += cnt[j].data + "," + cnt[j].count + " ";
            else
                out = "No Data";
            if (notest
                    || out.compareTo(expected[i][expected[i].length - 1]) != 0)
                error = true;
            if (dumpall)
                System.out.println(out);
        }

        if (!notest) {
            if (error)
                System.out.println("Test failed!");
            else
                System.out.println("Test passed.");
        }
    }
}