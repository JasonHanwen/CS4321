/*time 9.19, 2015*
 * 
 */
package cs4321.project2.operator;

/**
 * @author hanwenwang
 *
 */
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.*;
import net.sf.jsqlparser.expression.operators.arithmetic.*;
import net.sf.jsqlparser.expression.operators.conditional.*;
import net.sf.jsqlparser.expression.operators.relational.*;
import cs4321.project2.databasecatalog.Tuple;



import cs4321.project2.Expression.ExpressionVisitorImp;

public class SelectOperator extends UnaryOperatorNode{
	/**
	 */
	Expression expression;

	
	public SelectOperator(OperatorNode child, Expression e) {
		// TODO Auto-generated constructor stub
		super(child);
		expression = e;
	}
	
	public Tuple getNextTuple(){
		Tuple tuple = super.getChild().getNextTuple();
		//need to add condition
		if(tuple == null)
			return null;
		ExpressionVisitorImp visitor = new ExpressionVisitorImp(tuple);
		//judge the if this tuple is true or false in this situation
		boolean judge = true;
		
		if(expression != null){
			expression.accept(visitor);
			judge = visitor.getResult();
		}
		
		if(judge){
			return tuple;
		}
		else{
			return getNextTuple();
		}	
	}
	
	public void reset(){
		super.getChild().reset();
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
