import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

public class TareaGrafos {

	//public static GraphUI g = new GraphUI();
	
	/*public static void setEdge(boolean[][] graph, int v1, int v2, boolean value) {
		graph[v1][v2] = value;
		graph[v2][v1] = value;
		g.insertEdge(""+v1+v2, ""+v1, ""+v2, false);
	}/**/
	
	private static void updateNodes(ArrayList<String> table, String nodo) {
		if(!table.contains(nodo)) {
			table.add(nodo);
		}
	}
	
	public static void main(String[] args) {
		testProb2();
	}
	
	private static void testProb2() {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> nodeList = new ArrayList<String>();
		
		int nodos = sc.nextInt();
		int aristas = sc.nextInt();
		boolean[][] graph = new boolean[nodos][nodos];
		String nodoA, nodoB;
		
		for(int i = 0; i < nodos; i++) {
			nodeList.add(Character.toString((char) ('A'+i)));
		}
		
		for(int i = 0; i < aristas; i++) {
			nodoA = sc.next();
			nodoB = sc.next();
			graph[nodeList.indexOf(nodoA)][nodeList.indexOf(nodoB)] = true;
			graph[nodeList.indexOf(nodoB)][nodeList.indexOf(nodoA)] = true;
		}
		//getShortestPath(graph, nodeList);
		getShortestPath(graph, 0, 1);
	}
	
	private static void getShortestPath(boolean[][] graph, int startingPoint, int endingPoint) {
		
		
		
	}
	
	private static void testProb1() {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> nodeList = new ArrayList<String>();
		
		int nodos = sc.nextInt();
		int aristas = sc.nextInt();
		boolean[][] graph = new boolean[nodos][nodos];
		String nodoA, nodoB;
		
		for(int i = 0; i < nodos; i++) {
			nodeList.add(Character.toString((char) ('A'+i)));
		}
		
		for(int i = 0; i < aristas; i++) {
			nodoA = sc.next();
			nodoB = sc.next();
			graph[nodeList.indexOf(nodoA)][nodeList.indexOf(nodoB)] = true;
			graph[nodeList.indexOf(nodoB)][nodeList.indexOf(nodoA)] = true;
		}
		generateConnexPaths(graph, nodeList);
		
	}
	
	private static void generateConnexPaths(boolean[][] graph, ArrayList<String> nodeList) {
		
		boolean[] visited = new boolean[graph.length];
		String path = connexRute(visited, graph, 0, nodeList);
		if(path.length() != visited.length) {
			System.out.println("F");
			System.out.println(path);
			for(int i = 1; i < visited.length; i++) {
				if(!visited[i]) {
					path = connexRute(visited, graph, i, nodeList);
					System.out.println(path);
				}
			}
		} else {
			System.out.println("T");
			System.out.println(path);
		}
		
		
		
	}
	
	private static String connexRute(boolean[] visited, boolean[][] graph, int startingPoint, ArrayList<String> nodeList) {
		
		ArrayDeque<Integer> pendingNodes = new ArrayDeque<Integer>();
		pendingNodes.push(startingPoint);
		String path = "";
		int index = 0;
		while(!pendingNodes.isEmpty()) {
			index = pendingNodes.pop();
			if(!visited[index]) {
				visited[index] = true;
				path = path + "" + nodeList.get(index);
				for(int i = 0; i < visited.length; i++) {
					if(!visited[i] && graph[index][i]) {
						pendingNodes.push(i);
					}
				}
			}
		}
		
		char[] arr = path.toCharArray();
		Arrays.sort(arr);
		return new String(arr);
	}
	
	
}
