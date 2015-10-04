package cs4321.project2.operator;

/**
 * 
 * @author hanwenwang
 *
 */

public abstract class BinaryOperatorNode extends OperatorNode{
	private OperatorNode leftChild;
	private OperatorNode rightChild;
	private String LeftChildName;
	private String RightChildName;
	
	public BinaryOperatorNode(OperatorNode leftChild, OperatorNode rightChild) {
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	public BinaryOperatorNode(OperatorNode leftChild, OperatorNode rightChild, String LeftChildName,String RightChildName) {
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.LeftChildName = LeftChildName;
		this.RightChildName = RightChildName;
	}

	public OperatorNode getLeftChild() {
		return leftChild;
	}

	public OperatorNode getRightChild() {
		return rightChild;
	}
	
	public String getRightChildName(){
		return RightChildName;
	}
	public String getLeftChildName(){
		return LeftChildName;
	}
}
