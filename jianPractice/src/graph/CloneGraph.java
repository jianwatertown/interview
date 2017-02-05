package graph;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 	Question: Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * 
 * 	Solution:	
 * 		1.	create map<sourceNode,targetNode>
 * 		1.	DFS from root, and chain new node at the same time
 * 		2.	get new node from map when possible
 *
 *
 *  The key to this question is to understand, both in BFS and DFS, when you are about to create a copy of node N
 *	by defintion some of N's frineds F1 have been visited, (that's how you come to N in the first place);
 *	some of the friends F2 are not, those are the ones to be copied.
 *
 *  So the idea is, don't worry about the relationship with F2. Only set the relationship with F1 set for the newly,
 *  created node N_COPY.
 *
 *	this strategy will not work for
 *
 *
 * @author jian.wang
 *
 */


public class CloneGraph {

	public UndirectedGraphNode cloneGraph(UndirectedGraphNode root) {

		if(root==null) {return null;}

		// map from original to copy nodes
		Map<UndirectedGraphNode,UndirectedGraphNode> map = new HashMap<>();
		// queue of copied original nodes
		Queue<UndirectedGraphNode> q = new LinkedList<>();
		q.add(root);
		map.put(root,new UndirectedGraphNode(root.label));

		// visit all the friends in the queue
		while(!q.isEmpty()){

			// 1. get node
			UndirectedGraphNode node = q.poll();

			// 2. for each friend
			for (UndirectedGraphNode friend : node.neighbors) {
				// 3. unvisited
				if (!map.containsKey(friend)) {
					q.add(friend);
					UndirectedGraphNode frdCopy = new UndirectedGraphNode(friend.label);
					map.put(friend, frdCopy);
				}
				// 4. update "copied.neighors" to include this frdCopy
				map.get(node).neighbors.add(map.get(friend));
			}
		}
		return map.get(root);
	}

	// ----------------------------------------------------------------------------------------
	
    public UndirectedGraphNode cloneGraphDFS(UndirectedGraphNode node) {
        if(node==null) return null;
    	Map<UndirectedGraphNode,UndirectedGraphNode> map = new HashMap<>();
    	cloneGraph(node, map);
    	return map.get(node);
    }
    
    public void cloneGraph(UndirectedGraphNode node, Map<UndirectedGraphNode,UndirectedGraphNode> map) {

		// 1. copy node
		UndirectedGraphNode nodeCopy = new UndirectedGraphNode(node.label);
		map.put(node, nodeCopy);

		// 2. for each friend
		for (UndirectedGraphNode friend : node.neighbors) {
			// 3. visit unvisited ones
			if (!map.containsKey(friend)) {
				cloneGraph(friend, map);
			}
			// 4. establish relation
			// recursion in 3) made sure all the frend has been created
			nodeCopy.neighbors.add(map.get(friend));
		}
	}


}
