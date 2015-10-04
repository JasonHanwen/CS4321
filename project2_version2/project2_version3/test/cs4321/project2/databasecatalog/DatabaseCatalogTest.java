package cs4321.project2.databasecatalog;

import java.io.IOException;
import java.util.ArrayList;

public class DatabaseCatalogTest {

	public DatabaseCatalogTest() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws IOException{
		DatabaseCatalog databaseCatalog = DatabaseCatalog.getInstance();
		databaseCatalog.autogetTableInfo();
		Table table = databaseCatalog.getTable("Sailors");
		//test name
		System.out.println(table.getName());
		//test attributes
		ArrayList<String> attributes = new ArrayList<String>();
		attributes = table.getAttributes();
		int leng = attributes.size();
		String result = new String();
		for(int i = 0; i < leng; i++){
			result += attributes.get(i);
		}
		System.out.println(result);
	}
}
