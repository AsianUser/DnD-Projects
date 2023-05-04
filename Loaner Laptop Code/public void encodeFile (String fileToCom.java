
/**
 * 
 * turn the cursed binary tree shit in decoder into a Hashmap for (int, char)
 * may be slower, so confirm?
 * 
 */




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


public void decodeFile (String encodedFile)
{
    if (!encodedFile.substring(encodedFile.length()-5).equals (".huf"))
    {
        throw new IllegalArgumentException ("not a .huf");
    }

    try {
        BufferedReader buffReader = new BufferedReader (new FileReader (encodedFile));
        PrintWriter pWrite = new PrintWriter(encodedFile(0, encodedFile.length()-4)); // un .huf-ify

        int current;
        int next;
        // if not empty
        if (buffReader.ready())
        {
            current = buffReader.read();
        }
        if (buffReader.ready())
        {
            next = buffReader.read();
        }
        // main code
        while (buffReader.ready())
        {

            // int to str
            pWrite.

            current = next;
            next = buffReader.read();
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