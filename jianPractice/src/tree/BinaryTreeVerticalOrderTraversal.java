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
    	int left= 0;
    	int right = 0;
    	while(!bfsQ.isEmpty()){
    		Pair p = bfsQ.poll();
    		if(p==null||p.node==null) {continue;}
    		
        	if(!cMap.containsKey(p.c)){
        		ArrayList<Integer> oneColumn = new ArrayList<Integer>();
        		oneColumn.add(p.node.val);
        		cMap.put(p.c, oneColumn);
        	}
        	else{
        		ArrayList<Integer> oneColumn = cMap.get(p.c);
        		oneColumn.add(p.node.val);
        	}
        	bfsQ.add(new Pair(p.node.left,p.c-1));
        	bfsQ.add(new Pair(p.node.right,p.c+1));
        	left = Math.min(left, p.c-1);
        	right = Math.max(right, p.c+1);
    	}    	
    	
    	for(int i=left;i<=right;i++){
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
