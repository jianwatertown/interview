package tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeTraversal {

	// DFS
	public void inOrder(Node root){
		if(root==null) return;
		inOrder(root.left);
		visit(root);
		inOrder(root.right);
	}
	
	public void postOrder(Node root){
		if(root==null) return;
		postOrder(root.left);
		postOrder(root.right);
		visit(root);
	}
	
	// Preorder traversal is used to create a copy of the tree
	public void preOrder(Node root){
		if(root==null) return;
		visit(root);
		preOrder(root.left);
		preOrder(root.right);
	}	
	
	// BFS
	// note: only to enqueue when it's not null
	public void breathFirstSearch(Node root){
		Queue<Node> q = new LinkedList<Node>();
		if(root!=null) {q.add(root);}
		while(!q.isEmpty()){
			Node n = q.poll();
			visit(n);
			if(n.left!=null) q.add(n.left);
			if(n.right!=null) q.add(n.right);
		}
	}	
	
	public void visit(Node root){
		System.out.print(root.data+" ");
	}
	
	public static void main(String[] args){

		// construct
		BTreePrinter printer = new BTreePrinter();
		Node root = printer.constructATree();
		printer.printNode(root);
		
		// DFS
		TreeTraversal tra = new TreeTraversal();
		System.out.println("[in order]");
		tra.inOrder(root);
		System.out.println();
		
		System.out.println("[pre order]");
		tra.preOrder(root);
		System.out.println();
		
		System.out.println("[post order]");
		tra.postOrder(root);
		System.out.println();
		
		// BFS
		System.out.println("[bfs]");
		tra.breathFirstSearch(root);
		System.out.println();
	}
}