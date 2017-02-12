package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by jianwang on 2/10/17.
 *
 *
 * Just BFS each element and no optimizations
 *
 */
public class WallsAndGates {
    public void wallsAndGates(int[][] rooms) {

        // 1. for each element cell
        for(int i=0;i<rooms.length;i++){
            for(int j=0;j<rooms[0].length;j++){
                // 2. bfs the connected node if it's not an obstacle
                if(rooms[i][j]==0){
                    Queue<Point> q = new LinkedList<>();
                    q.add(new Point(i,j,0));
                    while(!q.isEmpty()){
                        Point head = q.poll();
                        // 3.visit friends
                        addPointToQueueIfInRange(head.x-1,head.y,head.d+1,q,rooms);
                        addPointToQueueIfInRange(head.x+1,head.y,head.d+1,q,rooms);
                        addPointToQueueIfInRange(head.x,head.y-1,head.d+1,q,rooms);
                        addPointToQueueIfInRange(head.x,head.y+1,head.d+1,q,rooms);
                    }
                }
            }
        }
    }

    // do no modification on the cell if existing value is smaller than new value
    public void addPointToQueueIfInRange(int x, int y, int newD, Queue q, int[][] rooms){
        if(x>=0&&x<rooms.length&&y>=0&&y<rooms[0].length&&rooms[x][y]>newD){
            rooms[x][y] = newD;
            q.add(new Point(x,y,newD));
        }
    }

    public class Point{
        int x;
        int y;
        int d;
        public Point(int x, int y, int distance){
            this.x = x;
            this.y = y;
            this.d = distance;
        }
    }

    //
    public void wallsAndGatesDFS(int[][] rooms) {
        for(int i=0;i<rooms.length;i++){
            for(int j=0;j<rooms[0].length;j++){
                if(rooms[i][j]==0){
                    dfs(rooms,i-1,j,1);
                    dfs(rooms,i+1,j, 1);
                    dfs(rooms,i,j-1, 1);
                    dfs(rooms,i,j+1, 1);
                }
            }
        }
    }

    private void dfs(int[][] rooms, int x, int y, int d){
        if(x>=0&&x<rooms.length&&y>=0&&y<rooms[0].length&&rooms[x][y]>d){
            rooms[x][y] = d;
            dfs(rooms,x-1,y,d+1);
            dfs(rooms,x+1,y,d+1);
            dfs(rooms,x,y-1,d+1);
            dfs(rooms,x,y+1,d+1);
        }
    }
}
