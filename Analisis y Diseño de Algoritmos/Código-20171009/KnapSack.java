package o2017;

import java.util.ArrayList;
import java.util.List;

public class KnapSack {

	static class Item{
		public double value, weight, fraction;
		
		public Item(double v, double w){
			this.value= v;
			this.weight= w;
			this.fraction = 0.0;
		}

	}
	
	
	static int select(List<Item> C){
		int index=-1;
		
		double maxVW =0;
		for(int i=0; i<C.size(); i++){
			Item item = C.get(i);
			double vw = item.value;///item.weight;
			if(vw>maxVW){ 
				maxVW=vw; //guardar el mejor vw
				index = i;
			}	
		}
		
		
		return index;	
	}
	
	
	public static List<Item> knapSack(List<Item>C, double W){
		List<Item>S =new ArrayList<Item>(C.size());
		double weight =0;
		
		while(weight < W){
			int k = select(C);
			Item x = C.remove(k);
			if(weight + x.weight <= W){
				x.fraction = 1.0;
				weight+=x.weight;
			}else{
				x.fraction= (W-weight)/x.weight;
				weight = W;
	
			}
			
			S.add(x);
	
		}
		
		return S;
	}

	
	public static void main(String[] args) {
		
		List<Item> C = new ArrayList<Item>();
		C.add(new Item(20,10));
		C.add(new Item(30,20));
		C.add(new Item(66,30));
		C.add(new Item(40,40));
		C.add(new Item(60,50));
		
		List<Item> S = knapSack(C, 70);
		double value=0, weight = 0;
		
		for(Item x:S){
			value+=x.value*x.fraction;
			weight+=x.weight*x.fraction;
			System.out.println("Valor:"+ x.value +
							   "\nFracción:"+x.fraction+
							   "= "+x.fraction*x.value+
							   "\npeso:"+x.weight*x.fraction);
			
		}
		
		System.out.println("valor total:"+value+
							"\nPeso total:"+weight);
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
