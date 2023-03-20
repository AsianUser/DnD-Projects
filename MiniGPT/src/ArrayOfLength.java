
public class ArrayOfLength {
	
	int [] array;
	int totalPos;
	
	public ArrayOfLength(int size)
	{
		array = new int[size];
		totalPos = size-1;
	}
	
	public void addToPos(int chara)
	{
		array[chara]++;
		array[totalPos]++;
	}
	
	
}
