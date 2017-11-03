
public class StyblinskiTang {

	static double MIN_X1 = -5, MIN_X2 = -5;
	static double MAX_X1 = 5, MAX_X2 = 5;
	
	static double BEST_X1 = 1, BEST_X2 = 1;
	
	//Rosenbrock 100 * (x2-x1^2)^2+(1-x1)^2
	static double R(double x1, double x2) {
		return 100 * Math.pow(x2 - x1*x1, 2) + 
				Math.pow(1-x1, 2);
	}
	
	//Styblinsky
	static double S(double x1, double x2) {
		return 0.5 * (Math.pow(x1, 4) - 16 * Math.pow(x1, 2) + 5*x1 + 
				Math.pow(x2, 4) - 16 * Math.pow(x2, 2) + 5*x2);
	}
	
	static double f(double x1, double x2) {
		return S(x1, x2);
		//return R(x1, x2);
	}
	
	static double getFitness(double r) {
		return 1.0 - (100 + r)/1000;
		//return 1.0 / ( 1.0 + r);
	}
	
	static double rand(double min, double max) {
		return (max - min) * Math.random() + min;
	}
	
	static double norm(double x, double y) {
		return Math.sqrt(x*x+y*y);
	}
	
	static void hillClimbing(int G, double minDx) {
		//elegir valores aleatorios
		double x1 = rand(MIN_X1, MAX_X1);
		double x2 = rand(MIN_X2, MAX_X2);
		//valor de la función en los puntos aleatorios
		double r = f(x1, x2);
		//evaluar la solución
		double fitness = getFitness(r);
		int g = 0; //contador de generaciones
		
		//tamaño del incremento
		double Dx1 = (MAX_X1-MIN_X1) * (1-fitness);
		double Dx2 = (MAX_X2-MIN_X2) * (1-fitness);
		
		while(g < G && norm(Dx1, Dx2) > minDx) {
			//calcular desplazamientos aleatorios
			double dx1 = rand(-Dx1/2, Dx1/2);
			double dx2 = rand(-Dx2/2, Dx2/2);
			double r1 = f(x1+dx1, x2+dx2);
			if(r1 < r) {
				r = r1;
				fitness = getFitness(r);
				x1 += dx1;
				x2 += dx2;
				Dx1 = (MAX_X1-MIN_X1) * (1-fitness);
				Dx2 = (MAX_X2-MIN_X2) * (1-fitness);
			}
			g++;
		}
		System.out.printf("Mejor solución: R(%.4f, %.4f) = %.7f\n" , x1, x2, r);
		System.out.printf("Generaciones = %d, fitness = %.4f\n", g+1, fitness);
		System.out.printf("Distance con el optimo: %.4f\n",norm(x1-BEST_X1, x2-BEST_X2));
	}
	
	static void randomSolutions(int N) {
		double bestX1 = 0, bestX2 = 0, bestR = Double.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			//Elegir valores aleatorios
			double x1 = rand(MIN_X1, MAX_X1);
			double x2 = rand(MIN_X2, MAX_X2);
			//valor de la función en los puntos aleatorios
			double r = f(x1, x2);
			if(r < bestR) {
				bestR = r;
				bestX1 = x1;
				bestX2 = x2;
			}
		}
		
		System.out.printf("Mejor solución: R(%.4f, %.4f) = %.7f\n" , bestX1, bestX2, bestR);
		System.out.printf("Distance con el optimo: %.4f\n",norm(bestX1-BEST_X1, bestX2-BEST_X2));
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("*************HILL CLIMBING*************");
		hillClimbing(1_000_000, 0.00001);
		System.out.println("*************RANDOM*************");
		randomSolutions(1_000_000);
	}
	
}
