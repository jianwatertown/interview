package graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *  http://wiki.jikexueyuan.com/project/easy-learn-algorithm/dijkstra.html
 *
 *
 *  N*LogN nodes
 */
public class Dijkstra {

    public int distance(int source, int target, int[][] edges, int size) {

        // 0. init
        Set<Integer> toVisit = new HashSet<>();
        for (int i = 1; i <= size; i++) {
            if (i != source) toVisit.add(i);
        }
        // distance from source to target originally is the edge array
        int[] distance = Arrays.copyOf(edges[source], size + 1);

        // Dijkstra core: until empty or the target has been reached
        while (!toVisit.isEmpty()) {

            // 1. find the closest to source from remaining set
            int minIndex = source;
            int minDistance = Integer.MAX_VALUE;

            for (int num : toVisit) {
                if (distance[num] < minDistance) {
                    minIndex = num;
                    minDistance = distance[num];
                }
            }

            // 2. return when found
            if (minIndex == target) {return minDistance;}

            // 3. visit this node and update its out-going edges
            toVisit.remove(minIndex);
            for (int i = 1; i <= size; i++) {
                // going through node *minIndex* is shorter
                if (toVisit.contains(i) &&
                        (edges[minIndex][i]                           // note: not to use
                                < distance[i] - minDistance)) {       // A+B<C to check as A+B might out of bound
                    distance[i] = edges[minIndex][i] + minDistance;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args){

        // edges
        int[][] edges = new int[7][7];
        for(int i=0;i<edges.length;i++){
            for(int j=0;j<edges.length;j++){
                if(i==j){
                    edges[i][j] = 0;
                }
                else{
                    edges[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        edges[1][2] =1;
        edges[1][3] = 12;
        edges[2][3] = 9;
        edges[2][4] = 3;
        edges[3][5] = 5;
        edges[4][3] = 4;
        edges[4][5] = 13;
        edges[4][6] = 15;
        edges[5][6] = 4;

        Dijkstra tester = new Dijkstra();
        System.out.println(tester.distance(1,6,edges,6));
        System.out.println(tester.distance(1,3,edges,6));
    }
}
