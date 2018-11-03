/* Project:      WordCountProject
// Module:       Correlator.java
// Contributors: Taylor Allen, Sam Hendryx, Andrew Cash
// Date:         10/19/18
 Purpose:      The command line form for Correlator will be as follows:java Correlator -a [-frequency | -unique] <filename2>
                  -a    Use an AVL Tree
                 Uses a function to correlate two documents
 */
public class AVLTree<E extends Comparable<? super E>> extends BinarySearchTree<E> implements DataCounter<E>{
	AVLNode overallRoot;

	public class AVLNode extends BSTNode {
		public AVLNode left;
		public AVLNode right;
		public int height;
		//private boolean balanced = true;
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
				}
				isBalanced();
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
	public void isBalanced() {
		isBalanced(overallRoot);
		//if it isn't balanced, fix
		//TODO::Switch statement to determine which case it is if tree is unbalanced.
		//cases already implemented
	}
	//this method checks if the Tree is balanced
	private int isBalanced(AVLNode root) {
		if(root==null) {
			return -1;
		}
		if(root!=null) {
			int switchNum = 0;
			int htRight = isBalanced(root.right);
			int htLeft = isBalanced(root.left);

			if(Math.abs(height(root.left)-height(root.right))>1||
					height(root.left) != htLeft || height(root.right)
					!= htRight) {

				switch(switchNum) {

				case 1: rotateLeft(root);
				break;
				case 2: doubleLeft(root);
				break;
				case 3: doubleRight(root);
				break;
				case 4: rotateRight(root);
				break;
				default: System.out.println("The switch statement isn't receiving a 1, 2, 3 or 4.");
				break;
				}
			}
		}
		return height (root);
	}
	private int height( AVLNode currentNode )
	{
		return currentNode == null ? -1 : currentNode.height;
	}
	public boolean isEmpty() {

		return overallRoot==null;
	}
	/*	 * Rotations	 */
	private AVLNode rotateLeft(AVLNode k2) //case 1
	{
		AVLNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
		k1.height = Math.max( height( k1.left ), k2.height ) + 1;
		return k1;
	}
	private AVLNode doubleLeft(AVLNode k3)// case 2
	{
		k3.left = rotateRight( k3.left );
		return rotateLeft( k3 );
	}
	private AVLNode doubleRight(AVLNode k1)//case 3
	{
		k1.right = rotateLeft( k1.right );
		return rotateRight( k1 );
	}
	private AVLNode rotateRight(AVLNode k1) //case 4
	{
		AVLNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
		k2.height = Math.max( height( k2.right ), k1.height ) + 1;
		return k2;
	}
}
