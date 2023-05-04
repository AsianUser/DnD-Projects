
public class Node {
	
	private int value;
	private char character;

	Node left;
	Node right;


	public int getValue()
	{
		return value;
	}
	
	public char getChar ()
	{
		return character;
	}

	public void setValue(int value)
	{
		this.value = value;
	}
	
	public void setChar (char character)
	{
		this.character = character;
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
		return ("value: " + value);
	}


}
