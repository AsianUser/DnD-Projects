import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import java.util.HashMap;

public class MiniGPT {

	// idea: generate ap csa text book + bible
	
	HashMap<String, int[]> hashMap = new HashMap<String, int[]> ();
	
	private int arrayLength = 256 + 1;
	private int strCountPos = 256;
	
	private String seed;
	private int seedStrCount;
	
	public MiniGPT(String fileName, int chainOrder) {
		try
		{
//			Scanner scanner = new Scanner(new File(fileName));
			BufferedReader buffReader = new BufferedReader (new FileReader (fileName));
			
			// prepare first key
			String key = "";
			for (int i = 0 ; i < chainOrder ; i++)
			{
				key += (char) buffReader.read();
			}
//			System.out.println (key);
			int [] value;
			
			// move buffReader into position
//			buffReader.skip(chainOrder);
			
			while (buffReader.ready())
			{
				int chara = buffReader.read();
				//filter
				if (chara > 255)
				{
					chara = 0;
				}
//				System.out.print((char) chara);
				
				// add key to hashMap if not already
				if (!hashMap.containsKey(key))
					hashMap.put(key, new int [arrayLength]);
				
				// record char to hashmap value
				value = hashMap.get(key);
				value [chara] ++;
				value [strCountPos] ++;
				hashMap.put(key, value);
//				System.out.println (hashMap.get(key)[strCountPos]);
				
				// make new seed as necessary
				if (value[strCountPos] > seedStrCount)
				{
					seed = key;
					seedStrCount = value[strCountPos];
				}
				
				// shift key
//				System.out.println ((char) chara);
				key = key.substring(1) + (char)chara;
			}
			
//			scanner.close();
			buffReader.close();
		}
		catch (Exception e)
		{
			System.out.println ("bruh" + e);
		}
	}

	
	public void generateText(String outputFileName, int numChars) {
		// create outputFile
		try
		{
			PrintWriter pWrite = new PrintWriter(outputFileName);
//			BufferedReader buffestReader = new BufferedReader (new FileReader (outputFileName));
			// prepare
			pWrite.write(seed);
//			System.out.println(seed);
			
			String prev = seed;
			
//			buffestReader.skip(seed.length());
//			System.out.println("startwrtie" + prev);
			int [] values;
			
			
			// actually write
			for (int j = seed.length() ; j < numChars ; j++)
			{
				// read in
				if (hashMap.containsKey(prev))
					values = hashMap.get(prev);
				else
					values = hashMap.get(seed);
//				System.out.println(values[strCountPos]);
				//gen random
				int num = (int) (Math.random() * values[strCountPos]);
//				System.out.println("numRand" + num);
				
				// begin substraction
				int pos = -1;
				while (num >= 0)
				{
					pos++;
					num -= values[pos];
//					System.out.print(num + "pos" + pos + " ");
//					System.out.print(pos + " ");
				}
//				System.out.println ("pos" + pos + " char" + (char)pos);
				pWrite.write ((char) pos);
				
				// move prev
//				System.out.println (prev + "e");
				prev = prev.substring(1) + (char) pos;
			}
			
			pWrite.close();
//			buffestReader.close();
		}
		catch (Exception e)
		{
			System.out.println ("bruh" + e);
		}
		
	}
}
