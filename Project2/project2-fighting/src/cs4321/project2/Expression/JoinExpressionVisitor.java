package cs4321.project2.Expression;

import net.sf.jsqlparser.expression.*;
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
import java.util.HashMap;
import java.util.List;
import cs4321.project2.databasecatalog.Aliases;
import java.util.ArrayList;

/**
 * this visitor is to 
 * @author hanwenwang
 *
 */
public class JoinExpressionVisitor implements ExpressionVisitor{

	List<expressionWithName> selectExpression = new ArrayList<expressionWithName>();
	List<expressionWithName> joinExpression = new ArrayList<expressionWithName>();
	String tableName = new String();
	
	public List<expressionWithName> getSelectExpression() {
		return selectExpression;
	}

	public List<expressionWithName> getJoinExpression() {
		return joinExpression;
	}

	public JoinExpressionVisitor() {
		// TODO Auto-generated constructor stub
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
	public void visit(LongValue arg0) {
		// TODO Auto-generated method stub
		
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
		leftExpression.accept(this);
	
		Expression rightExpression = arg0.getRightExpression();
		rightExpression.accept(this);
		
	}

	@Override
	public void visit(OrExpression arg0) {

		
	}

	@Override
	public void visit(Between arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(EqualsTo arg0) {
		Expression leftexpression = arg0.getLeftExpression();
		Expression rightexpression = arg0.getRightExpression();
		String name1 = new String();
		String name2 = new String();
		boolean isJoin = getGetclasswithName(leftexpression,rightexpression, name1,name2);
		expressionWithName ExpressionWithName;
		if(isJoin){
			ExpressionWithName = new expressionWithName(name1,name2,isJoin,arg0);
			joinExpression.add(ExpressionWithName);
		}
		else{
			ExpressionWithName = new expressionWithName(name1,isJoin,arg0);
			selectExpression.add(ExpressionWithName);
		}
	}

	@Override
	public void visit(GreaterThan arg0) {
		Expression leftexpression = arg0.getLeftExpression();
		Expression rightexpression = arg0.getRightExpression();
		String name1 = new String();
		String name2 = new String();
		boolean isJoin = getGetclasswithName(leftexpression,rightexpression, name1,name2);
		expressionWithName ExpressionWithName;
		if(isJoin){
			ExpressionWithName = new expressionWithName(name1,name2,isJoin,arg0);
			joinExpression.add(ExpressionWithName);
		}
		else{
			ExpressionWithName = new expressionWithName(name1,isJoin,arg0);
			selectExpression.add(ExpressionWithName);
		}	
	}

	@Override
	public void visit(GreaterThanEquals arg0) {
		Expression leftexpression = arg0.getLeftExpression();
		Expression rightexpression = arg0.getRightExpression();
		String name1 = new String();
		String name2 = new String();
		boolean isJoin = getGetclasswithName(leftexpression,rightexpression, name1,name2);
		expressionWithName ExpressionWithName;
		if(isJoin){
			ExpressionWithName = new expressionWithName(name1,name2,isJoin,arg0);
			joinExpression.add(ExpressionWithName);
		}
		else{
			ExpressionWithName = new expressionWithName(name1,isJoin,arg0);
			selectExpression.add(ExpressionWithName);
		}
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

	@Override
	public void visit(MinorThan arg0) {
		Expression leftexpression = arg0.getLeftExpression();
		Expression rightexpression = arg0.getRightExpression();
		String name1 = new String();
		String name2 = new String();
		boolean isJoin = getGetclasswithName(leftexpression,rightexpression, name1,name2);
		expressionWithName ExpressionWithName;
		if(isJoin){
			ExpressionWithName = new expressionWithName(name1,name2,isJoin,arg0);
			joinExpression.add(ExpressionWithName);
		}
		else{
			ExpressionWithName = new expressionWithName(name1,isJoin,arg0);
			selectExpression.add(ExpressionWithName);
		}
	}

	@Override
	public void visit(MinorThanEquals arg0) {
		Expression leftexpression = arg0.getLeftExpression();
		Expression rightexpression = arg0.getRightExpression();
		String name1 = new String();
		String name2 = new String();
		boolean isJoin = getGetclasswithName(leftexpression,rightexpression, name1,name2);
		expressionWithName ExpressionWithName;
		if(isJoin){
			ExpressionWithName = new expressionWithName(name1,name2,isJoin,arg0);
			joinExpression.add(ExpressionWithName);
		}
		else{
			ExpressionWithName = new expressionWithName(name1,isJoin,arg0);
			selectExpression.add(ExpressionWithName);
		}
	}

	@Override
	public void visit(NotEqualsTo arg0) {
		// TODO Auto-generated method stub
		Expression leftexpression = arg0.getLeftExpression();
		Expression rightexpression = arg0.getRightExpression();
		String name1 = new String();
		String name2 = new String();
		boolean isJoin = getGetclasswithName(leftexpression,rightexpression, name1,name2);
		expressionWithName ExpressionWithName;
		if(isJoin){
			ExpressionWithName = new expressionWithName(name1,name2,isJoin,arg0);
			joinExpression.add(ExpressionWithName);
		}
		else{
			ExpressionWithName = new expressionWithName(name1,isJoin,arg0);
			selectExpression.add(ExpressionWithName);
		}
	}

	@Override
	public void visit(Column arg0) {
				// TODO Auto-generated method stub
				String table = arg0.getTable().getName();
//				String trueTable = aliases.getTableName(table);
				tableName = table;
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
	/**
	 * this method is used when visited column class, so that we can get the tableName to judge
	 * if this is for joinexpression or selectexpression
	 * @return
	 */
	public String getTableName(){
		return tableName;
	}
	/**
	 * we can change the name to the construct the expressionWithName we want
	 * @param left
	 * @param right
	 * @param Name
	 * @return
	 */
	public boolean getGetclasswithName(Expression left, Expression right, String Name1, String Name2){
		 	
		JoinExpressionVisitor joinExpressionVisitor1 = new JoinExpressionVisitor();
		left.accept(joinExpressionVisitor1);
		
		JoinExpressionVisitor joinExpressionVisitor2 = new JoinExpressionVisitor();
		right.accept(joinExpressionVisitor2);
        
		boolean isJoin = false;
		
		String tableName1 = joinExpressionVisitor1.getTableName();
		String tableName2 = joinExpressionVisitor2.getTableName();
		
		if(!tableName1.equals("") && !tableName2.equals("") && !tableName1.equals(tableName2)){
			Name1 = tableName1;
			Name2 = tableName2;
			isJoin = true;
		}
		else if(!tableName1.equals("")){//tableName1 is not table
			String newName = new String();
			newName += tableName1;
			isJoin = false;
			Name1 = newName;
		}
		else if(!tableName2.equals("")){
			String newName = new String();
			newName += tableName2;
			isJoin = false;
			Name1 = newName;
		}
		return isJoin;
	}
}
