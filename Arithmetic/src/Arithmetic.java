//import java.util.ArrayList;
import java.util.Scanner;

public class Arithmetic {
	
//	ArrayList <String> stack ;
	
	//Evaluates a String exp that has an arithmetic expression, written in classic notation
	public static int evaluate(String exp) {
		
		return evaluateStout (convertClassicToStout(exp)) ;
	}
	
	//Returns the result of doing operand1 operation operand2,
	//e.g. operate(5, 2, "-") should return 3
	public static int operate(int operand1, int operand2, String operation) {
		if (operation.equals("/"))
			return operand1 / operand2 ;
		else if (operation.equals("*"))
			return operand1 * operand2 ;
		else if (operation.equals("+"))
			return operand1 + operand2 ;
		else if (operation.equals("-"))
			return operand1 - operand2 ;
		
		return 0;
	}
	
	//Evaluates a String exp that has an arithmetic expression written in STOUT notation
	public static int evaluateStout(String exp) {
		
		Scanner expression = new Scanner (exp) ;
		Stack<Integer> stack = new Stack <Integer> () ;
		
		while (expression.hasNext())
		{
			if (expression.hasNextInt())
				stack.push(expression.nextInt());
			else
			{
				int b = stack.pop() ;
				int a = stack.pop() ;
//				System.out.println (a + "," + b) ;
				stack.push(operate(a,b,expression.next()));
			}
		}
		expression.close();
		return stack.peek();
	}
	
	public static String convertClassicToStout(String exp) {
		
		Scanner expression = new Scanner (exp) ;
		String str = "" ;
		Stack <String> stack = new Stack <String> () ;
		
		while (expression.hasNext())
		{
			// if number, add to thing
			if (expression.hasNextInt())
			{
				str += expression.next() + " " ;
			}
			
			else
			{
				// make life easier
				String e = expression.next() ;
				
//				System.out.println ("e" + e) ;
//				if (e.equals("("))
//				{
//					stack.push(e) ;
//				}
				
				// if end of expression
				if (e.equals(")"))
				{
					String p = stack.pop() ;
					// while not front of expression, keep adding
					while (!p.equals ("("))
					{
						str += p + " " ;
						p = stack.pop() ;
					}
				}
				else
				{
					// stack everything else: operators, "("
					stack.push(e) ;
				}
			}
			
		}
		
//		while (!stack.isEmpty())
//		{
//			System.out.print (stack.pop() + " ") ;
//		}
		
		expression.close() ;
		
		return str.substring(0, str.length()-1) ;
	}
	
	
}