package cs4321.project2.databasecatalog;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import cs4321.project2.databasecatalog.Schema;
/**
 * 
 * @author hanwenwang
 * the tuplecomparator is to compare different tuple so that we can compare two different tuples
 * when using this tuplecomparator, the first thing we need to do is do add the indexList, so that 
 * the compare method can compare two tuples according to different list
 */
public class TupleComparator implements Comparator<Tuple>{
	
	ArrayList<String> sortedList = new ArrayList<String>();
	ArrayList<Integer> indexList = new ArrayList<Integer>();
	
    /**
     * get the indexList, and compare two tuples according to the integer of indexList
     * now the two tuple are compared according to string
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
	 * @param OrderByElements
	 */
	public void addSortedItems(List OrderByElements){	
	    int leng = OrderByElements.size();
	    Schema schema = Schema.getInstance();    
	    for(int i = 0; i < leng; i++){
	    	String tem = OrderByElements.get(i).toString();
	    	tem = tem.replace('.', ',');
	    	String[] sliptem = tem.split(",");   
	    	int index = schema.getAttributeIndex(sliptem[0], sliptem[1]);
	    	indexList.add(index);
	    }  
	}

}
