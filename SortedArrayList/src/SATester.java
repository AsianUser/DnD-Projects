
public class SATester {
	public static void main (String [] args)
	{
		SortedArrayList <Integer> sal = new SortedArrayList <Integer> () ;
		
		sal.add(1) ;
		System.out.println (sal) ;
		sal.add(3) ;
		System.out.println (sal) ;
		sal.add(4) ;
		System.out.println (sal) ;
		sal.add(2) ;
		System.out.println (sal) ;
		
		sal.remove(4) ;
		System.out.println (sal) ;
		sal.remove(1) ;
		System.out.println (sal) ;
		sal.remove(2) ;
		System.out.println (sal) ;
		sal.remove(3) ;
		System.out.println (sal) ;
	}
}
