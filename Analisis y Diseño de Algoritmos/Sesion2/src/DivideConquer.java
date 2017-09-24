import java.util.ArrayDeque;

public class DivideConquer {
	
	private static int count = 0;
	private static int compMerg = 0, compAda = 0;
	private static int movMerg = 0, movAda = 0;
	/*
	
	public static void textBinarySearchPro() {
		for(int i = 0; i < 100; i++) {
			int[] arr = Utils.createIntArray(100, -16, 16);
			System.out.println(Sorting.heapSort(arr));
			Utils.isSorted(arr);
			System.out.println("pos num = " + binarySearchPro(arr, i));
			System.out.println("llamadas: " + count);
		}
		
	}
	
	public static int binarySearchPro(int[] arr, int val) {
		return binarySearchPro(arr, val, 0, arr.length-1);
	}
	
	public static int binarySearchPro(int[] array, int val, int start, int end) {
		count++;
		if((end - start) < 2 || val < array[start] || val > array[end]) {
			return array[start] == val ? start : -1;
		}
		
		int dif = array[end] - array[start];
		if(dif == 0) {
			return array[start] == val ? start : -1;
		}
		
		//int index = (start + end) / 2;
		int index = (((val -array[start]) * (end - start)) / (array[end] - array[start])) + start;
		System.out.println("Punto de corte: " + index + ", start: " + start + ", end: " + end);
		
		if(array[index] == val) 
			return index;
		
		//if(index < start || index > end) {
		//	return -1;
		//}
		
		if(val < array[index])
			return binarySearchPro(array, val, start, index-1);
		else
			return binarySearchPro(array, val, index+1, end);
		
	}
	/**/
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
	
	private static void mergeSort(int[] array) {
		compMerg = 0;
		movMerg = 0;
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
			compMerg++;
			if(helper[i] <= helper[j]) {
				movMerg++;
				array[k] = helper[i];
				i++;
			} else {
				movMerg++;
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
	/**/
	
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
			System.out.println(compMerg);
		}
		
	}
	
	private static void quickSort(int[] array) {
		compMerg = 0;
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
			compMerg++;
			if(array[j] < pivot) {
				i++;
				Utils.swap(array, j, i);
				movMerg++;
			}
		}
		compMerg++;
		if(array[right] < array[i+1]) {
			Utils.swap(array, i+1, right);
			movMerg++;
		}
		
		return i+1;
	}
	
	public static int binarySearch(int[] arr, int val) {
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
	
	public static int binarySearchIte(int[] array, int val) {
		
		int start = 0;
		int end = array.length-1;
		
		while(true) {
			int index = (start + end) / 2;
			
			if(array[index] == val) { 
				return index;
			} else if(end == start){
				return -1;
			} else if(val < array[index]) {
				end = index - 1;
			} else {
				start = index + 1;
			}
			
			
		}
		
	}
	/*
	private static void testMergeIte() {
		int array[] = {10, 9,8,7,6};
		mergeSortIte(array);
		Utils.printArray(array);
	}
	
	public static void merge(int[] array, int[] tmpArray, int left1, int right1, int left2, int right2) {
        int count = right1 - left1 + right2 - left2 + 2;
        int array1Index = left1;
        int array2Index = left2;
        for(int i = 0; i < count; i ++) {
            if(array2Index > right2) {
                tmpArray[i] = array[array1Index];
                array1Index ++;
            } else if(array1Index > right1) {
                tmpArray[i] = array[array2Index];
                array2Index ++;
            } else if(array[array1Index] < array[array2Index]) {
                tmpArray[i] = array[array1Index];
                array1Index ++;
            } else {
                tmpArray[i] = array[array2Index];
                array2Index ++;
            }
        }
        for(int i = 0; i < count; i ++) array[left1 + i] = tmpArray[i];
    }
	
	public static void mergeSortIte(int[] array) {
		//pilla de parametros
		ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
		//insertar parametros iniciales a la pila
		stack.push(0); //limite izq
		stack.push(array.length-1); //limite der
		stack.push(0); //visitado = false
		int[] tempArray = new int[array.length];
		while(!stack.isEmpty()) {
			//extraer lo que está en la pila
			int visited = stack.pop();
			int right = stack.pop();
			int left = stack.pop();
			
			if(left == right)
				continue;
			
			int middle = left + (right - left) / 2;
			
			if(visited == 0) {
				//padre
				stack.push(left);
				stack.push(right);
				stack.push(1);
				//hijo der
				stack.push(middle+1);
				stack.push(right);
				stack.push(0);
				//hijo izq
				stack.push(left);
				stack.push(middle);
				stack.push(0);
			} else {
				merge(array, tempArray, left, middle, middle+1, right);
			}
			
		}
	}
	/**/
	public static void main(String[] args) {
		testProb3ADA();
		testMergeSort();
	}
	
	private static void testMergeSort() {
		System.out.println("--------------------Merge sort--------------------");
		int[] array;// = Utils.createIntArray(10, 0, 10);
		for(int i = 1; i <= 1000; i++) {
			array = Utils.createIntArray(i, 0, 10000);
			mergeSort(array);
			System.out.println(compMerg + ";" + movMerg + ";" + Utils.isSorted(array));
		}
	}
	
	public static void testProb3ADA() {
		System.out.println("--------------------ADA sort--------------------");
		int[] array;// = Utils.createIntArray(10, 0, 10);
		for(int i = 1; i <= 1000; i++) {
			array = Utils.createIntArray(i, 0, 10000);
			prob3ADA(array);
			System.out.println(compAda + ";" + movAda + ";" + Utils.isSorted(array));
		}
	}
	
	public static void prob3ADA(int[] array) {
		compAda = 0;
		movAda = 0;
		prob3ADA(array, 0, array.length-1);
	}
	
	public static void prob3ADA(int[] array, int start, int end) {
		compAda++;
		if(array[start] > array[end]) {
			movAda++;
			Utils.swap(array, start, end);
		}
		
		if((end - start) > 1) {
			int index = ((end-start) * 2) / 3;
			
			prob3ADA(array, start, start + index);
			prob3ADA(array, end - index, end);
			prob3ADA(array, start, start + index);
		}
	}
	
	
}
