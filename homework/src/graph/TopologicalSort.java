package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class TopologicalSort {
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {

        ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();
        HashMap<DirectedGraphNode, Integer> dependsCountMap = new HashMap();
        Set<DirectedGraphNode> from = new HashSet<>();
        Set<DirectedGraphNode> to = new HashSet<>();

        // 1. count dependency number, set from/to sets
        for(DirectedGraphNode node: graph){
            if(!to.contains(node)) {from.add(node);}

            for(DirectedGraphNode child:node.neighbors){
                if (dependsCountMap.containsKey(child)) {
                    dependsCountMap.put(child, dependsCountMap.get(child) + 1);
                } else {
                    dependsCountMap.put(child, 1);
                }
                if(from.contains(child)) {from.remove(child);}
                to.add(child);
            }
        }

        // 2. find the root
        if(from.size()!=1) {return null;}
        DirectedGraphNode root = from.iterator().next();
        result.add(root);

        // 3. BFS
        Queue<DirectedGraphNode> noInDegreeQ = new LinkedList<>();
        noInDegreeQ.add(root);

        while(!noInDegreeQ.isEmpty()){
            DirectedGraphNode node = noInDegreeQ.poll();
            // 4. visit out going edge
            for(DirectedGraphNode child:node.neighbors){
                dependsCountMap.put(child,dependsCountMap.get(child)-1);
                // 5. find a another good node
                if(dependsCountMap.get(child)==0){
                    noInDegreeQ.add(child);
                }
            }
        }
        return result;
    }
}
