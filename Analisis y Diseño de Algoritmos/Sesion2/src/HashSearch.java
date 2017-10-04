
import java.io.BufferedReader;
import java.io.FileReader;

public class HashSearch {

	static int HASH_LIST_SIZE = 0;
	
	
	static class Customer {
		String rfc;
		String name, address;
		int index;
		
		public Customer(String rfc, String name, String address, int index) {
			this.rfc     = rfc;
			this.name    = name;
			this.address = address;
			this.index   = index;
		}
	}
	
	
	static Customer[] readCustomers(String filename) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line = br.readLine().trim();
		final int COUNT = Integer.parseInt(line);
		Customer[] customers = new Customer[COUNT];
		for(int i = 0; i < COUNT; i ++) {
			line = br.readLine();
			String[] rowData = line.split("\t");
			customers[i] = new Customer(rowData[1].trim(), rowData[0].trim(), rowData[2].trim(), i);
		}
		br.close();
		return customers;
	}
	
	private static int getValue(char c){
		//0..9 --> 0..9
		//A..Z --> 10--35
		if(Character.isDigit(c))
			return c-'0';
		
		return c-'A'+10; //rango de 10 a 35		
	}
	
	
	private static int hashCode(String rfc){
		int h = getValue(rfc.charAt(0));
		for(int i=1; i<rfc.length(); i++){
			h=(h*36+ getValue(rfc.charAt(i))) % HASH_LIST_SIZE;
		}
		
		return h;
		
	}
	
	static class CustomerNode{
		Customer customer;
		CustomerNode next;
		
		CustomerNode(Customer customer){
			this.customer = customer;
			next = null;
		}
		
	}
	
	static CustomerNode[] createCustomerHashList(Customer[] customers){
		CustomerNode[] hashList = new CustomerNode[HASH_LIST_SIZE];
		
		for(Customer c: customers){
			int h = hashCode(c.rfc);
			CustomerNode current = hashList[h];
			CustomerNode newNode = new CustomerNode(c);
			if(current == null)
				hashList[h]= newNode;
			else{
				while(current.next!=null)
					current = current.next;
				
				current.next = newNode;
			}
					
		}			
		
		return hashList;
	}
	
	
	public static void printHashList(CustomerNode[] hashList){
		int i=0;
		for(CustomerNode nd: hashList){
			CustomerNode tmp = nd;
			System.out.println("["+ i++ +"]: ");
			while(tmp!=null){
				System.out.println(tmp.customer.rfc+ " ");
				tmp= tmp.next;
			}
			System.out.println();			
		}
	}
	
	public static Customer searchHashList(CustomerNode[] hashList, String rfc){
		int h = hashCode(rfc);
		CustomerNode current = hashList[h];
		
		while(current!=null){
			if(current.customer.rfc.equals(rfc))
				return current.customer;
			
			current = current.next;
		}	
				
		return null;
	}
	
	
	
	public static void main(String[] args) throws Exception {
		Customer[] customers = readCustomers("Clientes.txt");
		HASH_LIST_SIZE = customers.length;
		
		System.out.println(customers.length);
		
		//System.out.println(hashCode("ZZZZ991231ZZZ"));
		CustomerNode[] hashList = createCustomerHashList(customers);
		printHashList(hashList);
		Customer c = searchHashList(hashList, "BZFA621108KCA");
		if(c!=null)
			System.out.println(c.rfc + " "+ c.name);
		else
			System.out.println("no existe");
		
	}
	
	
	
	
}
