
public class Sort {

	private static int movimientos = 0;
	
 	public static int selectionSort(int[] array) {
		int index, counter = 0;
		movimientos = 0;
		for(int i = 0; i < array.length-1; i++) {
			index = i;
			for(int j = i + 1; j < array.length; j++) {
				counter++;
				if(array[index] > array[j]) {
					index = j;
				}
			}
			
			if(index != i) {
				movimientos++;
				Utils.swap(array, i, index);
			}
		}
		
		return counter;
	}
	
	public static int insertionSort(int[] array) {
		int j, comparaciones = 0;
		movimientos = 0;
		for(int i = 1; i < array.length; i++) {
			j = i;
			while(j > 0) {
				comparaciones++;
				if(array[j] < array[j-1]) {
					movimientos++;
					Utils.swap(array, j, j-1);
					j--;
				}else {
					break;
				}
			}
		}
		
		return comparaciones;
	}
	
	public static int bubbleSort(int[] array) {
		boolean swapped = true;
		int counter = 0;
		movimientos = 0;
		
		for(int i = 0; i < array.length-1 && swapped; i++) {
			swapped = false;
			for(int j = 0; j < array.length-i - 1; j++) {
				counter++;
				if(array[j] > array[j+1]) {
					Utils.swap(array, j, j + 1);
					movimientos++;
					swapped = true;
				}
			}
		}
		return counter;
	}
	
	public static int shellSort(int[] array) {
		int inner, outer, temp, comparaciones = 0, saltos = 0;
		movimientos = 0;
		
		while(saltos <= array.length / 3) {
			saltos = saltos * 3 + 1;
		}
		 
		while(saltos > 0) {
			
			for(outer = saltos; outer < array.length; outer++) {
				temp = array[outer];
				inner = outer;
				while(inner > saltos - 1) {
					comparaciones++;
					if(array[inner - saltos] >= temp) {
						array[inner] = array[inner - saltos];
						inner -= saltos;
						movimientos++;
					} else {
						break;
					}
				}
				array[inner] = temp;
			}
			saltos = (saltos - 1) / 3;
		}
		
		return comparaciones;
	}
	
	private static int parent(int index) {
		return (index - 1) / 2;
	}
	
	private static int leftChild(int index) {
		return (index * 2) + 1;
	}
	
	private static int rightChild(int index) {
		return (index * 2) + 2;
	}
	
	public static int heapSort(int[] array) {
		int comparaciones = 0;
		movimientos = 0;
		//Paso 1: Crear el montículo
		for(int i = 1; i < array.length; i++) {
			int child = i;
			int parent = parent(child);
			
			while(child > 0 && array[parent] < array[child]) {
				Utils.swap(array, child, parent);
				movimientos++;
				child = parent;
				parent = parent(child);
				comparaciones++;
			}
			comparaciones++;
		}
		
		//Paso 2: push-down
		
		int lastPosition = array.length-1;
		
		for(int i = 0; i < array.length-1; i++, lastPosition--) {
			Utils.swap(array, 0, lastPosition);
			movimientos++;
			int parent = 0, lChild = 1, rChild = lastPosition > 2 ? 2 : 1;
			int maxChild = array[lChild] > array[rChild] ? lChild : rChild;
			
			while(array[parent] < array[maxChild] &&
					maxChild < lastPosition) {
				Utils.swap(array, parent, maxChild);
				comparaciones++;
				movimientos++;
				parent = maxChild;
				lChild = leftChild(parent);
				if(lChild > lastPosition) 
					break;
				
				rChild = rightChild(parent);
				if(rChild > lastPosition)
					rChild = lChild;
				
				maxChild = array[lChild] > array[rChild] ? lChild : rChild;
			}
			comparaciones++;
		}
		
		return comparaciones;
	}
	
	private static void s3() {
		//int[] array = new int[] {7,8,6,5,12,25,28};
		//int[] array = new int[] {1,2,3,4,5,6,7};
		int[] array;
		int pComR = 0, pMovR = 0, pComA = 0, pMovA = 0, pComI = 0, pMovI = 0;
		int N = 1000000;
		for(int i = 0; i < 10; i++) {
			array = Utils.createIntArray(N, -1000, 1000);
			pComR += heapSort(array);
			pMovR += movimientos;
			
			pComA += heapSort(array);
			pMovA += movimientos;
			
			Utils.reverseArray(array);
			pComI += heapSort(array);
			pMovI += movimientos;
		}
		
		
		
		double esperado = 2*N*(Utils.lg(N)-4);
		double resultado = 0;
		System.out.println("Valor esperado: " + esperado);
		
		pComR /= 10;
		pMovR /= 10;
		System.out.println("Random:: Comparaciones: " + pComR + ", Movimientos: " + pMovR);
		resultado = (pComR * 100) / esperado;
		System.out.println("Porcentage:: Comparaciones: " + resultado );
		
		pComA /= 10;
		pMovA /= 10;
		System.out.println("Arreglado:: Comparaciones: " + pComA + ", Movimientos: " + pMovA);
		resultado = (pComA * 100) / esperado;
		System.out.println("Porcentage:: Comparaciones: " + resultado);
		
		pComI /= 10;
		pMovI /= 10;
		System.out.println("Invertido:: Comparaciones: " + pComI + ", Movimientos: " + pMovI);
		resultado = (pComI * 100) / esperado;
		System.out.println("Porcentage:: Comparaciones: " + resultado);
	}
	
	
	
	private static void s2() {
		//int[] array = new int[] {0,1,2,3,4,5,6,7,8,9};
		int[] array = new int[] {9,8,7,6,5,4,3,2,1,0};			
		System.out.println("Comparaciones hechas: " + shellSort(array));
		System.out.println("Movimientos hechos: " + movimientos);/**/
		/*int[] array;
		int min = -200, max = 200;
		System.out.println("Elementos en el arreglo;Promedio Comparaciones;Promedio Movimientos");
		int pMov = 0, pCom = 0;
		for(int i = 1; i <= 200; i++) {
			pMov = 0;
			pCom = 0;
			for(int j = 1; j <= i *100; j++) {
				array = Utils.createIntArray(i, min, max);
				pCom += shellSort(array);
				pMov += movimientos;
			}
			pCom /= (i*100);
			pMov /= (i*100);
			System.out.println(i + ";" + pCom + ";" + pMov);
		}/**/
	}
	
	public static void main(String[] args) {
		s3();
	}
	
}
