package graph;

import graph.NumberOfComponentsInGraph.CompressionFind;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
 * write a function to check whether these edges make up a valid tree.

    For example:
    
    Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
    
    Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
	
	Check 2 things: 1. whether there is loop 2. whether the number of connected components is 1
	
	https://discuss.leetcode.com/topic/21737/8-10-lines-union-find-dfs-and-bfs
	https://discuss.leetcode.com/topic/35515/share-my-25-line-dfs-20-line-bfs-and-clean-union-find-java-solutions/2
	https://discuss.leetcode.com/topic/49737/2ms-o-n-unionfind-solution-with-weighted-union-and-path-compression

	Solution 1: Union-find
	Solution 2: DFS
	Solution 3: BFS

 * @author jian.wang
 *
 */

public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
    	
    	// 1. basic verify the number of edges and points
    	if(n>edges.length+1) {return false;}					// orphan nodes

    	// 2. create id and sz for each element
    	int[] id = new int[n];
    	int[] sz = new int[n];
    	for(int i=0;i<n;i++){
    		id[i] = i;
    	}
    	Arrays.fill(sz, 1);		// tree at each node has size of 1
    	
    	
    	// 3. go through edges
        for(int[] edge:edges){
        	// 4. check if there is any 
        	if(find(edge[0],edge[1],id)) {
        		return false;
        	}
        	union(edge[0], edge[1], id,sz);
        }
        
        // 3. count distinct root, to see the number of components
        Set<Integer> componentRoot = new HashSet<>();
        for(int i=0;i<n;i++){
        	componentRoot.add(root(i,id));
        }
        return componentRoot.size()==1;
    }


	
	// used by find
	// need to go through log(n) element for finding a root
	public int root(int i, int[] id){
		// id[i] is not i itself, then move up
		// my parent is not myself, then my parent is my parent's parent
		while (id[i]!=i){
			id[i] = id[id[i]];					// <--- the only different line
			i=id[i];
		}
		return i;
	}
	
	public boolean find(int p, int q, int[] id){
		return root(p,id) == root(q,id);
	}
	
	// union the root of p and q
	// need to go through log(n) element for a merge
	public void union(int p, int q, int[] id, int[] sz){
		int i = root(p,id);
		int j = root(q,id);
		id[i] = j;
		
		// i longer, point j to it
		if(sz[i]>sz[j]){
    		id[j] = i;
		}
		else if (sz[i]<sz[j]){
    		id[i] = j;
		}
		else{
    		id[i] = j;
    		sz[j] = sz[j]+1;
		}
	}    
	
	
	
	// ------------------------------------------------------------------ 
}
