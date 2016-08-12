package dynamicProgramming;

public class MaxSubarray {

	// key: maxEndWith[i] can use maxEndWith[i-1]
    public static int maxSubArray(int[] nums) {
    	
    	int[] maxEndWith = new int[nums.length];
    	int maxSoFar = nums[0];
    	maxEndWith[0] = nums[0];
    	
    	// at some point we know maxAt[i-1], then
    	for(int i=1;i<nums.length;i++){

    		// 1. previous negative
    		if(maxEndWith[i-1]<=0){
    			maxEndWith[i] = nums[i];
    		}
    		// 2. previous positive, add current to previous
    		else{
    			maxEndWith[i] = maxEndWith[i-1] + nums[i];
    		}
    		maxSoFar = Math.max(maxSoFar, maxEndWith[i]);
    	}
    	return maxSoFar;
    }
    
    // sum = min_so_far
    public int maxSubArray2(int[] nums){
    	
    	int l=0;
    	int r=0;
    	int min_idx=-1;
    	int min_sum=0;
    	int sum=0;
    	int max_sum=0;
    	
    	for(int i=0;i<nums.length;i++){
    		sum += nums[i];
    		if(sum<min_sum){
    			min_sum = sum;
    			min_idx = i;
    		}
    		if(sum-min_sum>max_sum){
    			max_sum	= sum - min_sum;
    			l = min_idx+1;
    			r = i+1;
    		}
    	}
    	return 0;
    }
}
