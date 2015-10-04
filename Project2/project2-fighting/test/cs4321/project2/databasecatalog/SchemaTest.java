package cs4321.project2.databasecatalog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import cs4321.project2.databasecatalog.Schema;

public class SchemaTest {

	public SchemaTest() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws IOException{
		Schema schema = Schema.getInstance();
		//this is the where the schema information stores.
		schema.getSchemaInfor("samples/input/db/schema.txt");
		int index = schema.getAttributeIndex("Sailors", "C");
		System.out.println(index);
		System.out.println(schema.getAddress("Sailors"));
		
		File file;
		FileReader fr;
		try {
			file = new File(schema.getAddress("Sailors"));
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
		BufferedReader reader = new BufferedReader(fr);
		String content = reader.readLine();
		System.out.println(content);
	}
	//  /Users/hanwenwang/Desktop/schema.txt 
}
