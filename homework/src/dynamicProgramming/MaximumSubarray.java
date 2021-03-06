package dynamicProgramming;

public class MaximumSubarray {

	// key: maxEndWith[i] can use maxEndWith[i-1]
    public static int maxSubArray(int[] nums) {
    	
    	int[] maxEndWith = new int[nums.length];
    	int maxSoFar = nums[0];
    	maxEndWith[0] = nums[0];
    	
    	// at some point we know maxAt[i-1], then
    	for(int i=1;i<nums.length;i++){
			maxEndWith[i] = (maxEndWith[i-1]>0?maxEndWith[i-1]:0) + nums[i];
    		maxSoFar = Math.max(maxSoFar, maxEndWith[i]);
    	}
    	return maxSoFar;
    }
}
