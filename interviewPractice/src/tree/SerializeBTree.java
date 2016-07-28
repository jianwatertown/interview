package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SerializeBTree {
	
	public String serializeTree(Node root){
		return printTree(root);
	}
	
	public String printTree(Node root){
		Queue<Node> q = new LinkedList<Node>();
		if(root!=null){
			q.add(root);
		}
		String result = "";
		while(!q.isEmpty()){
			Node visiting = q.poll();
			result+=visiting.data;
			if(visiting.left!=null){
				q.add(visiting.left);				
			}
			if(visiting.right!=null){
				q.add(visiting.right);
			}
		}
		return result;
	}
	
	public Node deSerializedTree(String input){
	
		if(input.length()==0){
			return null;
		}
		
		List<Node> listNode = getNodesFromString(input);
		
		int childIndex =1;
		
		Queue<Node> headQ = new LinkedList<Node>();
		headQ.add(listNode.get(0));
		
		while(childIndex<listNode.size()&&headQ.size()!=0){
			Node currentHead = headQ.poll();
			Node leftChild = listNode.get(childIndex);
			currentHead.left = leftChild;
			headQ.add(leftChild);
			if(childIndex+1<listNode.size()){
				Node rightChild = listNode.get(childIndex+1);
				currentHead.right = rightChild;
				headQ.add(rightChild);
			}
			childIndex+=2;
		}
		return listNode.get(0);
	}
	
	public List<Node> getNodesFromString(String input){

		List<Node> listNode = new ArrayList<Node>();
		char[] nodesArray = input.toCharArray();
		
		for(char c:nodesArray){
			listNode.add(new Node(c));
		}
		return listNode;
	}
	
	public static Node constructATree(){
		Node<String> a = new Node("a");
		Node<String> b = new Node("b");
		Node<String> c = new Node("c");
		Node<String> d = new Node("d");
		Node<String> e = new Node("e");
		Node<String> f = new Node("f");
		Node<String> g = new Node("g");
		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		c.left = f;
		c.right = g;
		return a;
	}

	public static void main(String[] args){

		// construct
		BTreePrinter printer = new BTreePrinter();
		Node root = constructATree();
		printer.printNode(root);
		
		// end-to-end
		SerializeBTree test = new SerializeBTree();
		String str = test.serializeTree(root);
		System.out.println(str);
		Node newRoot = test.deSerializedTree(str);
		printer.printNode(newRoot);
		
	}
}
