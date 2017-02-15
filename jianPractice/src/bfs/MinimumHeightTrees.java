package bfs;

import java.util.*;

/**
 * Created by jianwang on 2/14/17.
 *
 * Cannot mess up multiple BFS together!!!
 *
 *
 *
 */
public class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        List<Integer> result = new LinkedList<>();
        Map<Integer,Set<Integer>> map = new HashMap<>();
        Map<Integer,Integer> distMap = new HashMap<>();
        Set<Integer> leaves = new HashSet<>();

        // 1. build edge map
        for(int[] edge:edges){
            addEdgeToMap(map,edge[0],edge[1],leaves);
            addEdgeToMap(map,edge[1],edge[0],leaves);
        }

        // 2. set initial distances
        for(int i=0;i<n;i++){
            distMap.put(i,0);
        }

        // 3. bfs all 0 - distance leaves
        int maxDistance = 0;
        for(Integer key: leaves){
            maxDistance = Math.max(maxDistance,bfs(distMap,map,key,leaves));
        }

        // 4. Find the roots
        for(Integer key:distMap.keySet()){
            if(distMap.get(key)==maxDistance){
                result.add(key);
            }
        }
        return result;
    }


    // bfs on per node basis
    public int bfs(Map<Integer,Integer> distMap, Map<Integer,Set<Integer>>map, int start, Set<Integer> leaves){

        int maxDistance = 0;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(start,0));
        Set<Integer> visited = new HashSet<>();
        visited.add(start);

        while(!q.isEmpty()){
            Point p = q.poll();
            // add all friends
            for(Integer c:map.get(p.val)){
                if(!visited.contains(c)&& !leaves.contains(c)) {
                    visited.add(c);
                    Point childNode = new Point(c,p.distance+1);

                    q.add(childNode);

                    // update global max if needed
                    distMap.put(c,Math.max(distMap.get(c),childNode.distance));

                    maxDistance = Math.max(maxDistance,distMap.get(c));
                }
            }
        }

        return maxDistance;
    }

    public void addEdgeToMap(Map<Integer,Set<Integer>>map, int key, int value, Set<Integer> leaves){
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


    public static class Point{
        int val;
        int distance;
        public Point(int v, int distance){
            this.val = v;
            this.distance = distance;
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
