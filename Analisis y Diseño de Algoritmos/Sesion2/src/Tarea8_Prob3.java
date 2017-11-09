import java.util.LinkedList;
import java.util.Scanner;

public class Tarea8_Prob3 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		double[] values = new double[num];
		
		for(int i = 0; i < num; i++) {
			values[i] = sc.nextDouble();
		}
		//double[] values = {1.81, 1.78, 1.80, 1.91, 1.79};
		//double[] values = {-33.801, 14.038, 26.531, -27.012, 12.310, -18.919, 43.677, 18.436, -1.931, 23.543, -1.136, 48.866, 15.093, 19.042, -5.109, -16.992, -20.666};
		//double[] values = {-32.616, -6.485, 35.364, -3.903, -26.552, 3.171, -6.807, 30.785, -18.755, 31.388, -11.384, -19.121, 13.706, -33.481, 3.064, -38.557, -40.589 };
		solution(values);
	}
	
	public static void solution(double[] values) {
		LinkedList<Double> result = new LinkedList<Double>();
		result.add(values[0]);
		printLinkedList(solution(values, 1, result));
		
	}
	
	public static LinkedList<Double> solution(double[] values, int index, LinkedList<Double> result) {
		
		if(index == values.length)
			return result;
		
		if(values[index] > result.getLast()) {
			result.add(values[index]);
			return solution(values, index+1, result);
		} else {
			//LinkedList<Double> ignored = (LinkedList<Double>) result.clone();
			LinkedList<Double> recognize = (LinkedList<Double>) result.clone();
			
			while(recognize.size() > 0 && recognize.getLast() > values[index]) 
				recognize.removeLast();
			
			recognize.add(values[index]);
			
			
			//ignored = solution(values, index+1, ignored);
			result = solution(values, index+1, result);
			recognize = solution(values, index+1, recognize);
			
			return result.size() > recognize.size() ? result : recognize;
		}
	}
	
	public static void printLinkedList(LinkedList<Double> ll) {
		
		while(ll.size() > 0) {
			System.out.printf("%.3f ",ll.pop());
		}
		System.out.println("");
	}
	

}
