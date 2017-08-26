
public class Sort {

	public static int selectionSort(int[] array) {
		int index, counter = 0;
		
		for(int i = 0; i < array.length-1; i++) {
			index = i;
			for(int j = i + 1; j < array.length; j++) {
				counter++;
				if(array[index] > array[j]) {
					index = j;
				}
			}
			
			if(index != i) {
				Utils.swap(array, i, index);
			}
		}
		
		return counter;
	}
	
	public static int insertionSort(int[] array) {
		int j, comparaciones = 0;
		
		for(int i = 1; i < array.length; i++) {
			j = i;
			while(j > 0 && array[j] < array[j-1]) {
				comparaciones++;
				Utils.swap(array, j, j-1);
				j--;
			}
		}
		
		return comparaciones;
	}
	
	public static int bubbleSort(int[] array) {
		boolean swapped = true;
		int counter = 0;
		for(int i = 0; i < array.length-1 && swapped; i++) {
			swapped = false;
			for(int j = 0; j < array.length-i - 1; j++) {
				counter++;
				if(array[j] > array[j+1]) {
					Utils.swap(array, j, j + 1);
					swapped = true;
				}
			}
		}
		return counter;
	}
	
	public static void main(String[] args) {
		int N = 10, min = -100, max = 100;
		//int[] array = Utils.createIntArray(N, min, max);
		int[] array = new int[] {0,1,2,3,4,9,8,7,6,5};
		System.out.println("Insertion Sort:");
		System.out.println("Arreglo: ");
		Utils.printArray(array);
		System.out.println("Esta acomodado: " + Utils.isSorted(array));
		System.out.println("Lo acomodamos...");
		System.out.println("Comparaciones hechas: " + insertionSort(array));
		Utils.printArray(array);
		System.out.println("Esta acomodado: " + Utils.isSorted(array));
		
	}
	
}
