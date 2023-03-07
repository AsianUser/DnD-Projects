
public class BinaryNode<E extends Comparable<E>> {

	private E value;
	private BinaryNode<E> left;
	private BinaryNode<E> right;
	
	public BinaryNode(E value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}
	
	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	public BinaryNode<E> getLeft() {
		return left;
	}

	public void setLeft(BinaryNode<E> left) {
		this.left = left;
	}

	public BinaryNode<E> getRight() {
		return right;
	}

	public void setRight(BinaryNode<E> right) {
		this.right = right;
	}
	
	public boolean hasLeft() {
		return left != null;
	}
	
	public boolean hasRight() {
		return right != null;
	}
	
	public boolean hasChildren() {
		return left != null || right != null;
	}

	public String toString() {
		return value.toString();
		
	}
	
}
