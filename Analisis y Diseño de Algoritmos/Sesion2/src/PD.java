
public class PD {

	//coefficiente binomail recursivo
	static long calls = 0;
	public static long coefficienteBinomial(int N, int K) {
		calls++;
		if(K == 0 || K == N) 
			return 1;
		
		if(K > N) 
			return 0;
		
		return coefficienteBinomial(N-1, K-1) + coefficienteBinomial(N-1, K);
	}
	
	//coefficiente binomail con programación dinámica
	public static long coefficientePD(int N, int K) {
		long[][] mat = new long[N+1][K+1];
		calls = 0;
		for(int n = 0; n <= N; n++) {
			for(int k = 0; k <= K; k++) {
				calls++;
				if(k == 0 || k == n) 
					mat[n][k]=1;
				else if( k > n)
					mat[n][k] = 0;
				else 
					mat[n][k] = mat[n-1][k-1] + mat[n-1][k];
			}
		}
		
		return mat[N][K];
	}
	
	public static int cambio(int C, int[] M) {
		int[][] mat = new int[M.length][C+1];
		
		for(int m = 0; m < M.length; m++) {
			for(int c = 0; c <= C; c++) {
				if(m == 0)
					mat[m][c] = c;
				else if(c == M[m])
					mat[m][c] = 1;
				else if(c < M[m]) {
					mat[m][c] = mat[m-1][c];
				} else {
					int nuevo = mat[m][c-M[m]]+1;
					if(nuevo < mat[m-1][c])
						mat[m][c] = nuevo;
					else
						mat[m][c] = mat[m-1][c];
				}
			}
		}
		
		System.out.println("Monedas usadas");
		int m = M.length-1;
		int c = C;
		
		while(m > 0) {
			int cont = 0;
			while(c > 0 && mat[m][c] < mat[m-1][c]) {
				cont++;
				c -= M[m];
			}
			System.out.println("De $"+M[m] + ": " + cont);
			m--;
		}
		System.out.println("De $"+M[m] + ": " + mat[m][c]);
		return mat[M.length-1][c];
	}
	
	public static void testBinomial() {
		int N = 30, K = 10;
		System.out.println(coefficienteBinomial(N, K));
		System.out.println(calls);
		System.out.println(coefficientePD(N, K));
		System.out.println(calls);
	}
	
	public static void main(String[] args) {
		//testBinomial();
		int[] M = {1,4,6,15,18,23, 25};
		System.out.println(cambio(1000, M));
	}
}
