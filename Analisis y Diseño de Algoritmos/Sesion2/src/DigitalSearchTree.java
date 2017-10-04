
public class DigitalSearchTree {
	
	
	static int getBit(int value, int bitIndex){
		return (value>>bitIndex) & 0x01;
	}
	
	static class NodeDT{
		int value, index;
		NodeDT left = null;
		NodeDT right = null;
		
		public NodeDT(int value, int index){
			this.value =value;
			this.index =index;
		}
		
	}
	
	
	static NodeDT createDT(int[] array){
		NodeDT root= new NodeDT(array[0],0);
		for(int i=1; i< array.length; i++){
			NodeDT current = root;
			NodeDT newNode = new NodeDT(array[i],i);
			int bitIndex =0;
			while(true){
				if(current.value == newNode.value) //no incluir valores repetidos
					break;
				
				int currentBit = getBit(array[i],bitIndex);
				
				if(currentBit==0){
					if(current.left ==null){
						current.left = newNode;
						break;
					}else{
						current = current.left;
					}
				}else{ // bit ==1
					if(current.right ==null){
						current.right = newNode;
						break;
					}else{
						current = current.right;
					}
					
				}
				bitIndex++;
			}
		
		}
		
		return root;		
	}
	
	public static void printDT(NodeDT root, String spaces){
		if(root ==null){
			System.out.println(spaces + "-");
			return;
		}
		
		System.out.println(spaces + root.value);
		
		printDT(root.left, spaces+" ");
		printDT(root.right, spaces+ " ");
	}

	
	public static int searchDT(NodeDT root, int value, int bitIndex){
		
		if(root ==null)
			return -1;
		
		if(root.value ==value)
			return root.index;
		
		if(getBit(value,bitIndex)==0)//buscar por el lado izquierdo
			return searchDT(root.left, value, bitIndex+1);
		else //lado derecho
			return searchDT(root.right, value, bitIndex+1);
		
	}
	
	
	private static void testDT(){
		int[] array={0,1,2,23,3,4,5,6,7,8,9,10};
		NodeDT root = createDT(array);
		printDT(root, "");
		
		System.out.println( "pos "+ searchDT(root,23, 0));
		
	}
	
	public static void main(String[] args) {
		testDT();
	}
	
	
	
}

