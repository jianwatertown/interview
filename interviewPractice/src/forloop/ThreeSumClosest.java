package forloop;

import java.util.Arrays;

/**
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

 * @author jian.wang
 *
 */
public class ThreeSumClosest {
	
	
    public int threeSumClosest(int[] nums, int target) {

    	Arrays.sort(nums);
    	int bestSum = nums[0]+nums[1]+nums[2];
    	
    	for(int i=0;i<nums.length-2;i++){
    		int left = i+1;
    		int right = nums.length-1;
    		while(left<right){
    			int sum = nums[left] +nums[right] + nums[i];
                if (Math.abs(target - sum) < Math.abs(target - bestSum)) {
                    bestSum = sum;
                }
                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
    		}
    	}
    	return bestSum;
    }
}
