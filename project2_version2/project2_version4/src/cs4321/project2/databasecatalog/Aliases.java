package cs4321.project2.databasecatalog;

import java.util.HashMap;
// all table name has its alias
public class Aliases {
	//to store the name of of aliase and the the table name it refers to
	HashMap<String, String> map = new HashMap<String, String>();
	private static Aliases aliases = null;
	
	private Aliases(){
	}
	//singleton pattern
	public static Aliases getInstance(){
		if(aliases == null){
			aliases = new Aliases();
		}
		return aliases;
	} 
	public void addaliase (String name, String tableName) {
		// TODO Auto-generated constructor stub
		map.put(name, tableName);
	}
	
	public String getTableName(String aliase){
		String result = map.get(aliase);
		return result;
	}
}
