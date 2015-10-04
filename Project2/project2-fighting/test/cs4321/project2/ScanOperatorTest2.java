package cs4321.project2;

import java.io.FileReader;

import cs4321.project2.databasecatalog.DatabaseCatalog;
import cs4321.project2.operator.ScanOperator;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;

/**
 * 
 * @author hanwenwang
 * this is to test reset of the scan operator
 *
 */
public class ScanOperatorTest2 {

	public ScanOperatorTest2() {
		// TODO Auto-generated constructor stub
	}
	
public static void main(String[] args) {
		
		DatabaseCatalog tableMap = DatabaseCatalog.getInstance();
		tableMap.autogetTableInfo();
		System.out.println(tableMap.getTableLocated("Sailors"));
		
		ScanOperator scanOperator = new ScanOperator("Sailors");		
//		scanOperator.dump();
//		scanOperator.getNextTuple();
//		scanOperator.reset();
		scanOperator.dump();
		System.out.println("");
		scanOperator.reset();
		scanOperator.dump();
//		try {
//			CCJSqlParser parser = new CCJSqlParser(new FileReader("/Users/hanwenwang/Desktop/queries.sql"));
//			Statement statement;
//			while ((statement = parser.Statement()) != null) {
//
//				Select select = (Select) statement;
//				System.out.println("Select body is " + select.getSelectBody());
//				PlainSelect plainSelect = (PlainSelect)select.getSelectBody();
//				plainSelect.getFromItem();
//					
//			}
//		} catch (Exception e) {
//			System.err.println("Exception occurred during parsing");
//			e.printStackTrace();
//		}
	}

}
