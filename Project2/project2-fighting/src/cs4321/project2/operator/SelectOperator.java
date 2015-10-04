/*time 9.19, 2015*
 * 
 */
package cs4321.project2.operator;

/**
 * @author hanwenwang
 *
 */
import net.sf.jsqlparser.expression.*;
import cs4321.project2.databasecatalog.Tuple;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cs4321.project2.Expression.ExpressionVisitorImp;

public class SelectOperator extends UnaryOperatorNode{
	/**
	 */
	Expression expression;
	String tableName;
	
	public SelectOperator(OperatorNode child, Expression e) {
		// TODO Auto-generated constructor stub
		super(child);
		expression = e;
	}
	
	
	public SelectOperator(OperatorNode child, Expression e, String tableName) {
		// TODO Auto-generated constructor stub
		super(child);
		expression = e;
		this.tableName = tableName;
	}
	
	public Tuple getNextTuple(){
		Tuple tuple = super.getChild().getNextTuple();
		//need to add condition
		if(tuple == null)
			return null;
		ExpressionVisitorImp visitor = new ExpressionVisitorImp(tableName,tuple);
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
	
	public void dump(String outputFile){
		File file = new File(outputFile);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileWriter fw;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
		} catch (IOException e) {
			e.printStackTrace();
			return;		
		}
		BufferedWriter bw = new BufferedWriter(fw);
		Tuple tuple = new Tuple("Hanwen Wang");
		do{
			tuple = getNextTuple();
			if(tuple!=null){
				try {
					bw.write(tuple.getContentString());
					bw.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}while(tuple!=null);
		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
