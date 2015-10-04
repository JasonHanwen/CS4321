package OperatorBuildPlan;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;

import java.io.FileReader;
import java.util.List;

/**
 * 
 * @author hanwenwang
 *
 */
import cs4321.project2.operator.*;
import cs4321.project2.databasecatalog.*;
import cs4321.project2.Expression.*;

import net.sf.jsqlparser.schema.Table;
/**
 * the class is to build the tree structure for the query. So that this class can turn the query to
 * a tree structure.
 * 
 * @author hanwenwang
 *
 */
public class OperatorBuild {
	

	public OperatorBuild(){
		// TODO Auto-generated constructor stub
	}
	/**
	 * build the tree structure
	 * 
	 * @param statement
	 * @param the outputfileName is the name for the output file
	 * @return
	 */
	public OperatorNode opetratorbuildTree(Statement statement){
		
			Select select = (Select) statement;
			
//			System.out.println("Select body is " + select.getSelectBody());
			
			PlainSelect plainSelect = (PlainSelect)select.getSelectBody();	
			
//			System.out.println(plainSelect.getFromItem().toString());
			
			//this is no join condition
			OperatorNode root;
			//for the scan operator we need to store the alias in the alias table
			if(plainSelect.getJoins()==null){
				String tableName = ((Table)plainSelect.getFromItem()).getName();
				if(plainSelect.getFromItem().getAlias() != null){
					Aliases aliases = Aliases.getInstance();
					aliases.addaliase(plainSelect.getFromItem().getAlias(), ((Table)plainSelect.getFromItem()).getName());
					tableName = plainSelect.getFromItem().getAlias();
				}
				ScanOperator scanOperator = new ScanOperator(tableName);
			
				root = scanOperator;
				if(plainSelect.getWhere() != null){
					SelectOperator selectOperator = new SelectOperator(scanOperator,plainSelect.getWhere(),tableName);
					root = selectOperator;
				}
				List selectItems = plainSelect.getSelectItems();
				if(selectItems != null && selectItems.get(0).toString().equals("*")){			
				}
				else{
					ProjectOperator projectOperator = new ProjectOperator(root,selectItems);
					root = projectOperator;
				}
				if(plainSelect.getOrderByElements()!=null){
					SortOperator sortOperator = new SortOperator(root,plainSelect.getOrderByElements());
					root = sortOperator;
				}
				if(plainSelect.getDistinct()!=null){
					DuplicateEliminationOperator duplicateEliminationOperator = new DuplicateEliminationOperator(root);
					root = duplicateEliminationOperator;
				}
			}
			else{
				//if(there is a lot of join, I mean that just join a list of join items)
				String tableName1 = ((Table)plainSelect.getFromItem()).getName();
				String tableAlias1 = plainSelect.getFromItem().getAlias();
				if(plainSelect.getFromItem().getAlias() != null){
					Aliases aliases = Aliases.getInstance();
					aliases.addaliase(tableAlias1, tableName1);
				}
				String table1 = tableName1;
				if(tableAlias1!=null)
					table1 = tableAlias1;
				ScanOperator scanOperator = new ScanOperator(table1);
			
				String tableAlias2 = new String();
				String tableTrueName2 = new String();
				System.out.println(plainSelect.getJoins().get(0).toString());
				String joinname = plainSelect.getJoins().get(0).toString();
				//like Reserves AS S
//				joinname.replaceAll(" ", ",");
				String[] nameArray = joinname.split(" ");
				if(nameArray.length <= 1){
					tableTrueName2 = joinname;
				}
				else{
					tableTrueName2 = nameArray[0];
					tableAlias2 = nameArray[2];
				}
				if(!tableAlias2.equals("")){
					Aliases aliases = Aliases.getInstance();
					aliases.addaliase(tableAlias2, tableTrueName2);
				}
				String table2 = tableTrueName2;
				if(!tableAlias2.equals(""))
					table2 = tableAlias2;
				ScanOperator scanOperator2 = new ScanOperator(table2);
				Expression expression = plainSelect.getWhere();
				
				JoinOperator joinOperator = new JoinOperator(scanOperator,scanOperator2,expression,table1,table2);
				root = joinOperator;
			}		
			return root;
	}
}
