package cs4321.project2.databasecatalog;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import cs4321.project2.databasecatalog.Aliases;
/**
 * @author hanwen wang
 * Schema information that store the schema information of all tables.
 * @param schema : a HashMap that stores the information of table the all its attributes
 * @param addressList : a HashMap that stores the name of that table and the addres that the table stores.
 */
public class Schema {
	//key: table Name and ArrayList<String> refers to all its attributes
	HashMap <String, ArrayList<String>> schema = new HashMap<String, ArrayList<String>>();
	//this is to store the address information for a specific table
	HashMap <String, String> addressList = new HashMap <String, String>();
	//so when read this function
	
	private static Schema schemaInstance = null;
	
	
	private Schema(){
	}
	//singleton pattern
	public static Schema getInstance(){
		if(schemaInstance == null){
			schemaInstance = new Schema();
		}
		return schemaInstance;
	} 
	
	/**
	 * put new information about table schema in this table, can get the attribute
	 * @param tableName       the name of table added
	 * @param attributeList   all their attributes form a linked list with string
	 */
	public void addSchemaInfo(String tableName, ArrayList<String> attributeList){
			schema.put(tableName, attributeList);
	}
	
	/**
	 * get the index of one attribute for one table
	 * @return the index of the attribute with name of attributeName in table with tableName
	 */
	public int getAttributeIndex(String tableName, String attributeName){
		Aliases aliases = Aliases.getInstance();
		String referTableName = aliases.getTableName(tableName);	
		ArrayList<String> AttributeList;
		try{	 AttributeList  = schema.get(referTableName);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		
		int result = -1;
		
		try{ 
			result = AttributeList.indexOf(attributeName);		
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * this method is to return the address information for specific table
	 * @param tableName
	 * @return
	 */
	public String getAddress(String tableName){
		return addressList.get(tableName);
	}
	/**
	 * we want to read all the schema information from one file. So that we need to store the schema information
	 * @param schemaLocation we can define the shema location
	 * @throws IOException 
	 */
	public void getSchemaInfor(String schemaLocation) throws IOException{				
				File file;
				FileReader fr;
				try {
					file = new File(schemaLocation);
					fr = new FileReader (file);
				} catch (NullPointerException e) {
					System.out.println("the schema information location can not be read");
					e.printStackTrace();
					return ;
				} catch (Exception e) {
					System.out.println("the schema information location can not be read");
					e.printStackTrace();
					return;
				} 	 
				BufferedReader reader = new BufferedReader(fr);
				//read the first line of this file
				String rowInf = new String("wanghanwen");
				while(rowInf != null){
					try{
						rowInf = reader.readLine();
						//System.out.println(rowInf); 
						if(rowInf != null){
							String[] stringArray = rowInf.split(" ");
							// this is to store all attribute String lsit for a single table
							ArrayList<String> newAttInf = new  ArrayList<String>();
							// so the first element of the line is the name of the table
							// the other elements are the attributes we need
							for(int i = 1; i < stringArray.length; i++){
								newAttInf.add(stringArray[i]);
							}
							String tableName = stringArray[0];
							
							schema.put(tableName, newAttInf);
							
							//put the schema address information into the schema
							String address = new String();
							address = "samples/input/db/data/";
							address = address + tableName;
							address = address + ".csv";
							addressList.put(tableName, address);
							
							//when I read this table in the schema,I need to store the tableName as Aliases
							Aliases aliases = Aliases.getInstance();
							aliases.addaliase(tableName, tableName);
						}
					}catch(IOException e){					
						e.printStackTrace();
					}	
				}
				reader.close();	
				fr.close();
	}
	
	/** 
	 * if the user did not define where we should read the location information, we use default address information
	 * the default address is in "samples/input/db/schema.txt".
	 * @throws IOException
	 */
	public void getSchemaInfor() throws IOException{
		File file;
		FileReader fr;
		try {
			file = new File("samples/input/db/schema.txt");
			fr = new FileReader (file);
		} catch (NullPointerException e) {
			System.out.println("the schema information location can not be read");
			e.printStackTrace();
			return ;
		} catch (Exception e) {
			System.out.println("the schema information location can not be read");
			e.printStackTrace();
			return;
		} 	 
		BufferedReader reader = new BufferedReader(fr);
		//read the first line of this file
		String rowInf = new String("wanghanwen");
		while(rowInf != null){
			try{
				rowInf = reader.readLine();
				//System.out.println(rowInf); 
				if(rowInf != null){
					String[] stringArray = rowInf.split(" ");
					// this is to store all attribute String lsit for a single table
					ArrayList<String> newAttInf = new  ArrayList<String>();
					// so the first element of the line is the name of the table
					// the other elements are the attributes we need
					for(int i = 1; i < stringArray.length; i++){
						newAttInf.add(stringArray[i]);
					}
					String tableName = stringArray[0];
					
					schema.put(tableName, newAttInf);
					
					//put the schema address information into the schema
					String address = new String();
					address = "samples/input/db/data/";
					address = address + tableName;
					address = address + ".csv";
					addressList.put(tableName, address);
					
					//when I read this table in the schema,I need to store the tableName as Aliases
					Aliases aliases = Aliases.getInstance();
					aliases.addaliase(tableName, tableName);
				}
			}catch(IOException e){					
				e.printStackTrace();
			}	
		}
		reader.close();	
		fr.close();
	}
}
