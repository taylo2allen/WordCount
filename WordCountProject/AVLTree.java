/* Project:      WordCountProject
// Module:       AVLTree.java
// Contributors: Taylor Allen, Sam Hendryx, Andrew Cash
// Date:         10/19/18
 Purpose:        Implementation of an AVLTree that extends BinarySearchTree.
 */

public class AVLTree<E extends Comparable<? super E>> extends BinarySearchTree<E> implements DataCounter<E>{
	@Override
	public void incCount(E data) {
		insert(data);
	}

	private void insert(E data){

		overallRoot = insert(data, overallRoot);
	}

	private BSTNode insert(E data, BSTNode node){
		if (node == null){ return new BSTNode(data); }

		int compareResult = data.compareTo(node.data);
		// data goes on left side of the current node
		if (compareResult < 0){ node.left = insert(data, node.left); }
		// data goes on right side of the current node
		else if (compareResult > 0){ node.right = insert(data, node.right); }
		// data is equal, so we increment count
		else { node.count++; }

		return balance(node);
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
		if (node == null){ return node; }

		if (height(node.left) - height(node.right)> 1) {
			if (height(node.left.left)>= height(node.left.right)) {
				node = rotateLeft(node);
			}
			else {
				node = doubleLeft(node);
			}
		}
		else if (height(node.right) - height(node.left)> 1) {
			if (height(node.right.right)
					>= height(node.right.left)) {
				node = rotateRight(node);
			}
			else {
				node = doubleRight(node);
			}
		}
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		return node;
	}
}
