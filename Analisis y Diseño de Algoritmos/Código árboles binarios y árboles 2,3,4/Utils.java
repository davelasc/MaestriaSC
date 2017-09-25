package o2017;

import java.util.Arrays;
import java.util.Random;

public class Utils {
	//crear arreglo de tamaño N con valores aleatorios
	public static int[] createIntArray(int N, int min, int max){
		int[] array = new int[N];
		Random r = new Random();
		//r.setSeed(1234);
		for(int i=0; i<array.length; i++)
			array[i] = min + r.nextInt(max-min+1);
		
		return array;
	}
	
	public static int[] createIntArray(int N, int min, int max, long seed){
		int[] array = new int[N];
		Random r = new Random();
		r.setSeed(seed);
		for(int i=0; i<array.length; i++)
			array[i] = min + r.nextInt(max-min+1);
		
		return array;
	}
	
	//imprimir en consola los valores del arreglo
	public static void printArray(int[] array){
		System.out.println(Arrays.toString(array));
	}
	
	//intercambia los valores del arreglo
	public static void swap(int[] array, int index1, int index2){
		if(index1>=0 && index1 < array.length &&
		   index2>=0 && index2 < array.length){
				int temp = array[index2];
				array[index2]= array[index1];
				array[index1]= temp;
		}else{
			System.out.println("indices fuera de rango");
		}
	}
	
	//Verdadero si el arreglo está ordenado ascendentemente
	public static boolean isSorted(int[] array){
		for(int i=0; i<array.length-1; i++){
			if(array[i]>array[i+1])
				return false;
		}
		
		return true;
		
	}
	
	public static void reverseArray(int []array){
		for(int i=0; i< array.length/2; i++)
			swap(array, i , array.length-i-1);
	}
	
	
	public static void main(String[] args) {
		
		int[] arr = createIntArray(10, -20, 20);
		printArray(arr);
		swap(arr, 0, 9);
		printArray(arr);
		
		System.out.println(isSorted(arr));
	}
	
	
	
	
	
	
	
	
}
