package graph;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 	Question: Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * 
 * 	Solution:
 * 		1. BFS, copy then put the node(original) into the queue for BFS
 *
 * 	some of the friends is already copied in previous loops, some of the friends were just copied:
 * 	so by now *ALL* friends are copied, we need to set *all* of "node"'s friends
 *
 * 	map.get(node).neighbors.add(map.get(friend));
 *
 * 	Queue contains "the work to do", the original nodes have the relationships so it must be the original tree
 *
 * the key is to make sure when hyou
 *
 * @author jian.wang
 *
 */


public class CloneGraph {

	public UndirectedGraphNode cloneGraph(UndirectedGraphNode root) {

		if(root==null) {return null;}

		// map from original to copy nodes
		Map<UndirectedGraphNode,UndirectedGraphNode> map = new HashMap<>();

		// queue of original nodes - all of those nodes have a copy in the hashmap
		// now, we need to set "their copies" neighbours correctly
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
				// 4. for *all* of its friends, update "copied.neighors" to include this frdCopy
				// map.get(node).neighbors.add(map.get(friend));
				UndirectedGraphNode copyNode = map.get(node);
				UndirectedGraphNode copyFrd = map.get(friend);
				copyNode.neighbors.add(copyFrd);
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
