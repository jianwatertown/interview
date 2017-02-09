package bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by jianwang on 2/8/17.
 *
 *   // 1. visit all point using BFS
    // 2. every time a point is pull from Q1, add to Q2(to mark as X)
    // 3. if all the connected nodes in Q1 can pass the boundary check, then make all elements in Q2 X,
    // if any of the elements in Q1 is bad, keep visting Q1 then set it to visited
 */
public class SurroundedRegions {

    public void solve(char[][] board) {

        // boundary check
        if(board==null||board.length==0||board[0].length==0){
            return;
        }

        // BFS setting
        boolean[][] visited = new boolean[board.length][board[0].length];
        Queue<Point> graph = new LinkedList<>();
        Queue<Point> q = new LinkedList<>();
        boolean currentGraphHasEdge = false;

        // going through the entire board
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){

                if(!visited[i][j]){
                    // visit the cell
                    q.add(new Point(i,j));
                    visited[i][j] = true;

                    // BFS all the nodes connected to P
                    while(!q.isEmpty()){
                        Point p = q.poll();
                        graph.add(p);
                        currentGraphHasEdge = currentGraphHasEdge&&isEdge(p.x,p.y,board);
                        q.addAll(getNewFriends(p.x,p.y,board,visited));
                    }
                    // re-write graph if needed
                    if(!currentGraphHasEdge){
                        while (!graph.isEmpty()){
                            Point head = graph.poll();
                            board[head.x][head.y] = 'X';
                        }
                    }
                    graph.clear();
                    currentGraphHasEdge = false;
                }
            }
        }



    }

    //////////////////////////////////////////////////////////////////////////////////////////
    public class Point {
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public boolean isEdge(int x, int y, char[][] board){
        return (x==0)||(y==0)||(x==board.length-1)||(y==board[0].length-1);
    }

    public Set<Point> getNewFriends(int x, int y, char[][] board, boolean[][] visited){
        Set<Point> friendsSet = new HashSet<>();
        addNewFriend(x-1,y,board,visited,friendsSet);
        addNewFriend(x+1,y,board,visited,friendsSet);
        addNewFriend(x,y-1,board,visited,friendsSet);
        addNewFriend(x,y+1,board,visited,friendsSet);
        return friendsSet;
    }

    public void addNewFriend(int x, int y, char[][] board,boolean[][] visited, Set<Point> frdSet){
        if((x>=0)&&(x<=board.length-1)&&(y>=0)&&(y<=board[0].length-1)&&(board[x][y]=='O')&&!visited[x][y]){
            visited[x][y]=true;
            frdSet.add(new Point(x,y));
        }
    }
}
