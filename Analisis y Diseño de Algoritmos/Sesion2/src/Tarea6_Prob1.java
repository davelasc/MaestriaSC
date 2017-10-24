import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Tarea6_Prob1 {
	
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
		//g.insertEdge(v1+v2, v1,v2, false);
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
		int prevLeader = 0, newLeader = 0;
		if(leaderI == leaderJ) {
			return false;
		}
		
		int rankLeaderI = ranks[i];
		int rankLeaderJ = ranks[j];
		
		if(rankLeaderI > rankLeaderJ || 
				(rankLeaderI == rankLeaderJ && leaderI > leaderJ)) {
			prevLeader = parents[j];
			newLeader = leaderI;
			parents[j] = leaderI;
			ranks[leaderI] += ranks[leaderJ];
		} else {
			prevLeader = parents[i];
			newLeader = leaderJ;
			parents[i] = leaderJ;
			ranks[leaderJ] += ranks[leaderI];
		}
		
		for(int k = 0; k < parents.length; k++) {
			if(parents[k] == prevLeader) {
				parents[k] = newLeader;
			}
		}
		
		return true;
	}
	
	public static void mstKruskal(double[][] graph, PriorityQueue<Edge> candidates, int[] parents, int[] ranks, boolean check) {
		List<Edge> mst = new ArrayList<Edge>(graph.length-1);
		
		while(mst.size() < graph.length-1 && candidates.size() > 0) {
			Edge minEdge = candidates.poll();
			boolean added = true;
			if(join(parents, ranks, minEdge.v1, minEdge.v2)) {
				mst.add(minEdge);
				added = true;
			}else {
				added = false;
			}
			
			if(check) {
				System.out.print(added ? "T " : "F ");
				for(int i = 0; i < ranks.length; i++) {
					if(parents[i] == parents[minEdge.v1]) {
						System.out.print(names[i]);
					}
				}
				System.out.println();
			}
		}
	}
	
	public static int[] initParents(int length) {
		int[] parents = new int[length];
		for(int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
		return parents;
	}
	
	public static int[] initRanks(int length) {
		int[] ranks = new int[length];
		//Inicialmente cada uno es padre de si mismo
		for(int i = 0; i < ranks.length; i++) {
			ranks[i] = 1;
		}
		return ranks;
	}
	
	public static void initCandidates(double[][] graph, String a, String b, PriorityQueue<Edge> candidates) {
		int i = map.get(a);
		int j = map.get(b);
		if(graph[i][j] != INF) {
			Edge e = new Edge(i, j, graph[i][j]);
			candidates.offer(e);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int inversionistas = sc.nextInt();
		int colaboradores = sc.nextInt();
		
		double[][] graph = initGraph(inversionistas, INF);
		
		int ranks[] = initRanks(graph.length);
		int parents[] = initParents(graph.length);
		PriorityQueue<Edge> candidates =  new PriorityQueue<Edge>();
		String a, b;
		for(int i = 0; i < colaboradores; i++) {
			a = sc.next();
			b = sc.next();
			setEdge(graph, a, b, 1);
			initCandidates(graph, a, b, candidates);
		}
		 
		mstKruskal(graph, candidates, parents, ranks, false);
		
		int opciones = sc.nextInt();
		for(int i = 0; i < opciones; i++) {
			a = sc.next();
			b = sc.next();
			setEdge(graph, a, b, 2);
			initCandidates(graph, a, b, candidates);
			mstKruskal(graph, candidates, parents, ranks, true);
		}
		
		
		
	}
}
