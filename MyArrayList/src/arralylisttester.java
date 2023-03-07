import java.util.ArrayList;


public class arralylisttester {

	public static void main (String [] args)
	{
		
		// test
		// start
		// end
		// 0
		// 1
		
		ArrayList<String> list = new ArrayList<String>(5);

		System.out.println (list.get(0)) ;
		
		
		
		MyArrayList<Integer> myList = new MyArrayList<Integer>(1);
		
		System.out.println (myList.get(0)) ;
		myList.add (3) ;
		myList.remove((Integer)3) ;

		System.out.println (myList.size()) ;

	}
}
