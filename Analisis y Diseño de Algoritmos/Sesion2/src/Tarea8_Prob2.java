import java.util.Scanner;

public class Tarea8_Prob2 {
	
	public static void main(String args[]) {
		testCambio();
	}

	public static void testCambio() {
		/*int C = 9;
		int[] M = {1, 2, 3, 4};
		int C = 188;
		int[] M = {1,4,7,10,13,14,18,22};*/
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int C = sc.nextInt();
		int MAX = sc.nextInt();
		int[] M = new int[n];
		
		for(int i = 0; i < n; i++)
			M[i] = sc.nextInt();
		monedasUsadas(M, C, MAX);
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
