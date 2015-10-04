package cs4321.project2.databasecatalog;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * this class is here for tackle the situation that after project we need to sort the whole tuple
 * otherwise we can not find the index of the schema
 */
public class intermeSchema {

	//this is to store the attribut list
	HashMap <String, ArrayList<String>> schema = new HashMap<String, ArrayList<String>>();
	//this is to store the address information for a specific table
	HashMap <String, String> addressList = new HashMap <String, String>();
	
	public intermeSchema() {
		// TODO Auto-generated constructor stub	
	}
	
	private static intermeSchema intermeSchemaInstance = null;
	public static intermeSchema getInstance(){
		if(intermeSchemaInstance == null){
			intermeSchemaInstance = new intermeSchema();
		}
		return intermeSchemaInstance;
	} 
	
	public int getAttributeIndex(String tableName, String attributeName){
		//if we want to read the aliase from the what's the name for this shcema
		//aliase is still the name before, aliase is as map
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
}
