package cs4321.project2.databasecatalog;

import java.util.HashMap;
/**
 * a class that uses singeleton pattern, This method for storing the aliase information.
 * @author hanwenwang
 * @param: map : a Hashmap that stores the information between the aliase and the table name for that aliase
 */
public class Aliases {
	//to store the name of of aliase and the the table name it refers to
	HashMap<String, String> map = new HashMap<String, String>();
	private static Aliases aliases = null;
	
	private Aliases(){
	}
	//singleton pattern
	/**
	 * the method to get the Aliase instance using singeleton pattern
	 * @return
	 */
	public static Aliases getInstance(){
		if(aliases == null){
			aliases = new Aliases();
		}
		return aliases;
	} 
	/**
	 * we read a query id a table has aliase,add the aliase into the schema information
	 * @param the name of aliase
	 * @param the table Name behind the aliase name
	 */
	public void addaliase (String name, String tableName) {
		// TODO Auto-generated constructor stub
		map.put(name, tableName);
	}
	
	/**
	 * we need to use this aliase name to find the true table
	 * @param the aliase name  
	 * @return the true table for aliase
	 */
	public String getTableName(String aliase){
		String result = map.get(aliase);
		return result;
	}
	
}
