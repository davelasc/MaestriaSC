import java.util.ArrayDeque;

public class Examen2_Prob7 {

	public static void main(String[] args) {
		System.out.println(fastF(5,6,7));
		System.out.println(fastF(10,9,8));
		System.out.println(fastF(30,29,28));
		System.out.println(fastF(95,96,97));
		System.out.println(fastF(130,130,130));
	}
	
	public static int fastF(int x, int y, int z ) {
		int result = 0;
		
		if(x <= 0 || y <= 0 || z <= 0)
			return result; 
		
		int[][] baseMatrix = {
				{-3,-1,-1},
				{-1,-3,-1},
				{-1,-1,-3}
			};
		
		ArrayDeque<Integer[]> pendingToProcess = new ArrayDeque<Integer[]>();
		pendingToProcess.add(new Integer[]{x,y,z});
		
		while(!pendingToProcess.isEmpty()) {
			result++;
			Integer[] superFunc = pendingToProcess.pop();
			
			for(int i = 0; i < 3; i++) {
				x = superFunc[0] + baseMatrix[i][0];
				y = superFunc[1] + baseMatrix[i][1]; 
				z = superFunc[2] + baseMatrix[i][2];
				if(x <= 0 || y <= 0 || z <= 0) 
					continue;
				pendingToProcess.add(new Integer[]{x,y,z});
			}
		}
		
		
		return result;
	}
	
	
	
}
