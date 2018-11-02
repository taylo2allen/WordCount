

public class AVLTree<E extends Comparable<? super E>> extends BinarySearchTree<E> implements DataCounter<E>{
	AVLNode overallRoot;

	public class AVLNode extends BSTNode {
		public AVLNode left;
		public AVLNode right;
		public int height;

		public AVLNode(E data) {
			super(data);
			height = 0;
			//System.out.println(this.height);
		}
	}

	@Override
	public void incCount(E data) {
		//System.out.println("Inserting: " + data);
		if (overallRoot == null) {
            overallRoot = new AVLNode(data);
        } else {
            // traverse the tree
			AVLNode currentNode = overallRoot;
            while (true) {

                // compare the data to be inserted with the data at the current
                // node
                int cmp = data.compareTo(currentNode.data);

                if (cmp == 0) {
                    // current node is a match
                    currentNode.count++;
                    return;
                } else if (cmp < 0) {
                    // new data goes to the left of the current node
                    if (currentNode.left == null) {
                        currentNode.left = new AVLNode(data);
                        return;
                    }
                    currentNode = currentNode.left;
                } else {
                    // new data goes to the right of the current node
                    if (currentNode.right == null) {
                        currentNode.right = new AVLNode(data);
                        return;
                    }
                    currentNode = currentNode.right;
                    //System.out.println(currentNode.height);
                }
            }
        }
	}

	protected int traverse(AVLNode root, DataCount<E>[] counts, int idx) {
		if(root != null) {
			System.out.println("Data: " + root.data + "\tCount: " + root.count);
			idx = traverse(root.left, counts, idx);
			counts[idx] = new DataCount<E>(root.data, root.count);
			idx = traverse(root.right, counts, idx + 1);
		}
		return idx;
	}

}
