package bfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jianwang on 2/21/17.
 *
 *  union-find with path compression and rank
 *
 */
public class NumberOfConnectedComponentsInAnUndirectedGraph {

    public int countComponents(int n, int[][] edges) {

        // id and rank
        int[] id = new int[n];
        for(int i=0;i<n;i++){
            id[i] = i;
        }
        int[] rk = new int[n];
        Arrays.fill(rk,1);

        // 1. for each edge try to union
        for(int[] edge: edges){
            // 2. if not connected yet
            if(!find(id,edge[0],edge[1])){
                // 3. connect time
                union(id, edge[0],edge[1],rk);
            }
        }

        // 4. go through the id set, *remember*
        Set<Integer> rootSet = new HashSet<>();
        for(int i=0;i<n;i++){
            rootSet.add(root(id,i));
        }
        return rootSet.size();
    }

    // path compression
    public int root(int[] id, int n){
        // until find the root of the tree
        while(id[n]!=n){
            id[n] = id[id[n]];
            n = id[n];
        }
        return n;
    }

    public boolean find(int[] id, int x, int y){
        return root(id,x) == root(id,y);
    }

    // rank compression
    public void union(int[] id, int x, int y, int[] rk){

        // same root return
        int i = root(id,x);
        int j = root(id,y);
        if(i==j) {return;}

        // attach smaller rank to bigger rank
        int rootWithSmallerRank = rk[i]<rk[j]?i:j;
        id[i] = rootWithSmallerRank; // point to smalller rank, whichever it is
        id[j] = rootWithSmallerRank; // point to smalller rank, whichever it is
        rk[rootWithSmallerRank]++;
    }

    public static void main(String[] args){
        NumberOfConnectedComponentsInAnUndirectedGraph tester = new
                NumberOfConnectedComponentsInAnUndirectedGraph();

        System.out.println(tester.countComponents(5,new int[][]{
                {0,1},{0,2},{2,3},{2,4}
        }));
    }
}
