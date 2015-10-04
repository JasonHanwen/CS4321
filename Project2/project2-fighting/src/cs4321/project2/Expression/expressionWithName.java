package cs4321.project2.Expression;


import net.sf.jsqlparser.expression.*;
/**
 * this class is to store the all join expressions 
 * @author hanwenwang
 *
 */
public class expressionWithName {
	
	String name1;  //this is the expression table Name
	String name2;
	boolean isjoin;  //this is if this expression is join
	Expression e;   // this is a expression
	public expressionWithName(String name, boolean isjoin, Expression e) {
		this.name1 = name;
		this.isjoin = isjoin;
		this.e = e;
	}
	public expressionWithName(String name1,String name2, boolean isjoin, Expression e) {
		this.name1 = name1;
		this.name2 = name2;
		this.isjoin = isjoin;
		this.e = e;
	}
	public String getName1() {
		return name1;
	}
	public String getName2(){
		return name2;
	}
	public boolean isIsjoin() {
		return isjoin;
	}
	public Expression getE() {
		return e;
	}
}
