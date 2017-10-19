import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Kruskal {

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
	
	public static int findLeader(int[] parents, int x) {
		while(parents[x] != x) {
			x = parents[x];
		}
		return x;
	}
	
	public static boolean join(int[] parents, int[] ranks, int i, int j) {
		int leaderI = findLeader(parents, i);
		int leaderJ = findLeader(parents, j);
		
		if(leaderI == leaderJ) {
			return false;
		}
		
		int rankLeaderI = ranks[i];
		int rankLeaderJ = ranks[j];
		
		if(rankLeaderI > rankLeaderJ || 
				(rankLeaderI == rankLeaderJ && leaderI > leaderJ)) {
			parents[j] = leaderI;
			ranks[leaderI] += ranks[leaderJ];
		} else {
			parents[i] = leaderJ;
			ranks[leaderJ] += ranks[leaderI];
		}
		
		return true;
	}
	
	public static List<Edge> mstKruskal(double[][] graph) {
		PriorityQueue<Edge> candidates = new PriorityQueue<Edge>();
		List<Edge> mst = new ArrayList<Edge>(graph.length-1);
		double suma = 0;
		//Init la lista de edges
		for(int i = 0; i < graph.length; i++) {
			for(int j = i + 1; j < graph[0].length; j++) {
				if(graph[i][j] != INF) {
					Edge e = new Edge(i, j, graph[i][j]);
					candidates.offer(e);
				}
			}
		}
		
		int parents[] = new int[graph.length];
		//Inicialmente cada uno es padre de si mismo
		for(int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
		
		int ranks[] = new int[graph.length];
		//Inicialmente cada uno es padre de si mismo
		for(int i = 0; i < ranks.length; i++) {
			ranks[i] = 1;
		}
		
		while(mst.size() < graph.length-1) {
			Edge minEdge = candidates.poll();
			if(join(parents, ranks, minEdge.v1, minEdge.v2)) {
				mst.add(minEdge);
				suma += minEdge.weight;
			}
		}
		System.out.println("Suma: " + suma);
		
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
		
		List<Edge> mst = mstKruskal(graph);
		System.out.println(mst);
		for(Edge e : mst) {
			g.setSelectedEdge(""+names[e.v1]+names[e.v2]);
			g.setSelectedEdge(""+names[e.v2]+names[e.v1]);
		}
	}

}
