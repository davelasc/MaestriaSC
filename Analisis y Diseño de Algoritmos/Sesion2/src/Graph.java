import java.util.ArrayDeque;

public class Graph {

	public static boolean DFS(boolean [][] graph, int start, int end) {
		ArrayDeque<Integer> pendingNodes = new ArrayDeque<Integer>();
		boolean[] processed = new boolean[graph.length];
		pendingNodes.push(start);
		
		while(!pendingNodes.isEmpty()) {
			int node = pendingNodes.pop();
			if(node == end) {
				return true;
			}
			System.out.println(node);
			if(!processed[node]) {
				processed[node] = true;
				for(int i = 0; i < graph.length; i++) {
					if(graph[node][i] && !processed[i]) {
						pendingNodes.push(i);
					}
				}
			}
		}
		
		return false;
	}
	
	public static boolean BFS(boolean [][] graph, int start, int end) {
		ArrayDeque<Integer> pendingNodes = new ArrayDeque<Integer>();
		boolean[] processed = new boolean[graph.length];
		pendingNodes.offer(start);
		
		while(!pendingNodes.isEmpty()) {
			int node = pendingNodes.poll();
			if(node == end) {
				return true;
			}
			System.out.println(node);
			if(!processed[node]) {
				processed[node] = true;
				for(int i = 0; i < graph.length; i++) {
					if(graph[node][i] && !processed[i]) {
						pendingNodes.offer(i);
					}
				}
			}
		}
		
		return false;
	}
	
	public static void setEdge(boolean[][] graph, int v1, int v2, boolean value) {
		graph[v1][v2] = value;
		graph[v2][v1] = value;
		g.insertEdge(""+v1+v2, ""+v1, ""+v2, false);
	}
	
	public static GraphUI g = new GraphUI(); 
	
	private static void testDFS() {
		boolean[][] graph = new boolean[6][6];
		setEdge(graph, 0, 2, true);
		setEdge(graph, 0, 4, true);
		setEdge(graph, 1, 3, true);
		setEdge(graph, 1, 5, true);
		setEdge(graph, 2, 3, true);
		setEdge(graph, 2, 5, true);
		setEdge(graph, 3, 4, true);
		
		System.out.println("DFS: \n" + DFS(graph, 0, 1));
		System.out.println("BFS: \n" + BFS(graph, 0, 1));
		g.displayGraph();
		
	}
	
	public static void main(String[] args) {
		testDFS();
	}
	
}
