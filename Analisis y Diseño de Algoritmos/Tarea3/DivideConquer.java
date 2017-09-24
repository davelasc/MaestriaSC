

public class DivideConquer {
	private static int comparaciones = 0;
	private static int movimientos = 0;
	
	
	public static void main(String[] args) {
		//testQuickSort();
		testMergeSort();
	}
	
	private static void testMergeSort() {
		System.out.println("Elementos en el arreglo;Promedio Comparaciones;Promedio Movimientos");
		//int[] array = {4,1,3,5,6,7,2};
		//int[] array = {4,1,3,2,6,5,7};
		int[] array;
		int pMov = 0, pCom = 0;
		for(int i = 1; i <= 200; i++) {
			pMov = 0;
			pCom = 0;
			for(int j = 1; j <= i *100; j++) {
				array = Utils.createIntArray(i, -2000, 2000);
				mergeSort(array);
				pMov += movimientos;
				pCom += comparaciones;
			}
			pCom /= (i*100);
			pMov /= (i*100);
			System.out.println(i + ";" + pCom + ";" + pMov);
			//System.out.println(comparaciones);
		}
		
	}
	
	private static void mergeSort(int[] array) {
		comparaciones = 0;
		movimientos = 0;
		mergeSort(array, 0, array.length-1);
	}
	
	private static void mergeSort(int[] array, int left, int right) {
		if(left < right) {
			int mid = left + ((right - left) / 2);
			
			mergeSort(array, left, mid);
			mergeSort(array, mid+1, right);
			merge(array, left, mid, right);
		}
	}
	
	private static void merge(int[] array, int left, int mid, int right) {
		int[] helper = new int[array.length];
		
		for(int i = left; i <= right; i++) {
			helper[i] = array[i];
		}
		
		int i = left, j = mid + 1, k = left;
		
		while(i <= mid && j <= right) {
			comparaciones++;
			if(helper[i] <= helper[j]) {
				movimientos++;
				array[k] = helper[i];
				i++;
			} else {
				movimientos++;
				array[k] = helper[j];
				j++;
			}
			k++;
		}
		
		while(i <= mid) {
			array[k] = helper[i];
			k++;
			i++;
		}
 	}
	
	
	private static void testQuickSort() {
		System.out.println("Elementos en el arreglo;Promedio Comparaciones;Promedio Movimientos");
		//int[] array = {4,1,3,5,6,7,2};
		//int[] array = {4,1,3,2,6,5,7};
		int[] array;
		int pMov = 0, pCom = 0;
		for(int i = 1; i <= 200; i++) {
			pMov = 0;
			pCom = 0;
			for(int j = 1; j <= i *100; j++) {
				array = Utils.createIntArray(i, -2000, 2000);
				quickSort(array);
			}
			pCom /= (i*100);
			pMov /= (i*100);
			//System.out.println(i + ";" + comparaciones + ";" + movimientos);
			System.out.println(comparaciones);
		}
		
	}
	
	private static void quickSort(int[] array) {
		comparaciones = 0;
		quickSort(array, 0, array.length - 1);
	}
	
	private static void quickSort(int[] array, int left, int right) {
		
		if(left < right) {
			int part = partition(array, left, right);
			quickSort(array, left, part-1);
			quickSort(array, part+1, right);
		}
	}
	
	private static int partition(int[] array, int left, int right) {
		int pivot = array[right];
		int i = left - 1;
		
		for(int j = left; j <= right; j++) {
			comparaciones++;
			if(array[j] < pivot) {
				i++;
				Utils.swap(array, j, i);
				movimientos++;
			}
		}
		comparaciones++;
		if(array[right] < array[i+1]) {
			Utils.swap(array, i+1, right);
			movimientos++;
		}
		
		return i+1;
	}
	
	
}
