package dynamicProgramming;

/**
 * Created by jianwang on 12/31/16.
 *
 *
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j),
 * inclusive.

     Example:
     Given nums = [-2, 0, 3, -5, 2, -1]

     sumRange(0, 2) -> 1
     sumRange(2, 5) -> -1
     sumRange(0, 5) -> -3


 */

public class NumArray {

    public int[] sum = null;
    public int[] nums = null;
    public NumArray(int[] nums) {
        if(nums==null||nums.length==0){
            return;
        }


        this.nums = nums;
        this.sum = new int[nums.length];
        this.sum[0] = nums[0];
        for(int i=1;i<nums.length;i++){
            this.sum[i] = sum[i-1] + nums[i];
        }
    }

    // hidden assumption: optimization on range query
    // sumRange(0,n) = sum[0...n]
    // sumRange(i,j) = sumRange(0,j) - sumRange(0,i) + i
    public int sumRange(int i, int j) {
        if(this.nums==null || i>j || i<0 || j>=nums.length){
            return 0;
        }
        return sum[j] - sum[i] + nums[i];
    }
}
