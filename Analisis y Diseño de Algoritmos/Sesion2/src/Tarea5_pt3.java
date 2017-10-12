import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;

public class Tarea5_pt3 {
	static String names[] = {"A", "B", "C", "D", "E", "F", "G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	static HashMap<String, Integer> map = new HashMap<String, Integer>();
	static {
		for(int i =0; i <names.length; i++)
			map.put(names[i], i);
	}
	
	public static String BFS(boolean[][]graph, int start, Hashtable<Integer, String> targets){
		
		ArrayDeque<Integer> pendingNodes = new ArrayDeque<>();
		boolean[] process = new boolean[graph.length];
		
		pendingNodes.offer(start);
		
		while(!pendingNodes.isEmpty()) {
			int node = pendingNodes.pop();
			
			if(targets.containsKey(node)) {
				return targets.get(node);
			}
			
			if(!process[node]){
				process[node]=true;
				for(int i=0; i<graph.length; i++){
					if(graph[node][i] && !process[i]){
						pendingNodes.offer(i);
					}
				}

			}
		}
		
		return "";
		
	}
	
	
	public static void setEdge(boolean[][]graph, String v1, String v2){
		setEdge(graph, map.get(v1), map.get(v2), true);
		
	}
	
	public static void setEdge(boolean[][]graph, int v1, int v2, boolean value){
		graph[v1][v2]= value;
		graph[v2][v1]= value;
	}
	
	private static void testTarea(){
		
		Scanner sc = new Scanner(System.in);
		int nodos = sc.nextInt();
		int aristas = sc.nextInt();
		int targetsL = sc.nextInt();
		Hashtable<Integer, String> targets = new Hashtable<Integer, String>();
		int start = sc.next().charAt(0) - 'A';
		String ele;
		for(int i = 0; i < targetsL; i++) {
			ele = sc.next();
			targets.put(ele.charAt(0) - 'A', ele);
		}
		
		boolean[][] graph = new boolean[nodos][nodos];
		for(int i = 0; i < aristas; i++) {
			setEdge(graph, sc.next(), sc.next());
		}
		
		System.out.println(BFS(graph, start, targets));
	}
	
	
	public static void main(String[] args) {
		testTarea();
	}
}
