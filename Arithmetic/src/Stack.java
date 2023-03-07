import java.util.LinkedList ;

public class Stack <E> {
	LinkedList <E> stk ;
	
	public Stack()
	{
		stk = new LinkedList <E> () ;
	}
	
	public void push (E obj)
	{
//		System.out.println (obj) ;
		stk.add(0, obj) ;
	}
	
	public E pop ()
	{
		return stk.remove(0);
	}
	
	public E peek ()
	{
		return stk.get(0) ;
	}
	
	public boolean isEmpty ()
	{
		return stk.isEmpty() ;
	}
	
}