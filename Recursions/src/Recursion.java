
import java.util.Objects;

public class Recursion {

	//Prints the value of every node in the singly linked list with the given head, but in reverse
	public static void reverseList(ListNode head) {
		if (head.getNext() != null)
			reverseList (head.getNext()) ;
		System.out.println (head.getValue()) ;
	}

	//A kid at the bottom of the stairs can jump up 1, 2, or 3 stairs at a time.
	//How many different ways can they jump up n stairs?
	// Jumping 1-1-2 is considered different than jumping 1-2-1
	public static long waysToJumpUpStairs(int n) {
		if (n == 0)
			return 1 ;
		else if (n == 1)
			return 1 ;
		else if (n == 2)
			return 2 ;

		return (waysToJumpUpStairs (n-1) + waysToJumpUpStairs (n-2) + waysToJumpUpStairs (n-3))  ;
	}


	//How many subsets are there of the numbers 1...n
	// that don't contain any consecutive integers?

	// every subset either contains n or doesnt
	// if has n, cant have n-1 (nonConsecutiveSubset(n-2)). if no have n then can have n-1 (nonConsecutiveSubset(n-1))
	// break up scavhunt into this

	public static long nonConsecutiveSubsets(int n) {
		if (n == 0)
			return 1 ;
		else if (n == 1)
			return 2 ;
		return (nonConsecutiveSubsets (n-1) + nonConsecutiveSubsets (n-2)) ;
	}


	// has all the base cases

	// can we start infected ??

	public static void infect(String[][] grid, int x, int y) {
		if (!Objects.equals (grid [x][y], "infected") && !Objects.equals (grid [x][y], "vaccinated"))
		{
			grid [x][y] = "infected" ;
			if (x != 0 && grid [x-1].length >= y + 1) // go up (check if not end and if has room)
				infect (grid, x-1, y) ;
			if (x+1 < grid.length && grid [x+1].length >= y + 1) // go up (check if not end and if has room)
				infect (grid, x+1, y) ;
			if (y != 0) // go up (check if not end and if has room)
				infect (grid, x, y-1) ;
			if (y+1 < grid[x].length) // go up (check if not end and if has room)
				infect (grid, x, y+1) ;
		}
	}



	//
	// --Line of Special Cases --> ---------------------------------------
	//


	//Prints all the permutations of str on separate lines
	//You may assume that str has no repeated characters
	//Order is your choice

	private static void permuteHelper (String str, int index){
		if (index == str.length())
			System.out.println (str) ;
		for (int j = index ; j < str.length() ; j ++)
		{ 
			// replace index with self or any of other options

			// replaces charAt index with charAt j
			char [] charArr = str.toCharArray() ;
			char replaceIndex = charArr[j] ;
			charArr [j] = charArr[index] ;
			charArr [index] = replaceIndex ;

			// reassemble string
			String newStr = "" ;
			for (int k = 0 ; k < charArr.length ; k ++)
			{
				newStr += charArr[k] ;
			}

			// recurse
			permuteHelper (newStr , index+1) ; 
		}
	}

	public static void permute(String str) {
		permuteHelper (str, 0) ;
	}


	//Prints all the subsets of str on separate lines
	//You may assume that str has no repeated characters
	//For example, subsets("abc") would print out "", "a", "b", "c", "ab", "ac", "bc", "abc"
	//Order is your choice

	public static void subsetHelper (String base, String remaining)
	{
		if (remaining.length() == 0)
			System.out.println (base) ;
		else
		{	// either contains char at start of remaining or doesnt

			// contains
			subsetHelper (base + remaining.substring(0,1), remaining.substring(1)) ;

			// doesnt contain
			subsetHelper (base, remaining.substring(1)) ;

		}
	}

	public static void subsets(String str) {
		subsetHelper ("", str) ;
	}

	//Prints a sequence of moves (one on each line)
	// to complete a Towers of Hanoi problem with
	//n disks starting on tower 0, ending on tower 1.
	// The towers are number 0, 1, 2, and each move should be of
	//the form "1 -> 2", meaning "take the top disk of tower 1 and 
	//put it on tower 2" etc.
	private static void hanoiSolver (int disk , int towerOriginal , int towerTarget , int towerTower , int diskCount)
	{

		if (disk == 1)
		{
			System.out.println (towerOriginal + " -> " + towerTarget) ;
		}
		else
		{
			hanoiSolver (disk-1, towerOriginal, towerTower, towerTarget, diskCount) ;
			System.out.println (towerOriginal + " -> " + towerTarget) ;
			hanoiSolver (disk-1, towerTower, towerTarget, towerOriginal, diskCount) ;
		}

	}

	public static void solveHanoi (int n) {

		hanoiSolver (n, 0, 1, 2, n) ;

	}



	private static void quickSorter (int[] array, int leftIndex, int rightIndex)
	{

		int pivot = (leftIndex + rightIndex)/2 ; // index at middle of list
//		System.out.println (":" + pivot + " " + array[pivot]) ;

		// sorting
		int j = leftIndex, k = rightIndex ;
//		System.out.println ("j" + j + "k" + k) ;

		while (j < k)
		{

			// while values on leftside are less than pivot, move on
			while (array[j] < array[pivot])
			{
				j ++ ;
//				System.out.println ("j" + j + " " + array[j]) ;
			}

			// while values on right side are greater than pivot, move on
			while (array [k] > array[pivot])
			{
				k -- ;
//				System.out.println ("k" + k + " " + array[k]) ;
			}

			//swap two vals
			if (j < k) // <-- not sure if "if" necessary (they are)
			{
//				System.out.println ("swap j" + array[j] + "k" + array[k]) ;
				int l = array [j] ;
				array[j] = array[k] ;
				array[k] = l ;

				// if swap on pivot:
				if (j == pivot)
				{
					pivot = k ;
//					System.out.println ("pivot change k " + pivot) ;
				}
				else if (k == pivot)
				{
					pivot = j ;
//					System.out.println ("pivot change j " + pivot) ;
				}

				// move to prevent avoid getting stuck on == pivot  ??
				j ++ ;
				//				k-- ;
			}

		}

//		for (int i = 0 ; i < array.length ; i ++)
//		{
//			System.out.print (array[i] + " ") ;
//		}

//		System.out.println ("p " + pivot) ;
		// recurse on left
		if (leftIndex < pivot - 1)
			quickSorter (array, leftIndex, pivot - 1) ;
		// recurse on right
		if (pivot + 1 < rightIndex)
			quickSorter (array, pivot, rightIndex) ;
	}

	//Performs a quickSort on the given array of ints
	//Use the middle element (index n/2) as the pivot
	public static void quickSort(int[] ints) {
		if (ints.length > 1)
			quickSorter  (ints, 0, ints.length - 1) ;
	}


	// You are partaking in a scavenger hunt!
	// You've gotten a secret map to find many of the more difficult
	// items, but they are only available at VERY specific times at
	// specific places.  You have an array, times[], that lists at which
	// MINUTE an item is available.
	// Items in the ScavHunt are worth varying numbers of points.
	// You also have an array, points[], same length as times[],
	// that lists how many points each of the corresponding items is worth.
	// Problem is: to get from one location to the other takes 5 minutes,
	// so if there is an item, for example, available at time 23 and another
	// at time 27, it's just not possible for you to make it to both: you'll
	// have to choose!
	// (but you COULD make it from a place at time 23 to another at time 28)
	// Write a method that returns the maximum POINTS you can get.

	// strat: either you go to first thing or you arent OR repeat whatever you did for subsets

	//

	
	private static int scavHunter (int[] times, int[] points, int index, int sumPoints, int indexLastHunt)
	{	
		if (index >= points.length)
		{
			return sumPoints ;
		}

		else
		{
			int has = 0, noHas = 0 ;
			if (index == 0 || indexLastHunt < 0 || times[index] >= times [indexLastHunt] + 5) // if time is possible or at start, 2 possibilities
			{
//								System.out.println ("i" + index) ;
				has = scavHunter (times, points, index + 1, sumPoints + points[index], index) ; // has
				noHas = scavHunter (times, points, index + 1, sumPoints, indexLastHunt) ; // no has
			}
			else // if time not possible, move on
			{
//				System.out.println ("i" + index) ;
				noHas = scavHunter (times, points, index + 1, sumPoints, indexLastHunt) ;
			}


			if (has > noHas)
			{
				return has ;
			}
			else
			{
				return noHas ;
			}

		}
	}
	
	public static int scavHunt(int[] times, int[] points) {
		return scavHunter (times, points, 0, 0, -1) ;
	}
	
	
	// old  version - works but can  be improved

//	private static void scavHunter (int[] times, int[] points, int index, int sumPoints, int indexLastHunt)
//	{	
//		if (index >= points.length)
//		{
//			// compare
//			if (sumPoints > maxPoints)
//				maxPoints = sumPoints ;
//		}
//
//		else
//		{
//			if (index == 0 || indexLastHunt < 0 || times[index] >= times [indexLastHunt] + 5) // if time is possible or at start, 2 possibilities
//			{
//				scavHunter (times, points, index + 1, sumPoints + points[index], index) ;
//				scavHunter (times, points, index + 1, sumPoints, indexLastHunt) ;
//			}
//			else
//			{
//				scavHunter (times, points, index + 1, sumPoints, indexLastHunt) ;
//			}
//
//		}
//	}
//
//	private static int maxPoints = 0;
//
//	public static int scavHunt(int[] times, int[] points) {
//		scavHunter (times, points, 0, 0, -1) ;
//		return maxPoints ;
//	}
	
	


	// ---------------------------------------------------------
	// ----------- line of incompletion -----------------------------------------
	// ----------- there be broken code --> --------------------
	// ---------------------------------------------------------

	

	//	//Performs a mergeSort on the given array of ints
	//	public static void mergeSort(int[] ints) {
	//
	//	}







}
