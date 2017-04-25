package dynamicProgramming;

/**
 * Created by jianwang on 4/24/17.
 */
public class MinimumPathSum {

    public int minPathSum(int[][] grid) {
        if(grid==null||grid.length==0||grid[0].length==0) { return 0;}
        int h = grid.length, w = grid[0].length;

        int[][] sum = new int[h][w];
        sum[0][0] = grid[0][0];         // special to [0][0]

        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                if(i!=0||j!=0) {
                    sum[i][j] = Math.min(i==0?Integer.MAX_VALUE:sum[i-1][j],j==0?Integer.MAX_VALUE:sum[i][j-1])+grid[i][j];
                }
            }
        }

        return sum[h-1][w-1];
    }

}
