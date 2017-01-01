package dynamicProgramming;

import java.util.Arrays;

/**
 * Created by jianwang on 12/31/16.
 */
public class PerfectSquares {


    public int numSquares(int n) {

        if(n<=0) {return 0;}

        // result[i] saves the result of numSquares(i)
        int[] result = new int[n+1];
        Arrays.fill(result,Integer.MAX_VALUE);
        result[0] = 0;

        // knowning i, try i-j*j
        for(int i=1;i<=n;i++){
            for(int j=1;j*j<i;j++){
                result[i] = Math.min(result[i],result[i-j*j]);
            }
        }
        return result[n];
    }
}
