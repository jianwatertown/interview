package graph;


import java.util.HashMap;
import java.util.Map;

/**
 * 	Question: Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * 
 * 	Solution:	
 * 		1.	create map<sourceNode,targetNode>
 * 		1.	DFS from root, and chain new node at the same time
 * 		2.	get new node from map when possible
 * 
 * @author jian.wang
 *
 */


public class CloneGraph {
	
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node==null) return null;
    	Map<UndirectedGraphNode,UndirectedGraphNode> map = new HashMap<UndirectedGraphNode,UndirectedGraphNode>();
    	cloneGraph(node, map);
    	return map.get(node);
    }
    
    public void cloneGraph(UndirectedGraphNode node, Map<UndirectedGraphNode,UndirectedGraphNode> map){
    	
    	// 1. visit node
    	UndirectedGraphNode nodeCopy = copyNode(node);
    	map.put(node, nodeCopy);
    	
    	// 2. for each friend
    	for(UndirectedGraphNode friend: node.neighbors){
    		// 3. visit unvisited ones
    		if(!map.containsKey(friend)){
    			cloneGraph(friend, map);
    		}
    		// 4. establish relation
    		nodeCopy.neighbors.add(map.get(friend));
    	}
    }
    
    public UndirectedGraphNode copyNode(UndirectedGraphNode node){
		UndirectedGraphNode copiedNode = new UndirectedGraphNode(node.label);
		return copiedNode;
	}
}
