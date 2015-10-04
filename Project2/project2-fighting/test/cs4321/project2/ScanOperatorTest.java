package cs4321.project2;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.util.ArrayList;

import org.junit.Test;
import cs4321.project2.Expression.ExpressionVisitorImp;
import cs4321.project2.databasecatalog.DatabaseCatalog;
import cs4321.project2.databasecatalog.Tuple;
//需要测试的类
import cs4321.project2.operator.ScanOperator;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.schema.Table;

public class ScanOperatorTest {
//	@Test
//	public void testReset{
//	}
	public static void main(String[] args) {
		
		DatabaseCatalog tableMap = DatabaseCatalog.getInstance();
		tableMap.autogetTableInfo();
		System.out.println(tableMap.getTableLocated("Sailors"));
		
//		ScanOperator scanOperator = new ScanOperator("Sailors");		
//		scanOperator.dump();
//		scanOperator.getNextTuple();
//		scanOperator.reset();
//		scanOperator.dump();
		
		try {
			CCJSqlParser parser = new CCJSqlParser(new FileReader("/Users/hanwenwang/Desktop/queries.sql"));
			Statement statement;
			while ((statement = parser.Statement()) != null) {
				Select select = (Select) statement;
				System.out.println("Select body is " + select.getSelectBody());
				PlainSelect plainSelect = (PlainSelect)select.getSelectBody();
				plainSelect.getFromItem();
				System.out.println(plainSelect.getFromItem().getAlias());
				System.out.println(((Table)plainSelect.getFromItem()).getName());
					
			}
		} catch (Exception e) {
			System.err.println("Exception occurred during parsing");
			e.printStackTrace();
		}
	}
	
}
	
