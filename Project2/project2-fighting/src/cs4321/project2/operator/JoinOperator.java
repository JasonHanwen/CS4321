package cs4321.project2.operator;

import cs4321.project2.Expression.ExpressionVisitorImp;
import cs4321.project2.databasecatalog.Tuple;
import net.sf.jsqlparser.expression.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		tuple1 = leftChild.getNextTuple();
	}
	
	public JoinOperator(OperatorNode leftChild,OperatorNode rightChild, Expression expression, String name1, String name2){
		super(leftChild,rightChild,name1,name2);
		this.expression = expression;
		tuple1 = leftChild.getNextTuple();
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
			String name1 = this.getLeftChildName();
			String name2 = this.getRightChildName();
			List<String> nameList = new ArrayList<String>();
			nameList.add(name1);
			nameList.add(name2);
			
			List<Tuple> tupleList = new ArrayList<Tuple>();
			tupleList.add(tuple1);
			tupleList.add(tuple2);		
			ExpressionVisitorImp expressionVisitorImp = new ExpressionVisitorImp(nameList, tupleList);
			expression.accept(expressionVisitorImp);
			boolean judge = expressionVisitorImp.getResult();
			
			if(judge){
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
				return newTuple;
			}
			else{
				return getNextTuple();
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
		
	
	public void reset(){
		
	}

}
