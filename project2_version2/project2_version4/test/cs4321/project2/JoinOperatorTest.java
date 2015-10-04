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
import cs4321.project2.operator.JoinOperator;

public class JoinOperatorTest {

	public JoinOperatorTest() {
		// TODO Auto-generated constructor stub
	}
	
public static void main(String[] args) throws IOException {
		
		DatabaseCatalog tableMap = DatabaseCatalog.getInstance();
		tableMap.autogetTableInfo();
		Schema schema = Schema.getInstance();
		schema.getSchemaInfor("/Users/hanwenwang/Desktop/samples/input/db/schema.txt");	
		try {
			CCJSqlParser parser = new CCJSqlParser(new FileReader("/Users/hanwenwang/Desktop/queries 4.sql"));
			
			Statement statement;
			while ((statement = parser.Statement()) != null) {

				Select select = (Select) statement;
				System.out.println("Select body is " + select.getSelectBody());
				PlainSelect plainSelect = (PlainSelect)select.getSelectBody();
				System.out.println(plainSelect.getSelectItems().toString());
				//list join is one list, so now we need just support two elements join
				System.out.println(plainSelect.getJoins().toString());
				System.out.println(plainSelect.getFromItem().toString());
				
				
				ScanOperator scanOperator = new ScanOperator(plainSelect.getFromItem().toString());
				ScanOperator scanOperator2 = new ScanOperator(plainSelect.getJoins().get(0).toString());
//				SelectOperator selectOperator = new SelectOperator(scanOperator,plainSelect.getWhere());
					
//				ProjectOperator projectOperator = new ProjectOperator(selectOperator,plainSelect.getSelectItems());
//				projectOperator.dump();
				
				
				JoinOperator joinOperator = new JoinOperator(scanOperator,scanOperator2);
				joinOperator.dump();
			}
		} catch (Exception e) {
			System.err.println("Exception occurred during parsing");
			e.printStackTrace();
		}
	}

}
