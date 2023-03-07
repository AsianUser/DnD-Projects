
public class HeapPQ<E extends Comparable<E>> implements MyPriorityQueue<E> {

	private E[] heap;
	private int objectCount;
	
	@SuppressWarnings("unchecked")
	public HeapPQ()
    {
        this.heap = (E[])new Comparable[3];
        this.objectCount = 0;
    }

	//Adds obj to the Priority Queue
	public void add(E obj)
	{
		if (objectCount + 1 == heap.length)
			increaseCapacity () ;
		heap[objectCount] = obj ;
		objectCount ++ ;
	}
	
	//Removes and returns the MINIMUM element from the Priority Queue
	public E removeMin()
	{
		E min = heap [0] ;
		//swap first and last
		swap(0, objectCount - 1) ;
		//remove
		heap [objectCount - 1] = null ;
		objectCount -- ;
		//bubble down
		bubbleDown(0) ;
		
		return min ;
	}
	
	//Returns the MINIMUM element from the Priority Queue without removing it
	public E peek()
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
		E[] newHeap = (E[])new Comparable[heap.length * 2];
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
		
		if (heap[pos1].compareTo(heap[pos2]) >= 0)
			return pos1 ;
		return pos2;
	}
	//Swaps the contents of indices i and j
	private void swap(int i, int j)
	{
		E posI = heap[i] ;
		
		heap[i] = heap[j] ;
		heap[j] = posI ;
	}

	// Bubbles the element at index i upwards until the heap properties hold again.
	private void bubbleUp(int i)
	{
		while (parent(i) >= 0 && heap[parent(i)].compareTo(heap[i]) < 0)
		{
			swap(parent (i), i) ;
			i = parent (i) ;
		}
	}
	
	// Bubbles the element at index i downwards until the heap properties hold again.
	private void bubbleDown(int i)
	{
		while (smallerChild(i) <= objectCount && heap[smallerChild(i)].compareTo(heap[i]) > 0)
		{
			swap(smallerChild (i), i) ;
			i = smallerChild (i) ;
		}
	}

}
