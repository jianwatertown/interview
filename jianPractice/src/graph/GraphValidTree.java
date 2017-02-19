package graph;


import bfs.TreeNode;

import java.util.*;


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
	// DFS
	// 			Notices
	// 				1) DFS needs to pass the "parent node" down
	// 				2) be careful of non-connected components
	//
	//
	// ------------------------------------------------------------------
	public boolean validTreeDFS(int n, int[][] edges) {

		// 1. construct tree
		Map<Integer, TreeNode>  treeMap = constructTreeMap(n,edges);


		// 2. edge cases
		if(edges.length==0){
			return n==1;
		}

		// 3. dfs
		Set<TreeNode> visited = new HashSet<>();
		return dfsKnowingParent(visited,treeMap.get(edges[0][0]),null)&& (visited.size()==n);
	}


	// dfs and know it's parent
	public boolean dfsKnowingParent(Set<TreeNode> visited, TreeNode root, TreeNode parent){

		if(visited.contains(root)){
			return false;
		}
		else{
			visited.add(root);
			boolean childNotVisited = true;
			for(TreeNode child:root.children){
				if(child!=parent){
					childNotVisited = childNotVisited && dfsKnowingParent(visited,child,root);
				}
			}
			return childNotVisited;
		}
	}

	public Map<Integer, TreeNode> constructTreeMap(int n, int[][] edges){
		Map<Integer, TreeNode>  treeMap = new HashMap<>();
		for(int[] edge:edges){
			TreeNode from = getNodeInMap(treeMap,edge[0]);
			TreeNode to = getNodeInMap(treeMap,edge[1]);
			from.children.add(to);
			to.children.add(from);
		}
		return treeMap;
	}

	public TreeNode getNodeInMap(Map<Integer,TreeNode> map, int value){
		if(!map.containsKey(value)){
			TreeNode n = new TreeNode(value,new HashSet<>());
			map.put(value,n);
		}
		return map.get(value);
	}

	public static class TreeNode{
		public int value;
		public Set<TreeNode> children = new HashSet<>();
		public TreeNode(int v, Set<TreeNode> children){
			this.children = children;
			this.value = v;
		}
	}

	// ------------------------------------------------------------------
	// 	BFS
	//		1. knowing the parent
	//		2. always put into visited before enqueuing
	// ------------------------------------------------------------------
	public boolean validTreeBFS(int n, int[][] edges) {

		// 1. construct tree
		Map<Integer, TreeNode>  treeMap = constructTreeMap(n,edges);
		Map<TreeNode,TreeNode> parentMap = new HashMap<>();

		// 2. edge cases
		if(edges.length==0){
			return n==1;
		}

		// 3. bfs
		Queue<TreeNode> q = new LinkedList<>();
		q.add(treeMap.get(edges[0][0]));
		parentMap.put(treeMap.get(edges[0][0]),null /*fake tree head*/);
		Set<TreeNode> visited = new HashSet<>();
		visited.add(treeMap.get(edges[0][0]));

		while(!q.isEmpty()){
			TreeNode parent = q.poll();

			// for all the next nevel
			for(TreeNode child: parent.children){

				// make sure "child -> parent -> child" is not used
				if(parentMap.get(parent)!=child){
					// cycle
					if(visited.contains(child)){
						return false;
					}
					else{
						visited.add(child);
						q.add(child);
						parentMap.put(child,parent);
					}
				}
			}
		}
		return (visited.size()==n);
	}

}
