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


    public int findTargetSumWays2(int[] nums, int s) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        return sum < s || (s + sum) % 2 > 0 ? 0 : subsetSum(nums, (s + sum) >>> 1);
    }


    // notice the subset problem here it uses counter, instead of true/false
    public int subsetSum(int[] nums, int s) {
        int[] dp = new int[s + 1];
        dp[0] = 1;
        for (int n : nums)
            for (int i = s; i >= n; i--)
                dp[i] += dp[i - n];
        return dp[s];
    }
}
