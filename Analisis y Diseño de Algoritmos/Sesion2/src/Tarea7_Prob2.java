import java.util.Arrays;
import java.util.Scanner;

public class Tarea7_Prob2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testProb2();
	}
	
	public static void testProb2() {
		
		Scanner sc = new Scanner(System.in);
		int coinNum = sc.nextInt();
		int expectedChange = sc.nextInt();
		
		int[] coins = new int[coinNum];
		for(int i = 0; i < coinNum; i++) 
			coins[i] = sc.nextInt();
		
		int[] amounts = new int[coinNum];
		for(int i = 0; i < coinNum; i++)
			amounts[i] = sc.nextInt();
		
		getTotalCoins(coins, amounts, expectedChange);
		
	}
	
	public static void getTotalCoins(int[] coins, int[] amounts, int expectedChange) {
		
		int[][] changeToGive = new int[coins.length][expectedChange+1];
		
		for(int i = 0; i < coins.length; i++) {
			for(int j = 0; j <= expectedChange; j++) {
				if(i == 0) { //Llenamos los unos, nos aseguramos de no pasarnos de los que tenemos
					changeToGive[i][j] = j <= amounts[i] ? j : amounts[i];
				} else {// Llenamos los otros escenarios
					int amountToUse = (j / coins[i]) <= amounts[i] ? (j / coins[i]) : amounts[i];
					int complementIndex = j - (amountToUse * coins[i]);
					int propousal = amountToUse + changeToGive[i-1][complementIndex];
					int total = propousal < changeToGive[i-1][j] ? propousal : changeToGive[i-1][j];
					changeToGive[i][j] = total;
				}
			}
		}
		
		System.out.println(changeToGive[coins.length-1][expectedChange]);
	}
}
