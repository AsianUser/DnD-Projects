
public class CompressTest {
	public static void main (String [] args)
	{
		HuffmanCodeGenerator huffCodeGen = new HuffmanCodeGenerator("TestText");
		// System.out.println ("q" + huffCodeGen.getFrequency('q'));
		System.out.println ("char 0" + huffCodeGen.getCode((char) 0));

		// huffCodeGen.huffmanTreeMaker();
		// huffCodeGen.createCode(huffCodeGen.getRoot());
		// huffCodeGen.makeCodeFile("codeFile");

		HuffmanEncoder huffEncode = new HuffmanEncoder("codeFile");
		System.out.println("\n");
		for (char c = 0; c < 128; c++) {
			System.out.println ((char)c + huffEncode.encodeChar(c));
		}
	}
}
