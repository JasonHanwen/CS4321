package cs4321.project2.Expression;

import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.AllComparisonExpression;
import net.sf.jsqlparser.expression.AnyComparisonExpression;
import net.sf.jsqlparser.expression.CaseExpression;
import net.sf.jsqlparser.expression.DateValue;
import net.sf.jsqlparser.expression.DoubleValue;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.InverseExpression;
import net.sf.jsqlparser.expression.JdbcParameter;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.TimeValue;
import net.sf.jsqlparser.expression.TimestampValue;
import net.sf.jsqlparser.expression.WhenClause;
import net.sf.jsqlparser.expression.operators.arithmetic.Addition;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseAnd;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseOr;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseXor;
import net.sf.jsqlparser.expression.operators.arithmetic.Concat;
import net.sf.jsqlparser.expression.operators.arithmetic.Division;
import net.sf.jsqlparser.expression.operators.arithmetic.Multiplication;
import net.sf.jsqlparser.expression.operators.arithmetic.Subtraction;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
//import the specific relational expression
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.SubSelect;

import cs4321.project2.databasecatalog.Tuple;
import cs4321.project2.databasecatalog.Schema;
import java.util.Stack;
import cs4321.project2.databasecatalog.DatabaseCatalog;
import java.util.HashMap;;

public class ExpressionVisitorImp implements ExpressionVisitor{
	//add a flag to judge the condition
	Boolean judge = false;
	private Tuple tuple;
	Schema schema = Schema.getInstance();
	DatabaseCatalog databaseCatalog = DatabaseCatalog.getInstance();
	Stack<String> stack = new Stack<String>();
	
	HashMap<String, Tuple> tupleList = new HashMap<String, Tuple >();
	
	public ExpressionVisitorImp(Tuple tuple) {
		this.tuple = tuple;
	}
	
	public ExpressionVisitorImp(String tableName, Tuple tuple) {
		tupleList.put(tableName, tuple);
	}

	public void setTuple(Tuple tuple){
		this.tuple = tuple; 
	}
	
	//so we need to set tuple then judge every tuple
	public boolean getResult(){
		return judge;
	}

	@Override
	public void visit(NullValue arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(Function arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(InverseExpression arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(JdbcParameter arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(DoubleValue arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	//turn to 
	public void visit(LongValue arg0) {
		stack.push(arg0.toString());
	}
	@Override
	public void visit(DateValue arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(TimeValue arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(TimestampValue arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(Parenthesis arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(StringValue arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(Addition arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(Division arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(Multiplication arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(Subtraction arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(AndExpression arg0) {
		// TODO Auto-generated method stub
		Expression leftExpression = arg0.getLeftExpression();
		Expression rightExpression = arg0.getRightExpression();
		ExpressionVisitorImp visitor1 = new ExpressionVisitorImp(tuple);
		leftExpression.accept(visitor1);
		boolean result1 = visitor1.getResult();
		ExpressionVisitorImp visitor2 = new ExpressionVisitorImp(tuple);
		rightExpression.accept(visitor2);
		boolean result2 = visitor2.getResult();
		judge = result1 && result2;
	}
	@Override
	public void visit(OrExpression arg0) {
		// TODO Auto-generated method stub
		Expression leftExpression = arg0.getLeftExpression();
		Expression rightExpression = arg0.getRightExpression();
		ExpressionVisitorImp visitor1 = new ExpressionVisitorImp(tuple);
		leftExpression.accept(visitor1);
		boolean result1 = visitor1.getResult();
		ExpressionVisitorImp visitor2 = new ExpressionVisitorImp(tuple);
		rightExpression.accept(visitor2);
		boolean result2 = visitor2.getResult();
		judge = result1 || result2;
		
	}
	@Override
	public void visit(Between arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(EqualsTo arg0) {
		// TODO Auto-generated method stub
		Expression leftexpression = arg0.getLeftExpression();
		 Expression rightexpression = arg0.getRightExpression();	 	
			//get the left and right expression
			//I want to get the left expression and get the right expression
			//how to get the value
			leftexpression.accept(this);
			rightexpression.accept(this);
			String rightitem = stack.pop();
			String leftitem = stack.pop();
			judge = Integer.parseInt(leftitem) == Integer.parseInt(rightitem);		
	}
	
	@Override
	public void visit(GreaterThan arg0) {
		// TODO Auto-generated method stub
		Expression leftexpression = arg0.getLeftExpression();
		 Expression rightexpression = arg0.getRightExpression();	 	
			//get the left and right expression
			//I want to get the left expression and get the right expression
			//how to get the value
			leftexpression.accept(this);
			rightexpression.accept(this);
			String rightitem = stack.pop();
			String leftitem = stack.pop();
			judge = Integer.parseInt(leftitem) > Integer.parseInt(rightitem);
		
	}
	@Override
	public void visit(GreaterThanEquals arg0) {
		// TODO Auto-generated method stub
		Expression leftexpression = arg0.getLeftExpression();
		 Expression rightexpression = arg0.getRightExpression();
		 	
			//get the left and right expression
			//I want to get the left expression and get the right expression
			//how to get the value
			leftexpression.accept(this);
			rightexpression.accept(this);
			String rightitem = stack.pop();
			String leftitem = stack.pop();
			judge = Integer.parseInt(leftitem) >= Integer.parseInt(rightitem);
		
	}
	@Override
	public void visit(InExpression arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(IsNullExpression arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(LikeExpression arg0) {
		// TODO Auto-generated method stub
		
	}
	// how to implement this visitor pattern
	@Override
	public void visit(MinorThan arg0) {
		Expression leftexpression = arg0.getLeftExpression();
		Expression rightexpression = arg0.getRightExpression();
		//get the left and right expression
		//I want to get the left expression and get the right expression
		//how to get the value
		leftexpression.accept(this);
		rightexpression.accept(this);
		String rightitem = stack.pop();
		String leftitem = stack.pop();
		judge = Integer.parseInt(leftitem) < Integer.parseInt(rightitem);
		
	}
	@Override
	public void visit(MinorThanEquals arg0) {
		// TODO Auto-generated method stub
		Expression leftexpression = arg0.getLeftExpression();
		 Expression rightexpression = arg0.getRightExpression();
		 	
			//get the left and right expression
			//I want to get the left expression and get the right expression
			//how to get the value
			leftexpression.accept(this);
			rightexpression.accept(this);
			String rightitem = stack.pop();
			String leftitem = stack.pop();
			judge = Integer.parseInt(leftitem) <= Integer.parseInt(rightitem);
		
	}
	@Override
	public void visit(NotEqualsTo arg0) {
		// TODO Auto-generated method stub
		Expression leftexpression = arg0.getLeftExpression();
		 Expression rightexpression = arg0.getRightExpression();
		 	
			//get the left and right expression
			//I want to get the left expression and get the right expression
			//how to get the value
			leftexpression.accept(this);
			rightexpression.accept(this);
			String rightitem = stack.pop();
			String leftitem = stack.pop();
			judge = Integer.parseInt(leftitem) != Integer.parseInt(rightitem);
	}
	/**
	 * for Column arg0, we get add the value to the stack
	 */
	@Override
	public void visit(Column arg0) {
		//test it to get the table names
		// TODO Auto-generated method stub
		String columnName = arg0.getColumnName();
		String table = arg0.getTable().getName();
		
		//get the tuple from the tuple list
		Tuple tuple1 = (Tuple)tupleList.get(table);
		int index = schema.getAttributeIndex(table, columnName);
//		System.out.println(index);
		String tem =tuple1.getContent().get(index);
//		System.out.println(tem);
		stack.add(tem);
	}
	
	@Override
	public void visit(SubSelect arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(CaseExpression arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(WhenClause arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(ExistsExpression arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(AllComparisonExpression arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(AnyComparisonExpression arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(Concat arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(Matches arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(BitwiseAnd arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(BitwiseOr arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(BitwiseXor arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
