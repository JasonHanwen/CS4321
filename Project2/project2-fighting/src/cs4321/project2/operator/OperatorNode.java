package cs4321.project2.operator;

import java.io.IOException;

import cs4321.project2.databasecatalog.Tuple;

//need design pattern
public abstract class OperatorNode {
	/**
	 * Abstract method for OperatorNode
	 * you can call getNextTuple() repeatedly to get the next tuple of the operator’s output. This is sometimes called “pulling tuples” from the operator. If the operator still has some available output, it will return the next tuple, otherwise it should return null.
	 * 
	 * @param visitor
	 *            visitor to be accepted
	 */
	public abstract Tuple getNextTuple();
	
	/**
	 * The reset() method tells the operator to reset its state and start returning its output again from the beginning
	 */
	public abstract void reset();
	
	/**
	 * The dump(String) method This method repeatedly calls getNextTuple() and writes each tuple to a suitable PrintStream
	 * the String means the output path
	 */
	public abstract void dump (String outputString);
	
	/**
	 * the dump
	 */
	public abstract void dump();

}
