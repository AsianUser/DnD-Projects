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
