import java.util.Scanner;

public class Tarea8_Prob1 {

	public static void main(String args[]) {
		testCambio();
	}

	public static void testCambio() {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int C = sc.nextInt();
		int[] M = new int[n];
		
		for(int i = 0; i < n; i++)
			M[i] = sc.nextInt();
		cambio(C, M);
	}
	
	public static void cambio(int C, int[] M){
		int[][]mat = new int[M.length][C+1];
		
		for(int m=0; m<M.length; m++){
			for(int c=0; c<=C; c++){
				if(m==0)
					mat[m][c]=c;
				else if (c==M[m])
					mat[m][c]=1;
				else if (c <M[m])
					mat[m][c]=mat[m-1][c];
				else{
					int nuevo = mat[m][c-M[m]]+1;
					if(nuevo < mat[m-1][c])
						mat[m][c]= nuevo;
					else
						mat[m][c]= mat[m-1][c];
				}
				
			}
			
		}
		monedasUsadas(M, C, mat[M.length-1][C]);
	}
	
	public static void monedasUsadas(int[] M, int C, int MAX) {
		int[] cam = new int[MAX];
		for(int i = M.length - 1; i >= 0; i--) {
			cam[0] = i;
			
			if((M[i] * MAX) < C) 
				break;
			monedasUsadas(M, C, cam, 0);
		}
		
	}
	
	public static void monedasUsadas(int[] M, int C, int[] cam, int index) {
		int t = C - M[cam[index]];
		index++;
		if(index == cam.length) {
			if(t == 0)
				printArray(M, cam);
		} else if(index < cam.length){
			int[] c = cam;
			for(int i = cam[index-1]; i >= 0; i--) {
				if(t >= 0) {
					cam[index] = i;
					monedasUsadas(M, t, c, index);				
				} else {
					break;
				}
			}
		}
	}
	
	
	public static void printArray(int[]M, int[] arr) {
		for(int i = 0; i < arr.length; i++)
			System.out.print(M[arr[i]] + " ");
		System.out.println("");
	}
	
}
