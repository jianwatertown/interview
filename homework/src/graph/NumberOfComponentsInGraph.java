package graph;

import java.util.HashSet;
import java.util.Set;

/***
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
 * write a function to find the number of connected components in an undirected graph.

Example 1:
     0          3
     |          |
     1 --- 2    4
Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

Example 2:
     0           4
     |           |
     1 --- 2 --- 3
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, 
[0, 1] is the same as [1, 0] and thus will not appear together in edges.


	Solution: 	https://www.cs.princeton.edu/~rs/AlgsDS07/01UnionFind.pdf
	Algorithm: 	Weighted Quick-Union with Path Compression


 * @author jian.wang
 * 
 */
public class NumberOfComponentsInGraph {
    public int countComponents(int n, int[][] edges) {

    	// 1. make set 
    	CompressionFind quickFind = new CompressionFind(n);
        
        // 2. go through the edges and make unions
        for(int[] edge:edges){						 // edge *  log(n) = mLog(N)
        	quickFind.union(edge[0], edge[1]);
        }
        
        // 3. count distinct root
        Set<Integer> componentRoot = new HashSet<>();
        for(int i=0;i<n;i++){
        	componentRoot.add(quickFind.root(i));
        }
        
        return componentRoot.size();
    }
    
    public class CompressionFind{
    	
    	public int[] id;
    	public int[] treeSizeRootAt;
    	
    	public CompressionFind(int n){
    		id = new int[n];
    		treeSizeRootAt = new int[n];
    		for(int i=0;i<n;i++){
    			id[i] = i;
    			treeSizeRootAt[i] = 1;
    		}
    	}
    	
    	// used by find
    	// need to go through log(n) element for finding a root
    	public int root(int i){
			// id[i] is not i itself, then move up
    		// my parent is not myself, then my parent is my parent's parent
    		while (id[i]!=i){
    			id[i] = id[id[i]];					// <--- the only different line
    			i=id[i];
    		}
    		return i;
    	}
    	
    	public boolean find(int p, int q){
    		return root(p) == root(q);
    	}
    	
    	// union the root of p and q
    	// need to go through log(n) element for a merge
    	public void union(int p, int q){
    		int i = root(p);
    		int j = root(q);
    		
    		// i longer, point j to it
    		if(treeSizeRootAt[i]>treeSizeRootAt[j]){
        		id[j] = i;
    		}
    		else if (treeSizeRootAt[i]<treeSizeRootAt[j]){
        		id[i] = j;
    		}
    		else{
        		id[i] = j;
        		treeSizeRootAt[j] = treeSizeRootAt[j]+1;
    		}
    	}
    }
    
    // *Only* optimzied for Find
	// O(MN) 
	// 		M union commands
	// 		N objects
    public class QuickFind {
    	
    	public int[] id;
    	
    	// make set
    	public QuickFind(int n){
    		id = new int[n];
    		for(int i=0;i<n;i++){
    			id[i] = i;
    		}
    	}
    	
    	// find
    	public boolean find(int p, int q){
    		return id[p] == id[q];
    	}
    	
    	// union, all pointing to p's root, now pointing to q's root
    	// 2 level flat, but needs to go through M element for 1 union
    	public void union (int p, int q){
    		
    		int pid = id[p];
    		for(int i=0;i<id.length;i++){
    			if (id[i] == pid){
    				id[i] = id[q];
    			}
    		}
    	}
    }
    
    // *Only* optimized for Union
    public class QuickUnion{
    	
    	public int[] id;
    	
    	public QuickUnion(int n){
    		id = new int[n];
    		for(int i=0;i<n;i++){
    			id[i] = i;
    		}
    	}
    	
    	// used by find
    	// need to go through log(n) element for finding a root
    	public int root(int i){
    		while ( i!=id[i]) i=id[i];
    		return i;
    	}
    	
    	public boolean find(int p, int q){
    		return root(p) == root(q);
    	}
    	
    	// union the root of p and q
    	// need to go through log(n) element for a merge
    	public void union(int p, int q){
    		int i = root(p);
    		int j = root(q);
    		id[i] = j;
    	}
    }
}
