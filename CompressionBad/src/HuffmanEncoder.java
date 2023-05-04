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
				huffCode.put(j, code);
			}
			
			buffReader.close();
		}
		catch (Exception e)
		{
			System.out.println ("bruh");
		}
	}
	
	public String encodeChar (char input)
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
}
