package cs4321.project2.operator;

/**
 * 
 * @author hanwenwang
 *
 */

public abstract class BinaryOperatorNode extends OperatorNode{
	private OperatorNode leftChild;
	private OperatorNode rightChild;
	
	public BinaryOperatorNode(OperatorNode leftChild, OperatorNode rightChild) {
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	public OperatorNode getLeftChild() {
		return leftChild;
	}

	public OperatorNode getRightChild() {
		return rightChild;
	}
}
