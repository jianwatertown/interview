package dynamicProgramming;

import java.util.Arrays;

/**
 *
 *
 * Given a positive integer n, break it into the sum of at least two positive integers and
 * maximize the product of those integers. Return the maximum product you can get.

     For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

 Note: You may assume that n is not less than 2 and not larger than 58.

 Show Hint

 * Created by jianwang on 12/31/16.
 */
public class IntegerBreak {

    //  for any i, try j:[1...i] X max[i-j]
    public int integerBreak(int n) {

        // max[i] represents the value max given i
        int[] max = new int[n+1];
        Arrays.fill(max,0);
        max[0] = 1;
        max[1] = 1;

        for(int i=0;i<=n;i++){
            // move from right to left
            // either j*(i-j) or j*max[i-j]
            for(int j=i-1;j>=1;j--){
                max[i] = Math.max(max[i],
                        Math.max(j*(i-j),j*max[i-j]));
            }
        }
        return max[n];
    }

    public static void main(String[] args){
        IntegerBreak a = new IntegerBreak();
        System.out.println(a.integerBreak(6));
    }

}

// 2, [0,1,1]
// 3, [0,1,1,2]
// 4, [0,1,1,2,0] -> [0,1,1,2,3]


