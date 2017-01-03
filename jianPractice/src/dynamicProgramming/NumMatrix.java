package dynamicProgramming;

/**
 * Created by jianwang on 1/2/17.
 */
public class NumMatrix {

    int[][] sum;

    public NumMatrix(int[][] matrix) {
        if(matrix==null||matrix.length==0||matrix[0].length==0) {
            return;
        }

        sum = new int[matrix.length][matrix[0].length];
        int[] colSum = new int[matrix.length];
        int[] rowSum = new int[matrix[0].length];


        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                sum[i][j] = rowSum[j] + colSum[i] + matrix[i][j] +
                        (((i-1)>=0 && (j-1)>=0)? /*assessable*/ sum[i-1][j-1]:0);
                colSum[i]+=matrix[i][j];
                rowSum[j]+=matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {

        return sum[row2][col2]/*biggest*/
                + (((col1-1)>=0 &&(row1-1)>=0)?sum[row1-1][col1-1]:0)
                - (((row1-1)>=0)? sum[row1-1][col2]:0)
                - (((col1-1)>=0)? sum[row2][col1-1]:0)
                ;
    }
}
