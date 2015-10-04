package cs4321.project2.operator;

import cs4321.project2.*;
import cs4321.project2.Expression.ExpressionVisitorImp;
import cs4321.project2.databasecatalog.Tuple;
import net.sf.jsqlparser.expression.*;
import java.util.ArrayList;

public class JoinOperator extends BinaryOperatorNode{
	//may contain the expression to decide wchich tuple this join should take
	Expression expression;
	//to store the tuple1
	Tuple tuple1;
	
	/**initialization method just to define the operatorNode
	 * 
	 * @param leftChild the leftOperator
	 * @param rightChild the rightOperator
	 */
	public JoinOperator(OperatorNode leftChild,OperatorNode rightChild, Expression expression){
		super(leftChild,rightChild);
		this.expression = expression;
	}
	

	/**
	 * scan one the the left Tuple first
	 */
	public Tuple getNextTuple(){
		Tuple tuple2 = this.getRightChild().getNextTuple();
		if(tuple2 == null){
			 //means return the head of the file
			 this.getRightChild().reset();
			 tuple1 = this.getLeftChild().getNextTuple();
			 return getNextTuple();
		}
		else{
			ArrayList<String> content1 = tuple1.getContent();
			//glues them together.
			ArrayList<String> content2 = tuple2.getContent();
			content1.addAll(content2);
			String result = new String();
			int leng = content1.size();
			for(int i = 0; i< leng; i++){
				result += content1.get(i);
			}			
			Tuple newTuple = new Tuple(result);		
			ExpressionVisitorImp visitor = new ExpressionVisitorImp(newTuple);
			expression.accept(visitor);
			boolean judge = visitor.getResult();
			if(judge){
				return newTuple;
			}
			else{
				return getNextTuple();
			}
		}		
		//return null;
	}	
	public void dump(){
		
		
	}
	
	public void reset(){
		
	}

}
