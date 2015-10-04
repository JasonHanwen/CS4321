package cs4321.project2.Expression;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.util.ArrayList;

import org.junit.Test;

import cs4321.project2.databasecatalog.DatabaseCatalog;
import cs4321.project2.databasecatalog.Schema;
import cs4321.project2.databasecatalog.Table;
import cs4321.project2.operator.ScanOperator;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import cs4321.project2.databasecatalog.Tuple;
import net.sf.jsqlparser.expression.operators.relational.MinorThan;
import net.sf.jsqlparser.schema.Column;


public class ExpressionVisitorImpTest {

	public ExpressionVisitorImpTest() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		//first get the table information
		Table Sailors = new Table("S","/Users/hanwenwang/Desktop/sailor.txt");		
		DatabaseCatalog tableMap = DatabaseCatalog.getInstance();
		tableMap.addTableInfo(Sailors);
		System.out.println(tableMap.getTableLocated("S"));
		
		//manually test 
		Schema schema = Schema.getInstance();
		ArrayList<String> arrtributes = new ArrayList<String>();
		arrtributes.add("A");
		arrtributes.add("B");
		arrtributes.add("C");
		schema.addSchemaInfo("S", arrtributes);
		
		try {
			CCJSqlParser parser = new CCJSqlParser(new FileReader("/Users/hanwenwang/Desktop/queries.sql"));
			Statement statement;
			while ((statement = parser.Statement()) != null) {
				System.out.println("Read statement: " + statement);
				Select select = (Select) statement;
				System.out.println("Select body is " + select.getSelectBody());
				//document: http://jsqlparser.sourceforge.net/docs/net/sf/jsqlparser/statement/select/PlainSelect.html
				PlainSelect plainSelect = (PlainSelect)select.getSelectBody();
				plainSelect.getFromItem();
				
//				ScanOperator scanOperator = new ScanOperator("Sailors");
				//Table table = tableMap.getTable(plainSelect.getFromItem().toString());
				//first we need to turn the information to table
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
//				((Column)((MinorThan)plainSelect.getWhere()).getLeftExpression()).getColumnName();
//				((Column)((MinorThan)plainSelect.getWhere()).getLeftExpression()).getTable().getName();
//				((Column)((MinorThan)plainSelect.getWhere()).getLeftExpression()).getTable().getAlias();
////				System.out.println((((MinorThan)plainSelect.getWhere()).getLeftExpression().getClass()));
//				System.out.println(((Column)((MinorThan)plainSelect.getWhere()).getLeftExpression()).getColumnName());
//				//so we need to construct the table name through the expression
//				System.out.println(((Column)((MinorThan)plainSelect.getWhere()).getLeftExpression()).getTable().getName());
//				System.out.println(((Column)((MinorThan)plainSelect.getWhere()).getLeftExpression()).getTable().getAlias());
			
//				int index = schema.getAttributeIndex(((Column)((MinorThan)plainSelect.getWhere()).getLeftExpression()).getTable().getName(),((Column)((MinorThan)plainSelect.getWhere()).getLeftExpression()).getColumnName());
//				System.out.println(index);
//				System.out.println(((Column)((MinorThan)plainSelect.getWhere()).getLeftExpression()).getTable().getSchemaName());
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
