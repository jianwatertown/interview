package bfs;


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

 *
 *  Union-Find Solution:
 *      1.  use "union-find" to detect Acylic Undirected Graph
 *      Idea: for each Edge, use both the Vertices of the edge to make subsets.
 *      If both the Vertices are in the same subset, a cycle is found.
 *
 *      1) make set for each node
 *      2) union: join two subtree (verified previously that they are not connected), with rank
 *      3) find: check the root for 2 nodes are the same
 *      4) root: has path compression
 *
 *      *REMEMBER*: need to call "find()" in the end to flatten the 1-level trees

 * @author jian.wang
 *
 */

public class GraphValidTree {

	// ------------------------------------------------------------------
	// DFS
	// 			Notices
	// 				1) DFS needs to pass the "parent node" down
	// 				2) be careful of non-connected components
	//

	public boolean validTreeUnionFind(int n, int[][] edges) {
		int[] id = new int[n];      //  id points to the root of your set
		int[] ht = new int[n];      //  height for each component

		// 1. make set for each Vertices
		for(int i=0;i<n;i++){
			id[i] = i;
			ht[i] = 1;
		}

		// 3. for each edge, union the two nodes into 1
		for(int[] edge: edges){
			// 3. if two components were connected then fail
			if(find(edge[0],edge[1],id)){
				return false;
			}
			union(edge[0],edge[1],id,ht);
		}

		Set<Integer> treeRoots = new HashSet<>();
		for(int x:id){
			treeRoots.add(root(id,x));
		}
		return treeRoots.size()==1;
	}

	// union to compoenets, with mini-height
	public void union(int x, int y, int[] id, int[] ht){

		// get the roots
		int i = root(id,x);
		int j = root(id,y);

		// merge longer to shorter
		int commontRoot = ht[i]<=ht[j]?i:j;
		id[i] = commontRoot;
		id[j] = commontRoot;
		ht[commontRoot] ++;
	}

	// check if two subtrees at the same
	public boolean find(int x, int y, int[] id){
		return root(id,x) == root(id,y);
	}

	// with path compression
	public int root(int[] id, int x){
		// when pointing to parent, not self
		while (id[x]!=x){
			id[x] = id[id[x]];  // point to parent's parent (grandparent)
			x = id[x];          // now compress grandparent
		}
		return id[x];
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
