

public class DivideConquer {
	private static int count = 0;
	
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
	
	public static void main(String[] args) {
		//testBinarySearch();
		//testMedian();
		testPartition();
	}
	
	
	
	
}
