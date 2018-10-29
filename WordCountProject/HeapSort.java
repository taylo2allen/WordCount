public class HeapSort {

    public <E extends Comparable<? super E>> void heapSort(DataCount<E> arr[]){
        int n = arr.length - 1;                                 //last element of array
        int i;                                                  //parent key
        int j;                                                  //child key
        DataCount temp;                                         //temporary DataCounts holder
        DataCount<E>[] tempArr = new DataCount[arr.length];     //temporary DataCounts array

        //bubble smaller items to the top
        for (i = (arr.length /2) - 1; i >= 0; i--){
            heapify (arr, i, n);
        }

        //start at the end of the array and count down
        for (n = arr.length - 1; n >= 0; n--){
            //move the smallest element from the root of the array
            temp = arr[0];
            //move last element of array to root
            arr[0] = arr[n];
            //move the smallest to the end of the array
            arr[n] = temp;

            //trickle down the larger value
            //start at root and check if children are smaller
            i = 0;                  //parent
            heapify (arr, 0, n - 1);
        }

    }

    public <E extends Comparable<? super E>> void heapify(DataCount<E> arr[], int i, int n){
        // arr              array
        // int i            index
        // int n            end of array
        int j;

        //don't go past the end of the array
        if ((i * 2) + 1 <= n) {
            j = (i * 2) + 1;
            //if j < n, then there should be one more spot after j. (we won't go past the end of the array)
            if (j < n) {
                //find the smallest valued index in the array
                j = getSmallestChild(arr, j);
            }
            //now compare i and j and move the index with the smallest count to i
            compareParentChild(arr, i, j, n);
        }
    }

    public <E extends Comparable<? super E>> int getSmallestChild(DataCount<E> arr[], int j){
        //compare outCounts[j] and outCounts[j+1]. All we want is the index that is smallest.
        if (arr[j].count > arr[j+1].count){
            //j+1 is the smallest index. increment j.
            j++;
        }
        //if they are equal, compare alphabetically
        if (arr[j].count == arr[j+1].count){
            //if counts[j].data is less than counts[j+1].data then increment j.
            if (arr[j].data.compareTo(arr[j+1].data) < 0){
                j++;
            }
        }
        return j;
    }

    public <E extends Comparable<? super E>> void compareParentChild(DataCount<E> arr[], int i, int j, int n){
        //now compare i and j and move the index with the smallest count to i
        if (arr[j].count < arr[i].count){
            swap(arr, i, j);
            //reheapify the swapped child
            heapify (arr, j, n);
        }
        //if their counts are equal, compare alphabetically
        else if (arr[j].count == arr[i].count){
            //if j comes after i in alphabet
            if (arr[j].data.compareTo(arr[i].data) > 0){
                //swap them
                swap(arr, i, j);
                //reheapify the swapped child
                heapify(arr, j, n);
            }
        }
    }

    public <E extends Comparable<? super E>> void swap(DataCount<E> arr[], int i, int j){
        DataCount temp;                                         //temporary DataCounts holder
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
