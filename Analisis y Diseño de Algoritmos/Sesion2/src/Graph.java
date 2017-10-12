import java.util.ArrayDeque;
import java.util.HashMap;

public class Graph {
	
	
	static String names[] = {"A", "B", "C", "D", "E", "F", "G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	static HashMap<String, Integer> map = new HashMap<String, Integer>();
	static {
		for(int i =0; i <names.length; i++)
			map.put(names[i], i);
	}
	
	
	public static boolean DFS(boolean[][]graph, int start, int end){
		
		ArrayDeque<Integer> pendingNodes = new ArrayDeque<>();
		boolean[] processed = new boolean[graph.length];
		pendingNodes.push(start);
		
		while(!pendingNodes.isEmpty()){
			int node = pendingNodes.pop();
			if(node==end){
				return true;
			}
			System.out.println(names[node]);
			if(!processed[node]){
				processed[node]=true;
				
				for(int i=0; i<graph.length; i++){
					if(graph[node][i] && !processed[i]){
						pendingNodes.push(i);
					}
				}

			}
				
		}
				
		return false;
		
	}
	
	public static boolean BFS(boolean[][]graph, int start, int end){
		
		ArrayDeque<Integer> pendingNodes = new ArrayDeque<>();
		boolean[] processed = new boolean[graph.length];
		pendingNodes.offer(start);
		
		while(!pendingNodes.isEmpty()){
			int node = pendingNodes.poll();
			if(node==end){
				return true;
			}
			
			System.out.println(names[node]);
			if(!processed[node]){
				processed[node]=true;
				
				for(int i=0; i<graph.length; i++){
					if(graph[node][i] && !processed[i]){
						pendingNodes.offer(i);
					}
				}

			}
				
		}
				
		return false;
		
	}
	
	public static void setEdge(boolean[][]graph, String v1, String v2){
		g.insertEdge(v1+v2, v1,v2, false);
		setEdge(graph, map.get(v1), map.get(v2), true);
		
	}
	
	public static void setEdge(boolean[][]graph, int v1, int v2, boolean value){
		graph[v1][v2]= value;
		graph[v2][v1]= value;
		
	}
	
	
	static GraphUI g = new GraphUI();
	
	private static void testDFS(){
		boolean[][]graph = new boolean[6][6];
		setEdge(graph, "A", "C");
		setEdge(graph, "A", "E");
		setEdge(graph, "B", "C");
		setEdge(graph, "B", "D");
		setEdge(graph, "C", "F");
		setEdge(graph, "D", "F");
		setEdge(graph, "E", "F");
		
		System.out.println(DFS(graph, 0, 1));
		System.out.println(BFS(graph, 0, 1));
		g.displayGraph();
		
	}
	
	
	public static void main(String[] args) {
		testDFS();
	}
	
	
	
	
	
}
