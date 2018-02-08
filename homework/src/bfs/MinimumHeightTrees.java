package bfs;

import java.util.*;

/**
 * Created by jianwang on 2/14/17.
 *
 * Cannot mess up multiple BFS together!!!
 *
 * The following works:
 *  leaves.clear();
    leaves.addAll(nextLevelLeaves);
    nextLevelLeaves.clear();
 *
 * But not this!
    leaves = nextLevelLeaves;
    // this will clear leaves too!
    nextLevelLeaves.clear();

 *
 */
public class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        List<Integer> result = new LinkedList<>();
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Set<Integer> leaves = new HashSet<>();
        Set<Integer> nextLevelLeaves = new HashSet<>();

        // 1. build tree as map
        for (int[] edge : edges) {
            buildTreeAsMapAndBuildLeaveSet(map, edge[0], edge[1], leaves);
            buildTreeAsMapAndBuildLeaveSet(map, edge[1], edge[0], leaves);
        }

        // 2. until 2 remaing
        while(map.size()>2){

            for(Integer out:leaves){

                // a. delete out-most layer
                Set<Integer> innodes = map.get(out);
                map.remove(out);

                // b. remove connection from innodes and add to leaves if possible
                for(Integer in:innodes){
                    Set<Integer> inChildren = map.get(in);
                    inChildren.remove(out);
                    if(inChildren.size()==1){
                        nextLevelLeaves.add(in);
                    }
                }
            }

            // go to nex layer
            leaves.clear();
            leaves.addAll(nextLevelLeaves);
            nextLevelLeaves.clear();
        }

        // 3. add to result set
        for(Integer key:map.keySet()){
            result.add(key);
        }

        // 4. stupid edge case
        if(n==1 && edges.length==0){
            result.add(0);
        }

        return result;
    }


    // an edge map is when A <-->B
    //  A.children = [B] also, B.children = [A]
    public void buildTreeAsMapAndBuildLeaveSet(Map<Integer,Set<Integer>>map, int key, int value, Set<Integer> leaves){

        // add if missing
        if(!map.containsKey(key)){
            map.put(key, new HashSet<>());
        }
        map.get(key).add(value);

        // fix the size of leaf set
        if(map.get(key).size()==1){
            leaves.add(key);
        }
        else{
            if(leaves.contains(key)){
                leaves.remove(key);
            }
        }
    }


    public static void main(String[] args){
        MinimumHeightTrees tester = new MinimumHeightTrees();
        int[][] input = new int [][]{
            {0,1},{0,2},{0,3},{3,4},{5,4},
        };
        System.out.println(tester.findMinHeightTrees(6,input)); // expected 3
    }
}
