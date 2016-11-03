package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class RouteBetweenNodes {

	public class Solution {
	   /**
	     * @param graph: A list of Directed graph node
	     * @param s: the starting Directed graph node
	     * @param t: the terminal Directed graph node
	     * @return: a boolean value
	     */
	    public boolean hasRoute(ArrayList<DirectedGraphNode> graph, 
	                            DirectedGraphNode s, DirectedGraphNode t) {
	    	
	    	if(s==null||t==null) {return false;}

	    	Queue<DirectedGraphNode> q = new LinkedList<DirectedGraphNode>();
	    	q.add(s);
	    	Set<DirectedGraphNode> visited = new HashSet<DirectedGraphNode>();
	    	
	    	while(!q.isEmpty()){
	    		DirectedGraphNode r = q.poll();
	    		if(r==t) {return true;}
	    		for(DirectedGraphNode n:r.neighbors){
	    			if(!visited.contains(n)){
	    				visited.add(n);
	    				q.add(n);
	    			}
	    		}
	    	}	    	
	    	return false;
	    }
	}
}
