
public class Util {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = new int[3];
		fillUpArray(array);
		System.out.println(array.toString());
	}
	
	private static void fillUpArray(int[] array) {
		for(int i = 0; i < array.length; i++) {
			array[i] = i;
		}
	}

}
