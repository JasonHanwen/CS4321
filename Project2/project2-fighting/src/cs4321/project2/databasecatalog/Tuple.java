package cs4321.project2.databasecatalog;

import java.util.ArrayList;

/**
 * @author hanwenwang
 * it parse the input string to several parts and add all of them to content
 * need to use schema information to improve the structure;
 * so schema is a structure and DatabaseCatalog is underlying structure
 */

public class Tuple{

	private ArrayList<String> content = new ArrayList<String>();
	//to store the index of a list that how can a tuple can be sorted
//	private ArrayList<String> AttributesOrder = new ArrayList<String>();
	
	public Tuple(String input) {
		String[] stringArray = input.split(",");
		for(int i = 0; i < stringArray.length; i++){
			content.add(stringArray[i]);
		}
	}

//	}
	/**
	 * @param SortedItems
	 * @method to add the method to add the sortedItems for Tuple
	 */
//	public void addSortedItems(String SortedItems){
//		
//	}

	public ArrayList<String> getContent() {
		return content;
	}
	
	//should change the name to ToString
	public String getContentString(){
		int leng = content.size();
		String result = new String();
		for(int i = 0; i < leng; i++){
			result += content.get(i);
			result += ",";
		}
		result = result.substring(0,result.length()-1);
		return result;
	}
	
	
	
   /**
    * This method is to get the compare value between tuple1 and tuple2
    */
//	public int compareTo(Tuple tuple2){
//		int leng = AttributesOrder.size();
//		int result = 0;
//		for(int i = 0; i < leng; i++){
//			String ele1 = this.content.get(i);
//			String ele2 = tuple2.content.get(i);
//			result = ele1.compareTo(ele2);
//			if(result != 0)
//				break;
//		}
//		return result;
//	}
}
