package graph;

import java.util.Arrays;


/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
 * write a function to check whether these edges make up a valid tree.

    For example:
    
    Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
    
    Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
	
	Check 2 things: 1. whether there is loop 2. whether the number of connected components is 1
	
	https://discuss.leetcode.com/topic/21737/8-10-lines-union-find-dfs-and-bfs
 * @author jian.wang
 *
 */

public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
    	
    	// 1. basic verify the number of edges and points
    	if(n>edges.length) {return false;}			// orphan nodes
    	
    	// 2. create real edge array
    	boolean[][] con = getRealEdge(edges,n);
    		
    	// 3. create start set S, pick 0 and bfs, upon completion, check remaining set size
    	Queue<>
    	
        return true;
    }
    
    
    public boolean[][] getRealEdge(int[][] input, int n){
    	boolean[][] con = new boolean[n][n];
    	for(int i=0;i<n;i++){
    		Arrays.fill(con[i], false);
    	}
    	for(int[] pair:input){
    		int from = pair[0];
    		int to = pair[1];
    		con[from][to] = true;
    		con[to][from] = true;
    	}
    	return con;
    }
    
}
