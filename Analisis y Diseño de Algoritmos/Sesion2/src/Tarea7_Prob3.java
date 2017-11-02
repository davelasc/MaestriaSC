import java.util.Scanner;

public class Tarea7_Prob3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String textA = sc.next();
		String textB = sc.next();
		largestSubStringNumber(textA, textB);
	}
	
	public static void largestSubStringNumber(String textA, String textB) {
		char[] arrA= textA.toCharArray();
		char[] arrB = textB.toCharArray();
		int[][] subStringLength = new int[arrA.length][arrB.length+1];
		
		for(int i = 0; i < arrA.length; i++) {
			for(int j = 0; j <= arrB.length; j++) {
				if(j == 0)
					continue;
				if(i == 0) {
					subStringLength[i][j] = arrB[j-1] == arrA[i] ? subStringLength[i][j-1] + 1 :
						subStringLength[i][j-1];
				} else {
					int propousal = arrB[j-1] == arrA[i] ? subStringLength[i][j-1] + 1 :
						subStringLength[i][j-1];
					subStringLength[i][j] = propousal > subStringLength[i-1][j] ? propousal : 
						subStringLength[i-1][j];
				}
			}
		}
		System.out.println(subStringLength[arrA.length-1][arrB.length]);
	}	
}
