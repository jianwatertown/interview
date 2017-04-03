package bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;


/*
*
*
*       step is the distance from the source node

        Why using PriorityQueue?

        We can consider this question as a shortest-route graph problem,
        that is, each stoppable point is a vertical, where every possible
        path between two nodes is an edge.
        In this way, we can using Dijkstra algorithm to solve this problem,
        and that's why we use PriorityQueue.
*
*
* */


public class MazeTwo {

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {

        // 0. edge case
        if (start[0]==destination[0] && start[1]==destination[1]) return 0;

        // 1. init
        int m=maze.length, n=maze[0].length;
        int[][] dir /*4 directions*/=new int[][] {{-1,0},{0,1},{1,0},{0,-1}};
        int[][] steps=new int[m][n];

        // MAX_VALUE means unaccessable
        for(int i=0;i<m;i++){
            Arrays.fill(steps[i],Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> queue=new PriorityQueue<>((p1,p2)->p1[2]-p2[2]);
        queue.add(new int[]{start[0],start[1],0});

        // 2. BFS
        while (!queue.isEmpty()) {
            int[] p=queue.poll();
            int x=p[0], y=p[1], step=p[2];

            // 3. we already have something better
            if(steps[x][y]<=step)
                continue;
            steps[x][y] = step;

            // 4. for each direction from [x][y]
            for (int i=0;i<4;i++) {
                int xx=x, yy=y;

                // 4. go until you *have entered* the wall
                while (xx>=0 && xx<m && yy>=0 && yy<n && maze[xx][yy]==0) {
                    xx+=dir[i][0];
                    yy+=dir[i][1];
                    step++;
                }
                // 5. backup from the wall
                xx-=dir[i][0];
                yy-=dir[i][1];
                step--;

                queue.add(new int[]{xx,yy,step});
            }
        }
        return steps[destination[0]][destination[1]]==Integer.MAX_VALUE?-1:steps[destination[0]][destination[1]];
    }
}
