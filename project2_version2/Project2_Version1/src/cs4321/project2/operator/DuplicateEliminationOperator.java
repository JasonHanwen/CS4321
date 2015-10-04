package cs4321.project2.operator;

import cs4321.project2.databasecatalog.Tuple;

public class DuplicateEliminationOperator extends UnaryOperatorNode{
	//store the last tuple
	Tuple lastTuple;
	public DuplicateEliminationOperator(OperatorNode child) {
		// TODO Auto-generated constructor stub
		super(child);
	}
	
	public void reset(){
		
	}
	
	/**
	 * get the distinct tuple from its child
	 */
	public Tuple getNextTuple(){
		Tuple tuple = super.getChild().getNextTuple();
		// the the tuple readed from its child is null than return null
		if(tuple == null)
			return null;
		
		if(lastTuple == null){
			lastTuple = tuple;
			return tuple;
		}
		
		else{
			if(lastTuple.equals(tuple)){
				return getNextTuple();
			}
			else{
				lastTuple = tuple;
				return tuple;
			}
		}
	}
	
	public void dump(){
		Tuple tuple = new Tuple("Hanwen Wang");
		do{
			tuple = getNextTuple();
			if(tuple!=null){
				System.out.println(tuple.getContentString());
			}
		}while(tuple!=null);
	}
}
