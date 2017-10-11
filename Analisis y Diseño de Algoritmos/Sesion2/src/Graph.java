import java.util.ArrayDeque;

public class Graph {

	public static boolean DFS(boolean [][] graph, int start, int end, GraphUI g) {
		ArrayDeque<Integer> pendingNodes = new ArrayDeque<Integer>();
		ArrayDeque<String> pendingEdges = new ArrayDeque<String>();
		boolean[] processed = new boolean[graph.length];
		pendingNodes.push(start);
		
		while(!pendingNodes.isEmpty()) {
			int node = pendingNodes.pop();
			if(node == end) {
				g.setSelectedNode(node+"");
				return true;
			}
			System.out.println(node);
			if(!processed[node]) {
				processed[node] = true;
				for(int i = 0; i < graph.length; i++) {
					if(graph[node][i] && !processed[i]) {
						pendingNodes.push(i);
						pendingEdges.push(""+node+""+i);
						g.setSelectedNode(""+node);
					}
				}
			}
			
			String edge = pendingEdges.pop();
			g.setSelectedEdge(edge);
			g.setSelectedEdge(""+edge.charAt(1)+""+edge.charAt(0)); 
			try {
				Thread.sleep(1000);
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		
		return false;
	}
	
	
	
	
	public static boolean BFS(boolean [][] graph, int start, int end, GraphUI g) {
		ArrayDeque<Integer> pendingNodes = new ArrayDeque<Integer>();
		boolean[] processed = new boolean[graph.length];
		pendingNodes.offer(start);
		int previous;
		while(!pendingNodes.isEmpty()) {
			int node = pendingNodes.poll();
			g.setSelectedNode(""+node);
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
		g.insertEdge(""+v1+""+v2, ""+v1, ""+v2, false);
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
		g.displayGraph();
		System.out.println("DFS: \n" + DFS(graph, 0, 1, g));
		//System.out.println("BFS: \n" + BFS(graph, 0, 1, g));
		
	}
	
	public static void main(String[] args) {
		testDFS();
	}
	
}
