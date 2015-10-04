package cs4321.project2.databasecatalog;

import java.util.HashMap;

public class Aliases {
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

}
