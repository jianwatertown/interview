package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Given a binary tree, return the vertical order traversal of its 
 * nodes' values. (ie, from top to bottom, column by column).

	If two nodes are in the same row and column, the order should be from 
	left to right.
 * @author jian.wang
 *
 *
 * 
 */
public class BinaryTreeVerticalOrderTraversal {
	
	public class Pair{
		TreeNode node;
		int c;
		public Pair(TreeNode n, int c) {this.node = n; this.c=c;}
	}	
	
    public List<List<Integer>> verticalOrder(TreeNode root) {
    	
    	// 1. input valid
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	if(root==null) {return result;}

    	// 2. setup for BFS
    	Map<Integer, ArrayList<Integer>> cMap = new HashMap<Integer,ArrayList<Integer>>();
    	Queue<Pair> bfsQ = new LinkedList<Pair>();
    	bfsQ.add(new Pair(root,0));
    	
    	// 3. travel through BFS
    	int min= 0;
    	int max = 0;
    	while(!bfsQ.isEmpty()){

			// 3.1 handle children
    		Pair p = bfsQ.poll();
    		min = Math.min(min,p.c);
			max = Math.max(max,p.c);
			ArrayList<Integer> oneColumn = cMap.getOrDefault(p.c,new ArrayList<>());
			oneColumn.add(p.node.val);
			cMap.put(p.c,oneColumn);

			// 3.2 handle children
			if(p.node.left!=null) {bfsQ.add(new Pair(p.node.left,p.c-1));}
			if(p.node.right!=null) {bfsQ.add(new Pair(p.node.right,p.c+1));}
    	}    	
    	
    	for(int i=min;i<=max;i++){
    		if(cMap.containsKey(i)){
    			result.add(cMap.get(i));
    		}
    	}
    	return result;
    }

    public static void main(String[] args){
    	List<Integer> a = new LinkedList<Integer>();
    	a.add(null);
    	a.add(null);
    	System.out.println(a.size());
    	System.out.println(a.get(0)==null);
    	System.out.println(a.get(1)==null);
    }
}
