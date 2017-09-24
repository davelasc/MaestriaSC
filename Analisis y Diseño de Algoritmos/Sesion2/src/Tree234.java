import java.util.LinkedList;

public class Tree234 {
	
	public static class Node234 {
		private LinkedList<Integer> values = new LinkedList<>();
		private LinkedList<Integer> indexes = new LinkedList<>();
		private LinkedList<Node234> children = new LinkedList<>();
		private Node234 parent = null;
		
		public Node234(int value, int index) {
			values.add(value);
			indexes.add(index);
		}
		
		public int getValue(int i) {
			return values.get(i);
		}
		
		public int getIndex(int i) {
			return indexes.get(i);
		}
		
		public Node234 getChild(int i) {
			return children.get(i);
		}
		
		public int getType() {
			return values.size()+1;
		}
		
		public boolean isLeaf() {
			return children.isEmpty();
		}
		
		public int insert(int value, int index) {
			if(getType() == 2) {//Nodo 2 (izq, der)
				if(value < values.get(0)) {
					values.add(0, value);
					indexes.add(0, index);
					return 0;
				} else {
					values.add(value);
					indexes.add(index);
					return 1;
				}
			} else if(getType() == 3) {//Nodeo 3 (izq, mid, der)
				if(value < values.get(0)) {//izq
					values.add(0, value);
					indexes.add(0, index);
					return 0;
				} else if(value < values.get(1)) {//med
					values.add(1, value);
					indexes.add(1, index);
					return 1;
				} else {//der
					values.add(value);
					indexes.add(index);
					return 2;
				}
			}
			
			return -1;
		}
		
		public Node234 getParent() {
			return this.parent;
		}
		
		public void addChild(Node234 child) {
			child.parent = this;
			children.add(child);
		}
		
		public void addChild(Node234 child1, Node234 child2, int index) {
			child1.parent = this;
			child2.parent = this;
			
			children.set(index, child1);
			children.add(index+1, child2);
		}
		
		public String toString() {
			String s= "";
			for(int i = 0; i < values.size(); i++) {
				s+= values.get(i) + " ";
			}
			
			return s;
		}
		
	}
	
	public static Node234 createTree234(int[] array) {
		Node234 root = new Node234(array[0], 0);
		
		for(int i = 1; i < array.length; i++) {
			Node234 current = root;
			boolean currentIsLeaf = false;
			
			while(!currentIsLeaf) {
				if(current.getType() == 4) {//desmenuzarlo
					Node234 left = new Node234(current.getValue(0), current.getIndex(0));
					Node234 right = new Node234(current.getValue(2), current.getIndex(2));
					
					if(!current.isLeaf()) {
						left.addChild(current.getChild(0));
						left.addChild(current.getChild(1));
						right.addChild(current.getChild(2));
						right.addChild(current.getChild(3));
					} else if(current == root) {
						root = new Node234(current.getValue(1), current.getIndex(1));
						root.addChild(left);
						root.addChild(right);
						current = root;
					} else {
						Node234 parent = current.getParent();
						int index = parent.insert(current.getValue(1), current.getIndex(1));
						parent.addChild(left, right, index);
						current = parent;
					}
					
				} else if(current.isLeaf()) {
					current.insert(array[i], i);
					currentIsLeaf = true;
				} else {
					if(current.getType() == 2) {
						if(array[i] < current.getValue(0)) {
							current = current.getChild(0);
						} else {
							current = current.getChild(1);
						}
					} else {
						if(array[i] < current.getValue(0)) {
							current = current.getChild(0);
						} else if (array[i] < current.getValue(1)){
							current = current.getChild(1);
						} else {
							current = current.getChild(2);
						}
					}
				}
			}
		}
		
		return root;
	}
	
	public static void printTree234(Node234 current, String spaces) {
		System.out.println(spaces + current.toString());
		if(current.isLeaf()) return;
		if(current.getType() == 4) System.out.println("nodo 4");
		for(int i = 0; i < current.getType(); i++) {
			printTree234(current.getChild(i), spaces + " ");
		}
	}
	
	private static void testTree234() {
		int array[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		Node234 root = createTree234(array);
		printTree234(root, "");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testTree234();
	}

}
