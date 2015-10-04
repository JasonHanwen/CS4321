package otherTest;
import cs4321.project2.databasecatalog.Tuple;

public class EqualTest {

	public EqualTest() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args){
		Tuple tuple1 = new Tuple("1");
		Tuple tuple2 = new Tuple("1");
		boolean result = false;
		result = tuple1.equals(tuple2);
		System.out.println(result);
	}

}
