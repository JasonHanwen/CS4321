package cs4321.project2.operator;

import cs4321.project2.databasecatalog.Tuple;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import cs4321.project2.databasecatalog.TupleComparator;

/**
 * 
 * @author hanwenwang
 * sortOperator 
 * @param internBuffer   to store all the tuple from its child
 * @ tupleComparator     to define how to compare the tuple between two tuples
 *
 */
public class SortOperator extends UnaryOperatorNode{
	
	ArrayList<Tuple> internBuffer = new ArrayList<Tuple>();
	TupleComparator tupleComparator = new TupleComparator();
	//to store the position information of the internBuffer with all sorted tuple
	int leng = 0;
	int position = 0;
	
	/**
	 * the constructor method for sortOperator, we need to give the orderByElements to this methods
	 * read all tuples from its child and sort these tuples.
	 * @param child
	 * @param orderByElements
	 */
	public SortOperator(OperatorNode child, List orderByElements){ 
		// TODO Auto-generated constructor stub
		super(child);
		tupleComparator.addSortedItems(orderByElements);
		getAll();
		sort();
	}
	
	/**
	 * this is to read the next tuple from its internbuffer.
	 */
	public Tuple getNextTuple(){
		if(position < leng){
			Tuple tem = internBuffer.get(position);
			position++;
			return tem;
		}
		return null;
	}
	
	/**
	 * this method is to read tuple from its child operator, then store its tuple in its internBuffer
	 * @return Tuple
	 */
	public Tuple getChildTuple(){
		Tuple tuple = super.getChild().getNextTuple();
		if(tuple == null){
			return null;
		}
		else{
			internBuffer.add(tuple);
			return tuple;
		}
	}
	
	
	/**
	 * read all tuples from its child operator and store these tuples to its internBuffer
	 */
	public void getAll(){
		Tuple tuple = new Tuple("Hanwen Wang");
		do{
			tuple = getChildTuple();
			leng ++;
		}while(tuple!=null);
		//get rid of the null
		leng--;
		
	}
	
	 
	
	/**
	 * sort all tuples in its internBuffer
	 */
	public void sort(){
		Collections.sort(internBuffer, tupleComparator);
	}
		
	/**
	 * output all tuples from its innerBuffer and output these tuples
	 */
	public void dump(){
		Tuple tuple = new Tuple("Hanwen Wang");
		do{
			tuple = getNextTuple();
			if(tuple!=null){
				System.out.println(tuple.getContentString());
			}
		}while(tuple!=null);	
	}
	
	public void reset(){
		
	}
}
