package cs4321.project2;

import java.io.FileReader;
import java.io.IOException;

import cs4321.project2.databasecatalog.DatabaseCatalog;
import cs4321.project2.databasecatalog.Schema;
import cs4321.project2.operator.ProjectOperator;
import cs4321.project2.operator.ScanOperator;
import cs4321.project2.operator.SelectOperator;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import cs4321.project2.operator.SortOperator;
import cs4321.project2.operator.ProjectOperator;
import cs4321.project2.operator.DuplicateEliminationOperator;

public class DuplicateEliminationOperatorTest {

	public DuplicateEliminationOperatorTest() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws IOException{
		DatabaseCatalog tableMap = DatabaseCatalog.getInstance();
		tableMap.autogetTableInfo();
		System.out.println(tableMap.getTableLocated("Reserves"));
		Schema schema = Schema.getInstance();
		schema.getSchemaInfor("/Users/hanwenwang/Desktop/samples/input/db/schema.txt");
		
		try {
			CCJSqlParser parser = new CCJSqlParser(new FileReader("/Users/hanwenwang/Desktop/queries 3.sql"));
			Statement statement;
			while ((statement = parser.Statement()) != null) {

				Select select = (Select) statement;
				System.out.println("Select body is " + select.getSelectBody());
				PlainSelect plainSelect = (PlainSelect)select.getSelectBody();
				plainSelect.getFromItem();
				
				//first I need to support only one B
				System.out.println(plainSelect.getSelectItems().toString());
				ScanOperator scanOperator = new ScanOperator(plainSelect.getFromItem().toString());
			//	scanOperator.dump();
				ProjectOperator projectOperator = new ProjectOperator(scanOperator,plainSelect.getSelectItems());
				System.out.println(plainSelect.getOrderByElements());	
				SortOperator sortOperator = new SortOperator(projectOperator,plainSelect.getOrderByElements());
//				sortOperator.dump();	
				DuplicateEliminationOperator duplicateEliminationOperator = new DuplicateEliminationOperator(sortOperator);
				duplicateEliminationOperator.dump();
			}
		} catch (Exception e) {
			System.err.println("Exception occurred during parsing");
			e.printStackTrace();
		}
	}
}
