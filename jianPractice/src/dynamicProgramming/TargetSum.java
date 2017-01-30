package dynamicProgramming;

/**
 * Created by jianwang on 1/29/17.
 */
public class TargetSum {

    public int findTargetSumWays(int[] nums, int S) {
        return findTargetSumWays(nums, S, 0);
    }

    public int findTargetSumWays(int[] nums, int target, int start) {

        // 1. base case, used all elements
        if(start==nums.length){
            return target==0? 1: 0;
        }

        // 2. use num[start] as possitive and negative
        int result = 0;
        result+= findTargetSumWays(nums,target+nums[start],start+1);
        result+= findTargetSumWays(nums,target-nums[start],start+1);
        return result;
    }
}
