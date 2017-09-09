package Busqueda;
import java.util.Arrays;
import java.util.Random;

public class Utils {
	
	/*public static int[] createIntArray(int N, int min, int max) {
		int[] array = new int[N];
		Random r = new Random();
		//r.setSeed(1234);
		for(int i = 0; i < N; i++) {
			array[i] = min + r.nextInt(Math.abs(max - min));
		}
		return array;
	}
	
	public static void reverseArray(int[] array) {
		for(int i = 0; i < array.length / 2; i++) {
			swap(array, i, array.length-i-1);
		}
	}/**/
	
	public static void printArray(int[] array) {
		System.out.println(Arrays.toString(array));
	}
	
	/*public static void swap(int[] array, int index1, int index2) {
		if(0 <= index1 && index1 < array.length &&
				0 <= index2 && index2 < array.length) {
			int temp = array[index1];
			array[index1] = array[index2];
			array[index2] = temp;
		} else {
			System.out.println("Cannot swap the values with the given indexes");
		}
	}
	
	public static boolean isSorted(int[] array) {
		for(int i = 0; i < array.length-1; i++) {
			if(array[i] > array[i + 1]) {
				return false;
			}
		}
		return true;
	}/**/
	
	public static double lg(int N) {
		return (Math.log(N) / Math.log(2));
	}
}
