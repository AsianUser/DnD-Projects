import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.HashMap;


// NOTE: Stout recommend using for-each loops over for loops
// 			- investigate & replace as necessary




public class HuffmanCodeGenerator {
	
	HashMap<Character, Integer> charCount = new HashMap<Character, Integer> ();
	MyBST <Integer> huffTree ;
	HashMap<Character, String> huffCode = new HashMap<Character, String> ();
	
	public HuffmanCodeGenerator (String inputFile)
	{
		// fill in all chars
		for (int j = 0 ; j < 128 ; j++)
		{
			if (!charCount.containsKey((char) j))
				charCount.put((char) j, 0);
		}
		
		try
		{
			BufferedReader buffReader = new BufferedReader (new FileReader (inputFile));
			
			// move buffReader into position
//			buffReader.skip(chainOrder);
			
			while (buffReader.ready())
			{
				Character chara = (char)buffReader.read();
				//filter
				if (chara > 255)
				{
					chara = 0;
				}
				
//				// add key to hashMap if not already
//				if (!charCount.containsKey(chara))
//					charCount.put(chara, 0);
				
				// record char to hashmap value
				int value = charCount.get(chara);
//				System.out.println (value);
				value ++;
				charCount.put(chara, value);
				
			}
			
			buffReader.close();
		}
		catch (Exception e)
		{
			System.out.println ("bruh" + e);
		}
		
		
		createHuffman();
//		makeCodeFile("codeFile");
		
	}
	
	public int getFrequency (char c)
	{
		return charCount.get(c);
	}
	
	@SuppressWarnings("null")
	public void createHuffman ()
	{
		// lambda is fun
//		PriorityQueue<MyBST<Integer>> priorQ= new PriorityQueue<MyBST<Integer>>(11, (a,b) -> a.getRoot().getValue() - b.getRoot().getValue());
//		PriorityQueue<BinaryNode<Integer>> priorQ= new PriorityQueue<BinaryNode<Integer>>(11, (a,b) -> a.getValue() - b.getValue());
		HeapPQ priorQ = new HeapPQ();
		
		// add nodes to pq
		for (int j = 0 ; j < 128 ; j++)
		{
			System.out.println (j + " " + getFrequency((char) j));
			if (getFrequency ((char) j) > 0)
				priorQ.add(new BinaryNode<Integer> ((char) j, getFrequency((char) j)));
		}
		
		// begin sorting
		while (priorQ.size() > 1)
		{
			BinaryNode<Integer> temp1 = priorQ.removeMin();
			BinaryNode<Integer> temp2 = priorQ.removeMin();
			BinaryNode<Integer> total = new BinaryNode<Integer> ((char) 128, temp1.getValue() + temp2.getValue());
			// sort out branch placement
			total.setLeft(temp1);
			total.setRight(temp2);
			priorQ.add(total);
		}
		
//		MyBST <Integer> 
		
		createCodeHelper (priorQ.removeMin(), "");
		
		
	}
	
	public void createCode (BinaryNode<Integer> root)
	{
		createCodeHelper (root, "");
	}
	
	public void createCodeHelper (BinaryNode<Integer> root, String binary)
	{
		// if leaf
		if (!root.hasChildren())
		{
			System.out.println (root.getChar() +  binary);
			huffCode.put(root.getChar(), binary);
		}
		else
		{
			// left
			createCodeHelper (root.getLeft(), binary += "0");
			// right
			createCodeHelper (root.getRight(), binary += "1");
		}
	}
	
	public String getCode (char c)
	{
		return (huffCode.get(c));
//		String str = "";
//		int value = charCount.get(c);
//		
//		BinaryNode <Integer> point = huffTree.getRoot() ;
//		while (point != null && !point.getValue().equals(value))
//		{
//			if (point.getValue().compareTo(value) > 0)
//			{
//				point = point.getLeft() ; 
//				str += "0";
//			}
//			else if (point.getValue().compareTo(value) < 0)
//			{
//				point = point.getRight() ;
//				str += "1";
//			}
//		}
////		if (point == null)
////			return false ;
////		return true ;
//		
//		return str;
	}
	
	public void makeCodeFile (String codeFile)
	{
		try
		{
			PrintWriter pWrite = new PrintWriter(codeFile);

			for (int j = 0 ; j < 128 ; j ++)
			{
				pWrite.write(getCode ((char) j) + "\n");
			}

			pWrite.close();
		}
		catch (Exception e)
		{
			System.out.println ("bruh" + e);
		}
	}
	
	
}
