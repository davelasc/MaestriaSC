import java.util.Arrays;

public class Queens {
	
	static int count;
	
	static boolean isValid(int[] arr, int k) {
		for(int i = 0; i < k; i++) {
			if(arr[i] == arr[k]) return false;
			if((arr[k]-arr[i]) == (k-i)) return false;
			if((arr[i]-arr[k]) == (k-i)) return false;
		}
		return true;
	}
	
	static void solutions(int n) {
		count = 0;
		int arr[] = new int[n];
		solutions(arr,0);
	}
	
	static void solutions(int[] arr, int k) {
		int n = arr.length;
		if(k == n) {
			//mostrar el arreglo
			count++;
			System.out.println(Arrays.toString(arr));
		} else {
			for(int i = 0; i < n; i++) {
				arr[k] = i;
				if(isValid(arr, k)) {
					solutions(arr, k+1);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int n = 5;
		solutions(n);
		System.out.println("# soluciones = " + count);
	}

}
