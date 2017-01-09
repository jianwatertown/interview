package dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jianwang on 1/7/17.
 */
public class LargestDivisibleSubset {

    public List<Integer> largestDivisibleSubset(int[] nums) {

        List<Integer> result = new ArrayList<>();

        if(nums==null||nums.length==0) {
            return result;
        }


        boolean[][] dp = new boolean[nums.length][nums.length];
        int[]size = new int[nums.length];
        int indexOfMax = 0;
        Arrays.fill(size,0);

        for(int i = 0;i<nums.length;i++){
            for(int j=0;j<nums.length;j++){
                dp[i][j] = ((nums[i] % nums[j]) == 0 );
                if(dp[i][j]){
                    size[i] ++;
                }
            }
            if(size[i]>size[indexOfMax]){
                indexOfMax = i;
            }
        }

        // assemble the list of sum
        for(int i=0;i<nums.length;i++){
            result.add(nums[i]);
        }
        
        return result;
    }

    public static void main(String[] args){
        LargestDivisibleSubset test = new LargestDivisibleSubset();
        System.out.println(test.largestDivisibleSubset(new int[] {1,2,4,8,3}));
    }
}
