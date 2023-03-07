import java.lang.StringBuilder;
import java.util.Objects;

// Implements a singly-linked list.

public class MyHashSet {
	private ListNode[] buckets;
	private int objectCount;
	private double loadFactorLimit;
		// low load factor --> faster, but larger
		// high load factor --> slower, but smaller

	// Constructor: creates an empty hash set with default parameters
	public MyHashSet()
	{
		this.buckets = new ListNode[10];
		this.objectCount = 0;
		this.loadFactorLimit = 0.75;
	}

	// Constructor: creates a hash set with the given initial bucket size and load factor limit
	public MyHashSet(int bucketCount, double loadFactorLimit)
	{
		this.buckets = new ListNode[bucketCount];
		this.objectCount = 0;
		this.loadFactorLimit = loadFactorLimit;
	}

	// Return a pointer to the bucket array
	public ListNode[] getBuckets() {
		return this.buckets;
	}

	// Returns true if this set is empty; otherwise returns false.
	public boolean isEmpty()
	{
		return (objectCount == 0);
	}

	// Returns the number of elements in this set.
	public int size()
	{
		return objectCount;
	}
	
	// Return the bucket index for the object
	public int whichBucket(Object obj) {
		return (0x7FFFFFFF & obj.hashCode()) % this.buckets.length;
	}

	// Returns the current load factor (objCount / buckets)
	public double currentLoadFactor() {
		/* -- IMPLEMENT THIS -- */
		return (double) objectCount / buckets.length ;
	}


	// Return true if the object exists in the set, otherwise false.
	// Use the .equals method to check equality.
	public boolean contains(Object obj) {
		/* -- IMPLEMENT THIS -- */
		int pos = whichBucket (obj) ;
		ListNode b = buckets[pos] ;
		while (b != null)
		{
			if (Objects.equals(b.getValue(),obj))
			{
				return true ;
			}
			
			b = b.getNext() ;
		}
		return false ;
	}

	// Add an object to the set.
	// If the object already exists in the set you should *not* add another.
	// Return true if the object was added; false if the object already exists.
	// If an item should be added, add it to the beginning of the bucket.
	// After adding the element, check if the load factor is greater than the limit.
	// - If so, you must call rehash with double the current bucket size.
	public boolean add(Object obj) {
		/* -- IMPLEMENT THIS -- */
		if (contains (obj))
			return false ;
		
		// add
		int pos = whichBucket(obj) ;
		ListNode b = new ListNode (obj, buckets[pos]) ;
		buckets[pos] = b ;
		objectCount ++ ;
		
		// check and rehash
		if (currentLoadFactor () > loadFactorLimit)
		{
			rehash (2 * buckets.length) ;
		}

		return true ;
	}

	// Remove the object.  Return true if successful, false if the object did not exist
	public boolean remove(Object obj) {
		/* -- IMPLEMENT THIS -- */
		int pos = whichBucket (obj) ;
		
		// find obj within bucket
		ListNode b = buckets[pos] ;
		
		// check node
		if (b == null)
			return false ;
		if (Objects.equals(b.getValue(),obj)) // use this Objects.equals ()
		{
			buckets[pos] = b.getNext() ;
			objectCount -- ;
			return true ;
		}
		// check next
		while (b.getNext() != null)
		{
			// if next is equal
			if (Objects.equals(b.getNext().getValue(), obj))
			{
				// set next to one after next
				b.setNext(b.getNext().getNext());
				objectCount -- ;
				return true ;
			}
			
			// move forward
			b = b.getNext() ;
		}
		return false ;
	}

	// Rehash the set so that it contains the given number of buckets
	// Loop through all existing buckets, from 0 to length
	// rehash each object into the new bucket array in the order they appear on the original chain.
	public void rehash(int newBucketCount) {
		/* -- IMPLEMENT THIS -- */
		ListNode[] oldBuckets = buckets ;
		buckets = new ListNode [newBucketCount] ;
		
		// go through old list
		for (int j = 0 ; j < oldBuckets.length ; j ++)
		{
			// go through chain
			ListNode b = oldBuckets[j] ;
			while (b != null)
			{
				int pos = whichBucket(b.getValue()) ;
//				ListNode b = new ListNode (b.getValue(), buckets[pos]) ;
				buckets[pos] = new ListNode (b.getValue(), buckets[pos]) ;
				
				b = b.getNext() ;
			}
		}
	}

	// The output should be in the following format:
	// [ #1, #2 | { b#: v1 v2 v3 } { b#: v1 v2 } ]
	// #1 is the objCount
	// #2 is the number of buckets
	// For each bucket that contains objects, create a substring that indicates the bucket index
	// And list all of the items in the bucket (in the order they appear)
	
	// eg [3, 10 | {b2 16 John} {b453545 r L}] 
	// skip empty buckets
	public String toString() 
	{
		/* -- IMPLEMENT THIS -- */
		StringBuilder str = new StringBuilder () ;
		
		str.append("[ " + objectCount + ", " + buckets.length + " | ") ;
		
		// go through list
		for (int j = 0 ; j < buckets.length ; j ++)
		{
			if (buckets[j] != null)
			{
				str.append(bucketString (buckets[j], j) + " ") ;
			}
		}
		
		return str.toString() + "]" ;
	}
	
	public String bucketString (ListNode bucket, int pos)
	{
		StringBuilder str = new StringBuilder () ;
		
		str.append("{ b" + pos + ": ") ;
		
		// move through bucket
		ListNode b = bucket ;
		while (b != null)
		{
			str.append(b.getValue() + " ") ;
			b = b.getNext() ;
		}
		
		return str.toString() + "}" ;
		
	}

}