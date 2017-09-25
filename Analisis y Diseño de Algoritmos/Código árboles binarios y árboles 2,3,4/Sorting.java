package o2017;



public class Sorting {

	static int movimientos =0;
	
	public static int selectionSort(int[] array){
		int comparaciones =0;
		movimientos =0;
		for(int i=0; i< array.length-1; i++){
			int minIndex = i;
			for(int j=i+1; j<array.length; j++){
				comparaciones++;
				if(array[j]<array[minIndex])
					minIndex = j; //elemento más pequeño
			}
			if(minIndex!=i){
				movimientos+=3;
				Utils.swap(array,i,minIndex);
			}
		}
		
		return comparaciones;
	}
	
	
	public static void insertionSort(int [] array){
		for(int i=1; i<array.length; i++){
			int value = array[i];
			int j=i-1;
			while(j>=0 && value<array[j]){
				array[j+1]= array[j];
				j--;
			}
			array[j+1]= value;
		}
	}
	
	public static int bubbleSort(int [] array){
		boolean swapped = true;
		int comparaciones =0;
		for(int i=0; i<array.length-1 && swapped; i++){
			swapped=false;
			for(int j=0; j<array.length-i-1; j++){
				comparaciones++;
				if(array[j]>array[j+1]){
					swapped=true;
					Utils.swap(array,j,j+1);
				}
			}
			
		}
		return comparaciones;
		
	}
	
	
	private static int parent(int index){
		return (index-1)/2;
	}
	
	private static int leftChild(int index){
		return 2*index +1;
	}
	
	private static int rightChild(int index){
		return 2*index +2;
	}
	
	
	public static int heapsort(int[] array){
		int movimientos = 0;
		
		//Paso1: Crear el montículo
		
		for(int i=1; i<array.length; i++){
			int child = i;
			int parent = parent(child);
			
			while(child>0 && array[parent]<array[child]){
				movimientos++;
				Utils.swap(array, child, parent);
				child = parent;
				parent = parent(child);
			}
		}
		
		System.out.println("Heap: ");
		Utils.printArray(array);
		
		
		//Paso2: push-down (ordenar)
		
		int lastPosition = array.length-1;
		
		for(int i=0; i<array.length-1; i++, lastPosition--){
			Utils.swap(array, 0, lastPosition);
			movimientos++;
			int parent =0;
			int lChild =1;
			int rChild =lastPosition>2?2:1;
			int maxChild = (array[lChild]>array[rChild])? lChild: rChild;
			
			while(array[parent]<array[maxChild] && maxChild < lastPosition){
				Utils.swap(array, parent , maxChild );
				movimientos++;
				parent = maxChild;
				lChild = leftChild(parent);
				
				if(lChild >lastPosition)
					break;
				
				rChild = rightChild(parent);
				if(rChild>lastPosition) 
					rChild=lChild;
				
				maxChild = (array[lChild]>array[rChild])? lChild: rChild;
				
			}
			
			
		}
		
			
		
		return movimientos;	
	}
	
	
		
	public static void s3(){
		int arr[]={1,2,3,4,5,6,7};
		int arr2[]={7,8,6,5,12,25,28};
		System.out.println(heapsort(arr));
	}
	
	
	
	public static void main(String[] args) {
		/*int[] arr = Utils.createIntArray(100, -2000, 2000);
		System.out.println(Utils.isSorted(arr));
		//Utils.printArray(arr);
		int comp=selectionSort(arr);
		System.out.println("Desordenado:"+comp);
		//Utils.printArray(arr);
		selectionSort(arr);
		
		comp= selectionSort(arr);
		System.out.println("Ordenado:"+comp);
		System.out.println("movimientos:"+movimientos);
		//Utils.printArray(arr);
		Utils.reverseArray(arr);
		//Utils.printArray(arr);
		comp= selectionSort(arr);
		System.out.println("Invertido:"+comp);
		
		//insertionSort(arr);
		System.out.println(Utils.isSorted(arr));
		System.out.println("comparaciones:"+ comp);
		//Utils.printArray(arr);
	
		Utils.printArray(arr);
		//bubbleSort(arr);
		//Utils.swap(arr, 98, 99);
		int comp = bubbleSort(arr);
		Utils.printArray(arr);
		System.out.println(Utils.isSorted(arr));
		System.out.println("BubbleSort Comparaciones:"+ comp);
		*/
		
		s3();
		
		
		
	}
	
	
}
