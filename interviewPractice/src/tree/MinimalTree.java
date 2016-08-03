package tree;


// given a sorted array with unique integer elements, find create the tree with minimal height
public class MinimalTree {
	
	public Node getMinimalTree(int[] sq){
		return getMinimalTree(sq, 0, sq.length-1);
	}
	
	public Node getMinimalTree(int[] sq, int s, int e){

		if(s>e || s<0 || e>=sq.length) {return null;}
		
		int mid = (s+e)/2;
		Node root = new Node(sq[mid]);
		
		root.left = getMinimalTree(sq,s,mid-1);
		root.right = getMinimalTree(sq,mid+1,e);
		return root;
	}
	
	public static void main(String[] args){

		// construct
		BTreePrinter printer = new BTreePrinter();
		int[] nums = {1,2,3,4,5,6,7,8,9,10};
		
		Node root = new MinimalTree().getMinimalTree(nums);
		printer.printNode(root);
	}
	
}
