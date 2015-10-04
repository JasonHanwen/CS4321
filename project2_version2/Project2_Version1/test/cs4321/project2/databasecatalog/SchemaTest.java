package cs4321.project2.databasecatalog;

import java.io.IOException;

import cs4321.project2.databasecatalog.Schema;

public class SchemaTest {

	public SchemaTest() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws IOException{
		Schema schema = Schema.getInstance();
		schema.getSchemaInfor("/Users/hanwenwang/Desktop/schema.txt");
		int index = schema.getAttributeIndex("Sailors", "C");
		System.out.println(index);
	}
	//  /Users/hanwenwang/Desktop/schema.txt 
}
