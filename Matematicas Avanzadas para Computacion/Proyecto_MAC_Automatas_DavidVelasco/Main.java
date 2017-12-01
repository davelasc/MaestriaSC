
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

	private static String cadenaDeEntrada = "";
	private static HashMap<String, Integer> alfabeto = null;
	private static HashMap<Integer, Integer> nodosFinales = null;
	private static int nodoInicial = -1;
	private static String[][] matrizDeTransicion = null;
	
	public static void main(String[] args) {
		
		String[] testCases = {
				//"Ejemplo.txt"
				"Prob1.txt",
				"Prob2.txt",
				"Prob3.txt",
				"Prob4.txt"
		};
		//NOTA: No usar paths que contengan espacio!!
		//String path = "C:/Users/uidp1602/Desktop/Proyecto_MAC_Automatas_DavidVelasco/Problema1/";
		String path = "C:/Users/uidp1602/Desktop/Proyecto_MAC_Automatas_DavidVelasco/Problema2/";
		for(int i = 0; i < testCases.length; i++) {
			String pathDelArchivo = path+testCases[i];
			if(crearValoresDeEntrada(pathDelArchivo)) {
				System.out.println("Test case: "+(i+1)+" de "+testCases.length+", Archivo: "+testCases[i]);
				validaCamino();
			}
				
				
		}
	}
	
	private static boolean crearValoresDeEntrada(String pathDelArchivo) {
		
		try {
			FileReader fr = new FileReader(pathDelArchivo);
			BufferedReader br = new BufferedReader(fr);
			cadenaDeEntrada = br.readLine();
			String alfabetoCrudo = br.readLine();
			nodoInicial = Integer.parseInt(br.readLine());
			String nodosFinalesCrudo = br.readLine();
			
			LinkedList<String> listaCruda = new LinkedList<String>();
			String transicionActual = "";
			while((transicionActual = br.readLine()) != null) {
				listaCruda.add(transicionActual);
			}
			
			nodosFinales = new HashMap<Integer, Integer>();
			String[] fc = nodosFinalesCrudo.split(";");
			for(int i = 0; i < fc.length; i++)
				nodosFinales.put(Integer.parseInt(fc[i]), i);
			
			String[] ac = alfabetoCrudo.split(";");
			alfabeto = new HashMap<String, Integer>();
			for(int i = 0; i < ac.length; i++)
				alfabeto.put(ac[i], i);
			
			matrizDeTransicion = new String[listaCruda.size()][alfabeto.size()];
			for(int i = 0; i < matrizDeTransicion.length; i++)
				matrizDeTransicion[i] = listaCruda.pop().split(";");
			br.close();
			fr.close();
			return true;
		} catch(IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	private static void validaCamino() {
		String camino = nodoInicial+"/";
		String comprobacion = "E:"+nodoInicial;
		int nodoActual = nodoInicial;
		String transicionActual = "";
		for(int i = 0; i < cadenaDeEntrada.length(); i++) {
			transicionActual = cadenaDeEntrada.charAt(i) + "";
			int index = indexDeAlfabeto(transicionActual);
			comprobacion += "-T:" + transicionActual + "->";
			
			if(index < 0) 
				continue;
			
			nodoActual = Integer.parseInt(matrizDeTransicion[nodoActual][index]);
			comprobacion += "E:"+nodoActual;
			camino+= nodoActual+"/";
		}
		
		imprimirResultado(nodoActual, camino, comprobacion);
	}
	
	private static int indexDeAlfabeto(String letra) {
		return alfabeto.containsKey(letra) ? alfabeto.get(letra) : -1;
	}
	
	private static void imprimirResultado(int nodoActual, String camino, String comprobacion) {
		System.out.println(esNodoFinal(nodoActual) ? "Aceptada" : "Rechazada");
		System.out.println(camino);
		System.out.println("Explicación:");
		System.out.println("Validano el camino: " + cadenaDeEntrada);
		System.out.println(comprobacion);
		System.out.println("----------------------------------------------------------------");
	}
	
	private static boolean esNodoFinal(int nodo) {
		return nodosFinales.containsKey(nodo);
	}
	
	
	
}
