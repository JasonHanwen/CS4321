package cs4321.project2.databasecatalog;

import java.util.*;
/**
 * 
 * @author hanwenwang
 *
 */
public class Table {
	private String name;
	private String lacatedInformation;
	private ArrayList<String> attributes;
	// how to get  the schema of one table
//	private HashMap<String,Integer> schema = new HashMap<String,Integer>();
	
	public Table(String name, String lacatedInformation) {
		super();
		this.name = name;
		this.lacatedInformation = lacatedInformation;
	}
	/**
	 * 
	 * @param name
	 * @param lacatedInformation
	 * @param attributes means the all attributes of one table
	 */
	
	public Table(String name, String lacatedInformation,ArrayList<String>attributes){
		this.name = name;
		this.lacatedInformation = lacatedInformation;
		this.attributes = attributes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLacatedInformation() {
		return lacatedInformation;
	}

	public void setLacatedInformation(String lacatedInformation) {
		this.lacatedInformation = lacatedInformation;
	}
	public ArrayList<String> getAttributes() {
		return attributes;
	}	
}
