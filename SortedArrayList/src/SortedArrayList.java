import java.util.ArrayList;
import java.util.Collections ;

/*
 * Main advantage: sorted
 *  - can use binarySearch
 */

public class SortedArrayList<E extends Comparable<E>> {

	private ArrayList<E> list;
	
	public SortedArrayList() {
		list = new ArrayList<E> () ;
	}
	
	public int size() {
		return list.size() ;
	}
	// O(logn)
	public boolean contains(E obj) {
		return Collections.binarySearch (list, obj) >= 0 ;
	}
	
	//May not contain more than one of the same object
	// BinarySearch, then insert			O(n)
	// SequentialSearch, then insert		O(n)
	// Add at end, then Quicksort			O(nlogn)
	
	public boolean add(E obj) {
		
		int pos = -(Collections.binarySearch (list, obj) + 1) ;
		if (pos >= 0)
		{
			list.add(pos, obj);
			return true ;
		}
		return false ;
		

	}
	
	// BinarySearch, then remove			O(n)
	// SequentialSearch, then remove		O(n)
	public boolean remove(E obj) {
		int pos = Collections.binarySearch (list, obj) ;
		if (pos >= 0)
		{
			list.remove(pos) ;
			return true ;
		}
		return false ;
	}
	
	// O(1)
	public E min() {
		return list.get(0) ;
	}
	
	// O(1)
	public E max() {
		return list.get(size() - 1) ;
	}
	
	public String toString ()
	{
		StringBuilder str = new StringBuilder () ;
		for (int j = 0 ; j < size () ; j ++)
		{
			str.append(list.get(j) + " ") ;
		}
		
		return str.toString() ;
	}
	
}
