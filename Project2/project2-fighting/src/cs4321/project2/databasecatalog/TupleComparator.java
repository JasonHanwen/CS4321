package cs4321.project2.databasecatalog;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import cs4321.project2.databasecatalog.Schema;
/**
 * @author hanwenwang
 * the tuple comparator is to compare different tuple so that we can compare two different tuples
 * when using this tuplecomparator, the first thing we need to do is do add the indexList, so that 
 * the compare method can compare two tuples according to different list
 */
//we should design a schema for this comparator for order by project form

public class TupleComparator implements Comparator<Tuple>{
	
	ArrayList<String> sortedList = new ArrayList<String>();
	ArrayList<Integer> indexList = new ArrayList<Integer>();
	
    /**
     * get the indexList, and compare two tuples according to the integer of indexList
     * now the two tuple are compared according to string
     * @param tuple1: the first tuple need to be compared
     */
	@Override
	public int compare(Tuple tuple1, Tuple tuple2) {
		// TODO Auto-generated method stub
		int leng = indexList.size();	
		int result = 0;
		for(int i = 0; i < leng; i++){
			int index = indexList.get(i);
			String ele1 = tuple1.getContent().get(index);
			String ele2 = tuple2.getContent().get(index);
			result = ele1.compareTo(ele2);
			if(result != 0)
				break;
		}
		return result;		
	}
	
	/**
	 * the method to judge whether or not the two tuple is equal
	 * @param tuple1
	 * @param tuple2
	 * @return if the two tuples are equal return true; else return false
	 */
	public boolean equals(Tuple tuple1, Tuple tuple2){
		int leng1 = tuple1.getContent().size();
		int leng2 = tuple2.getContent().size();
		if(leng1 != leng2)
			return false;
		for(int i = 0; i < leng1; i++){
			if(tuple1.getContent().get(i).equals(tuple2.getContent().get(i))){
				;
			}
			else{
				return false;
			}
			
		}
		return true;
	}
	
	/**
	 * this method is to add the indexList of the sorting
	 * we can add different order list accordingly
	 * @param OrderByElements//if we get order by list that we can get the list
	 */
	public void addSortedItems(List OrderByElements){	
	    int leng = OrderByElements.size();
	    Schema schema = Schema.getInstance();    
	    for(int i = 0; i < leng; i++){
	    	String tem = OrderByElements.get(i).toString();
	    	tem = tem.replace('.', ',');
	    	String[] sliptem = tem.split(","); 
	    	//get the true tableName
	    	Aliases aliases = Aliases.getInstance();
	    	String tureTableName = aliases.getTableName(sliptem[0]);
	    	int index = schema.getAttributeIndex(tureTableName, sliptem[1]);
	    	indexList.add(index);
	    }  
	}
}
