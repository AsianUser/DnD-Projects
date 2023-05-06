import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class HuffmanDecoder {
	
//	HashMap<Integer, String> huffCode = new HashMap<Integer, String> ();
	Node huffCode = new Node ();
	
	public HuffmanDecoder (String codeFile)
	{
		try
		{
			BufferedReader buffReader = new BufferedReader (new FileReader (codeFile));

			for (int j = 0 ; j < 128 ; j ++)
			{
				String code = buffReader.readLine();
				
				// create tree
				Node root = huffCode;
				for (int k = 0 ; k < code.length() ; k ++)
				{
					if (code.charAt(k) == '0')
					{
						// if already exist,
						if (root.hasLeft())
						{
							root = root.getLeft();
						}
						// else create
						else
						{
							root.setLeft(new BinaryNode (0));
							root = root.getLeft();
						}
					}
					else // equals '1'
					{
						// if already exist
						if (root.hasRight())
						{
							root = root.getRight();
						}
						// else create
						else
						{
							root.setRight(new BinaryNode(1));
							root = root.getRight();
						}
					}
				}
				
				root.setChar ((char) j);
				
			}
			
			buffReader.close();
		}
		catch (Exception e)
		{
			System.out.println ("bruh" + e);
		}
	}
	
	public void decodeFile (String encodedFile)
	{
		if (!encodedFile.substring(encodedFile.length()-5).equals (".huf"))
		{
			throw new IllegalArgumentException ("not a .huf");
		}
	
		try {
			BufferedReader buffReader = new BufferedReader (new FileReader (encodedFile));
			PrintWriter pWrite = new PrintWriter(encodedFile.substring(0, encodedFile.length()-4)); // un .huf-ify
	
			int current=0;
			int next=0;
			// if not empty
			if (buffReader.ready())
			{
				current = buffReader.read();
			}
			// main code
			while (buffReader.ready())
			{
				next = buffReader.read();
	
				// if end of file
				if ((char)next != '0' && (char)next != '1')
				{
					String str = Integer.toString (current);
					str.substring(0,(8-Character.getNumericValue(next)));
					
					pWrite.write (str);
				}
				// int to str
				else
				{
					pWrite.write (current);
				}
	
				current = next;
			}
			// deal with remainder
	
			
			buffReader.close();
			pWrite.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println ("bruh" + e);
		}
	
	}



	public boolean isCode (String binary) // trace tree
	{
		Node root = huffCode;
		for (int k = 0 ; k < binary.length() ; k ++)
		{
			if (binary.charAt(k) == '0')
			{
				// if already exist,
				if (root.hasLeft())
				{
					root = root.getLeft();
				}
				else
				{
					return false; // out of bounds
				}
			}
			else // equals '1'
			{
				// if already exist
				if (root.hasRight())
				{
					root = root.getRight();
				}
				else
				{
					return false; // out of bounds
				}
			}
		}
		return (!root.hasChildren());
	}
	
	public char decodeChar (String binary)
	{
		Node root = huffCode;
		for (int k = 0 ; k < binary.length() ; k ++)
		{
			if (binary.charAt(k) == '0')
			{
				// if already exist,
				if (root.hasLeft())
				{
					root = root.getLeft();
				}
				// else create
				else
				{
					root.setLeft(new BinaryNode (0));
					root = root.getLeft();
				}
			}
			else // equals '1'
			{
				// if already exist
				if (root.hasRight())
				{
					root = root.getRight();
				}
				// else create
				else
				{
					root.setRight(new BinaryNode (1));
					root = root.getRight();
				}
			}
		}
		return (root.getChar());
	}
	
	public void decodeLong (String encodedFile, String decodedFile)
	{
		try
		{
			BufferedReader buffReader = new BufferedReader (new FileReader (encodedFile));
			PrintWriter pWrite = new PrintWriter(decodedFile);
			
			String binary = "";
			
			while (buffReader.ready())
			{
				int chara = buffReader.read();
				binary += (char) chara;
				
				// check if is code
				if (isCode(binary))
				{
					pWrite.write(decodeChar(binary));
					binary = "";
				}
			}
			
			buffReader.close();
			pWrite.close();
		}
		catch (Exception e)
		{
			System.out.println ("bruh" + e);
		}
	}
}
