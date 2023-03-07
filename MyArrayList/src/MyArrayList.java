import java.util.Objects;

/* See ArrayList documentation here:
 * http://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
 */

/*
 * Your indexed functions should throw IndexOutOfBoundsException if index is invalid!
 */

public class MyArrayList<E> {

	/* Internal Object counter */
	protected int objectCount;
	/* Internal Object array */
	protected E [] internalArray;
	
	// O(1)
	/* Constructor: Create it with whatever capacity you want? */
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		this.internalArray = (E[])new Object[100];
	}
	
	// O(1)
	/* Constructor with initial capacity */
	@SuppressWarnings("unchecked")
	public MyArrayList(int initialCapacity){
		if (initialCapacity < 0)
			throw new IndexOutOfBoundsException(initialCapacity) ;
		this.internalArray = (E[])new Object[initialCapacity];
	}

	// O(1)
	/* Return the number of active slots in the array list */
	public int size() {
		/* ---- YOUR CODE HERE ---- */
		return objectCount ;
	}

	// O(1)
	/* Are there zero objects in the array list? */
	public boolean isEmpty() {
		/* ---- YOUR CODE HERE ---- */
		return (objectCount == 0) ;
	}

	// O(1)
	/* Get the index-th object in the list. */
	public E get(int index) {
		/* ---- YOUR CODE HERE ---- */
		if (index >= objectCount || index < 0)
			throw new IndexOutOfBoundsException(index) ;

		return (internalArray[index]) ;
	}


	// O(1)
	/* Replace the object at index with obj.  returns object that was replaced. */
	public E set(int index, E obj) {
		/* ---- YOUR CODE HERE ---- */
		
		if (index > objectCount || index < 0)
			throw new IndexOutOfBoundsException(index) ;
		
		E original = internalArray [index] ;
		internalArray[index] = obj ;
		
		return original ;
		
	}

	// O(n)
	/* Returns true if this list contains an element equal to obj;
	 otherwise returns false. */
	public boolean contains(E obj) {
		/* ---- YOUR CODE HERE ---- */
		return (indexOf (obj) >= 0) ;
		
	}



//
	
	// O(1) + O(n) --> O(n)
	// best case O(1)
	/* Insert an object at index */
	@SuppressWarnings("unchecked")
	public void add(int index, E obj) {
		/* ---- YOUR CODE HERE ---- */
		if (index > objectCount || index < 0)
			throw new IndexOutOfBoundsException(index) ;
		
		shift (index) ;
		internalArray [index] = obj ;
		objectCount ++ ;
		
	}

	// O(n) + O(1) --> O(n)
	/* Add an object to the end of the list; returns true */
	@SuppressWarnings("unchecked")
	public boolean add(E obj) {
		/* ---- YOUR CODE HERE ---- */
		add (objectCount, obj) ;
		return true ;
		
	}

	// O(n)
	/* Remove the object at index and shift.  Returns removed object. */
	public E remove(int index) {
		/* ---- YOUR CODE HERE ---- */
		if (index > objectCount || index < 0)
			throw new IndexOutOfBoundsException(index) ;
		
		E orig = internalArray [index] ;
		compress(index) ;
		objectCount -- ;
		return orig ;
	}

	// Best: O(n) Worst: O(n)
	/* Removes the first occurrence of the specified element from this list, 
	 * if it is present. If the list does not contain the element, it is unchanged. 
	 * More formally, removes the element with the lowest index i such that
	 * (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists). 
	 * Returns true if this list contained the specified element (or equivalently, 
	 * if this list changed as a result of the call). */
	public boolean remove(E obj) {
		/* ---- YOUR CODE HERE ---- */
		int index = indexOf(obj) ;
		if (index == -1)
			return false ;
		else
		{
			remove(index) ;
			return true ;
		}
	}

	// O(n)
	// This method will search list for all occurrences of obj and move them to the end
	// of the list without disrupting the order of the other elements.
	@SuppressWarnings("unchecked")
	public void moveToBack(E obj)
	{
		E [] tempArray = (E[])new Object[objectCount] ;
		int front = 0 ;
		int back = objectCount - 1 ;
		for (int j = 0 ; j < objectCount ; j++)
		{
			if (Objects.equals(internalArray[j], obj))
			{
				tempArray [back] = obj ;
				back -- ;
			}
			else
			{
				tempArray [front] = internalArray[j] ;
				front ++ ;
			}
		}
		internalArray = tempArray ;
		
		

	}

	// O(n)
	/* For testing; your string should output as "[X, X, X, X, ...]" where X, X, X, X, ... are the elements in the ArrayList.
	 * If the array is empty, it should return "[]".  If there is one element, "[X]", etc.
	 * Elements are separated by a comma and a space. */
	public String toString() {
		/* ---- YOUR CODE HERE ---- */
		
		if (objectCount == 0)
			return "[]" ;
		
		StringBuilder output = new StringBuilder ("[") ;
		if (objectCount == 1)
			return output.append(internalArray [0] + "]").toString() ;
		
		
		for (int j = 0 ; j < objectCount ; j ++)
			output.append(internalArray[j] + ", ") ;
		
		return output.substring(0, output.length() - 2) + "]" ;
	}
	
	
	
	
	// ease of life methods

		// O(n)
		@SuppressWarnings("unchecked")
		private void expand (int newSize)
		{
			E [] newArray = (E[])new Object[newSize] ;

			for (int j = 0 ; j < internalArray.length ; j ++)
			{
				newArray [j] = internalArray [j] ;
			}

			internalArray = newArray ;
			
		}
		
		// O(n)
		@SuppressWarnings("unchecked")
		private void shift(int index) // clears up pos index
		{
			if (objectCount + 1 >= internalArray.length)
				expand (internalArray.length * 2) ;
			
//			System.out.println(objectCount) ;
			for (int j = objectCount ; j > index ; j --)
				internalArray [j] = internalArray [j-1] ;
		}
		
		// O(1)
		@SuppressWarnings("unchecked")
		private void compress(int index)
		{
			for (int j = index ; j < objectCount ; j++) // was internalArray.length - 1
				internalArray [j] = internalArray [j+1] ;
		}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	// extra
	
	// O(n)
	// return index of first instance of obj
	public int indexOf (E obj)
	{
		for (int j = 0 ; j < objectCount ; j ++)
		{
			if (Objects.equals(internalArray[j],obj))
			{
				return j ;
			}
		}
		return (-1) ;
	}
	
	// O(n)
	// clear entire arraylist
	@SuppressWarnings("unchecked")
	public void clear()
	{
		
		internalArray = (E[])new Object [internalArray.length] ;
		
//		//O(n)
//		// start from back, so remove is always O(1)
//		for (int j = objectCount - 1 ; j >= 0 ; j--)
//		{
//			remove(j) ;
//		}
		
//		//O(n^2)
//		while (objectCount > 0) // || !isEmpty()
//		{
//			remove(0) ;
//		}
	}
	
	//Best: O(1) Worst: O(n)
	// remove last instance of obj
	public boolean removeLast (E obj)
	{
		for (int j = objectCount - 1 ; j >= 0 ; j --)
		{
			if (internalArray[j].equals(obj))
			{
				compress (j) ;
				return true ;
			}
		}
		
		return false ;
	}
	
	// m is size of other
	// Best: O(m * n) < -- dont need to remove
	// Worst: O(m * n) <-- actually remove smth
	// remove all in other arraylist from current arraylist
	@SuppressWarnings("unchecked")
	public boolean removeAll (MyArrayList<E> other)
	{
		for (int j = 0 ; j < other.size() ; j++)
		{
			int pos = 0 ;
			int count = 0 ;
			E [] tempArray = (E[])new Object[this.objectCount - 1] ;


			for (int k = 0 ; k < objectCount ; k ++)
			{
				// if not in other array, add
				if (!other.get(j).equals(internalArray[k]))
				{
					tempArray [pos] = internalArray[k] ;
					pos ++ ;
				}
				// if in other array, dont add and shift objectCount
				else
				{
					count ++ ;
				}
			}
			
			internalArray = tempArray ;
			objectCount -= count ;
			
		}
		return true ;
	}
	
	// remove all in range
	public void removeRange (int startIndex, int fromIndex)
	{
		
	}

}

