import java.util.Arrays;
import java.util.Random;

public class AgenteViajero {
	
	static int[] bestRoute;
	static int minTime;
	static int cont;
	
	static void calculateMinTime(int[][] graph, int start) {
		int N = graph.length;
		minTime = Integer.MAX_VALUE;
		bestRoute = new int[N-1];
		cont = 0;
		for(int i = 0; i < N; i++) {
			if(i == start) {continue;}
			int[] solution = new int[N-1];
			solution[0] = i;
			boolean[] visited = new boolean[N];
			visited[start] = true;
			visited[i] = true;
			processSolution(graph, start, 1, solution, visited);
		}
		System.out.println("Min time: " + minTime);
		System.out.println("Best route: " + 
							start + ", " + Arrays.toString(bestRoute) + ", " + start);
	}
	
	static void processSolution(int[][] graph, int start, int k,
								int[] solution, boolean[] visited) {
		int N = graph.length;
		cont++;
		if(k == N-1) {//Ya se formó una solución
			int time = graph[start][solution[0]] + graph[solution[N-2]][start];
			for(int i = 0; i < N-2; i++) {
				time += graph[solution[i]][solution[i+1]];
			}
			
			if(time < minTime) {
				minTime = time;
				bestRoute = solution;
			}
		} else { //aun no se completa la solución
			for(int i = 0; i < N; i++) {
				if(visited[i]) {continue;}
				int[] newSolution = solution.clone();
				newSolution[k] = i;
				boolean[] newVisited = visited.clone();
				newVisited[i] = true;
				processSolution(graph, start, k+1, newSolution, newVisited);
			}
		}
	}
	
	/**********************BackTracking**********************/
	
	static void fastCalculateMinTime(int[][] graph, int start) {
		int N = graph.length;
		minTime = Integer.MAX_VALUE;
		bestRoute = new int[N-1];
		cont = 0;
		for(int i = 0; i < N; i++) {
			if(i == start) {continue;}
			int[] solution = new int[N-1];
			solution[0] = i;
			boolean[] visited = new boolean[N];
			visited[start] = true;
			visited[i] = true;
			fastProcessSolution(graph, start, 1, solution, visited, graph[start][i]);
		}
		System.out.println("Min time: " + minTime);
		System.out.println("Best route: " + 
							start + ", " + Arrays.toString(bestRoute) + ", " + start);
	}
	
	static void fastProcessSolution(int[][] graph, int start, int k, 
			int[] solution, boolean[] visited, int currentTime) {
		int N = graph.length;
		cont++;
		if (k == N - 1) {// Ya se formó una solución
			int time = currentTime + graph[solution[N - 2]][start];

			if (time < minTime) {
				minTime = time;
				bestRoute = solution;
			}
		} else { // aun no se completa la solución
			for (int i = 0; i < N; i++) {
				if (visited[i]) {continue;}
				if(minTime < currentTime + graph[solution[k-1]][i]) {continue;}
				
				int[] newSolution = solution.clone();
				newSolution[k] = i;
				boolean[] newVisited = visited.clone();
				newVisited[i] = true;
				fastProcessSolution(graph, start, k + 1, newSolution, newVisited, currentTime + graph[solution[k-1]][i]);
			}
		}
	}
	
	
	
	public static int[][] randomGraph(int N, int max){
        int g[][] = new int[N][N];
        Random r = new Random();
        r.setSeed(1234);
        for(int i =0; i<N; i++){
            for(int j=0; j<N; j++){
                if(i==j) continue;
                g[i][j]= (int)(r.nextInt(max));
            }
        }
       
        return g;
    }
	
	private static void testFuerzaBruta() {
		int[][] graph = randomGraph(12, 100);
		long ini = System.currentTimeMillis();
		calculateMinTime(graph, 0);
		long fin = System.currentTimeMillis();
		System.out.println("Duración: " + (fin - ini));
		System.out.println("Llamadas: " + cont);
	}
	
	private static void testBackTracking() {
		int[][] graph = randomGraph(13, 100);
		long ini = System.currentTimeMillis();
		fastCalculateMinTime(graph, 0);
		long fin = System.currentTimeMillis();
		System.out.println("Duración: " + (fin - ini));
		System.out.println("Llamadas: " + cont);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//testFuerzaBruta();
		//System.out.println();
		testBackTracking();
	}

}
