package dfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/***
 *
 * Best explaination:
 *      Undigrected Graph: Eula Path:
 *              http://wattlebird.github.io/2015/06/28/%E6%AC%A7%E6%8B%89%E8%B7%AF/
 * and  Directed Graph:
 *              http://www.geeksforgeeks.org/hierholzers-algorithm-directed-graph/
 * reference:
 *      https://en.wikipedia.org/wiki/Eulerian_path
 *
 *
 *
 From JFK we first visit JFK -> A -> C -> D -> A.
 There we're stuck, so we write down A as the end of the route and retreat back to D.
 There we see the unused ticket to B and follow it: D -> B -> C -> JFK -> D. Then we're stuck again,
 retreat and write down the airports while doing so: Write down D before the already written A, then JFK before the D, etc.
 When we're back from our cycle at D, the written route is D -> B -> C -> JFK -> D -> A.

 Then we retreat further along the original path, prepending C, A and finally JFK to the route, ending up with the route JFK -> A -> C -> D -> B -> C -> JFK -> D -> A.

 https://en.wikipedia.org/wiki/Eulerian_path

 http://www.geeksforgeeks.org/hierholzers-algorithm-directed-graph/
 */
public class ReconstructItinerary {

    Map<String, PriorityQueue<String>> targets = new HashMap<>();
    List<String> route = new LinkedList();


    public List<String> findItineraryLeetCode(String[][] tickets) {
        for (String[] ticket : tickets)
            targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
        visit("JFK");
        return route;
    }


    void visit(String airport) {
        // remaining edge to visit
        while(targets.containsKey(airport) && !targets.get(airport).isEmpty())
            visit(targets.get(airport).poll());
        route.add(0, airport);
    }


    /**
     * computeIfAbsent
     */
//     if (map.get(key) == null) {
//        V newValue = mappingFunction.apply(key);
//        if (newValue != null)
//            map.put(key, newValue);
//    }

    // sorted in increasing order, so the shorted path will be visited first
    Map<String, PriorityQueue<String>> map = new HashMap<>();
    List<String> path = new LinkedList<>();

    public List<String> findItinerary(String[][] tickets) {

        // 1. construct the directed graph
        for(String[] ticket:tickets){
            map.computeIfAbsent(ticket[0],k-> new PriorityQueue()).add(ticket[1]);
        }
        dfs("JFK");
        return path;
    }


    public void dfs(String node){
        System.out.println(node);
        System.out.println(map);
        while(map.containsKey(node)         // because the end destination is not in the map
                &&!map.get(node).isEmpty()){
            dfs(map.get(node).poll());      // poll returns null when queue is empty
        }
        path.add(0,node);
    }

    public static void main(String[] args){
        ReconstructItinerary rt = new ReconstructItinerary();
        String[][] path = {
                {"MUC","LHR"},
                {"JFK","MUC"},
                {"SFO","SJC"},
                {"LHR","SFO"},
        };
        rt.findItinerary(path);
    }
}
