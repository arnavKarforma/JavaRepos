package blackboard;

// RECURSIVE implementation of Binary Search Tree

/**
 * A class that implements the ADT binary search tree by extending BinaryTree.
 * 
 * @author Frank M. Carrano
 * @version 2.0
 */
public class BinarySearchTreeR<T extends Comparable<? super T>> extends BinaryTree<T>
		implements SearchTreeInterface<T>, java.io.Serializable {
	private static final long serialVersionUID = -8525107615930150549L;

	public BinarySearchTreeR() {
		super();
	} // end default constructor

	public BinarySearchTreeR(T rootEntry) {
		super();
		setRootNode(new BinaryNode<T>(rootEntry));
	} // end constructor

	public void setTree(T rootData) // disable setTree (see Segment 27.6)
	{
		throw new UnsupportedOperationException();
	} // end setTree

	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
		throw new UnsupportedOperationException();
	} // end setTree

	// 27.09
	public T getEntry(T entry) {
		return findEntry(getRootNode(), entry);
	} // end getEntry

	private T findEntry(BinaryNodeInterface<T> rootNode, T entry) { // header uses BinaryNodeInterface instead of
																	// BinaryNode
																	// to avoid cast of values returned from
																	// getLeftChild and
																	// getRightChild
		T result = null;

		if (rootNode != null) {
			T rootEntry = rootNode.getData();

			if (entry.equals(rootEntry))
				result = rootEntry;
			// entry < rootEntry)
			else if (entry.compareTo(rootEntry) < 0)
				result = findEntry(rootNode.getLeftChild(), entry);
			else
				result = findEntry(rootNode.getRightChild(), entry);
		} // end if

		return result;
	} // end findEntry

	// 27.10
	public boolean contains(T entry) {
		return getEntry(entry) != null;
	} // end contains

	// 27.16
	public T add(T newEntry) {
		T result = null;

		if (isEmpty())
			setRootNode(new BinaryNode<T>(newEntry));
		else
			result = addEntry(getRootNode(), newEntry);

		return result;
	} // end add

	// 27.15
	/** Task: Adds newEntry to the nonempty subtree rooted at rootNode. */
	private T addEntry(BinaryNodeInterface<T> rootNode, T newEntry) {
		assert rootNode != null;
		T result = null;
		int comparison = newEntry.compareTo(rootNode.getData());

		if (comparison == 0) // newEntry matches entry in root
		{
			result = rootNode.getData();
			rootNode.setData(newEntry);
		} else if (comparison < 0) // newEntry < entry in root
		{
			if (rootNode.hasLeftChild())
				result = addEntry(rootNode.getLeftChild(), newEntry);
			else
				rootNode.setLeftChild(new BinaryNode<T>(newEntry));
		} else // newEntry > entry in root
		{
			assert comparison > 0;

			if (rootNode.hasRightChild())
				result = addEntry(rootNode.getRightChild(), newEntry);
			else
				rootNode.setRightChild(new BinaryNode<T>(newEntry));
		} // end if

		return result;
	} // end addEntry

	// 27.29
	public T remove(T entry) {
		ReturnObject oldEntry = new ReturnObject(null);
		BinaryNodeInterface<T> newRoot = removeEntry(getRootNode(), entry, oldEntry);
		setRootNode(newRoot);

		return oldEntry.get();
	} // end remove

	// 27.30
	/**
	 * Task: Removes an entry from the tree rooted at a given node.
	 * 
	 * @param rootNode a reference to the root of a tree
	 * @param entry    the object to be removed
	 * @param oldEntry an object whose data field is null
	 * @return the root node of the resulting tree; if entry matches an entry in the
	 *         tree, oldEntry's data field is the entry that was removed from the
	 *         tree; otherwise it is null
	 */
	private BinaryNodeInterface<T> removeEntry(BinaryNodeInterface<T> rootNode, T entry, ReturnObject oldEntry) {
		if (rootNode != null) {
			T rootData = rootNode.getData();
			int comparison = entry.compareTo(rootData);

			if (comparison == 0) // entry == root entry
			{
				oldEntry.set(rootData);
				rootNode = removeFromRoot(rootNode);
			} else if (comparison < 0) // entry < root entry
			{
				BinaryNodeInterface<T> leftChild = rootNode.getLeftChild();
				BinaryNodeInterface<T> subtreeRoot = removeEntry(leftChild, entry, oldEntry);
				rootNode.setLeftChild(subtreeRoot);
			} else // entry > root entry
			{
				BinaryNodeInterface<T> rightChild = rootNode.getRightChild();
				rootNode.setRightChild(removeEntry(rightChild, entry, oldEntry));
			} // end if
		} // end if

		return rootNode;
	} // end removeEntry

	// 27.32
	/**
	 * Task: Removes the entry in a given root node of a subtree.
	 * 
	 * @param rootNode the root node of the subtree
	 * @return the root node of the revised subtree
	 */
	private BinaryNodeInterface<T> removeFromRoot(BinaryNodeInterface<T> rootNode) {
		// Case 1: rootNode has two children
		if (rootNode.hasLeftChild() && rootNode.hasRightChild()) {
			// find node with largest entry in left subtree
			BinaryNodeInterface<T> leftSubtreeRoot = rootNode.getLeftChild();
			BinaryNodeInterface<T> largestNode = findLargest(leftSubtreeRoot);

			// replace entry in root
			rootNode.setData(largestNode.getData());

			// remove node with largest entry in left subtree
			rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
		} // end if

		// Case 2: rootNode has at most one child
		else if (rootNode.hasRightChild())
			rootNode = rootNode.getRightChild();
		else
			rootNode = rootNode.getLeftChild();

		// Assertion: if rootNode was a leaf, it is now null

		return rootNode;
	} // end removeEntry

	// 27.33
	/**
	 * Task: Finds the node containing the largest entry in a given tree.
	 * 
	 * @param rootNode the root node of the tree
	 * @return the node containing the largest entry in the tree
	 */
	private BinaryNodeInterface<T> findLargest(BinaryNodeInterface<T> rootNode) {
		if (rootNode.hasRightChild())
			rootNode = findLargest(rootNode.getRightChild());

		return rootNode;
	} // end findLargest

	// 27.34
	/**
	 * Task: Removes the node containing the largest entry in a given tree.
	 * 
	 * @param rootNode the root node of the tree
	 * @return the root node of the revised tree
	 */
	private BinaryNodeInterface<T> removeLargest(BinaryNodeInterface<T> rootNode) {
		if (rootNode.hasRightChild()) {
			BinaryNodeInterface<T> rightChild = rootNode.getRightChild();
			BinaryNodeInterface<T> root = removeLargest(rightChild);
			rootNode.setRightChild(root);
		} else
			rootNode = rootNode.getLeftChild();

		return rootNode;
	} // end removeLargest

	private class ReturnObject implements java.io.Serializable {
		private T item;

		private ReturnObject(T entry) {
			item = entry;
		} // end constructor

		public T get() {
			return item;
		} // end get

		public void set(T entry) {
			item = entry;
		} // end set
	} // end ReturnObject
} // end BinarySearchTreeR