package dynamicProgramming;

/**
 * Created by jianwang on 4/14/17.
 */
public class MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        // 1. basic
        if(matrix==null||matrix.length==0||matrix[0].length==0){return 0;}
        int row = matrix.length,column = matrix[0].length;

        // 2. reachable 1s from left, reachable 1s from left;
        int[][] left = new int[row+1][column+1];
        int[][] up = new int[row+1][column+1];
        int[][] edge = new int[row+1][column+1];

        int max = 0;

        // 3. N*N DP
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(matrix[i][j]=='1'){
                    // update left and up
                    left[i+1][j+1]=left[i+1][j]+1;
                    up[i+1][j+1]=up[i][j+1]+1;

                    // reachable edge
                    edge[i+1][j+1] = Math.min(Math.min(left[i+1][j+1],up[i+1][j+1]),edge[i][j]+1);
                }
                else {
                    // reset reachable # of '1' to 0
                    left[i+1][j+1] = 0;
                    up[i+1][j+1] = 0;
                }
                max = Math.max(max,edge[i+1][j+1]);
            }
        }
        return max*max;
    }

    public static void main(String[] args){
        MaximalSquare t = new MaximalSquare();
        System.out.println(t.maximalSquare(new char[][]
                {
                        {'1','1'},
                        {'1','1'}
                }));
    }
}
