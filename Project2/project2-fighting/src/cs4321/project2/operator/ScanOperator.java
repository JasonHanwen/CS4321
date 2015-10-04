package cs4321.project2.operator;

import cs4321.project2.databasecatalog.Tuple;
import java.util.ArrayList;
import cs4321.project2.databasecatalog.DatabaseCatalog;
import cs4321.project2.databasecatalog.Schema;
import cs4321.project2.databasecatalog.*;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import cs4321.project2.databasecatalog.Aliases;
import java.io.FileWriter;
import java.io.BufferedWriter;
//we should use simpleton pattern for database table storation
public class ScanOperator extends LeafOperatorNode{

	public String tableName; //aliases
	public BufferedReader reader;
	public boolean endFile = false;
	public String outFile;
	
	/**
	 * Method to initialize the class
	 */
	public ScanOperator(String tableName){
		//get the true table about the aliase
		this.tableName = tableName;
		Aliases aliases = Aliases.getInstance();
		String referTableName = aliases.getTableName(tableName);
		Schema schema = Schema.getInstance();
		String tableLocation = schema.getAddress(referTableName);
	
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
//		fr.close();
	}
	
	public ScanOperator(String tableName,String outputFile){
		//get the true table about the aliase
		this.outFile = outputFile;
		this.tableName = tableName;
		Aliases aliases = Aliases.getInstance();
		String referTableName = aliases.getTableName(tableName);
		Schema schema = Schema.getInstance();
		String tableLocation = schema.getAddress(referTableName);
	
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
//		fr.close();
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
	 * the reset start a new BufferedReader
	 */
	public void reset(){
		//change if we have finished all files
		endFile = false;
		Schema schema = Schema.getInstance();
		Aliases aliases = Aliases.getInstance();
		String trueTableName = aliases.getTableName(tableName);
		String tableLocation = schema.getAddress(trueTableName);
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
		reader = new BufferedReader(fr);
	}
	
	/**
	 * The dump() method This method repeatedly calls getNextTuple() and writes each tuple to a suitable PrintStream
	 */
	public void dump(){	
		while(endFile != true){
			Tuple tem = getNextTuple();
			if(tem != null){
				System.out.println(tem.getContentString());
			}
			else{
				endFile = true;
			}
		}
	};
	
	
	/**
	 * The dump 
	 * @param outputFile: the location for the outputFile.
	 * @throws IOException 
	 */
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
		while(endFile != true){
			Tuple tem = getNextTuple();
			if(tem != null){
				try {
					bw.write(tem.getContentString());
					bw.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				endFile = true;
			}
		}	
		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getContent(Tuple tuple){
		ArrayList<String> content = tuple.getContent();
		int leng = content.size();
		String result = new String();
		for(int i = 0; i < leng; i++){
			result += content.get(i);
		}
		return result;
	}
			
	public String getTableName(){
		return tableName;
	}
}
