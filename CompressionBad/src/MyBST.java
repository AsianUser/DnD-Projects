// Implements a BST with BinaryNode nodes

import java.lang.StringBuilder;

public class MyBST<E extends Comparable<E>> {
	
	private BinaryNode<E> root;  // holds the root of this BST

	// Constructor: creates an empty BST.
	public MyBST() {
		root = null ;
	}
	public MyBST (char character, E val)
	{
		root = new BinaryNode<E> (character, val);
	}

	public BinaryNode<E> getRoot() {
		return root;
	}
	
	public boolean hasRoot()
	{
		return (root != null);
	}
	
	public void setRoot(BinaryNode<E> root)
	{
		this.root = root;
	}
	
	// Returns true if this BST contains value; otherwise returns false.
	public boolean contains(E value)
	{
		BinaryNode <E> point = root ;
		while (point != null && !point.getValue().equals(value))
		{
			if (point.getValue().compareTo(value) > 0)
			{
				point = point.getLeft() ; 
			}
			else if (point.getValue().compareTo(value) < 0)
			{
				point = point.getRight() ;
			}
		}
		if (point == null)
			return false ;
		return true ;
				
	}

	// helper
	private BinaryNode<E> findPosOfPrev (BinaryNode<E> node, E value)
	{
		// if equal
		if (node.getValue().equals(value))
			return null ;
		
		// if can go left and has to go left
		if (node.hasLeft() && node.getValue().compareTo(value) > 0)
		{
			return findPosOfPrev(node.getLeft(), value) ;
		}
		// if can go right and has to go right
		else if (node.hasRight() && node.getValue().compareTo(value) < 0)
		{
			return findPosOfPrev(node.getRight(), value) ;
		}
		
		// if no way forward
		return node ;
		
	}
	
	// Adds value to this BST, unless this tree already holds value.
	// Returns true if value has been added; otherwise returns false.
	public boolean add(char character, E value)
	{
		// first val
		if (root == null)
		{
			root = new BinaryNode <E> (character, value) ;
			return true ;
		}
		
		
		// find position of previous node
		BinaryNode <E> node = findPosOfPrev (root, value) ;
		
		if (node == null)
		{
			return false ;
		}
		if (node.getValue().compareTo(value) > 0)
		{
			node.setLeft(new BinaryNode <E> (character, value));
		}
		else
		{
			node.setRight(new BinaryNode <E> (character, value));
		}
		return true ;
	}

	// Removes value from this BST.  Returns true if value has been
	// found and removed; otherwise returns false.
	
	/*
	 * 0 child: parent disown
	 * 1 child: parent adopt child
	 * 2 child: find heir
	 * 			(smallest on right, largest on left)
	 * 			copy heir's value into removed node
	 * 			REMOVE heir's orig
	 */
	
	public boolean remove(E value)
	{
		if (contains(value))
		{
			root = removeHelper (value, root) ;
			return true ;
		}
		return false ;
		
//		return removeHelper (value, root) ;
	}
	
	// returns the removed node
	private BinaryNode<E> removeHelper (E value, BinaryNode<E> root)
	{
		// if empty
		if (root == null)
		{
			return null ;
//			return false ;
		}
		
		// find node
		
		// set right to next node after deletion
		if (root.getValue().compareTo(value) < 0)
		{
//			return removeHelper(value, root.getRight()) ;
			root.setRight(removeHelper(value, root.getRight()));
		}
		// set left to the next node after deletion
		else if (root.getValue().compareTo(value) > 0)
		{
//			return removeHelper (value, root.getLeft()) ;
			root.setLeft(removeHelper (value, root.getLeft())) ;
		}
		
		// once found
//		else if (root.getValue().compareTo(value) == 0)
		else
		{
			// if 0-1 child
			if (root.getLeft() == null)
			{
				return root.getRight() ;
//				root = root.getRight() ;
//				return true ;
			}
			if (root.getRight() == null)
			{
				return root.getLeft() ;
//				root = root.getLeft() ;
//				return true ;
			}
			
			// if 2 child
			
			// find heir & return val
			E maxLeft = findMaxHelper(root).getValue() ;
			root.setValue(maxLeft);
			// delete heir
			root.setLeft(removeHelper (maxLeft, root.getLeft()));
//			return removeHelper (maxLeft, root.getLeft()) ;
			
			
		}
		
		// keep val of previous ones same once deleted nodes are dealt with
		return root ;
		
		
	}
	
	// find max on left side of tree
	public BinaryNode<E> findMaxHelper (BinaryNode<E> root)
	{
		if (root.getLeft() == null)
			return root ;
		
		BinaryNode <E> max = root.getLeft() ;
		while (max.getRight() != null)
		{
			max = max.getRight() ;
		}
		return max ;
	}
	
	public BinaryNode<E> findMax (BinaryNode<E> root)
	{
		BinaryNode <E> max = root;
		while (max.getRight() != null)
		{
			max = max.getRight() ;
		}
		return max ;
	}
	
	public BinaryNode<E> getMin (BinaryNode<E> root)
	{
		BinaryNode <E> min = root;
		while (min.getLeft() != null)
		{
			min = min.getLeft() ;
		}
		return removeHelper (root.getValue(), root);
	}
	
	

	// Returns a bracket-surrounded, comma separated list of the contents of the nodes, in order
	// e.g. ["Apple", "Cranberry", "Durian", "Mango"]
	public String toString()
	{
		return "[" + toStringHelper(root) + "]" ;
	}
	
	private String toStringHelper (BinaryNode<E> node)
	{ // 6 lines
		// if empty
		if (node == null)
			return "" ;
		
		// if leaf
		if (!node.hasChildren())
		{
			return node.toString() ;
		}
		
		StringBuilder str = new StringBuilder () ;
		// add left
		if (node.hasLeft())
			str.append(toStringHelper (node.getLeft()) + ", ") ;
		// add val
		str.append(node.toString()) ;
		// add right
		if (node.hasRight ())
			str.append(", " + toStringHelper(node.getRight())) ;
		
		return str.toString() ;
	}


}
