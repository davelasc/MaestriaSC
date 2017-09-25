package o2017;

public class Trees {
	
	static class Node{
		public int value, index;
		public Node left=null, right=null;
		
		public Node(int value, int index){
			this.value = value;
			this.index = index;
		}
	
	}
	
	public static Node createBinaryTree(int[] array){
		Node root = new Node(array[0],0);
		
		for(int i=1; i<array.length; i++){
			Node current = root;
			Node newNode = new Node(array[i],i);
			while(newNode.value!=current.value){
				if(newNode.value< current.value){
					if(current.left==null){
						current.left = newNode;
						break;
					}else{
						current = current.left;
					}
					
				}else{
					if(current.right ==null){
						current.right = newNode;
						break;
					}else{
						current = current.right;
					}
					
				}
				
			}

			
		}

		
		return root;
		
	}
	
	
	public static int binaryTreeSearchIte(Node root, int value){
		Node current = root;
		
		while(current!=null){
			
			if(current.value==value)
				return current.index;
			
			if(value<current.value)
				current = current.left;
			else
				current = current.right;
	    }
	
		return -1;
	}
	
	public static int binaryTreeSearch(Node root, int value){
		if(root==null)
			return -1;
		if(root.value==value)
			return root.index;
		
		if(value<root.value)
			return binaryTreeSearch(root.left, value);
		else
			return binaryTreeSearch(root.right, value);

	}
	
	
	
	
	
	
	public static void print(Node n, String spaces){
		if(n==null) return;
		
		System.out.print(spaces);
		
		System.out.println(n.value  + ", "+n.index); 
		//System.out.print(n.value  + ", ");//PREORDEN
		print(n.left,spaces+" ");
		//System.out.print(n.value  + ", "); //INORDEN
		print(n.right, spaces+" ");
		//System.out.print(n.value  + ", "); //POST-ORDEN
		
	}
	
	
	
	public static void main(String[] args) {
		
		int[] array = {10,8,15,6,9,7,14,18,16};
		Node root = createBinaryTree(array);
		print(root,"");
		System.out.println();
		System.out.println("pos :"+binaryTreeSearch(root, 10));
		
	}
	
	
}
