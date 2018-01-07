package bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel.
 *
 * The black pixels are connected, i.e., there is only one black region.
 * Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels,
 * return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
 *
 * [
    "0010",
    "0110",
    "0100"
    ]

 * and x = 0, y = 2,
    Return 6.
 *
 * ////////////////////////////////////////////////////////////////////////////////////////
 *
          *
          *
          *
          *
          *
          *
          * Set<Point> set = new HashSet<>();
            set.add(new Point(1,1));
            System.out.println(set.contains(new Point(1,1)));  // false






            OMG!!!OMG!!!OMG!!!OMG!!!OMG!!!   Look up!



 * ////////////////////////////////////////////////////////////////////////////////////////
 * ////////////////////////////////////////////////////////////////////////////////////////
 */
public class SmallestRectangleEnclosingBlackPixels {

    int[] deltaXRange = {-1,0,1};
    int[] deltaYRange = {-1,0,1};

    public int minArea(char[][] image, int x, int y) {

        // 1. init
        if(image==null||image.length==0) {return 0;}
        int up = y;
        int down = y;
        int left = x;
        int right = x;

        // 2. BFS the grid
        int[][] visited = new int[image.length][image[0].length];
        Queue<Point> q = new LinkedList<>();
        Point starting = new Point(x,y);
        q.add(starting);
        visited[x][y]=1;

        while(!q.isEmpty()){
            Point visitedNode = q.poll();
            for(int deltaX:deltaXRange){
                for(int deltaY:deltaYRange){
                    if(deltaX*deltaX==deltaY*deltaY) {continue;}      // no diagnal
                    Point nextToVisit = new Point(visitedNode.x+deltaX,visitedNode.y+deltaY);
                    if(nextToVisit.isUnvisitedBlack(image)&&visited[nextToVisit.x][nextToVisit.y]==0){
                        up = Math.min(up,nextToVisit.y);
                        down = Math.max(down,nextToVisit.y);
                        left = Math.min(left,nextToVisit.x);
                        right = Math.max(right,nextToVisit.x);
                        q.add(nextToVisit);
                        visited[nextToVisit.x][nextToVisit.y]=1;
                    }
                }
            }
        }
        return (right-left+1) * (down-up+1);
    }

    public static class Point{
        int x;
        int y;
        public Point(int a, int b){
            x = a;
            y = b;
        }

        public boolean isUnvisitedBlack(char[][] image){
            int width = image.length;
            int height = image[0].length;
            return (x>=0&&x<=width-1&&y>=0&&y<=height-1&&image[x][y]=='1');
        }
    }

    public static void main(String[] args){

        // 1. this is important to remember
        Set<Point> set = new HashSet<>();
        set.add(new Point(1,1));
        System.out.println(set.contains(new Point(1,1)));

        // 2. this is important too
        SmallestRectangleEnclosingBlackPixels tester = new SmallestRectangleEnclosingBlackPixels();
        char[][] image = new char[][]{
            {'0','0','1','0'},
            {'0','1','1','0'},
            {'0','1','0','0'}
        };
        System.out.println(tester.minArea(image,0,2));
    }
}
