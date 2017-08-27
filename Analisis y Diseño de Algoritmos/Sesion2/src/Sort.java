
public class Sort {

	private static int movimientos = 0;
	
 	public static int selectionSort(int[] array) {
		int index, counter = 0;
		movimientos = 0;
		for(int i = 0; i < array.length-1; i++) {
			index = i;
			for(int j = i + 1; j < array.length; j++) {
				counter++;
				if(array[index] > array[j]) {
					index = j;
				}
			}
			
			if(index != i) {
				movimientos++;
				Utils.swap(array, i, index);
			}
		}
		
		return counter;
	}
	
	public static int insertionSort(int[] array) {
		int j, comparaciones = 0;
		movimientos = 0;
		for(int i = 1; i < array.length; i++) {
			j = i;
			while(j > 0) {
				comparaciones++;
				if(array[j] < array[j-1]) {
					movimientos++;
					Utils.swap(array, j, j-1);
					j--;
				}else {
					break;
				}
			}
		}
		
		return comparaciones;
	}
	
	
	public static int bubbleSort(int[] array) {
		boolean swapped = true;
		int counter = 0;
		movimientos = 0;
		
		for(int i = 0; i < array.length-1 && swapped; i++) {
			swapped = false;
			for(int j = 0; j < array.length-i - 1; j++) {
				counter++;
				if(array[j] > array[j+1]) {
					Utils.swap(array, j, j + 1);
					movimientos++;
					swapped = true;
				}
			}
		}
		return counter;
	}
	
	public static void main(String[] args) {
		/*//int[] array = new int[] {0,1,2,3,4,5,6,7,8,9};
		int[] array = new int[] {9,8,7,6,5,4,3,2,1,0};
		Utils.printArray(array);
		System.out.println("Comparaciones hechas: " + bubbleSort(array));
		System.out.println("Movimientos hechos: " + movimientos);/**/
		int[] array;
		int min = -200, max = 200;
		System.out.println("Elementos en el arreglo;Comparaciones;Movimientos");
		for(int i = 1; i <= 200; i++) {
			array = Utils.createIntArray(i, min, max);
			System.out.println(i + ";" + bubbleSort(array) + ";" + movimientos);
		}/**/
	}
	
}
