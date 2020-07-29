package com.dmlab;

public class BinaryTree<Key extends Comparable<? super Key>, E> {

	class Node {
		private Key mKey;
		private E mValue;

		private Node mLeft;
		private Node mRight;


		public Node(Key key, E value) {
			mKey = key;
			mValue = value;
			mLeft = null;
			mRight = null;
		}

		public Key getKey() {
			return mKey;
		}

		public E getValue() {
			return mValue;
		}

		/**
		 * Insert a node to the subtree, the root of which is this node.
		 * If the subtree already has node with the given key in the param,
		 * replace the value of the node in the subtree.
		 * Please compare keys using compareTo method.
		 * e.g. when "int compare = KEY0.compareTo(KEY1)"
		 * if compare == 0, this means KEY0 is equal to KEY1
		 * if compare > 0, KEY0 > KEY1
		 * if compare < 0, KEY0 < KEY1
		 *
		 * @param key
		 * @param value
		 * @return None
		 */
		public void insert(Key key, E value) {
			// TODO: Fill this function
			insertHelp(mRoot, key, value);
		}

		private Node insertHelp(Node root, Key key, E value){
			if(root==null){
				return new Node(key, value);
			}
			if(root.getKey().compareTo(key)>0){
				root.mLeft = insertHelp(root.mLeft, key, value);
			}else{
				root.mRight = insertHelp(root.mRight, key, value);
			}
			return root;
		}

		/**
		 * Find the value of item by the key in the subtree, the root of which is this node.
		 *
		 * @param key
		 * @return the value of item matched with the given key.
		 * null, if there is no matching node in this subtree.
		 */
		public E find(Key key) {
			// TODO: Fill this function
			return findHelp(mRoot, key).getValue();
		}

		private Node findHelp(Node root, Key key){
			if(root == null){
				return null;
			}
			if(root.getKey().compareTo(key)>0){
				findHelp(root.mLeft, key);
			}else if(root.getKey().compareTo(key)==0){
				return root;
			}else{
				findHelp(root.mRight, key);
			}
			return root;
		}

		@Override
		public String toString() {
			return "[" + String.valueOf(mKey) + ":" + String.valueOf(mValue) + "]";
		}

		/**
		 * Traverse with the preorder in this subtree.
		 *
		 * @return The String to be printed-out which contains series of nodes as the preorder traversal.
		 */
		public String preOrder() {
			String result = preOrderHelp(mRoot);
			return result;
		}

		private String preOrderHelp(Node root){
			if(root == null){
				return "";
			}
			String result = root.toString();
			result += preOrderHelp(root.mLeft);
			result += preOrderHelp(root.mRight);
			return result;
		}

		/**
		 * Traverse with the inorder in this subtree.
		 *
		 * @return The String to be printed-out which contains series of nodes as the inorder traversal.
		 */
		public String inOrder() {
			// TODO: Fill this function
			String result = inOrderHelp(mRoot);
			return result;
		}

		private String inOrderHelp(Node root){
			if(root == null){
				return "";
			}
			String result = inOrderHelp(root.mLeft);
			result += root.toString();
			result += inOrderHelp(root.mRight);
			return result;
		}

		public Node findMin() {
			if (mLeft == null)
				return this;
			else
				return mLeft.findMin();
		}

		public Node findMax() {
			if (mRight == null)
				return this;
			else
				return mRight.findMax();
		}

		/**
		 * Delete a node,the key of which match with the key given as param, from this subtree.
		 * You may need to define new method to find minimum of the subtree.
		 *
		 * @return the node of which parent needs to point after the deletion.
		 */
		public Node delete(Key key) {
			// TODO: Fill this function
			Node temp = findHelp(mRoot, key);
			if(temp != null){
				mRoot = deleteHelp(mRoot, key);
			}
			return temp;
		}

		private Node deleteHelp(Node root, Key key){
			if(root == null){
				return null;
			}
			if(root.getKey().compareTo(key)>0){
				root.mLeft = deleteHelp(root.mLeft, key);
			}else if(root.getKey().compareTo(key)<0){
				root.mRight = deleteHelp(root.mRight, key);
			}else{
				if(root.mRight == null){
					return root.mLeft;
				}else if(root.mLeft == null){
					return root.mRight;
				}else{
					Node temp = root.mRight.findMin();
					root.mValue = temp.mValue;
					root.mKey = temp.mKey;
					root.mRight = root.mRight.delete(temp.mKey);
				}
			}
			return root;
		}
		/**
		 * Flatten binary search tree into binary tree
		 *
		 */
		public void flattenBinaryTree(Node root) {
			// TODO: Fill this function

		}

		private Node flattenhelp(Node root){
			if(root == null){
				return null;
			}
			Node newRoot = root.findMin();
			newRoot.mRight = root.delete(newRoot.mKey);
			newRoot.mRight = root.flattenhelp(root);
			return newRoot;
		}

		/**
		 * Find the lowest common ancestor
		 * If k1 or k2 are not in the tree, return the root
		 * @return
		 */
		public Node lowestCommonAncestor(Node root, Key k1, Key k2) {
			// TODO: Fill this function
			Node one = findHelp(root, k1);
			Node two = findHelp(root, k2);

			int first = root.getKey().compareTo(k1);
			int second = root.getKey().compareTo(k2);

			if(first*second < 0){
				return root;
			}
			else if(first > 0 && second > 0){
				lowestCommonAncestor(root.mLeft, k1, k2);
			}
			else if(first > 0 && second > 0){
				lowestCommonAncestor(root.mRight, k1, k2);
			}
			return null;
		}
	}

	private Node mRoot;
	public int count;

	public BinaryTree() {
		mRoot = null;
	}

	public void insert(Key key, E value) {
		if (mRoot == null) {
			mRoot = new Node(key, value);
		} else {
			mRoot.insert(key, value);
		}
		count ++;
	}

	public void delete(Key key) {
		if (mRoot == null)
			return;
		mRoot = mRoot.delete(key);
		count --;
	}

	public E find(Key key) {
		if (mRoot == null)
			return null;
		return mRoot.find(key);
	}

	public void preOrder() {
		System.out.print("preorder : ");
		if (mRoot == null) {
			System.out.println("None");
			return;
		}
		System.out.println(mRoot.preOrder());
	}

	public void inOrder() {
		System.out.print("inorder : ");
		if (mRoot == null) {
			System.out.println("None");
			return;
		}
		System.out.println(mRoot.inOrder());

	}

	public void flattenBinaryTree() {
		System.out.println("Flatten binary seatch tree");
		if (mRoot == null) ;
		else
			mRoot.flattenBinaryTree(mRoot);
	}

	public void lowestCommonAncestor(Key k1, Key k2) {
		if (mRoot == null)
			System.out.println("The tree is empty");
		else if (k1 == null || k2 == null) {
			System.out.println("Error, the key is null");
		} else if (mRoot.find(k1) != null && mRoot.find(k2) != null) {
			Node n = mRoot.lowestCommonAncestor(mRoot, k1, k2);
			System.out.println("LCA of " + k1 + " and " + k2 + " : [" + n.getKey() + ":" + n.getValue() + "]");
		} else {
			System.out.println("The key doesnt exist");
		}

	}
}
