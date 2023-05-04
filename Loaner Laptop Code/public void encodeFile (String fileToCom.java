public void encodeFile (String fileToCompress)
{
    
}


public void decodeFile (String encodedFile)
{
    if (!encodedFile.substring(encodedFile.length()-5).equals (".huf"))
    {
        throw new IllegalArgumentException ("not a .huf");
    }


}