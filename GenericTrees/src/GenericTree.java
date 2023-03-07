import java.util.ArrayDeque;
import java.util.ArrayList;


public class GenericTree {
	private TreeNode root;
	
	public GenericTree() {
		root = null;
	}
	
	public GenericTree(TreeNode root) {
		this.root = root;
	}
	
	public TreeNode getRoot() {
		return root;
	}
	
	public int size() {
		//YOUR CODE HERE
		//Code this method using a stack
		
		if (root == null) return 0;
		
		
		int size = 0 ;
		
		ArrayDeque <TreeNode> stack = new ArrayDeque <TreeNode> () ;
		
		// get started
		size += 1 ;
		ArrayList<TreeNode> childs = root.getChildren() ;
		for (int j = 0 ; j < childs.size() ; j ++)
		{
			stack.push(childs.get(j));
		}
		
		while (!stack.isEmpty())
		{
			// pop 
			ArrayList<TreeNode> childrens = stack.pop().getChildren() ;
			size ++ ;
			
			// add childs
			for (int j = 0 ; j < childrens.size() ; j ++)
			{
				stack.push(childrens.get(j));
			}
			
		}
		
		return size ;
	}
	
	public boolean contains(Object obj) {
		if (root == null) return false;
		//Code this method using recursion in the TreeNode class
		return root.contains(obj);
	}

	
	/*
	 * NOT GRADED
	 */
	public String toString() {
		// Order is irrelevant:
		// Each TreeNode's contents should be included, on a separate line
		return "";
	}
	
	public String toStringStack() {
		//Use a stack to do the toString
		StringBuilder str = new StringBuilder () ;
		ArrayDeque <TreeNode> stack = new ArrayDeque <TreeNode> () ;
		
		// get started
		str.append(root.getValue() + "\n") ;
		ArrayList<TreeNode> childs = root.getChildren() ;
		for (int j = 0 ; j < childs.size() ; j ++)
		{
			stack.push(childs.get(j));
		}
		
		while (!stack.isEmpty())
		{
			// pop 
			TreeNode node = stack.pop() ;
			str.append(node.getValue() + "\n") ; 
			
			ArrayList<TreeNode> childrens = node.getChildren() ;
			
			// add childs
			for (int j = 0 ; j < childrens.size() ; j ++)
			{
				stack.push(childrens.get(j));
			}
			
		}
		
		return str.toString();
	}
	
	public String toStringQueue() {
		//Use a queue to do the toString
		StringBuilder str = new StringBuilder () ;
		ArrayDeque <TreeNode> queue = new ArrayDeque <TreeNode> () ;
		
		// get started
		str.append(root.getValue() + "\n") ;
		ArrayList<TreeNode> childs = root.getChildren() ;
		for (int j = 0 ; j < childs.size() ; j ++)
		{
			queue.add(childs.get(j));
		}
		
		while (!queue.isEmpty())
		{
			// pop 
			TreeNode node = queue.remove() ;
			str.append(node.getValue() + "\n") ; 
			
			ArrayList<TreeNode> childrens = node.getChildren() ;
			
			// add childs
			for (int j = 0 ; j < childrens.size() ; j ++)
			{
				queue.add(childrens.get(j));
			}
			
		}
		
		return str.toString();
	}
	
	public String toStringRecursion() {
		//Use recursion to do the toString
//		return root.getValue() + " " + root.recursiveToString();
		return root.recursiveToString();
	}
}
