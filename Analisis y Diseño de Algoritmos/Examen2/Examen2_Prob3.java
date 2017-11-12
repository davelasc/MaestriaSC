import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Examen2_Prob3 {

	public static int INF = 0;//Integer.MAX_VALUE;
	static String names[] = {"A", "B", "C", "D", "E", "F", "G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	static HashMap<String, Integer> map = new HashMap<String, Integer>();
	static {
		for(int i =0; i <names.length; i++)
			map.put(names[i], i);
	}
	
	public static void setEdge(int[][]graph, int v1, int v2, int value){
		graph[v1][v2]= value;
		
	}
	
	public static void setEdge(int[][]graph, String v1, String v2, int value){
		setEdge(graph, map.get(v1), map.get(v2), value);
		setEdge(graph, map.get(v2), map.get(v1), value);
	}
	
	public static int[][] initGraph(int size, int initVal) {
		int[][] graph = new int[size][size];
		for(int[] row: graph) {
			Arrays.fill(row, initVal);
		}
		
		return graph;
	}
	
	//**********************************************************************
	
	public static boolean existeRuta(int[][] mapa, int T, int inicio, int destino) {
		
		if(inicio == destino) {
			return true;
		}
		
		int[] distances = new int[mapa.length];
		boolean[] processed = new boolean[mapa.length];
		int[] voyage = new int[mapa.length];
		
		for(int i = 0; i < distances.length; i++) {
			distances[i] = Integer.MAX_VALUE;
			voyage[i] = -1;
		}
		
		ArrayDeque<Integer[]> pendingNodes = new ArrayDeque<Integer[]>();
		
		pendingNodes.push(new Integer[]{inicio, -1});
		
		while(!pendingNodes.isEmpty()) {
			Integer[] node = pendingNodes.pop();
			if(processed[node[0]])
				continue;
			
			processed[node[0]] = true;
			voyage[node[0]] = node[1];
			
			for(int i = 0; i < mapa.length; i++) {
				if(mapa[node[0]][i] > 0 && T <= mapa[node[0]][i]) {
					pendingNodes.add(new Integer[]{i, node[0]});
				}
					
			}
		}
		
		return voyage[destino] > 0 ? true : false;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int nodes = sc.nextInt();
		int[][] graph = initGraph(nodes, INF);
		int start = map.get(sc.next());
		int end = map.get(sc.next());
		int max = sc.nextInt();
		int conn = sc.nextInt();
		String a, b;
		int w;
		for(int i = 0; i < conn; i++) {
			a = sc.next();
			b = sc.next();
			w = sc.nextInt();
			setEdge(graph, a, b, w);
		}
		
		System.out.println(existeRuta(graph, start, end, max));
		
	}
	
}
