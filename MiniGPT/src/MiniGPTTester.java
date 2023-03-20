
public class MiniGPTTester {
	public static void main (String [] args)
	{
		MiniGPT miniGPT = new MiniGPT("TestText", 10);
		miniGPT.generateText("Output", 10000);
	}
}
