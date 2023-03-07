
public class TestBST {
	
	public static void main (String [] args)
	{
		MyBST<Integer> bst = new MyBST<Integer>();
		
		
		int j = (int) (Math.random() * 10) + 1 ;
		System.out.println("j: " + j);
		
		Integer[] ints = new Integer [j];
		
		for (int i = 0 ; i < j ; i ++)
		{
			int l = (int) (Math.random() * 20) ;
			System.out.println (l) ;
			ints[i] = l ;
			System.out.println(bst.add(l)) ;
		}
		
		for (int i = 0 ; i < j ; i ++)
		{
			System.out.println ("____") ;
			System.out.println (ints[i]) ;
			System.out.println (bst.remove(ints[i])) ;
			System.out.println (bst.getRoot()) ;
			
		}
		
	}
}
