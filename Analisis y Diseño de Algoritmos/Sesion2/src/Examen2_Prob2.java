import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Examen2_Prob2 {

public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] pesos = new int[N];
		int[] capacidades = new int[N];
		
		for(int i = 0; i < N; i++) {
			pesos[i] = sc.nextInt();
		}
		
		for(int i = 0; i < N; i++) {
			capacidades[i] = sc.nextInt();
		}
		
		int[] result = alturaBT(pesos, capacidades);
		
		System.out.println(Arrays.toString(result));
	}
	
	@SuppressWarnings("unchecked")
	public static int[] alturaBT (int[] pesos, int[] capacidades) {
		LinkedList<Element> result = new LinkedList<Element>();
		
		for(int i = 0; i < pesos.length; i++) {
			LinkedList<Element> temp = new LinkedList<Element>();
			temp.add(new Element(i, pesos[i], capacidades[i]));
			LinkedList<Element> propousal = alturaBT(createList(pesos, capacidades), temp);
			
			if(propousal.size() >= result.size()) 
				result = (LinkedList<Element>)propousal.clone();
		}
		
		return toIntArray(result);
	}
	
	private static class Element{
		
		private int index, weight, capacity;
		
		public Element(int index, int weight, int capacity) {
			this.index = index;
			this.weight = weight;
			this.capacity = capacity;
		}
		
		public boolean canHoldItem(Element ele) {
			return capacity >= ele.weight + this.weight ? true : false;
		}
	}
	
	private static LinkedList<Element> createList(int[] pesos, int[] capacidades) {
		LinkedList<Element> elements = new LinkedList<Element>();
		
		for(int i = 0; i < pesos.length; i++) {
			elements.add(new Element(i, pesos[i], capacidades[i]));
		}
		
		return elements;
		
	}

	private static LinkedList<Element> alturaBT(LinkedList<Element> universe, LinkedList<Element> propousal) {
		if(universe.size() == 0)
			return propousal;
		
		int index = getIndex(universe);
		Element candidate = universe.remove(index);
		Element parent = propousal.peek();
		
		if(parent.index == candidate.index) 
			return alturaBT(universe, propousal);
		
		
		if(parent.canHoldItem(candidate)) {
			propousal.add(candidate);
			propousal.peek().capacity -= candidate.weight; 
		}
			
		
		return alturaBT(universe, propousal);
	}
	
	private static int getIndex(LinkedList<Element> universe) {
		int index = -1, capacity = 0;
		for(int i = 0; i < universe.size(); i++) {
			if(capacity < universe.get(i).capacity) {
				capacity = universe.get(i).capacity;
				index = i;
			}
		}
		
		return index;
	}
	
	private static int[] toIntArray(LinkedList<Element> list) {
		int[] arr = new int[list.size()];
		
		for(int i = 0; i < list.size(); i++)
			arr[i] = list.get(i).index;
		
		return arr;
	}
	
	
	
}
