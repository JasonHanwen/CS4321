package cs4321.project2.operator;

import cs4321.project2.databasecatalog.Tuple;
import java.util.ArrayList;
import cs4321.project2.databasecatalog.DatabaseCatalog;
import java.io.Reader;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


//we should use simpleton pattern for database table storation
public class ScanOperator extends LeafOperatorNode{

	public String tableName;
	public BufferedReader reader;
	public boolean endFile = false;
	
	/**
	 * Method to initialize the class
	 */
	public ScanOperator(String tableName){
		this.tableName = tableName;
		DatabaseCatalog databaseCatalog = DatabaseCatalog.getInstance();
		String tableLocation = databaseCatalog.getTableLocated(tableName);		
		File file;
		FileReader fr;
		try {
			file = new File(tableLocation);
			fr = new FileReader (file);
		} catch (NullPointerException e) {
			System.out.println("the parameter can not be null");
			e.printStackTrace();
			return;
		} catch (Exception e) {
			System.out.println("can not find the text location. Please add the location first");
			e.printStackTrace();
			return;
		} 	 
		//Initialize the BufferedReader
		reader = new BufferedReader(fr);
		
		//mark the head of the file
//		try{reader.mark(0);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
	}
	
		
	/**
	 * Method to get the next Tuple we need
	 * 
	 * @return string representation of next tuple
	 */
	public Tuple getNextTuple(){
		Tuple tuple;
		try{
			String content = reader.readLine();
			if(content!= null){
				tuple = new Tuple(content);
				return tuple;
			}
		}catch(IOException e){
			endFile = true;
			e.printStackTrace();
		}	
		return null;
	}
	
	
	
	/**
	 * The reset() method tells the operator to reset its state and start returning its output again from the beginning
	 */
	public void reset(){
		try{
			reader.reset();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * The dump() method This method repeatedly calls getNextTuple() and writes each tuple to a suitable PrintStream
	 */
	public void dump(){	
		while(endFile != true){
			Tuple tem = getNextTuple();
			if(tem != null){
				System.out.println(getContent(tem));
			}
			else{
				endFile = true;
			}
		}
	};
	
	public String getContent(Tuple tuple){
		ArrayList<String> content = tuple.getContent();
		int leng = content.size();
		String result = new String();
		for(int i = 0; i < leng; i++){
			result += content.get(i);
		}
		return result;
	}
	
	public void setTableName(String tableName){ 
		tableName = this.tableName;
	}
		
	public String getTableName(){
		return tableName;
	}
}
