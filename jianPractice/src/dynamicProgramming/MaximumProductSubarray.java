package dynamicProgramming;


/**
 * Loop through the array, each time remember the max and min value for the previous product, 
 * the most important thing is to update the max and min value: 
 * we have to compare among max * A[i], min * A[i] as well as A[i], 
 * since this is product, a negative * negative could be positive.
	
 * @author jian.wang
 *
 */
public class MaximumProductSubarray {

    // copied from internet
    public int maxProduct2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = nums[0], min = nums[0], result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);
            if (max > result) {
                result = max;
            }
        }
        return result;
    }

    // knowing i-1, how to get i (use variable name j)
    public int maxProduct(int[] nums) {

        if(nums ==null || nums.length==0 ) return 0;
        int i_yes_max= nums[0], i_no_max =  nums[0], i_yes_min = nums[0], i_no_min = nums[0];

        for (int i=1;i<nums.length;i++){
            // transition
            int j_yes_max = maxOf(i_yes_max*nums[i],nums[i]*i_yes_min,nums[i]);
            int j_no_max = maxOf(i_yes_max,i_no_max);
            int j_yes_min = minOf(i_yes_max*nums[i],nums[i]*i_yes_min,nums[i]);
            int j_no_min =minOf(i_yes_min,i_no_min);

            // move on
            i_yes_max = j_yes_max;
            i_no_max = j_no_max;
            i_yes_min = j_yes_min;
            i_no_min = j_no_min;
        }
        return maxOf(i_no_max,i_yes_max);
    }

    public int minOf(int... nums){
        int min = nums[0];
        for(int i=1;i<nums.length;i++){
            min = Math.min(min,nums[i]);
        }
        return min;
    }

    public int maxOf(int... nums){
        int max = nums[0];
        for(int i=1;i<nums.length;i++){
            max = Math.max(max,nums[i]);
        }
        return max;
    }
}
