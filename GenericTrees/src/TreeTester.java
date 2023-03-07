
import java.util.ArrayList;
import java.util.Random;


public class TreeTester {
	public static void main (String [] args)
	{
//		Integer[] list = {3,9,7,5,4,10,5,6,2,1,8};
		Integer[] list = {9,3};

		TreeNode roots = new TreeNode(12);

		GenericTree tree = new GenericTree(roots);

		Random r = new Random(10000);
		ArrayList<TreeNode> nodeList = new ArrayList<TreeNode>();
		nodeList.add(tree.getRoot());
		tree.getRoot().addChild(list[0]);
		nodeList.add(tree.getRoot().getChildren().get(0));
		for (int i = 1; i < list.length; i++) {
			Object c = list[i];
			int randIndex = r.nextInt(nodeList.size()-1);
			TreeNode parent = nodeList.get(randIndex);
			parent.addChild(c);
			nodeList.add(parent.getChildren().get(parent.getChildren().size()-1));
		}
		
		
		
		System.out.println (tree.toStringRecursion()) ;
		System.out.println (tree.contains(12)) ;
	}
}
