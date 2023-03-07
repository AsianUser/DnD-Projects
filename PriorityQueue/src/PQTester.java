
public class PQTester {
	public static void main (String [] args)
	{
		MyPQ<Integer> pq = new MyPQ<Integer>();
		
		pq.add(10);
		pq.add( 6);
		pq.add(11);
		pq.add( 8);
		pq.add( 9);
		pq.add( 5);
		pq.add( 7);
		
		while (!pq.isEmpty())
		{
			System.out.println (pq.removeMin()) ;
		}
	}
}
