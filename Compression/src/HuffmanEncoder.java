import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import java.util.HashMap;

public class HuffmanEncoder {
	
	HashMap<Integer, String> huffCode = new HashMap<Integer, String> ();

	public HuffmanEncoder (String codeFile)
	{
		try
		{
			BufferedReader buffReader = new BufferedReader (new FileReader (codeFile));
			

			for (int j = 0 ; j < 128 ; j ++)
			{
				String code = buffReader.readLine();
				System.out.println ("code" + code);
				huffCode.put(j, code);
				System.out.println (huffCode.get(j));
			}
			
			buffReader.close();
		}
		catch (Exception e)
		{
			System.out.println ("bruh");
		}
	}
	
	public String encodeChar (int input)
	{
		return huffCode.get(input);
	}
	
	public void encodeLong (String fileToCompress, String encodedFile)
	{
		try
		{
			BufferedReader buffReader = new BufferedReader (new FileReader (fileToCompress));
			PrintWriter pWrite = new PrintWriter(encodedFile);
			
			while (buffReader.ready())
			{
				int k = buffReader.read();
				pWrite.write(huffCode.get(k));
			}
			
			buffReader.close();
			pWrite.close();
		}
		catch (Exception e)
		{
			System.out.println ("bruh");
		}
	}

	public void encodeFile (String fileToCompress)
	{
		try
		{
			BufferedReader buffReader = new BufferedReader (new FileReader (fileToCompress));
			PrintWriter pWrite = new PrintWriter(fileToCompress + ".huf");
			
			String str = "";

			while (buffReader.ready())
			{
				int k = buffReader.read();
				str += (char) k;
				if (str.length() == 8)
				{
					pWrite.write((char) Integer.parseInt(str));
					str = "";
				}
			}
			// if str is less 
			
			if (str.length() < 8)
			{
				
			}


			buffReader.close();
			pWrite.close();
		}
		catch (Exception e)
		{
			System.out.println ("bruh");
		}
	}

}
