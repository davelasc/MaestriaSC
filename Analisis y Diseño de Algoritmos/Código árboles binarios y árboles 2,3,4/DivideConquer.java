package o2017;

import java.util.ArrayDeque;

public class DivideConquer {

	static int cont =0;
	
	//búsqueda binaria
	
	public static int binarySearch(int[]array, int val){
		cont =0;
		return binarySearch(array, val, 0, array.length); 
	}
	
	public static int binarySearchPro(int[]array, int val){
		cont =0;
		return binarySearchPro(array, val, 0, array.length-1); 
	}
	
	private static int binarySearch(int[]array, int val, int start, int end){
		cont++;
		if((end-start)<2)
			return (array[start]==val)?start:-1;
		
		//si hay 2 o más elementos: encontrar la mitad
		int temp = (end-start)/2 + start;
		
		//verificar si el valor está en la mitad
		if(array[temp]==val)
			return temp;
		
		if(val<array[temp])
			return binarySearch(array, val, start, temp);
		else
			return binarySearch(array, val, temp, end);

	}
	
	//con interpolación
	private static int binarySearchPro(int[]array, int val, int start, int end){
		cont++;
		
		if((end-start)<2 || val < array[start] || val > array[end])
			return (array[start]==val)?start:-1;
		
		
		int dif = array[end]-array[start];
		if(dif==0)
			return (array[start]==val)?start:-1;
		
		
		//si hay 2 o más elementos: encontrar la mitad
		//int temp = (end-start)/2 + start;
	
		int temp = (val-array[start])*(end-start)/ (array[end]-array[start])+ start;
		//System.out.println("Punto de corte: "+ temp + " start: "+start + " end: "+end);
		
		
		//verificar si el valor está en la mitad
		if(array[temp]==val)
			return temp;
		
		if(val<array[temp])
			return binarySearchPro(array, val, start, temp-1);
		else
			return binarySearchPro(array, val, temp+1, end);

	}
	
	
	
	public static int binarySearchIte(int[] array, int val){
		int start = 0;
		int end = array.length-1;
		while(true){
			//si hay 2 o más elementos: encontrar la mitad
			int temp = (end-start)/2 + start;
			
			//verificar si el valor está en la mitad
			if(array[temp]==val){
				return temp;
			}else if(end == start)
				return -1;
			else if (val<array[temp])
				end = temp-1;
			else
				start = temp+1;
			
		}
	}
	
	
	
	/*número Mediana(L: lista de N elementos, K: posición esperada) 
	P el número de elementos más pequeños que LN/2
	Si P = K, devolver Ln/2
	Si P > K 		en la mitad hay un número grande
	Crear una lista L1 con los elementos más pequeños que LN/2
	Devolver Mediana(L1, K)
	Si P < K 		 en la mitad hay un número pequeño
	Crear una lista L2 con los elementos más grandes que LN/2
	Devolver  Mediana(L2, K – P – 1)*/
	
	
	public static int median(int[]array){
		cont =0;
		return median(array, array.length/2);
	}
	
	private static int median(int [] array, int k){
		cont++;
		int p=0;
		int n2 = array.length/2;
		//elementos menores a  array[n2]
		for(int i=0; i<array.length; i++)
			if(array[i]<array[n2])
				p++;
		
		if(p==k) return array[n2];
		if(p>k){
			int [] listMin = new int[p];
			for(int i=0, j=0; i<array.length; i++)
				if(array[i]<array[n2])
					listMin[j++]= array[i];
			return median(listMin,k);
		}
		
		int[] listMax = new int[array.length-p-1];
		for(int i=0, j=0; i<array.length; i++)
			if(array[i]>array[n2])
				listMax[j++]= array[i];
		return median(listMax,k-p-1);

	}
	
	
	public static int  partition(int[] array, int left, int right){
		int pivot = array[right];
		int p1 = left-1;
		int p2 = right;
		
		while(p1<p2){
			p1++;
			p2--;
			while(p1<=right && array[p1]<pivot) p1++;
			while(p2>left && array[p2]>pivot)p2--;
			if(p1<p2)
				Utils.swap(array, p1, p2);
			
		}
		Utils.swap(array, p1, right);
		
		return p1;
	}
	
	
	private static void merge(int[] array, int left1, int right1, int left2, int right2) {
        int[] tmpArray = new int[array.length];
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
	
	static class Params{
		public int left;
		public int right;
		public boolean visited;
		
		public Params(int left,int right,boolean visited){
			this.left=left;
			this.right=right;
			this.visited = visited;
		}
	}
	
	public static void mergeSortIte2(int[] array){
		//pila de parametros
		ArrayDeque<Params> stack = new ArrayDeque<Params>();
		//insertar parametros iniciales a la pila 
		stack.push(new Params(0,array.length-1,false));
		while(!stack.isEmpty()){
			//extraer lo que está en la pila
			Params p = stack.pop();
					
			if(p.left == p.right)
				continue;
			
			int middle = p.left + (p.right-p.left)/2;
			if(!p.visited){ //no ha sido visitado
				stack.push(new Params(p.left,p.right, true)); 
				stack.push(new Params(middle+1,p.right, false));//hijo derecho
				stack.push(new Params(p.left,middle, false));//hijo izquierdo
			}else{
				merge(array, p.left, middle, middle+1, p.right);
			}
		}
	
	}
	
	
	public static void mergeSortIte(int[] array){
		//pila de parametros
		ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
		//insertar parametros iniciales a la pila 
		stack.push(0); //limite izquierdo
		stack.push(array.length-1); //límite derecho
		stack.push(0); //visitado = false
		while(!stack.isEmpty()){
			//extraer lo que está en la pila
			int visited = stack.pop();
			int right = stack.pop();
			int left = stack.pop();
			
			if(left == right)
				continue;
			
			int middle = left + (right-left)/2;
			
			if(visited ==0){ //no ha sido visitado
				//padre
				stack.push(left); //izquierdo
				stack.push(right); //derecha
				stack.push(1); // visitado true
				//hijo derecho
				stack.push(middle+1); //izquierdo
				stack.push(right); //derecha
				stack.push(0);
				//hijo izquierdo
				stack.push(left); //izquierdo
				stack.push(middle); //derecha
				stack.push(0);
			}else{
				merge(array, left, middle, middle+1, right);
			}

		}
		
		
	}
	
	

	private static void testPartition(){
		int[] array = {13,10,47,5,26,19,8,14};
		int[] array2= {13, 10, 8, 5, 26, 19};
		System.out.println("Pivote pos = "+ partition(array, 0, array.length-1));
		System.out.println("Pivote pos = "+ partition(array2, 0, array2.length-1));
		Utils.printArray(array);
		Utils.printArray(array2);
	}
	
	private static void testMedian(){
		int[] array = {19,10,47,5,26,35,8,13};
		System.out.println("La mediana es "+ median(array));
		System.out.println("llamadas: "+cont);
	}
	
	
	private static void testBinarySearch(){
		int[] array = Utils.createIntArray(10000, 0, 200000);
		System.out.println(Sorting.heapsort(array));
		Utils.isSorted(array);
		Utils.printArray(array);
		System.out.println("pos num = " + binarySearch(array, 1000));
		System.out.println("llamadas: "+cont);
	}
	
	private static void testBinarySearchPro(){
		//int[] array = {1,2,3,5,6,8,9,23};
		int[] array = Utils.createIntArray(10000, -200000, 200000);
		System.out.println(Sorting.heapsort(array));
		Utils.isSorted(array);
		//Utils.printArray(array);
		//System.out.println("val" + array[5011]);
		System.out.println("pos num  = " + binarySearchPro(array,1000));
		System.out.println("llamadas: "+cont);
	}
	
	private static void testMergeSortIte(){
		int array[]= {10,9,8,7,6,5,4};
		mergeSortIte(array);
		Utils.printArray(array);
		
	}
	
	public static void main(String[] args) {
		testBinarySearch();
		for(int i=0; i<100; i++)
		   testBinarySearchPro();
		//testMedian();
		//testPartition();
		//testMergeSortIte();
	}
	
	
}
