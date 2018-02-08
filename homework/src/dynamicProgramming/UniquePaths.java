package dynamicProgramming;

import java.util.Arrays;

public class UniquePaths {

    public int uniquePaths(int m, int n) {
        int[][] cache = new int[m][n];
        for(int i=0;i<m;i++){
            Arrays.fill(cache[i],-1);
        }
        return uniquePaths(m-1,n-1,cache);
    }

    public int uniquePaths(int m, int n, int [][] cache) {

        // 1. out of bound
        if(m<0||n<0) {return 0;}

        // 2. start
        if(m==0&&n==0) {return 1;}

        // 3. recursion, with cache
        if(cache[m][n]!=-1) {return cache[m][n];}
        int result = uniquePaths(m-1,n,cache) + uniquePaths(m,n-1,cache);
        cache[m][n] = result;
        return result;
    }

    // iterative solution
    public int uniquePaths2(int m, int n) {

        int[][] result = new int[m][n];
        result[0][0] = 1;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(!(i==0&&j==0)){
                    result[i][j] = (i==0? 0:result[i-1][j])+
                            (j==0? 0:result[i][j-1]);
                }
            }
        }
        return result[m-1][n-1];
    }
}
