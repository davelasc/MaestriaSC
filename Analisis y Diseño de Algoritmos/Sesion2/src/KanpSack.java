import java.util.ArrayList;
import java.util.List;

public class KanpSack {
	
	public static class Item {
		
		public double weight;
		public double fraction;
		public double value;

		public Item(int i, int j) {
			this.value = i;
			this.weight = j;
			this.fraction = 0.0;
		}
		
	}
	
	
	public static List<Item> knapSack(List<Item> c, double W) {
		List<Item> s = new ArrayList<Item>(c.size());
		double weight = 0;
		
		while(weight < W) {
			int k = select(c);
			Item x = c.remove(k);
			if(weight + x.weight <= W) {
				x.fraction = 1.0;
				weight += x.weight;
			} else {
				x.fraction = (W - weight) / x.weight;
				weight = W;
			}
			
			s.add(x);
		}
		
		return s;
	}


	private static int select(List<Item> c) {
		// TODO Auto-generated method stub
		int index = -1;
		double maxVW = 0;
		
		for(int i = 0; i < c.size(); i++) {
			Item item = c.get(i);
			double vw = item.value / item.weight;
			if(vw > maxVW) {
				maxVW = vw;
				index = 1;
			}
		}
		return index;
	}
	
	public static void main(String[] args) {
		List<Item> c = new ArrayList<Item>();
		c.add(new Item(20,10));
		c.add(new Item(30,20));
		c.add(new Item(66,30));
		c.add(new Item(40,40));
		c.add(new Item(60,50));
		
		List<Item> s = knapSack(c, 70);
		double value = 0, weight = 0;
		
		for(Item x: s) {
			value+= x.value * x.fraction;
			weight = x.weight * x.fraction;
			System.out.println("Valor: " + x.value + "\n" +
								"Fracción: " + x.fraction + "\n" +
								"= " + x.fraction*x.weight);
		}
		
		System.out.println("Valor total: " + value + "\nPeso Total: " + weight);
	}

}
