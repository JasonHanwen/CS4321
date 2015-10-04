package otherTest;

public class splitTest {

	public splitTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args){
		String test = "Sailors.A";
		String test2= test.replace('.', ',');
		String[] test3 = test2.split(",");
		String[] test1 = test.split(".");
		int leng = test3.length;
		System.out.println(leng);
		for(int i = 0; i < leng; i++){
			System.out.println(test3[i]);
		}
				
	}
}
