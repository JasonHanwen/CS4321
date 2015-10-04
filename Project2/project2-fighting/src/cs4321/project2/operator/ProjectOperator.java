package cs4321.project2.operator;

import cs4321.project2.databasecatalog.Schema;
import cs4321.project2.databasecatalog.Tuple;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import net.sf.jsqlparser.schema.Column;
import java.util.ArrayList;

public class ProjectOperator extends UnaryOperatorNode{
	//so we can know the schema we have to search the content of table
	Schema schema = Schema.getInstance();
	ArrayList<String> selectItemsList = new ArrayList<String>();
	boolean isAllItems = false;
	String tableName;
	/**
	 * constructor method of this operatorNode read the list of selectItems.
	 * @param child
	 * @param selectItems
	 */
	public ProjectOperator(OperatorNode child, List selectItems) {
		super(child);		
		//if the selectItems is * so we need to get all the information from the table, now we do not have table name
		addAllItems(selectItems);
	}
	
	public ProjectOperator(OperatorNode child, List selectItems, String tableName) {
		super(child);		
		//if the selectItems is * so we need to get all the information from the table, now we do not have table name
		addAllItems(selectItems);
		this.tableName = tableName;
	}
	/**
	 * a constructor method that deals with the situation that slect all columns
	 * @param child
	 * @param selectItems
	 * @param tableName
	 */
	
	/** 
	 * add all needed items to schema
	 */
	public void addAllItems(List selectItems){
		if(selectItems != null && selectItems.get(0).toString().equals("*")){
			isAllItems = true;		
		}
		else{
			int leng = selectItems.size();
			for(int i = 0; i < leng; i++){
				String tem = selectItems.get(i).toString();
				System.out.println(tem);
				selectItemsList.add(tem);
			}	
		}
	}
	
	public Tuple getNextTuple(){
		Tuple tuple = super.getChild().getNextTuple();
		if(tuple == null){
			return null;
		}
		else{
			if(isAllItems)
				return tuple;
		    String result = new String();
		    int leng = selectItemsList.size();
		    for(int i = 0; i < leng; i++){
		    	String tem = selectItemsList.get(i);
		    	//split the string to two parts. the first one is the name of the table 
		    	//and the other is the name of the attribute
		    	//replace the item in the table name "." to ","
		    	tem = tem.replace('.', ',');
		    	String[] sliptem = tem.split(",");
		    	//we must change here to use alias
		    	int index = schema.getAttributeIndex(sliptem[0], sliptem[1]);
		    	result += (tuple.getContent().get(index));
 		    	result += ",";
		    }
		    result = result.substring(0,result.length() - 1);
//		    System.out.println(result);
		    return new Tuple(result);
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
