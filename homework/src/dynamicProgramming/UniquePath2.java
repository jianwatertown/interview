package dynamicProgramming;

import java.util.Arrays;

/**
 * Follow up for "Unique Paths":

     Now consider if some obstacles are added to the grids. How many unique paths would there be?

     An obstacle and empty space is marked as 1 and 0 respectively in the grid.

     For example,
     There is one obstacle in the middle of a 3x3 grid as illustrated below.

     [
     [0,0,0],
     [0,1,0],
     [0,0,0]
     ]

 */
public class UniquePath2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 1. init
        if(obstacleGrid==null||obstacleGrid.length==0||obstacleGrid[0].length==0){
            return 0;
        }
        int maxY = obstacleGrid[0].length;
        int maxX = obstacleGrid.length;
        int[][] ways = new int[maxX][maxY];
        for(int i=0;i<maxX;i++){
            Arrays.fill(ways[i],0);
        }

        // 2. from left upper corner to right bottom corner
        if(!isIn(0,0,obstacleGrid)){return 0;}
        ways[0][0] = 1;
        for(int i=0;i<=maxX;i++){
            for(int j=0;j<=maxY;j++){
                if(isIn(i,j,obstacleGrid)){
                    if(isIn(i+1,j,obstacleGrid)){
                        ways[i+1][j] +=ways[i][j];
                    }
                    if(isIn(i,j+1,obstacleGrid)){
                        ways[i][j+1] +=ways[i][j];
                    }
                }
            }
        }


        return ways[maxX-1][maxY-1];
    }


    public boolean isIn(int x, int y, int[][] obstacleGrid){
        if(x<0||y<0||x>=obstacleGrid.length||y>=obstacleGrid[0].length){
            return false;
        }
        return obstacleGrid[x][y] == 0;
    }
}
