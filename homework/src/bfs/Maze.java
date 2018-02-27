package bfs;

import java.util.LinkedList;

public class Maze {

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {

        // 0. edge case
        if (start[0]==destination[0] && start[1]==destination[1]) return true;

        // 1. init
        int m=maze.length, n=maze[0].length;
        int[][] dir /*4 directions*/=new int[][] {{-1,0},{0,1},{1,0},{0,-1}};
        boolean[][] visited=new boolean[m][n];

        LinkedList<int[]> queue=new LinkedList<>();
        visited[start[0]][start[1]]=true;
        queue.add(new int[]{start[0],start[1]});

        // 2. BFS
        while (!queue.isEmpty()) {
            int[] p=queue.poll();
            int x=p[0], y=p[1];

            // 3. for each direction
            for (int i=0;i<4;i++) {
                int xx=x, yy=y;

                // 4. go until you *have entered* the wall
                while (xx>=0 && xx<m && yy>=0 && yy<n && maze[xx][yy]==0) {
                    xx+=dir[i][0];
                    yy+=dir[i][1];
                }
                // 5. backup from the wall
                xx-=dir[i][0];
                yy-=dir[i][1];

                // 6. node handling
                if (visited[xx][yy]) continue;
                visited[xx][yy]=true;
                if (xx==destination[0] && yy==destination[1]) return true;
                queue.add(new int[]{xx, yy});
            }
        }
        return false;
    }
}