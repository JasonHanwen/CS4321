package cs4321.project2.sqlparser;
import java.io.FileReader;
import java.io.File;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.expression.*;

import cs4321.project2.databasecatalog.DatabaseCatalog;
import cs4321.project2.databasecatalog.Table;
import cs4321.project2.operator.ScanOperator;
import java.util.List;

//
/**
 * Example class for getting started with JSQLParser. Reads SQL statements from
 * a file and prints them to screen; then extracts SelectBody from each query
 * and also prints it to screen.
 * 
 * @author Lucja Kot
 */
//so this function can get every thing I want to learn from a query about scan and selection
public class ParserExample {

	private static final String queriesFile = "queries.sql";

	public static void main(String[] args) {
		try {
//			File file = new File("/Users/hanwenwang/Desktop/queries.sql");
//			FileReader fr = new FileReader (file);
//			 char[] c = new char[(int) file.length()];
//			 fr.read(c);
//			 System.out.println(new String(c));
			
			//so at first we need to add a String address to the hashtable
			
		
			Table Sailors = new Table("Sailors","/Users/hanwenwang/Desktop/Sailors.rtf");
//			DatabaseCatalog tableMap = new DatabaseCatalog();
			DatabaseCatalog tableMap = DatabaseCatalog.getInstance();
			
			tableMap.addTableInfo(Sailors);
			System.out.println(tableMap.getTableLocated("Sailors"));
			
//			ScanOperator scanOperator = new ScanOperator("Sailors");
////			scanOperator.setTableName("Sailors");
//			scanOperator.tableName = "Sailors";
//			System.out.println(scanOperator.tableName);
//			System.out.println(scanOperator.getTableName());
			
			CCJSqlParser parser = new CCJSqlParser(new FileReader("/Users/hanwenwang/Desktop/queries.sql"));
			Statement statement;
			while ((statement = parser.Statement()) != null) {
				System.out.println("Read statement: " + statement);
				Select select = (Select) statement;
				System.out.println("Select body is " + select.getSelectBody());
				//document: http://jsqlparser.sourceforge.net/docs/net/sf/jsqlparser/statement/select/PlainSelect.html
				PlainSelect plainSelect = (PlainSelect)select.getSelectBody();	
				System.out.println(plainSelect.getFromItem().toString());
				ScanOperator scanOperator = new ScanOperator(plainSelect.getFromItem().toString());
				
				Expression expression = plainSelect.getWhere();
				List<String> selectItems = plainSelect.getSelectItems();
				// this is projection
				plainSelect.getSelectItems();
				
				scanOperator.dump();
//				plainSelect.getFromItem();
//				plainSelect.getWhere();
//				plainSelect.getSelectItems();
//				plainSelect.getJoins();
//				System.out.println("Select body is " + plainSelect.getSelectItems());
//				System.out.println("Select body is " + plainSelect.getFromItem());
//				System.out.println("Select body is " + plainSelect.getWhere());
//				System.out.println("Select body is " + plainSelect.getJoins());			
			}
		} catch (Exception e) {
			System.err.println("Exception occurred during parsing");
			e.printStackTrace();
		}
	}
}