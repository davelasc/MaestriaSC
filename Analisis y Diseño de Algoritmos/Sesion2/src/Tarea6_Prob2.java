import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Tarea6_Prob2 {
	public static double INF = Double.MAX_VALUE;
	static String names[] = {"A", "B", "C", "D", "E", "F", "G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	static HashMap<String, Integer> map = new HashMap<String, Integer>();
	static {
		for(int i =0; i <names.length; i++)
			map.put(names[i], i);
	}
	
	public static void setEdge(double[][]graph, int v1, int v2, double value){
		graph[v1][v2]= value;
		graph[v2][v1]= value;
		
	}
	
	public static double[][] initGraph(int size, double initVal) {
		double[][] graph = new double[size][size];
		for(double[] row: graph) {
			Arrays.fill(row, initVal);
		}
		
		return graph;
	}
	
	public static void setEdge(double[][]graph, String v1, String v2, double value){
		setEdge(graph, map.get(v1), map.get(v2), value);
	}
	
	/************************ PRIM *********************/
	
	@SuppressWarnings("rawtypes")
	public static class Edge implements Comparable<Edge> {

		public int v1, v2;
		public double weight;
		
		public Edge(int v1, int v2, double weight) {
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge e) {
			
			if(this.weight < e.weight) return -1;
			if(this.weight > e.weight) return 1;
			return 0;
		}
		
		public String toString() {
			return "("+names[v1]+","+names[v2]+")_"+weight;
		}
		
		public boolean equals(Object o) {
			if(!(o instanceof Edge)) return false;
			Edge e = (Edge)o;
			
			if((e.v1 == this.v1 && e.v2 == this.v2) ||
					(e.v2 == this.v1 && e.v1 == this.v2))
				return true;
			
			return false;
		}
		
	}
	
	public static double mstPrim(double[][] graph) {
		ArrayList<Edge> mst = new ArrayList<Edge>(graph.length-1);
		boolean visited[] = new boolean[graph.length];
		double cost = 0.0, distance = 0.0;
		visited[0] = true;
		Hashtable<String, Integer> memory = new Hashtable<String, Integer>();
		PriorityQueue<Edge> candidates = new PriorityQueue<Edge>();
		
		visited[0] = true;

		//A�adir los vecinos del nodo 0 a la cola de prioridad
		for(int i = 1; i < graph[0].length; i++) {
			if(graph[0][i] < INF) {
				candidates.add(new Edge(0,i,graph[0][i]));
			}
		}
		
		while(mst.size() < graph.length-1) {
			Edge miniEdge = candidates.poll();
			if(visited[miniEdge.v1] != visited[miniEdge.v2]) {
				memory.put(miniEdge.v1+""+miniEdge.v2, 0);
				memory.put(miniEdge.v2+""+miniEdge.v1, 0);
				mst.add(miniEdge);
				distance += miniEdge.weight;
				int newNode = !visited[miniEdge.v1] ? miniEdge.v1 : miniEdge.v2;
				visited[newNode] = true;
				for(int j = 1; j < graph[0].length; j++) {
					if(graph[newNode][j] < INF) {
						candidates.add(new Edge(newNode, j, graph[newNode][j]));
					}
				}
			}
		}
		
		for(Edge edge : candidates) {
			if(!memory.containsKey(edge.v1+""+edge.v2)) {
				memory.put(edge.v1+""+edge.v2, 0);
				memory.put(edge.v2+""+edge.v1, 0);
				//System.out.println(!memory.containsKey(edge));
				cost += (edge.weight / 2);
			}
		}
		
		System.out.println(distance);
		return cost;
	}
	
	public static void insertCandidate(int x, int y, double weight, LinkedList<Edge> candidates) {
		candidates.add(new Edge(x, y, weight));
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int nodos = sc.nextInt();
		int aristas = sc.nextInt();
		LinkedList<Edge> candidates = new LinkedList<Edge>();
		String a, b, existe;
		double costo = 0.0, peso;
		double[][] graph = initGraph(nodos, INF);
		
		for(int i = 0; i < aristas; i++) {
			a = sc.next();
			b = sc.next();
			peso = sc.nextDouble();
			existe = sc.next();
			if(existe.equals("F")) {
				costo += peso;
			}
			setEdge(graph, a, b, peso);
			insertCandidate(map.get(a), map.get(b), peso, candidates);
		}
		costo += mstPrim(graph);
		System.out.println(costo);
	}
}
