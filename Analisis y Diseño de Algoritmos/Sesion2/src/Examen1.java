
public class Examen1 {

	public static void main(String[] args) {
		int[] arrayInc = {1,2,3,4,5,6,7,8,9,10,11,12};
		int[] arrayDec = {12,11,10,9,8,7,6,5,4,3,2,1};
		int[] arrayWrong = {1,2,3,4,5,6,1,2,3,4,5,6,7};
		
		System.out.println("Max es verdadero: " + isHeap(arrayDec, true));
		System.out.println("Max es falso: " + isHeap(arrayInc, false));
		System.out.println("Debe tronar, max es v: " + isHeap(arrayWrong, true));
		System.out.println("Debe tronar, max es f: " + isHeap(arrayWrong, false));
	}
	
	public static boolean isHeap(int[] array, boolean max) {
		int rightChild = 0, leftChild = 0;
		for(int i = 0; i < array.length; i++) {
			leftChild = i * 2 + 1;
			rightChild = i * 2 + 2;
			if(leftChild >= array.length || rightChild >= array.length)
				return true;
			
			if(max) {
				if(array[i] < array[leftChild] || 
					array[i] < array[rightChild])
					return false;
			} else {
				if(array[i] > array[leftChild] || 
						array[i] > array[rightChild])
						return false;
			}
		}
		
		return true;
	}
	
	
	
}
