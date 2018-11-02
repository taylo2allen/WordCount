import BinarySearchTree.AVLNode;

/*// Project:      WordCountProject
// Module:       AVLTree.java
// Contributors: Taylor Allen, Sam Hendryx, Andrew Cash
// Date:         1/2/18
 Purpose:      The commandline form for AVLTree will be as follows:java AVLTree WordCount/Correlator-a <filename1> <filename2>
                  -a    Use an AVL Tree
                 Uses a function to correlate two documents
 */
public class AVLTree<E extends Comparable<? super E>> extends BinarySearchTree<E> implements DataCounter<E>{
	/*protected class AVLNode {
        *//**
         * The left child of this node.
         *//*
        public AVLNode left;

        *//**
         * The right child of this node.
         *//*
        public AVLNode right;

        *//**
         * The data element stored at this node.
         *//*
        public E data;

        *//**
         * The count for this data element.
         *//*
        public int count;
        
        public int height;
        
        *//**
         * Create a new data node. Also takes care of incrementing the tree
         * size.
         *
         * @param data data element to be stored at this node.
         *//*
        
        public AVLNode(E data) {
            this.data = data;
            count = 1;
            left = right = null;
            size++;
        }*/
	public static BinarySearchTree avlObj = new BinarySearchTree();
	
	avlObj.
	
	/*public AVLNode() {
		
        overallRoot = null;
        size = 0;
    }
	*/
	//}
}
