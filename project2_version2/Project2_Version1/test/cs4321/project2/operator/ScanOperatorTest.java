package cs4321.project2.operator;

import java.io.FileReader;
import java.util.ArrayList;

import cs4321.project2.Expression.ExpressionVisitorImp;
import cs4321.project2.databasecatalog.DatabaseCatalog;
import cs4321.project2.databasecatalog.Table;
import cs4321.project2.databasecatalog.Tuple;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;

import org.junit.Test;
import static org.junit.Assert.*;

public class ScanOperatorTest {
	@Test
	public void testSelectSimpleNum(){
		
	}

	public static void main(String[] args) {
		try {
			Table Sailors = new Table("Sailors","/Users/hanwenwang/Desktop/queries.sql");
			DatabaseCatalog tableMap = DatabaseCatalog.getInstance();
			tableMap.addTableInfo(Sailors);
			System.out.println(tableMap.getTableLocated("Sailors"));
			
			CCJSqlParser parser = new CCJSqlParser(new FileReader("/Users/hanwenwang/Desktop/queries.sql"));
			Statement statement;
			while ((statement = parser.Statement()) != null) {
				System.out.println("Read statement: " + statement);
				Select select = (Select) statement;
				System.out.println("Select body is " + select.getSelectBody());
				PlainSelect plainSelect = (PlainSelect)select.getSelectBody();
				
				System.out.println(plainSelect.getFromItem().toString());
				System.out.println(plainSelect.getWhere());
				
				String tupleTem = "1,200,50";
				String[] stringArray = tupleTem.split(",");
				int leng = stringArray.length;
//				for(int i = 0; i < leng; i++){
//					System.out.println( stringArray[i]);
//				}
				Tuple tuple = new Tuple(tupleTem);
				ArrayList<String> content = tuple.getContent();
				System.out.println(plainSelect.getFromItem().getAlias());
				ExpressionVisitorImp visitor = new ExpressionVisitorImp(tuple);
				plainSelect.getWhere().accept(visitor);
				System.out.println(visitor.getResult());	
			}
		} catch (Exception e) {
			System.err.println("Exception occurred during parsing");
			e.printStackTrace();
		}
	}
}


