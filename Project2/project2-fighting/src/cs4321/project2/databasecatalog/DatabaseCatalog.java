package cs4321.project2.databasecatalog;
// so we do not need to achieve all things at once
//use singleton pattern
import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class DatabaseCatalog {
	// So we can have a hashset to store all information about table information
	HashMap<String,Table> tableMap = new HashMap<String,Table>();
	private static DatabaseCatalog databaseCatalog = null;
	
	private DatabaseCatalog(){
	}
	//singleton pattern
	public static DatabaseCatalog getInstance(){
		if(databaseCatalog == null){
			databaseCatalog = new DatabaseCatalog();
		}
		return databaseCatalog;
	} 
	
	public Table getTable(String tableName){
		Table table = tableMap.get(tableName);
		return table;
	}
	
	public String getTableLocated(String tableName){
		String location = tableMap.get(tableName).getLacatedInformation();
		return location;
	}
	
	public void addTableInfo(Table table){
			String name = table.getName();
			tableMap.put(name , table);
	}
	
	/**
	 * 
	 */
	public void autogetTableInfo(){
		//read the file on the desktop
		String location = "/Users/hanwenwang/Desktop/samples/input/db/schema.txt";
		File file;
		FileReader fr;
		try {
			file = new File(location);
			fr = new FileReader (file);
		} catch (NullPointerException e) {
			System.out.println("the catalog location can not be read");
			e.printStackTrace();
			return ;
		} catch (Exception e) {
			System.out.println("the schema information location can not be read");
			e.printStackTrace();
			return;
		} 	 
		BufferedReader reader = new BufferedReader(fr);
		//Initialize the rowInf string 
		String rowInf = new String("wanghanwen");
		while(rowInf != null){
			try{
				rowInf = reader.readLine(); 
				if(rowInf != null){		
					String[] stringArray = rowInf.split(" ");
					//to store the table name
					String name = new String();
					//to store the attributes information
					ArrayList<String> attributes = new  ArrayList<String>();
					/**
					 * based on different system, we should change the stirng accordingly
					 */
					//to store the located address for the table
					String address = new String();
					
					name = stringArray[0];
					address = "/Users/hanwenwang/Desktop/samples/input/db/data/";
					address = address + name;
					address = address + ".csv";
//					System.out.println(address);
					//name is the first work of one sentence
					
					for(int i = 1; i < stringArray.length; i++){
						attributes.add(stringArray[i]);
					}	
					Table table = new Table (name,address,attributes);
					tableMap.put(name, table);
				}
			}catch(IOException e){					
				e.printStackTrace();
			}		
		}
	}
}
