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
	
	/*******************HILL CLIMBING***********************/
	static int getTime(int[] solution, int[][] graph, int start) {
		
		int time = graph[start][solution[0]] + graph[solution[solution.length-1]][start];
		for(int i = 0; i < solution.length-1; i++) {
			time += graph[solution[i]][solution[i+1]];
		}
		
		
		return time;
	}
	
	static int rand(int min, int max) {
		return (int)((max - min+1) * Math.random()) + min;
	}
	
	static int[] randomPath(int N, int start) {
		int[] solution = new int[N-1];
		
		for(int i = 0, s = 0; i < N ; i++) {
			if(i == start) {
				continue;
			}
			solution[s++] = i;
		}
		
		//permutación aleatoria (fisher-yates)
		for(int s = 0; s < N-1; s++) {
			int t = rand(s, solution.length);
			Utils.swap(solution, s, t);
			
		}
		
		return solution;
	}
	
	static void hillClimbing(int G, int[][] graph, int start) {
		//solución aleatoria
		int[] solution = randomPath(graph.length, start);
		//evaluar la solución
		int time = getTime(solution, graph, start);
		
		for(int g=0; g < G; g++) {
			int i1 = rand(0, solution.length-1);
			int i2 = rand(0, solution.length-1);
			while(i1 == i2) {
				i2 = rand(0, solution.length-1);
			}
			
			//hacer el sawp de i1 con i2
			Utils.swap(solution, i1, i2);
			int time1 = getTime(solution, graph, start);
			if(time1 < time) {
				time = time1;
			} else {
				//no fue mejor, regresar al estado anterior
				Utils.swap(solution, i1, i2);
			}
		}
		System.out.println("/********************Hill*********************/");
		System.out.println("Min time: " + time);
		System.out.println("Best route: " + start + " " + Arrays.toString(solution));
	}
	
	static void randomSolution(int G, int[][] graph, int start) {
		//solución aleatoria
		int[] bestSolution = randomPath(graph.length, start);
		//evaluar la solución
		int bestTime = getTime(bestSolution, graph, start);
		for(int g = 0; g < G; g++) {
			int[] solution = randomPath(graph.length, start);
			int time = getTime(solution, graph, start);
			if(time < bestTime) {
				bestTime = time;
				bestSolution = solution;
			}
		}
		System.out.println("/********************RAND*********************/");
		System.out.println("Min time: " + bestTime);
		System.out.println("Best route: " + start + " " + Arrays.toString(bestSolution));
	}
	
	private static void testHillClimbing() {
		int[][] graph = randomGraph(20, 100);
		hillClimbing(100000, graph, 0);
		randomSolution(100000, graph, 0);
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
		//testBackTracking();
		testHillClimbing();
	}

}
