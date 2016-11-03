package sortAndSearch;

/**
 * 	Given an unsorted array nums, reorder it in-place such that 
 * 	
 * 	nums[0] <= nums[1] >= nums[2] <= nums[3]....

	For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 * @author jian.wang
 * 
 * 
 *  a,b,c,d
 *  
 *  Use {1,3,6,9,8,2} as input data.
 * Step 1: make each [even, odd] pair to be [small, large] --> {1,3,6,9,2,8}
 * Step 2: make each [odd, even] pair to be [large, small] --> {1,6,3,9,2,8} Done!
 *
 */
public class WiggleSort {
	
	public void wiggleSort(int[] nums) {
	    for (int i = 1; i < nums.length; i += 2)
	        if (nums[i] < nums[i - 1]) swap(nums, i, i - 1);
	    for (int i = 2; i < nums.length; i += 2)
	        if (nums[i] > nums[i - 1]) swap(nums, i, i - 1);
	}
	
	public void swap(int[] a, int p, int q) {
	    int t = a[p];
	    a[p] = a[q];
	    a[q] = t;
	}
}
