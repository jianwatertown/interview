package bfs;

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

	// Union find simple
	public boolean validTreeSimple(int n, int[][] edges) {
		int[] parent = new int[n];
		for(int i=0;i<n;i++){parent[i] = i;}

		for(int[] edge: edges){
			if(find2(edge[0],parent)==find2(edge[1],parent)) {return false;}
			union2(edge[0],edge[1],parent);
		}
		int firstRoot = find2(0,parent);
		for(int i=0;i<n;i++){if(find2(i,parent)!=firstRoot) {return false;}}
		return true;
	}

	public void union2(int a, int b, int[] parent){
		parent[find2(a,parent)]=parent[find2(b,parent)];
	}

	public int find2(int lookup,int[] parent){
		while(parent[lookup]!=lookup){lookup = parent[lookup];}
		return parent[lookup];
	}

	// ------------------------------------------------------------------
	// Union Find + path compression and mini-weight
	// ------------------------------------------------------------------
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
			if(find(edge[0],edge[1],id)){return false;}
			union(edge[0],edge[1],id,ht);
		}

		Set<Integer> treeRoots = new HashSet<>();
		for(int x:id){
			treeRoots.add(root(id,x));
		}
		return treeRoots.size()==1;
	}

	// union to components, with mini-height
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
		Map<Integer, TreeNode>  treeMap = constructEdgeMap(n,edges);


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
	// ------------------------------------------------------------------
	public Map<Integer, Set<Integer>> constructEdgeMap(int n, int[][] edges){
		Map<Integer, Set<Integer>>  edgeMap = new HashMap<>();
		for(int i=0;i<n;i++) { edgeMap.put(i,new HashSet<>());}

		for(int[] edge:edges){
			int from = edge[0];
			int to = edge[1];
			edgeMap.get(from).add(to);
			edgeMap.get(to).add(from);
		}
		return edgeMap;
	}

	public boolean validTree2(int n, int[][] edges) {

		if(n==0 || edges.length!=n-1){ return false;}

		// 1. construct tree
		Map<Integer, Set<Integer>> edgeMap = constructEdgeMap(n,edges);

		// 2. bfs
		Queue<Integer> q = new LinkedList<>();
		q.add(0);
		Set<Integer> visited = new HashSet<>();
		visited.add(0);

		while(!q.isEmpty()){
			Integer visitedNode = q.poll();

			// for all the next level
			for(Integer child: edgeMap.get(visitedNode)){
				// skip visited
				if(visited.contains(child)){continue;}
				visited.add(child);
				q.add(child);
			}
		}
		return (visited.size()==n);
	}
}
