import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Dijkstra {
	
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
		
	}
	
	public static void setEdge(double[][]graph, String v1, String v2, double value){
		g.insertEdge(v1+v2, v1,v2, ""+value, true);
		setEdge(graph, map.get(v1), map.get(v2), value);	
	}
	
	public static double[][] initGraph(int size, double initVal) {
		double[][] graph = new double[size][size];
		for(double[] row: graph) {
			Arrays.fill(row, initVal);
		}
		
		return graph;
	}
	
	//**********************************************************************
	
	public static double[] dijkstra(double[][] graph, int start) {
		
		double[] distances = new double[graph.length];
		boolean[] processed = new boolean[graph.length];
		
		for(int i = 0; i < distances.length; i++) {
			distances[i] = INF;
		}
		
		ArrayDeque<Integer> pendingNodes = new ArrayDeque<>();
		ArrayDeque<Double> pendingDistances = new ArrayDeque<>();
		
		pendingNodes.push(start);
		pendingDistances.push(0.0);
		
		while(!pendingNodes.isEmpty()) {
			int node = pendingNodes.pop();
			double distance = pendingDistances.pop();
			
			if(processed[node] && distance >= distances[node]) {
				continue;
			}
			
			distances[node] = distance;
			
			if(!processed[node])
				processed[node] = true;
			
			for(int i = 0; i < graph.length; i++) {
				if(graph[node][i] < INF) {
					pendingNodes.push(i);
					pendingDistances.push(distances[node] + graph[node][i]);
				}
			}
					
		}
		
		return distances;
		
	}
	
	//**********************************************************************
	
	
	
	public static void main(String[] args) {
		double[][] graph = initGraph(9, INF);
		setEdge(graph, "A", "B", 3);
		setEdge(graph, "A", "D", 2);
		setEdge(graph, "A", "F", 1);
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
		
		double distances[] = dijkstra(graph, map.get("A"));
		System.out.println(Arrays.toString(distances));
	}
}
