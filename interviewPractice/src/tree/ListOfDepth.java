package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ListOfDepth {
	
	public List<ArrayList<Node>> getListOfDepth(Node root){
		
		List<ArrayList<Node>> result = new  ArrayList<ArrayList<Node>>();
		
		// two queues to support it
		Queue<Node> leftQ = new LinkedList<Node>();
		Queue<Node> rightQ = new LinkedList<Node>();
		
		leftQ.add(root);
		
		while(!leftQ.isEmpty() || !rightQ.isEmpty()){

			ArrayList<Node> left = new ArrayList<Node>();
			ArrayList<Node> right = new ArrayList<Node>();
			
			addToQueue(leftQ, rightQ, left);
			if(!left.isEmpty()) result.add(left);
			
			addToQueue(rightQ, leftQ, right);
			if(!right.isEmpty()) result.add(right);
		}
		return result;
	}
	
	
	public void addToQueue(Queue<Node> source, Queue<Node> target, ArrayList<Node> result){

		while(!source.isEmpty()){
			Node node = source.poll();
			
			result.add(node);
			if(node.left!=null) target.add(node.left);
			if(node.right!=null) target.add(node.right);
		}
	}
	
	public static void main(String[] args){
		// construct
		BTreePrinter printer = new BTreePrinter();
		Node root = printer.constructATree();
		printer.printNode(root);
		
		ListOfDepth test = new ListOfDepth();
		for(ArrayList<Node> level:test.getListOfDepth(root)){
			System.out.println("------------------------------");
			for(Node n: level){
				System.out.println(n.data+" ");
			}
		}

	}
}
