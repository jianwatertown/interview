package bfs;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jianwang on 2/14/17.
 *
 *
 *  Question: How to tell if a graph acyclic?
 *  http://www.geeksforgeeks.org/detect-cycle-undirected-graph/
 *  http://www.geeksforgeeks.org/union-find/
 *
 *
 *  Solution:
 *      1.  use "union-find" to detect Acylic Undirected Graph
 *      Idea: for each Edge, use both the Vertices of the edge to make subsets.
 *      If both the Vertices are in the same subset, a cycle is found.
 *
 *      1) make set for each node
 *      2) union: (make 2-level tree at most) then union each edge, if 2 are already in the same set, return false
 *      3) find(flatten tree): find with compression
 *
 *      *REMEMBER*: need to call "find()" in the end to flatten the 1-level trees
 *
 */
public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        int[] id = new int[n];      //  id points to the root of your set

        // 1. make set for each Vertices
        for(int i=0;i<n;i++){
            id[i] = i;
        }

        // 2. for each edge, union the two nodes into 1
        for(int i =0;i<edges.length;i++){
            if(!union(edges[i][0],edges[i][1],id)){
                return false;
            }
        }

        Set<Integer> treeRoots = new HashSet<>();
        for(int x:id){
            treeRoots.add(find(id,x));
        }
        return treeRoots.size()==1;
    }

    // return False.
    //      if the two nodes are already in the same set
    // return True
    //      if otherwise, child -> parent
    public boolean union(int x, int y, int[] id){
        // child(big) -> parent(small)
        int parent = x<y?x:y;
        int child = x<y?y:x;
        if(find(id,parent)==find(id,child)){
            return false;
        }
        else{
            id[child] = parent;
            return true;
        }
    }


    public int find(int[] id, int x){
        // when pointing to parent, not self
        while (id[x]!=x){
            id[x] = id[id[x]];  // point to parent's parent (grandparent)
            x = id[x];          // now compress grandparent
        }
        return id[x];
    }

//    public boolean validTree(int n, int[][] edges) {
//        // initialize n isolated islands
//        int[] nums = new int[n];
//        Arrays.fill(nums, -1);
//
//        // perform union find
//        for (int i = 0; i < edges.length; i++) {
//            int x = find(nums, edges[i][0]);
//            int y = find(nums, edges[i][1]);
//
//            // if two vertices happen to be in the same set
//            // then there's a cycle
//            if (x == y) return false;
//
//            // union
//            nums[x] = y;
//        }
//
//        return edges.length == n - 1;
//    }
//
//    int find(int nums[], int i) {
//        if (nums[i] == -1) return i;
//        return find(nums, nums[i]);
//    }

    // 4
    // [[0,1],[2,3],[1,2]]
    public static void main(String[] args){
        GraphValidTree tester = new GraphValidTree();
        int[][] input = new int[][]{
                {0,1},{2,3},{1,2}
        };
        System.out.println(tester.validTree(4,input));
    }



    public static void printArray(int [] ar){
        for(int i:ar){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
