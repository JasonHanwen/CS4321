package cs4321.project2.operator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cs4321.project2.databasecatalog.Tuple;
import cs4321.project2.databasecatalog.TupleComparator;

public class DuplicateEliminationOperator extends UnaryOperatorNode{
	//store the last tuple
	Tuple lastTuple;
	TupleComparator  tupleComparator = new TupleComparator ();
	
	public DuplicateEliminationOperator(OperatorNode child) {
		super(child);
	}
	
	public void reset(){
		
	}
	
	/**
	 * get the distinct tuple from its child
	 */
	public Tuple getNextTuple(){
		Tuple tuple = super.getChild().getNextTuple();
		// the the tuple readed from its child is null than return null
		if(tuple == null)
			return null;
		
		if(lastTuple == null){
			lastTuple = tuple;
			return tuple;
		}
		
		else{
			if(tupleComparator.equals(lastTuple, tuple)){
				return getNextTuple();
			}
			else{
				lastTuple = tuple;
				return tuple;
			}
		}
	}
	
	public void dump(){
		Tuple tuple = new Tuple("Hanwen Wang");
		do{
			tuple = getNextTuple();
			if(tuple!=null){
				System.out.println(tuple.getContentString());
			}
		}while(tuple!=null);
	}
	
	public void dump(String outputFile){
		File file = new File(outputFile);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileWriter fw;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
		} catch (IOException e) {
			e.printStackTrace();
			return;		
		}
		BufferedWriter bw = new BufferedWriter(fw);
		Tuple tuple = new Tuple("Hanwen Wang");
		do{
			tuple = getNextTuple();
			if(tuple!=null){
				try {
					bw.write(tuple.getContentString());
					bw.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}while(tuple!=null);
		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
