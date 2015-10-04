package cs4321.project2;

import java.io.FileReader;
import java.io.IOException;

import cs4321.project2.databasecatalog.DatabaseCatalog;
import cs4321.project2.databasecatalog.Schema;
import cs4321.project2.operator.ScanOperator;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import cs4321.project2.operator.SelectOperator;

public class SelectOperatorTest {

	public SelectOperatorTest() {
		// TODO Auto-generated constructor stub
	}
	
public static void main(String[] args) throws IOException {
		
		DatabaseCatalog tableMap = DatabaseCatalog.getInstance();
		tableMap.autogetTableInfo();
		System.out.println(tableMap.getTableLocated("Sailors"));
		Schema schema = Schema.getInstance();
		schema.getSchemaInfor("/Users/hanwenwang/Desktop/samples/input/db/schema.txt");
		
//		ScanOperator scanOperator = new ScanOperator("Sailors");		
////		scanOperator.dump();
////		scanOperator.getNextTuple();
////		scanOperator.reset();
//		scanOperator.dump();
		
		try {
			CCJSqlParser parser = new CCJSqlParser(new FileReader("/Users/hanwenwang/Desktop/queries 2.sql"));
			Statement statement;
			while ((statement = parser.Statement()) != null) {

				Select select = (Select) statement;
				System.out.println("Select body is " + select.getSelectBody());
				PlainSelect plainSelect = (PlainSelect)select.getSelectBody();
				plainSelect.getFromItem();
				ScanOperator scanOperator = new ScanOperator(plainSelect.getFromItem().toString());
				SelectOperator selectOperator = new SelectOperator(scanOperator,plainSelect.getWhere());
				selectOperator.dump();
			}
		} catch (Exception e) {
			System.err.println("Exception occurred during parsing");
			e.printStackTrace();
		}
	}
}
