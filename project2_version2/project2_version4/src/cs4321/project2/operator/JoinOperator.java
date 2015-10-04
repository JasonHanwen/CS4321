package cs4321.project2.operator;

import cs4321.project2.*;
import cs4321.project2.Expression.ExpressionVisitorImp;
import cs4321.project2.databasecatalog.Tuple;
import net.sf.jsqlparser.expression.*;
import java.util.ArrayList;

public class JoinOperator extends BinaryOperatorNode{
	Expression expression;
	Tuple tuple1;
	
	/**initialization method just to define the operatorNode
	 * 
	 * @param leftChild the leftOperator
	 * @param rightChild the rightOperator
	 */
	//need to judge if the expression is null
	public JoinOperator(OperatorNode leftChild,OperatorNode rightChild, Expression expression){
		super(leftChild,rightChild);
		this.expression = expression;
	}
	
	public JoinOperator(OperatorNode leftChild,OperatorNode rightChild){
		super(leftChild,rightChild);
		//make the tuple1 not null
		tuple1 = leftChild.getNextTuple();
	}
	

	/**
	 * scan one the the left Tuple first
	 */
	public Tuple getNextTuple(){
		// first we need to judge the leftnode od the join is not null if it is null means we have finished
		if(tuple1 == null)
			return null;
		// get the tuple from right node
		Tuple tuple2 = this.getRightChild().getNextTuple();
		if(tuple2 == null){
			 this.getRightChild().reset();
			 tuple1 = this.getLeftChild().getNextTuple();
			 return getNextTuple();
		}
		else{
			// we need to judge the condition here
			/**
			 * important part we need to judge condition to judge if we will keep the tuple
			 * how can I judge the join condition for two different tables
			 */
			ArrayList<String> content1 = new ArrayList<String>();
			content1 = tuple1.getContent();
			
			ArrayList<String> content2 = new ArrayList<String>();
			content2 = tuple2.getContent();
			
			ArrayList<String> content3 = new ArrayList<String>();
			content3.addAll(content1);
			content3.addAll(content2);
			
			String result = new String();
			int leng = content3.size();
			for(int i = 0; i< leng; i++){
				result += content3.get(i);
				result += ",";
			}		
			result = result.substring(0, result.length() - 1);
			Tuple newTuple = new Tuple(result);		
//			ExpressionVisitorImp visitor = new ExpressionVisitorImp(newTuple);
//			expression.accept(visitor);
//			boolean judge = visitor.getResult();
//			if(judge){
//				return newTuple;
//			}
//			else{
//				return getNextTuple();
//			}
			return newTuple;
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
		
	
	public void reset(){
		
	}

}
