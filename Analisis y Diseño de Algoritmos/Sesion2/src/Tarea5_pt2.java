import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Tarea5_pt2 {
	static String names[] = {"A", "B", "C", "D", "E", "F", "G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	static HashMap<String, Integer> map = new HashMap<String, Integer>();
	static {
		for(int i =0; i <names.length; i++)
			map.put(names[i], i);
	}
	
	private static boolean processChildren(boolean[][] graph, boolean[] process, int parent, ArrayDeque<Integer> childrenQueue) {
		boolean hasChildren = false;
		for(int i = 0; i < graph[parent].length; i++) {
			if(!graph[parent][i] || process[i] || i == parent)
				continue;
			childrenQueue.push(i);
			hasChildren = true;
		}
		return hasChildren;
	}
	
	private static boolean hasChildren(boolean[][] graph, boolean[] process, int parent) {
		for(int i = 0; i < graph[parent].length; i++) {
			if(graph[parent][i] && !process[i] && i != parent)
				return true;
		}
		return false;
	}
	
	public static ArrayDeque<Integer> DFS(boolean[][]graph, int start, int end){
		
		ArrayDeque<Integer> pendingNodes = new ArrayDeque<>();
		ArrayDeque<Integer> root = new ArrayDeque<Integer>();
		boolean[] process = new boolean[graph.length];
		
		root.add(start);
		
		if(start == end) {
			return root;
		}
		
		while(!root.isEmpty()) {
			
			int node = root.pollLast();
			
			if(node == end) {
				root.add(node);
				return root;
			}
			
			process[node] = true;
			if(hasChildren(graph, process, node)) {
				if(processChildren(graph, process, node, pendingNodes)) {
					root.add(node);
					if(pendingNodes.isEmpty()) {
						return null;
					} else {
						root.add(pendingNodes.pop());
					}
				}
			}
		}
		
		return null;
		
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
	
	private static void testTarea(){
		/*boolean[][] graph = new boolean[9][9];
		setEdge(graph, "A", "B");
		setEdge(graph, "A", "H");
		setEdge(graph, "A", "I");
		setEdge(graph, "B", "C");
		setEdge(graph, "B", "I");
		setEdge(graph, "I", "D");
		setEdge(graph, "I", "H");
		setEdge(graph, "C", "D");
		setEdge(graph, "D", "E");
		setEdge(graph, "D", "F");
		setEdge(graph, "H", "G");
		setEdge(graph, "G", "F");
		
		g.displayGraph();
		
		ArrayDeque<Integer> result = DFS(graph, 1, 7);
		System.out.println(result);
		int start = result.poll();
		int end;
		while(!result.isEmpty()) {
			end = result.poll();
			g.setSelectedEdge(""+names[start]+names[end]);
			g.setSelectedEdge(""+names[end]+names[start]);
			start = end;
		}*/
		
		Scanner sc = new Scanner(System.in);
		ArrayList<String> nodeList = new ArrayList<String>();
		
		int nodos = sc.nextInt();
		int aristas = sc.nextInt();
		int start = sc.next().charAt(0) - 'A';
		int end = sc.next().charAt(0) - 'A';
		
		boolean[][] graph = new boolean[nodos][nodos];
		for(int i = 0; i < aristas; i++) {
			setEdge(graph, sc.next(), sc.next());
		}
		
		ArrayDeque<Integer> result = DFS(graph, start, end);
		for(int r : result) {
			System.out.print(names[r]);
		}
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		testTarea();
	}
}
