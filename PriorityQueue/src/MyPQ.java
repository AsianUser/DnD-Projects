import java.util.LinkedList ;

public class MyPQ <E extends Comparable <E>> implements MyPriorityQueue <E>
{
	LinkedList <E> queue ;
	
//	E min ;
//	int pos = 0 ;
	
	public MyPQ()
	{
		queue = new LinkedList <E> () ;
	}

	//Adds obj to the Priority Queue
	public void add(E obj)
	{
		int pos = 0 ;
		// sort
		while (pos < queue.size() && obj.compareTo(queue.get(pos)) > 0)
		{
			pos ++ ;
		}
		
		queue.add(pos, obj) ;
		
//		if (!queue.isEmpty() && min.compareTo(obj) > 0)
//		{
//			min = obj ;
//			pos = queue.size() ;
//		}
//		else if (queue.isEmpty())
//		{
//			min = obj ;
//			pos = 0 ;
//		}
	}

	//Removes and returns the MINIMUM element from the Priority Queue
	public E removeMin()
	{
		
		return (queue.remove(0)) ;
		
//		if (isEmpty ())
//		{
//			return null;
//		}
//		
//		E min = queue.getFirst();
//		int pos = 0 ;
//		for (int j = 0 ; j < queue.size() ; j ++)
//		{
//			if (min.compareTo(queue.get(j)) > 0)
//			{
//				pos = j ;
//				min = queue.get(j) ;
//			}
//		}
//		
//		queue.remove(pos) ;
//		return min ;
		
	}

	//Returns the MINIMUM element from the Priority Queue without removing it
	public E peek()
	{
		
		return (queue.get(0)) ;
		
//		if (isEmpty ())
//		{
//			return null;
//		}
//		
//		E min = queue.getFirst();
//		for (int j = 0 ; j < queue.size() ; j ++)
//		{
//			if (min.compareTo(queue.get(j)) > 0)
//			{
//				min = queue.get(j) ;
//			}
//		}
//		
//		return min ;
	}

	// Returns true if the priority queue is empty
	public boolean isEmpty()
	{
		return queue.isEmpty() ;
	}

}
