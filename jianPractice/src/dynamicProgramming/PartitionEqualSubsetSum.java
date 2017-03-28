package dynamicProgramming;

import java.util.Arrays;


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
    
    Not sure if we can re-write this in recursions
    
    
    canPartitionTopDown -> from right to left, made sure each number is used exact once
    						changes in elements on the right will not affect elements on the left
    
    canPartitionBottomUp -> left to right, more intuitive, but need to copy of the array to swap with

    time complexity: M (sum of the array) * N (# of elements in the array)
    
 * @author jian.wang
 *
 */
public class PartitionEqualSubsetSum {
	
	// top down
    public boolean canPartitionTopDown(int[] nums) {
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


        // classic: can a subset of array adds up to a target?
        // dp def
        boolean[] dp = new boolean[volumn + 1];
        // dp init
        dp[0] = true;
        // dp transition
        for (int i = 0; i < nums.length; i++) {
            for (int j = volumn; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[volumn];
    }
    
    /**
     * Bottom Up will only work with this line
     * 	boolean[] dq2 = Arrays.copyOf(dp, dp.length);
     * 	because each number cannot be used multiple times so we need a dq2 to remember old values
     * 
     * 
     * @param nums
     * @return
     */
    
    public boolean canPartitionBottomUp(int[] nums) {
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
        for (int i = 0; i < nums.length; i++) {
        	boolean[] dq2 = Arrays.copyOf(dp, dp.length);
        	for(int sum=0;sum<=volumn-nums[i];sum++){
        		dp[sum+nums[i]] = dq2[sum+nums[i]] || dq2[sum];	//	maybe use num[i] 
        	}
        }
        return dp[volumn];
    }

    // practise on 1/26/2017
    public boolean canPartition(int[] nums) {

        // 1. get the target
        int sum = 0;
        for(int n:nums){
            sum+= n;
        }
        if(sum%2!=0){
            return false;
        }
        int targetSum = sum/2;

        // 2. classic dp
        // target[i] means if a subset of the nums[] can sum up to i
        boolean[] target  = new boolean[targetSum+1];
        target[0] = true;

        // from 1 to targetSum

        /****
         *
         *
         *  Since each element can be only used once, the loop has to start from the
         *  candidate numbers to the sum
         *
         *
         */

        // try each element
        for(int n: nums){

            boolean[] targetBeforeUsingN = Arrays.copyOf(target,target.length);

            // from [0,targetSum-n]
            for(int i=0;i<=targetSum-n;i++){

                target[i+n] = targetBeforeUsingN[i] || targetBeforeUsingN[i+n];
            }
        }
        return target[targetSum];
    }


    // practise on 3/28/2017, notice the return expression, this method gives the counter
    public boolean canPartition2(int[] nums) {

        // 1. get the target
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int targetSum = sum / 2;

        // 2. classic dp
        // target[i] means if a subset of the nums[] can sum up to i
        int[] target = new int[targetSum + 1];
        target[0] = 1;

        for(int num:nums){
            for(int s=targetSum;s-num>=0;s--){
                target[s] += target[s-num];
            }
        }
        return target[targetSum]>=1;
    }


    public static void main(String[] args){
        PartitionEqualSubsetSum tester = new PartitionEqualSubsetSum();
        System.out.println(tester.canPartition(new int[]{1,2,5}));
    }
}
