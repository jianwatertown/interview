package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/***
 * Good work on this question.
 * 
 * Pre-order traversal
 * 		1.	r = stack.pop()
 * 		2.	if(r!=null) stack.add(r.right,r.left)
 * 		3.	output+=r.data
 * 
 * Reverse it
 * 		1.	r = output.removeFirst()
 * 		2.	if(r!=null && stack.size>=0) r.left,r.right = stack.pop2()
 * 		3.	stack.add(r)
 * 
 * @author jian.wang
 */
public class SerializeBTreePreOrder {
	
	public String serializeTree(Node root){
		return printTreePreOrder(root);
	}
	
	public String printTreePreOrder(Node root){
		
		Stack<Node> stack = new Stack<Node>();
		stack.add(root);
		StringBuffer sb = new StringBuffer();
		
		while(stack.size()!=0){
			Node node = stack.pop();
			if(node==null){
				sb.append("#");
			}
			else{
				sb.append(node.data);
				stack.add(node.right);
				stack.add(node.left);
			}
		}
		return sb.toString();
	}
	
	
	public Node deSerializedTree(String input){
	
		// remember from the right to the left
		List<Node> listNode = getNodesFromString(input);
		Stack<Node> stack = new Stack<Node>();
		
		for(int i=listNode.size()-1;i>=0;i--){

			// 1. get one node
			Node current = listNode.get(i);
			
			// 2. valid node and queue has enough node
			char c = (Character) current.data;
			if('#'!=c && stack.size()>=2){
				current.left = stack.pop();
				current.right = stack.pop();
			}
			
			// 3. put current into the stack
			stack.add(current);
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
	

	public static void main(String[] args){

		// construct
		BTreePrinter printer = new BTreePrinter();
		Node root = printer.constructATree();
		printer.printNode(root);
		
		// end-to-end
		SerializeBTreePreOrder test = new SerializeBTreePreOrder();
		String str = test.serializeTree(root);
		System.out.println(str);
		Node newRoot = test.deSerializedTree(str);
		printer.printNode(newRoot);
		
	}
}
