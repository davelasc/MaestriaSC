import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Tarea7_Prob1 {
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
	
	public static void dijkstra(double[][] graph, int start, int end) {
		
		if(start == end) {
			System.out.println(names[start]);
			return;
		}
		
		double[] distances = new double[graph.length];
		int[] voyage = new int[graph.length];
		boolean[] processed = new boolean[graph.length];
		
		for(int i = 0; i < distances.length; i++) {
			distances[i] = INF;
			voyage[i] = -1;
		}
		
		ArrayDeque<Integer[]> pendingNodes = new ArrayDeque<Integer[]>();
		ArrayDeque<Double> pendingDistances = new ArrayDeque<>();
		
		pendingNodes.push(new Integer[]{start, -1});
		pendingDistances.push(0.0);
		while(!pendingNodes.isEmpty()) {
			Integer[] node = pendingNodes.pop();
			double distance = pendingDistances.pop();
			
			if(processed[node[0]] && distance >= distances[node[0]]) {
				continue;
			}
			
			distances[node[0]] = distance;
			voyage[node[0]] = node[1];
			
			if(!processed[node[0]]) {
				processed[node[0]] = true;
			}
				
			
			for(int i = 0; i < graph.length; i++) {
				if(graph[node[0]][i] < INF) {
					pendingNodes.push(new Integer[]{i, node[0]});
					pendingDistances.push(distances[node[0]] + graph[node[0]][i]);
				}
			}
		}
		
		String path = "";
		double distance = distances[end];
		while(end != -1) {
			path = names[end] + "" + path;
			end = voyage[end];
		}
		System.out.println(Arrays.toString(voyage));
		System.out.println(path + " " + distance);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int nodes = sc.nextInt();
		double[][] graph = initGraph(nodes, INF);
		int start = map.get(sc.next());
		int end = map.get(sc.next());
		
		int conn = sc.nextInt();
		String a, b;
		double w;
		for(int i = 0; i < conn; i++) {
			a = sc.next();
			b = sc.next();
			w = sc.nextDouble();
			setEdge(graph, a, b, w);
		}
		
		dijkstra(graph, start, end);
		
	}
}
