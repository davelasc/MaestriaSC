
public class Trees {
	
	static class Node {
		public int value, index;
		public Node left = null, right = null;
		
		public Node(int value, int index) {
			this.value = value;
			this.index = index;
		}
	}
	
	public static Node createBinaryTree(int[] array) {
		Node root = new Node(array[0], 0);
		Node current;
		Node newNode;
		
		for(int i = 1; i < array.length; i++) {
			current = root;
			newNode = new Node(array[i], i);
			while(newNode.value != current.value) {
				if(newNode.value < current.value) {
					if(current.left == null) {
						current.left = newNode;
						break;
					} else {
						current = current.left;
					}
				}else {
					if(current.right == null) {
						current.right = newNode;
						break;
					} else {
						current = current.right;
					}
				}
			}
		}
		
		return root;
	}
	
	public static void print(Node n, String spaces) {
		if(n == null) return;
		
		System.out.print(spaces);
		System.out.println(n.value + ", " + n.index);
		
		print(n.left, spaces+" ");
		print(n.right, spaces+" ");
	}
	
	public static void main(String[] args) {
		int[] array = {3,6,2,9,0,11,1};
		Node root = createBinaryTree(array);
		print(root, "");
	}
	
}
