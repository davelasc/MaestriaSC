import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Examen2_Prob1 {

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
		
		int[] result = torreGreedy(pesos, capacidades);
		
		System.out.println(Arrays.toString(result));
	}
	
	public static int[] torreGreedy (int[] pesos, int[] capacidades) {
		
		LinkedList<Element> universe = createList(pesos, capacidades);
		LinkedList<Element> tower = new LinkedList<Element>();
		int totalCapacity = -1;
		int index = 0;
		while(universe.size() > 0) {
			index = select(universe);
			Element candidate = universe.remove(index);
			
			if(totalCapacity < 0) {//First iteration
				totalCapacity = candidate.capacity;
				tower.add(candidate);
				continue;
			} 
			
			int cap = totalCapacity - candidate.weight;
			if(cap >= 0) {
				totalCapacity = cap;
				tower.add(candidate);
			}
			
		}
		
		return toIntArray(tower);
	}
	
	private static class Element{
		
		private int index, weight, capacity;
		
		public Element(int index, int weight, int capacity) {
			this.index = index;
			this.weight = weight;
			this.capacity = capacity;
		}
		
		public int getRemainingCapacity() {
			return this.capacity - this.weight;
		}
		
	}
	
	private static LinkedList<Element> createList(int[] pesos, int[] capacidades) {
		LinkedList<Element> elements = new LinkedList<Element>();
		for(int i = 0; i < pesos.length; i++) {
			elements.add(new Element(i, pesos[i], capacidades[i]));
		}
		return elements;
	}
	
	private static int select(LinkedList<Element> universe) {
		int candidate = -1, index = 0;
		for(int i = 0; i < universe.size(); i++) {
			if(candidate < universe.get(i).getRemainingCapacity()) {
				candidate = universe.get(i).getRemainingCapacity();
				index = i;
			}
		}
		return index;
	}

	private static int[] toIntArray(LinkedList<Element> elements) {
		
		int[] result = new int[elements.size()];
		
		for(int i = 0; i < elements.size(); i++) {
			result[i] = elements.get(i).index;
		}
		
		return result;
	}
	
}
