
public class CharNode implements Node {
	private int value;
	private char character;
	
	public CharNode(char character, int value) {
		this.character = character;
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public char getChar ()
	{
		return character;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public void setChar (char character)
	{
		this.character = character;
	}
	
	public boolean hasChildren() {
		return false;
	}

	public String toString() {
		return character + " " + value;	
	}
}
