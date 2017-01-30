package dynamicProgramming;

import java.util.Arrays;

/**
 * Created by jianwang on 1/30/17.
 */
public class ClimbingStairs {

    public int climbStairs(int n) {
        int[] cache = new int[n+1];
        // 1 has 1 way, 2 has 2 ways
        cache[1] = 1;
        cache[2] = 2;
        Arrays.fill(cache,-1);
        return helper(n,cache);
    }

    public int helper(int n, int[] cache){
        if(n<0) {return 0;}
        if(n==0) {return 1;}
        if(cache[n]!=-1) { return cache[n];}
        int steps = helper(n-1,cache) + helper(n-2,cache);
        cache[n] = steps;
        return steps;
    }
}
