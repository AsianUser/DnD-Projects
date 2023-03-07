import java.io.File;
import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.PriorityQueue;

/* You are allowed (and expected!) to use either Java's ArrayDeque or LinkedList class to make stacks and queues,
 * and Java's PriorityQueue class to make a priority queue */

public class CookieMonster {

	private int [][] cookieGrid;
	private int numRows;
	private int numCols;

	//Constructs a CookieMonster from a file with format:
	//numRows numCols
	//<<rest of the grid, with spaces in between the numbers>>
	public CookieMonster(String fileName) {
		int row = 0;
		int col = 0;
		try
		{
			Scanner input = new Scanner(new File(fileName));

			numRows    = input.nextInt();  
			numCols    = input.nextInt();
			cookieGrid = new int[numRows][numCols];

			for (row = 0; row < numRows; row++) 
				for (col = 0; col < numCols; col++)
					cookieGrid[row][col] = input.nextInt();

			input.close();
		}
		catch (Exception e)
		{
			System.out.print("Error creating maze: " + e.toString());
			System.out.println("Error occurred at row: " + row + ", col: " + col);
		}

	}

	public CookieMonster(int [][] cookieGrid) {
		this.cookieGrid = cookieGrid;
		this.numRows    = cookieGrid.length;
		this.numCols    = cookieGrid[0].length;
	}

	public int checkGrid (int row, int col)
	{
		if (row >= numRows || col >= numCols || cookieGrid[row][col] < 0)
			return -1;

		return cookieGrid [row][col] ;
	}

	/* RECURSIVELY calculates the route which grants the most cookies.
	 * Returns the maximum number of cookies attainable. */
	public int recursiveCookies() {
		if (cookieGrid == null || cookieGrid.length == 0 || cookieGrid[0].length == 0)
			return 0;

		return recursiveCookieHelper (0, 0, 0) ;
	}	

	public int recursiveCookieHelper (int row, int col, int max)
	{
		// grid is invalid, return max
		int gridChecked = checkGrid (row, col) ;
		if (gridChecked < 0)
		{
			return max ;
		}

		int right = 0, down = 0 ;
//		System.out.println ("Row" + row + "Col" + col) ;
		// go right
		right = recursiveCookieHelper (row, col + 1, max + cookieGrid[row][col]) ;
		// go down
		down = recursiveCookieHelper (row + 1, col, max + cookieGrid[row][col]) ;

		// return biggest path
		return Math.max(right, down) ;
	}

	/* Calculate which route grants the most cookies using a QUEUE.
	 * Returns the maximum number of cookies attainable. */
	/* From any given position, always add the path right before adding the path down */
	
	public int queueCookies() {
		ArrayDeque<OrphanScout> abandonment = new ArrayDeque <OrphanScout> () ;
		int max = 0 ;

		// start - add one
		OrphanScout start = new OrphanScout (0, 0, cookieGrid [0][0]) ;
		if (start.getCookiesDiscovered() >= 0)
		{
			if (start.getCookiesDiscovered() >= max)
				max = start.getCookiesDiscovered() ;
			abandonment.addLast(start) ;
		}
		
		// while not empty
		while (!abandonment.isEmpty ())
		{
			OrphanScout scout = abandonment.removeFirst() ;
			int row = scout.getEndingRow() ;
			int col = scout.getEndingCol() ;
			int cookie = scout.getCookiesDiscovered() ;
			
			int rightGrid = checkGrid (row, col+1) ;
			int downGrid = checkGrid (row+1, col) ;
			// if right is clear
			if (rightGrid >= 0)
			{
				OrphanScout right = new OrphanScout (row, col+1, cookie + rightGrid) ;
				if (right.getCookiesDiscovered() >= max)
				{
					max = right.getCookiesDiscovered() ;
				}
				abandonment.addLast(right) ;
			}
			// if down is clear
			if (downGrid >= 0)
			{
				OrphanScout down = new OrphanScout (row+1, col, cookie + downGrid) ;
				if (down.getCookiesDiscovered() >= max)
				{
					max = down.getCookiesDiscovered() ;
				}
				abandonment.addLast(down) ;
			}
		}

		return max ;
	}

	/* Calculate which route grants the most cookies using a stack.
	 * Returns the maximum number of cookies attainable. */
	/* From any given position, always add the path right before adding the path down */
	public int stackCookies() {
		ArrayDeque<OrphanScout> abandonment = new ArrayDeque <OrphanScout> () ;
		int max = 0 ;

		// start - add one
		OrphanScout start = new OrphanScout (0, 0, cookieGrid [0][0]) ;
		if (start.getCookiesDiscovered() >= 0)
		{
			if (start.getCookiesDiscovered() >= max)
				max = start.getCookiesDiscovered() ;
			abandonment.addFirst(start) ;
		}
		
		// while not empty
		while (!abandonment.isEmpty ())
		{
			OrphanScout scout = abandonment.removeFirst() ;
			int row = scout.getEndingRow() ;
			int col = scout.getEndingCol() ;
			int cookie = scout.getCookiesDiscovered() ;
			
			int rightGrid = checkGrid (row, col+1) ;
			int downGrid = checkGrid (row+1, col) ;
			// if right is clear
			if (rightGrid >= 0)
			{
				OrphanScout right = new OrphanScout (row, col+1, cookie + rightGrid) ;
				if (right.getCookiesDiscovered() >= max)
				{
					max = right.getCookiesDiscovered() ;
				}
				abandonment.addFirst(right) ;
			}
			// if down is clear
			if (downGrid >= 0)
			{
				OrphanScout down = new OrphanScout (row+1, col, cookie + downGrid) ;
				if (down.getCookiesDiscovered() >= max)
				{
					max = down.getCookiesDiscovered() ;
				}
				abandonment.addFirst(down) ;
			}
		}

		return max ;
	}

	/* Calculate which route grants the most cookies using a priority queue.
	 * Returns the maximum number of cookies attainable. */
	/* From any given position, always add the path right before adding the path down */
	public int pqCookies() {
		PriorityQueue<OrphanScout> abandonment = new PriorityQueue <OrphanScout> () ;
		int max = 0 ;

		// start - add one
		OrphanScout start = new OrphanScout (0, 0, cookieGrid [0][0]) ;
		if (start.getCookiesDiscovered() >= 0)
		{
			if (start.getCookiesDiscovered() >= max)
				max = start.getCookiesDiscovered() ;
			abandonment.add(start) ;
		}
		// while not empty
		while (!abandonment.isEmpty ())
		{
			OrphanScout scout = abandonment.poll() ;
			int row = scout.getEndingRow() ;
			int col = scout.getEndingCol() ;
			int cookie = scout.getCookiesDiscovered() ;
			
			int rightGrid = checkGrid (row, col+1) ;
			int downGrid = checkGrid (row+1, col) ;
			// if right is clear
			if (rightGrid >= 0)
			{
				OrphanScout right = new OrphanScout (row, col+1, cookie + rightGrid) ;
				if (right.getCookiesDiscovered() >= max)
				{
					max = right.getCookiesDiscovered() ;
				}
				abandonment.add(right) ;
			}
			// if down is clear
			if (downGrid >= 0)
			{
				OrphanScout down = new OrphanScout (row+1, col, cookie + downGrid) ;
				if (down.getCookiesDiscovered() >= max)
				{
					max = down.getCookiesDiscovered() ;
				}
				abandonment.add(down) ;
			}
		}

		return max ;
	}


}
