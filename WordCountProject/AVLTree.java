/* Project:      WordCountProject
// Module:       Correlator.java
// Contributors: Taylor Allen, Sam Hendryx, Andrew Cash
// Date:         10/19/18
 Purpose:      The command line form for Correlator will be as follows:java Correlator -a [-frequency | -unique] <filename2>
                  -a    Use an AVL Tree
                 Uses a function to correlate two documents
 */
public class AVLTree<E extends Comparable<? super E>> extends BinarySearchTree<E> implements DataCounter<E>{
	protected BSTNode overallRoot;
/*
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
*/
	@Override
	public void incCount(E data) {
		super.incCount(data);
		isBalanced();
	}
	
	public void isBalanced() {
		isBalanced(overallRoot);
		//if it isn't balanced, fix
		//TODO::Switch statement to determine which case it is if tree is unbalanced.
		//cases already implemented
	}
	//this method checks if the Tree is balanced
	private int isBalanced(BSTNode root) {
		if(root==null) {
			return -1;
		}
		if(root!=null) {
			int htRight = isBalanced(root.right);
			int htLeft = isBalanced(root.left);

			if(Math.abs(height(root.left)-height(root.right))>1||
					height(root.left) != htLeft || height(root.right)
					!= htRight) {
				balance(root);
			}
		}
		return height (root);
	}
	private int height(BSTNode currentNode )
	{
		return currentNode == null ? -1 : currentNode.height;
	}
	public boolean isEmpty() {

		return overallRoot==null;
	}
	/*	 * Rotations	 */
	private BSTNode rotateLeft(BSTNode k2) //case 1
	{
		BSTNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
		k1.height = Math.max( height( k1.left ), k2.height ) + 1;
		return k1;
	}
	private BSTNode doubleLeft(BSTNode k3)// case 2
	{
		k3.left = rotateRight( k3.left );
		return rotateLeft( k3 );
	}
	private BSTNode doubleRight(BSTNode k1)//case 3
	{
		k1.right = rotateLeft( k1.right );
		return rotateRight( k1 );
	}
	private BSTNode rotateRight(BSTNode k1) //case 4
	{
		BSTNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
		k2.height = Math.max( height( k2.right ), k1.height ) + 1;
		return k2;
	}
	private BSTNode balance(BSTNode node)
	{
		if (height(node.left) - height(node.right)> 1) {
			if (height(node.left.left)>= height(node.left.right)) {
				node = rotateRight(node);
			}
			else {
				node = doubleLeft(node);
			}
		}
		else if (height(node.right) - height(node.left)> 1) {
			if (height(node.right.right)
					>= height(node.right.left)) {
				node = rotateLeft(node);
			}
			else {
				node = doubleRight(node);
			}
		}
		return node;
	}
}
