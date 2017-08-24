
public class Sort {

	public static int selectSort(int[] array) {
		int index = 0, counter = 0;
		for(int i = 0; i < array.length-1; i++) {
			index = i;
			for(int j = i + 1; j < array.length; j++) {
				counter++;
				if(array[index] > array[j]) {
					index = j;
				}
			}
			
			if(index != i) {
				Util.swap(array, i, index);
			}
		}
		return counter;
	}
	
	
	
	public static void main(String[] args) {
		
		int[] array = Util.createIntArray(7, -5, 5);
		Util.printArray(array);
		System.out.println("Is array sorted: " + Util.isSorted(array));
		System.out.println("Número de comparaciones: " + selectSort(array));
		Util.printArray(array);
		System.out.println("Is array sorted: " + Util.isSorted(array));
	}

}
