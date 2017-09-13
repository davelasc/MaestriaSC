

public class DivideConquer {
	//private static int count = 0;
	private static int comparaciones = 0;
	private static int movimientos = 0;
	
	
	public static void main(String[] args) {
		//testBinarySearch();
		//testMedian();
		//testPartition();
		//testQuickSort();
		testMergeSort();
	}
	
	/*public static int binarySearch(int[] arr, int val) {
		return binarySearch(arr, val, 0, arr.length);
	}
	
	public static int binarySearch(int[] arr, int val, int start, int end) {
		count++;
		if((end - start) < 2) {
			return arr[start] == val ? start : -1;
		}
		
		int index = (start + end) / 2;
		
		if(arr[index] == val) 
			return index;
		
		if(val < arr[index])
			return binarySearch(arr, val, start, index);
		else
			return binarySearch(arr, val, index, end);
		
	}
	
	public static int median(int[] arr) {
		return median(arr, arr.length/2);
	}
	
	public static int median(int[] arr, int k) {
		count++;
		int p = 0;
		int n2 = arr.length / 2;
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] < arr[n2])
				p++;
		}
		
		if(p == k)
			return arr[n2];
		
		if(p > k) {
			int[] listMin = new int[p];
			for(int i = 0, j= 0; i < arr.length; i++) {
				if(arr[i] < arr[n2]) {
					listMin[j++] = arr[i];
				}
			}
			return median(listMin, k);
		}

		int[] listMax = new int[arr.length - p - 1];
		for(int i = 0, j= 0; i < arr.length; i++) {
			if(arr[i] > arr[n2]) {
				listMax[j++] = arr[i];
			}
		}
		return median(listMax, k - p - 1);
		
	}
	
	public static void testMedian() {
		int[] arr = {19,10,47,5,26,35,8,13};
		System.out.println("La mediana es: " + median(arr));
		System.out.println("llamadas: " + count);
	}
	
	public static void testBinarySearch() {
		int[] arr = Utils.createIntArray(10000000, -200000, 200000);
		System.out.println(Sorting.heapSort(arr));
		Utils.isSorted(arr);
		System.out.println("pos num 15 = " + binarySearch(arr, 15));
		System.out.println("llamadas: " + count);
	}
	
	public static int partition(int[] array, int left, int right) {
		
		int pivot = array[right];
		int p1 = left - 1;
		int p2 = right;
		
		while(p1 < p2) {
			p1++;
			p2--;
			
			while(p1 <= right && array[p1] < pivot) p1++;
			while(p2 > left && array[p2] > pivot) p2--;
			if(p1 < p2)
				Utils.swap(array, p1, p2);
		}
		
		Utils.swap(array, p1, right);
		
		return p1;
	}
	
	public static void testPartition() {
		int[] arr = {19,10,47,5,26,35,8,13};
		System.out.println("Pivote pos: " + partition(arr, 0, arr.length-1));
	}
	/**/
	
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
