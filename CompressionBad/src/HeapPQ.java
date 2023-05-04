
public class HeapPQ {

	private BinaryNode<Integer>[] heap;
	private int objectCount;
	
	@SuppressWarnings("unchecked")
	public HeapPQ()
    {
//        this.heap = (BinaryNode<Integer>[])new Comparable[3];
        this.objectCount = 0;
    }

	//Adds obj to the Priority Queue
	public void add(BinaryNode<Integer> obj)
	{
//		System.out.println (obj) ;
		
		if (objectCount + 1 == heap.length)
			increaseCapacity () ;
		heap[objectCount] = obj ;
		bubbleUp (objectCount) ;
		objectCount ++ ;
	}
	
	//Removes and returns the MINIMUM element from the Priority Queue
	public BinaryNode<Integer> removeMin()
	{
		if (objectCount == 0)
			return null ;
		
		BinaryNode<Integer> min = heap [0] ;
		
		//swap first and last
//		System.out.println ("pos " + (objectCount - 1)) ;
		swap(0, objectCount - 1) ;
		
		//remove
		heap [objectCount - 1] = null ;
		objectCount -- ;
//		System.out.println (toString ()) ;
		
		//bubble down
		
		bubbleDown(0) ;
		
		
		return min ;
	}
	
	//Returns the MINIMUM element from the Priority Queue without removing it
	public BinaryNode<Integer> peek()
	{
		return heap[0] ;
	}
	
	// Returns true if the priority queue is empty
	public boolean isEmpty()
	{
		return objectCount == 0 ;
	}
	
	//Returns the number of elements in the priority queue
	public int size()
	{
		return objectCount ;
	}
	
	public String toString()
	{
		StringBuffer stringbuf = new StringBuffer("[");
		for (int i = 0; i < objectCount; i++)
		{
			stringbuf.append(heap[i]);
			if (i < objectCount - 1)
				stringbuf.append(", ");
		}
		stringbuf.append("]\nor alternatively,\n");

		for(int rowLength = 1, j = 0; j < objectCount; rowLength *= 2)
		{
			for (int i = 0; i < rowLength && j < objectCount; i++, j++)
			{
				stringbuf.append(heap[j] + " ");
			}
			stringbuf.append("\n");
			if (j < objectCount)
			{
				for (int i = 0; i < Math.min(objectCount - j, rowLength*2); i++)
				{
					if (i%2 == 0)
						stringbuf.append("/");
					else
						stringbuf.append("\\ ");
				}
				stringbuf.append("\n");
			}
		}
		return stringbuf.toString();
	}
	
	//Doubles the size of the heap array
	@SuppressWarnings("unchecked")
	private void increaseCapacity()
	{
		BinaryNode<Integer>[] newHeap = (BinaryNode<Integer>[])new Comparable[heap.length * 2];
		for (int j = 0 ; j < heap.length ; j ++)
		{
			newHeap[j] = heap[j] ;
		}
			
		heap = newHeap ;
	}

	//Returns the index of the "parent" of index i
	private int parent(int i)
	{
		return (i-1)/2 ;
	}
	//Returns the index of the *smaller child* of index i
	private int smallerChild(int i)
	{
		int pos1 = 2*i+1;
		int pos2 = 2*i+2;
		
		// if at end
		if (pos1 >= objectCount)
			return -1 ;
		
		// have 1 child
		if (pos2 >= objectCount)
			return pos1 ;
		
		// have 2 child
		if (heap[pos1].getValue().compareTo(heap[pos2].getValue()) <= 0)
			return pos1 ;
		return pos2;
		
	}
	//Swaps the contents of indices i and j
	private void swap(int i, int j)
	{
		BinaryNode<Integer> posI = heap[i] ;
		
		heap[i] = heap[j] ;
		heap[j] = posI ;
	}

	// Bubbles the element at index i upwards until the heap properties hold again.
	private void bubbleUp(int i)
	{
//		int pos = i ;
//		while (parent(pos) >= 0 && heap[parent(pos)].compareTo(heap[pos]) > 0)
//		{
//			swap(parent (pos), pos) ;
//			i = parent (pos) ;
//		}
		while (parent(i) >= 0 && heap[parent(i)].getValue().compareTo(heap[i].getValue()) > 0)
		{
			swap(parent (i), i) ;
			i = parent (i) ;
		}
	}
	
	// Bubbles the element at index i downwards until the heap properties hold again.
	private void bubbleDown(int i)
	{
		int pos = i ;
		int smallPos = smallerChild(pos) ;
		while (smallPos >= 0 && heap[smallPos].getValue().compareTo(heap[pos].getValue()) < 0)
		{
//			System.out.println ("smallChild " + smallerChild(i)) ;
			swap(smallPos, pos) ;
			pos = smallPos ;
			smallPos = smallerChild(pos) ;
		}
	}

}
