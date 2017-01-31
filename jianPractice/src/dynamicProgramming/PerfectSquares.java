package dynamicProgramming;

import java.util.Arrays;

/**
 * Created by jianwang on 12/31/16.
 *
 * key is 2nd loop "for(int j=1;j*j<i;j++){"
 *
 */
public class PerfectSquares {


    public int numSquares(int n) {

        if(n<=0) {return 0;}

        // we can use 1s at least
        int[] result = new int[n+1];
        for(int i=0;i<result.length;i++){
            result[i] = i; // use 1
        }

        // knowning i, try i-j*j
        for(int i=1;i<=n;i++){
            for(int j=1;j*j<i;j++){
                result[i] = Math.min(result[i],result[i-j*j]);
            }
        }
        return result[n];
    }
}
