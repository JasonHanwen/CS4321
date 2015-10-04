package cs4321.project2.operator;

public abstract class UnaryOperatorNode extends OperatorNode{
	
	private OperatorNode child;
	
	public UnaryOperatorNode(OperatorNode child) {
		super();
		this.child = child;
	}

	public OperatorNode getChild() {
		return child;
	}
	
}
