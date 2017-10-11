import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Prim {
	public static GraphUI g = new GraphUI(); 
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
		g.insertEdge(v1+v2, v1,v2, false);
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
	
	public static List<Edge> mstPrim(double[][] graph) {
		//Lista de solución
		ArrayList<Edge> mst = new ArrayList<Edge>(graph.length-1);
		boolean visited[] = new boolean[graph.length];
		PriorityQueue<Edge> candidates = new PriorityQueue<Edge>();
		
		visited[0] = true;

		//Añadir los vecinos del nodo 0 a la cola de prioridad
		for(int i = 1; i < graph[0].length; i++) {
			if(graph[0][i] < INF) {
				candidates.add(new Edge(0,i,graph[0][i]));
			}
		}
		
		while(mst.size() < graph.length-1) {
			//Sacar arista candidata con menor peso
			Edge miniEdge = candidates.poll();
			if(visited[miniEdge.v1] != visited[miniEdge.v2]) {
				//Añadir la arista a la solución
				mst.add(miniEdge);
				//Añadir al nodo a la lista de visitados
				int newNode = !visited[miniEdge.v1] ? miniEdge.v1 : miniEdge.v2;
				visited[newNode] = true;
				for(int j = 1; j < graph[0].length; j++) {
					if(graph[newNode][j] < INF) {
						candidates.add(new Edge(newNode, j, graph[newNode][j]));
					}
				}
			}
		}
		
		return mst;
	}
	
	public static void main(String[] args) {
		
		double[][] graph = initGraph(9, INF);
		setEdge(graph, "A", "B", 3);
		setEdge(graph, "A", "H", 4);
		setEdge(graph, "A", "I", 5);
		setEdge(graph, "B", "C", 7);
		setEdge(graph, "B", "I", 7);
		setEdge(graph, "I", "D", 8);
		setEdge(graph, "I", "H", 3);
		setEdge(graph, "C", "D", 6);
		setEdge(graph, "D", "E", 2);
		setEdge(graph, "D", "F", 1);
		setEdge(graph, "H", "G", 4);
		setEdge(graph, "G", "F", 8);
		
		g.displayGraph();
		
		List<Edge> mst = mstPrim(graph);
		System.out.println(mst);
		for(Edge e : mst) {
			g.setSelectedEdge(""+names[e.v1]+names[e.v2]);
			g.setSelectedEdge(""+names[e.v2]+names[e.v1]);
		}

	}

}
