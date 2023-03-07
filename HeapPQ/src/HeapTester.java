
public class HeapTester {
	public static void main (String [] args)
	{
		HeapPQ<Integer> pq = new HeapPQ<Integer>();
		
		pq.add(3);
		pq.add(6);
		pq.add(4);
//		pq.add( 8);
//		pq.add( 9);
//		pq.add( 5);
//		pq.add( 7);
//		pq.add(100);
//		pq.add(28);
//		pq.add(30);
//		
//		pq.peek() ;
//
//		System.out.println (pq.toString()) ;
//		
//		System.out.println (pq.removeMin()) ;
//		System.out.println (pq.toString()) ;
//
//		System.out.println (pq.removeMin()) ;
//		System.out.println (pq.toString()) ;
		
//		for (int j = 0 ; j < 10 ; j ++)
//		{
//			pq.add((int) (Math.random() * 100) - (int) (Math.random() * 100));
//		}
//		pq.add(null) ;
		System.out.println (pq.toString()) ;
		
		for (int j = 0 ; j < 3 ; j ++)
		{
			System.out.println ("min:" + pq.removeMin()) ;
//			pq.removeMin() ;
//			System.out.println (pq.toString()) ;
//			System.out.println ("___________________________________") ;
		}
		
		

	}

}