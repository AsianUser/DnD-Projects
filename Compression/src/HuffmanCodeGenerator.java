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
	HashMap<Character, String> huffCode = new HashMap<Character, String> ();
	Node root;
	
	public HuffmanCodeGenerator (String inputFile)
	{
		// fill in all chars
		for (int j = 0 ; j < 128 ; j++)
		{
			if (!charCount.containsKey((char) j))
				charCount.put((char) j, 0);
		}
		
		// actually read
		try
		{
			BufferedReader buffReader = new BufferedReader (new FileReader (inputFile));
			
			// move buffReader into position
//			buffReader.skip(chainOrder);
			
			while (buffReader.ready())
			{
				Character chara = (char)buffReader.read();
				//filter
				if (chara > 127)
				{
					chara = 0;
				}
				
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
		
		
		// createHuffman();
//		makeCodeFile("codeFile");
		huffmanTreeMaker();
		createCode(getRoot());
		makeCodeFile("codeFile");
		
	}
	
	public int getFrequency (char c)
	{
		return charCount.get(c);
	}

	public Node getRoot()
	{
		return root;
	}
	
	public void huffmanTreeMaker ()
	{
		// System.out.println ("treemaker");
		
		HeapPQ priorQ = new HeapPQ();
		
		// add nodes to pq
		for (int j = 0 ; j < 128 ; j++)
		{
			// System.out.println (j + " " + getFrequency((char) j));
			// check char actually appear
			if (getFrequency ((char) j) > 0)
				priorQ.add(new CharNode ((char) j, getFrequency((char) j)));
		}
		
		// begin sorting
		while (priorQ.size() > 1)
		{
			Node temp1 = priorQ.removeMin();
			Node temp2 = priorQ.removeMin();
			BinaryNode total = new BinaryNode (temp1.getValue() + temp2.getValue());
			// sort out branch placement
			total.setLeft(temp1);
			total.setRight(temp2);
			priorQ.add(total);
		}
		
		root = priorQ.removeMin();
		
	}
	
	public void createCode (Node root)
	{
		// System.out.println ("codecreate");
		createCodeHelper (root, "");
	}
	
	public void createCodeHelper (Node root, String binary)
	{
		// if leaf
		if (!root.hasChildren())
		{
			// System.out.println (root.getChar() +  binary);
			huffCode.put(root.getChar(), binary);
		}
		else
		{
			// left
			createCodeHelper (root.getLeft(), binary + "0");
			// right
			createCodeHelper (root.getRight(), binary + "1");
		}

		// fill in for all chars
		for (int j = 0 ; j < 128 ; j++)
		{
			if (!huffCode.containsKey((char) j))
				huffCode.put((char) j, "");
		}
	}
	
	public String getCode (char c)
	{
		// System.out.println ("c" + huffCode.get(c));
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
		// System.out.println ("makefile");

		try
		{
			PrintWriter pWrite = new PrintWriter(codeFile);

			for (int j = 0 ; j < 128 ; j ++)
			{
				char c = (char) j;

				// System.out.println ("print" + j + (char) j + " " + getCode(c));

				pWrite.write(getCode(c));
				pWrite.write("\n");
			}

			pWrite.close();
		}
		catch (Exception e)
		{
			System.out.println ("bruh" + e);
		}
	}
	
	public void encodeFile (String fileToCompress)
{
    try {
        BufferedReader buffReader = new BufferedReader (new FileReader (fileToCompress));
        PrintWriter pWrite = new PrintWriter(fileToCompress + ".huf");

        int charCount = 0;
        String str = "";

        while (buffReader.ready())
        {
            Character chara = (char) buffReader.read();
            
            charCount ++;
            str = str + chara;

            if (charCount == 8)
            {
                // str to int
                // int to char
                Character printChar = (char) Integer.parseInt (str);
                pWrite.print (printChar);
                str = "";
                charCount = 0;
            }

        }

        // deal with remainder
        for (int j = 0 ; j < 8-charCount ; j ++)
        {
            str = str + "0";
        }
        pWrite.write (8-charCount);
        

        buffReader.close();
        pWrite.close();
    }
    catch (Exception e) {
        // TODO: handle exception
        System.out.println ("bruh" + e);
    }
}

	
}
