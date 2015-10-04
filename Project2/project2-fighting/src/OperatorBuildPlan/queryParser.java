package OperatorBuildPlan;

import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.expression.*;

import cs4321.project2.databasecatalog.DatabaseCatalog;
import cs4321.project2.databasecatalog.Schema;
import cs4321.project2.databasecatalog.Table;
import cs4321.project2.operator.OperatorNode;
import cs4321.project2.operator.ScanOperator;
import java.util.List;
/**
 * this class is to read the query and output the result. This is a main class
 * @author hanwenwang
 *
 */
public class queryParser {

	public queryParser() {
		// TODO Auto-generated constructor stub
	}
	
	private  static String queriesFile;
	
	public static void main(String[] args) throws IOException {	
		//queriesFile = "/Users/hanwenwang/Desktop/queries.sql";
		//read the schema information
		//the path for the input file
		String inputFilepath = args[0];
		inputFilepath = inputFilepath+"/queries.sql";
		queriesFile = inputFilepath;
		
		String outputPath = args[1];
		String outputpath = outputPath +"/query";
		
		Schema schema = Schema.getInstance();
		schema.getSchemaInfor();
		
		try {
			CCJSqlParser parser = new CCJSqlParser(new FileReader(queriesFile));
			Statement statement;
			int num = 0;
			while ((statement = parser.Statement()) != null) {
				num++;
				String outputName = outputpath+ num;	
				System.out.println("this is result of query"+num);
				System.out.println("Read statement: " + statement);		
				OperatorBuild operatorBild = new OperatorBuild();
				try{
					OperatorNode root = operatorBild.opetratorbuildTree(statement);
					root.dump(outputName);
				}catch(Exception e){
					System.err.println("Exception occurred for query" + num);
				}
			}
		} catch (Exception e) {
			System.err.println("Exception occurred during parsing");
			e.printStackTrace();
		}
	}
}
