
public class CookieMonsterTest {
	public static void main (String [] args)
	{
		int [][] grid = {{ 4, 0},
						 {10,-2},
						 {-1,1000}} ;
			
//			{{ 1,10, -1, 2},
//						 { 1,-1,-1,-1},
//						 { 1, 1, 1,1000000000}} ;
		
		CookieMonster CMon = new CookieMonster (grid) ;
		
		System.out.println (CMon.recursiveCookies()) ;
		System.out.println (CMon.stackCookies()) ;
		System.out.println (CMon.queueCookies()) ;
		System.out.println (CMon.pqCookies()) ;
	}
}
