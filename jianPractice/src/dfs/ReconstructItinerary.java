package dfs;

import java.util.List;

/***
 * Example 1:
     tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
     Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
     Example 2:
     tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
     Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
     Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.

 */
public class ReconstructItinerary {

    public List<String> findItinerary(String[][] tickets) {

        // 1. construct a bunch of nodes put them in hashmap, and fix their children list

        // 2. starting from JFK DFS and construct paths

        // 3. pick the shortest path
        return null;
    }


//    public void dfs()

    public class Node{
        String value;
        List<Node> children;
        public Node(String value,List<Node> nodes){
            this.value = value;
            this.children = nodes;
        }
    }

}
