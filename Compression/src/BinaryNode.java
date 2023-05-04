
public class BinaryNode extends Node {

	int value;

	Node left;
	Node right;

	public BinaryNode (int value)
	{
		this.value = value;
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		super.setValue(value);
	}
	
	public Node getLeft()
	{
		return left;
	}

	public void setLeft(Node left)
	{
		this.left = left;
	}

	public Node getRight()
	{
		return right;
	}

	public void setRight(Node right)
	{
		this.right = right;
	}
	
	public boolean hasLeft()
	{
		return (left != null);
	}
	
	public boolean hasRight()
	{
		return (right != null);
	}
	
	public boolean hasChildren()
	{
		return (hasLeft() || hasRight());
	}

	public String toString()
	{
		return ("binNode - val: " + value);
	}
	
}
