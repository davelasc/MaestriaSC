import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class NN_AEIU {
	
	static int trainingSetSize = 0;
	
	static int inputLayerLength = 0;
	static int hiddenLayerLength = 0;
	static int outputLayerLength = 0;
	
	static byte[][] trainingSet = null;
	static double[][] expectedOutputs = null;
	static double [][] inputHiddenWeights = null;
	static double [][] hiddenOutputWeights = null;
	static double [] hiddenLayerValues = null;
	static double [] outputLayerValues = null;
	
	static final double LEARNING_RATE = 0.2;
	static final double TEST_THRESHOLD = 1.0 / 6.0;
	static final double TRAIN_THRESHOLD = TEST_THRESHOLD / 8.0;
	
	static void initWeights (){
		for(int i=0; i<inputLayerLength;i++)
			for(int j=0; j<hiddenLayerLength; j++)
				inputHiddenWeights[i][j]= -0.1 + 0.2*Math.random();
		
		for(int j=0; j<hiddenLayerLength; j++)
			for(int k = 0; k < outputLayerLength; k++) {
				hiddenOutputWeights[j][k] = -0.1 + 0.2*Math.random();
			}
	}
	
	static double sigmoid(double z){
		return 1.0/(1+ Math.exp(-z));
	}
	
	static double dsigmoid(double z){
		return z*(1-z);
	}
	
	static void feedForward(int currentInput){
		
		byte[] input = trainingSet[currentInput];
	
		for(int j =0; j<hiddenLayerLength; j++){
			double dot =0;
			for (int i =0; i< input.length; i++){
				dot+= input[i]*inputHiddenWeights[i][j];
			}
			hiddenLayerValues[j]= sigmoid(dot);
		}
		
		double output = 0;
		for(int k=0; k<hiddenLayerLength; k++) {
			output =0;
			for(int j = 0; j < outputLayerLength; j++) 
				output+= hiddenLayerValues[j]*hiddenOutputWeights[j][k];
			outputLayerValues[k] = sigmoid(output);
		}
		
		output = sigmoid(output);
	}
	
	static void backPropagation(int index){
		
		double[] outputDelta = new double[outputLayerLength];
		
		for(int k=0; k < outputLayerLength; k++) {
			
			double error = expectedOutputs[index][k] - outputLayerValues[k];
			outputDelta[k] = dsigmoid(outputLayerValues[k]) * error;
		}
		
		double[] hiddenDeltas = new double[hiddenLayerLength];
		for(int j = 0; j < hiddenLayerLength; j++) {
			double dotProduct = 0.0;
			for(int k = 0; k < outputLayerLength; k++) {
				dotProduct += outputDelta[k] * hiddenOutputWeights[j][k];
			}
			hiddenDeltas[j] = dsigmoid(hiddenLayerValues[j]) * dotProduct;
		}
		
		
		byte[] input = trainingSet[index];
		for(int i = 0; i < input.length; i++) {
			for(int j = 0; j < hiddenLayerLength; j++) {
				inputHiddenWeights[i][j] += LEARNING_RATE * hiddenDeltas[j] * input[i];
			}
		}
		
	}
	
	static void readTrainPatterns(String filename) throws IOException {
        Scanner sc = new Scanner(new FileReader(filename));
        trainingSetSize     = sc.nextInt();
        inputLayerLength    = sc.nextInt();
        outputLayerLength   = sc.nextInt();
        hiddenLayerLength   = inputLayerLength / 2;
        trainingSet         = new byte[trainingSetSize][inputLayerLength];
        inputHiddenWeights  = new double[inputLayerLength][hiddenLayerLength];
        hiddenLayerValues   = new double[hiddenLayerLength];
        hiddenOutputWeights = new double[hiddenLayerLength][outputLayerLength];
        expectedOutputs     = new double[trainingSetSize][outputLayerLength];
        for (int r = 0; r < trainingSetSize; r++) {
            for (int c = 0; c < inputLayerLength; c++) {
                trainingSet[r][c] = sc.nextByte();
            }
            for (int c = 0; c < outputLayerLength; c++) {
                expectedOutputs[r][c] = sc.nextDouble();
            }
        }
        sc.close();
    }
   
    static boolean testPattern(byte[] pattern, double[] expectedOutput) {
        
		for(int j =0; j<hiddenLayerLength; j++){
			double dot =0;
			for (int i =0; i< pattern.length; i++){
				dot+= pattern[i]*inputHiddenWeights[i][j];
			}
			hiddenLayerValues[j]= sigmoid(dot);
		}
		
		
		for(int k=0; k<hiddenLayerLength; k++) {
			double output = 0;
			for(int j = 0; j < outputLayerLength; j++) 
				output+= hiddenLayerValues[j]*hiddenOutputWeights[j][k];
			outputLayerValues[k] = sigmoid(output);
		}
		
		for(int k = 0; k < outputLayerLength; k++) {
			double diff = Math.abs(expectedOutput[k] - outputLayerValues[k]);
			if(diff > TEST_THRESHOLD) {
				return false;
			}
		}
    	
    	return true;
    }
	
    static boolean isCorrect(int index) {
    	
    	for(int k = 0; k < outputLayerLength; k++) {
			double diff = Math.abs(expectedOutputs[index][k] - outputLayerValues[k]);
			if(diff > TRAIN_THRESHOLD) {
				return false;
			}
		}
    	
    	return true;
    }
    
	static void trainMN() {
		initWeights();
		int hits = 0;
		
		for(int epoch = 1; epoch <= 600; epoch++) {
			for(int id = 0; id < trainingSetSize; id++) {
				feedForward(id);
				if(isCorrect(id)) {
					hits++;
					if(hits >= trainingSetSize) {
						return;
					}
				} else {
					hits = 0;
					backPropagation(id);
				}
			}
		}
	}
	
	
	
	
	
	

}
