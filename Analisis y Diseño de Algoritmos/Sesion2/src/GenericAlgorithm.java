import java.util.Arrays;

public class GenericAlgorithm {
	
	static final int GENOTYPE_LENGTH = 12; //Par
	static final int POPULATION_SIZE = GENOTYPE_LENGTH * 20;
	static final int GENERATIONS = 10_000;
	static final double CROSSOVER_ODDS = 0.8;
	static final double MUTATION_ODDS = 0.2;
	
	static double MAX_VALUE = Math.pow(10, GENOTYPE_LENGTH / 2) - 1;
	static double MIN_X1 = -5, MIN_X2 = -5;
	static double MAX_X1 = 5, MAX_X2 = 5;
	
	//Styblinsky
	static double S(double x1, double x2) {
		return 0.5 * (Math.pow(x1, 4) - 16 * Math.pow(x1, 2) + 5*x1 + 
				Math.pow(x2, 4) - 16 * Math.pow(x2, 2) + 5*x2);
	}
	
	static class Individual {
		double x1, x2; //fenotipo
		byte genotype[];
		double fitness;
		
		public Individual(boolean createArray) {
			if(createArray) {
				this.genotype = new byte[GENOTYPE_LENGTH];
			}
		}
		
		public Individual clone() {
			Individual ind = new Individual(false);
			ind.x1 = this.x1;
			ind.x2 = this.x2;
			ind.genotype = this.genotype.clone();
			return ind;
		}
		
		public void initRandom() {
			for(int i = 0; i < this.genotype.length; i++) {
				this.genotype[i] = (byte)(Math.random() * 10);
			}
			
			//Actualizar el fenotipo
			this.updatePhenotype();
		}
		
		public void updateFitness() {
			double f = S(this.x1, this.x2);
			this.fitness = 1.0 - (100 + f) / 1100; //rango de 0 a 1
		}
		
		public void updatePhenotype() {
			//El cromosoma {1,2,3,4} corresponda a 4,321
			this.x1 = 0;
			this.x2 = 0;
			
			double pot = 1;
			
			for(int i = 0; i < GENOTYPE_LENGTH / 2; i++) {
				this.x1 += this.genotype[i] * pot;
				this.x2 += this.genotype[(GENOTYPE_LENGTH / 2) + i] * pot;
				pot *= 10;
			}
			
			this.x1 = MIN_X1 + (this.x1 / MAX_VALUE) * (MAX_X1 - MIN_X1);
			this.x2 = MIN_X2 + (this.x2 / MAX_VALUE) * (MAX_X2 - MIN_X2);
		}
		
		public String toString() {
			return String.format("<%.5f, %.5f, %s", this.x1, this.x2,
					Arrays.toString(this.genotype));
		}
		
	}
	
	static Individual[] population;
	static double fitnessSum;
	static int bestIndividualIndex;
	
	public static void runGeneticAlgorithm() {
		createPopulation();
		
		for(int i = 0; i < GENERATIONS; i++) {
			calculateFitness();
			selection();
			crossover();
			mutation();
		}
		
		System.out.println(population[bestIndividualIndex] + ": " + 
				population[bestIndividualIndex].fitness);
	}
	
	private static void createPopulation() {
		population = new Individual[POPULATION_SIZE];
		
		for(int i = 0; i < population.length; i++) {
			population[i] = new Individual(true);
			population[i].initRandom();
		}
	}
	
	private static void calculateFitness() {
		fitnessSum = 0;
		bestIndividualIndex = 0;
		
		for(int i = 0; i < population.length; i++) {
			population[i].updateFitness();
			fitnessSum += population[i].fitness;
			if(population[i].fitness > population[bestIndividualIndex].fitness) {
				bestIndividualIndex = i;
			}
		}
	}
	
	//TODO: Qué pasa con el mejor individuo?
	private static void selection() {
		Individual tmpPopulation[] = new Individual[POPULATION_SIZE];
		//ciclo que controla el lanzamiento de la ruleta
		tmpPopulation[0] = population[bestIndividualIndex].clone();
		bestIndividualIndex = 0;
		for(int i = 1; i < POPULATION_SIZE; i++) {
			double rnd = fitnessSum * Math.random();
			int j = 0; 
			double fs = 0;
			while(fs < rnd && j < POPULATION_SIZE) {
				 fs += population[j].fitness;
				 j++;
			}
			
			tmpPopulation[i] = population[j-1].clone();
		}
		
		population= tmpPopulation;
	}
	
	private static void crossover() {
		for(int i = 0; i < POPULATION_SIZE; i+= 2) {
			if(i == bestIndividualIndex || i+1 == bestIndividualIndex) 
				continue;
			
			if(Math.random() > CROSSOVER_ODDS) 
				continue;
			
			byte[] genotype1 = population[i].genotype;
			byte[] genotype2 = population[i+1].genotype;
			
			int id1 = (int)(Math.random() * GENOTYPE_LENGTH);
			int id2 = id1 + (int)(Math.random() * (GENOTYPE_LENGTH - id1));
			
			for(int id = id1; id <= id2; id++) {
				if(Math.random() < 0.5) {
					byte tmp = genotype1[id];
					genotype1[id] = genotype2[id];
					genotype2[id] = tmp;
				}
			}
		}
	}
	
	private static void mutation() {
		for(int i = 0; i < POPULATION_SIZE; i++) {
			if(i== bestIndividualIndex) continue;
			if(Math.random() > MUTATION_ODDS) continue;
			
			int id = (int)(Math.random() * GENOTYPE_LENGTH);
			byte[] genotype = population[i].genotype;
			byte b = (byte)(Math.random()*10);
			while(b == genotype[id]) 
				b = (byte)(Math.random()*10);
			
			genotype[id] = b;
		}
	}
	
	public static void main(String[] args) {
		runGeneticAlgorithm();
	}
	
}
