import java.util.ArrayList;
import java.lang.StringBuilder;

public class TreeNode {
	private ArrayList<TreeNode> children;
	private Object value;
	

	public TreeNode(Object value) {
		this.value = value;
		children = new ArrayList<TreeNode>();
	}
	public ArrayList<TreeNode> getChildren() {
		return children;
	}
	
	public void addChild(Object value) {
		children.add(new TreeNode(value));
	}
	
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	public boolean contains(Object obj) {
		//YOU DO THIS
		if (value.equals(obj))
		{
			return true ;
		}
		
		for (int j = 0 ; j < children.size() ; j ++)
		{
			if (children.get(j).contains(obj))
				return true;
		}
		return false ;
	}

	public String toString() {
		return value.toString();
	}
	
	public String recursiveToString() {
		//YOU DO THIS
		StringBuilder str = new StringBuilder () ;
//		
//		if (children.size() == 0)
			str.append(value + "\n") ;
//		else
		{
			for (int j = 0 ; j < children.size() ; j ++)
			{
				str.append (children.get(j).recursiveToString()) ;
			}
		}

		return str.toString();
	}
}
