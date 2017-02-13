package bfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by jianwang on 2/11/17.
 */
public class PacificAtlanticWaterFlow {
    public List<int[]> pacificAtlantic(int[][] matrix) {

        List<int[]> result = new LinkedList<>();
        if(matrix==null||matrix.length==0||matrix[0].length==0) {return result;}
        int[][] pacific = new int[matrix.length][matrix[0].length];
        int[][] atlantic = new int[matrix.length][matrix[0].length];

        Queue<Point> q = new LinkedList();
        // x [0,matrix.length-1]; y [0,matrix[0].length-1]

        // top row, x==0
        for(int y=0;y<matrix[0].length;y++){
            q.add(new Point(0,y));
            pacific[0][y]=1;
        }
        // left column, y==0
        for(int x=0;x<matrix.length;x++){
            q.add(new Point(x,0));
            pacific[x][0]=1;
        }
        visitGraphBFS(q,matrix,pacific);

        // x==matrix[-1]
        for(int y=0;y<matrix[0].length;y++){
            q.add(new Point(matrix.length-1,y));
            atlantic[matrix.length-1][y]=1;
        }
        // y=matrix[0][-1]
        for(int x=0;x<matrix.length;x++){
            q.add(new Point(x,matrix[0].length-1));
            atlantic[x][matrix[0].length-1]=1;
        }
        visitGraphBFS(q,matrix,atlantic);

        // 2. check results
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(pacific[i][j]==1 && atlantic[i][j]==1){
                    result.add(new int[]{i,j});
                }
            }
        }

        return result;
    }

    public void visitGraphBFS(Queue<Point> q, int[][] matrix, int[][] visited){
        while(!q.isEmpty()){
            Point p = q.poll();
            if(p.x==1&&p.y==3){
                System.out.println();
            }
            addBiggerAndUnvisited(p.x-1,p.y,matrix,matrix[p.x][p.y],visited,q);
            addBiggerAndUnvisited(p.x+1,p.y,matrix,matrix[p.x][p.y],visited,q);
            addBiggerAndUnvisited(p.x,p.y-1,matrix,matrix[p.x][p.y],visited,q);
            addBiggerAndUnvisited(p.x,p.y+1,matrix,matrix[p.x][p.y],visited,q);
        }
    }

    public void addBiggerAndUnvisited(int x, int y, int[][] matrix, int value, int[][] visited, Queue<Point> q){

        if(x>=0 && x< matrix.length && y>=0 && y<matrix[0].length && matrix[x][y]>=value &&visited[x][y]==0) {
            visited[x][y] = 1;
            q.add(new Point(x,y));
        }
    }

    public class Point{
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public void printMetrix(int[][] m){
        for(int i=0;i<m.length;i++){
            for(int j=0;j<m[0].length;j++){
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        PacificAtlanticWaterFlow tester = new PacificAtlanticWaterFlow();
        int[][] input = new int[][]{
                {1,2,2,3,5},
                {3,2,3,4,4},
                {2,4,5,3,1},
                {6,7,1,4,5},
                {5,1,1,2,4}
        };
        List<int[]> result = tester.pacificAtlantic(input);
        for(int i=0;i<result.size();i++){
            System.out.println(result.get(i)[0]+" "+result.get(i)[1]);
        }
    }
}
