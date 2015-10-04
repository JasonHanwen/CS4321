package cs4321.project2.databasecatalog;
import java.io.FileReader;
import java.io.IOException;

import cs4321.project2.databasecatalog.Aliases;
import cs4321.project2.operator.ProjectOperator;
import cs4321.project2.operator.ScanOperator;
import cs4321.project2.databasecatalog.DatabaseCatalog;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.schema.Table;

public class AliasesTest {
	public AliasesTest() {
		// TODO Auto-generated constructor stub
	}
	
public static void main(String[] args) throws IOException {
		//get the information of table address
		DatabaseCatalog databaseCatalog = DatabaseCatalog.getInstance();
		databaseCatalog.autogetTableInfo();
		Schema schema = Schema.getInstance();
		schema.getSchemaInfor("samples/input/db/schema.txt");
		Aliases aliases = Aliases.getInstance();
//		aliases.addaliase("S", "Sailors");
//		ScanOperator scanOperator = new ScanOperator("S");		
//		scanOperator.dump();
		
		try {
			CCJSqlParser parser = new CCJSqlParser(new FileReader("/Users/hanwenwang/Desktop/queries.sql"));
			Statement statement;
			while ((statement = parser.Statement()) != null) {
				Select select = (Select) statement;
				System.out.println("Select body is " + select.getSelectBody());
				PlainSelect plainSelect = (PlainSelect)select.getSelectBody();
				String aliasName = ((Table)plainSelect.getFromItem()).getAlias();
				String tableName = ((Table)plainSelect.getFromItem()).getName();
				aliases.addaliase(aliasName, tableName);
				ScanOperator scanOperator1 = new ScanOperator(aliasName);		
//				scanOperator1.dump();
				ProjectOperator projectOperator = new ProjectOperator(scanOperator1,plainSelect.getSelectItems());
				projectOperator.dump();
			}
		} catch (Exception e) {
			System.err.println("Exception occurred during parsing");
			e.printStackTrace();
		}
	}
}
