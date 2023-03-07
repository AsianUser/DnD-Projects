
public class retester {
	public static void main (String [] args)
	{
		int [] g = {3,4,5,6,7,8,9} ;
		int [] f = {5,9,9,0,2,7,6} ;
		Recursion.quickSort(f);
		
		for (int j = 0 ; j < f.length ; j++)
		{
			System.out.println (f[j]) ;
		}
		
	}
}
