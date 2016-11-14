package dynamicProgramming;


/**
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 * 
 * 	Each of the array element will not exceed 100.
	The array size will not exceed 200.
	
	Input: [1, 5, 11, 5]

    Output: true
    
    Explanation: The array can be partitioned as [1, 5, 5] and [11].
    
    Input: [1, 2, 3, 5]

    Output: false
    
    Explanation: The array cannot be partitioned into equal sum subsets.
    
    
    
 * @author jian.wang
 *
 */
public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        // check edge case
        if (nums == null || nums.length == 0) {
            return true;
        }
        // preprocess
        int volumn = 0;
        for (int num : nums) {
            volumn += num;
        }
        if (volumn % 2 != 0) {
            return false;
        }
        volumn /= 2;
        // dp def
        boolean[] dp = new boolean[volumn + 1];
        // dp init
        dp[0] = true;
        // dp transition
        for (int i = 1; i <= nums.length; i++) {
            for (int j = volumn; j >= nums[i-1]; j--) {
                dp[j] = dp[j] || dp[j - nums[i-1]];
            }
        }
        return dp[volumn];
    }
}
