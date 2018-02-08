package twoPointers;

/**
 * Created by jianwang on 4/9/17.
 */
public class RemoveDuplicatesfromSortedArray {
    public int removeDuplicates(int[] nums) {
        if(nums==null||nums.length==0) {return 0;}
        int size = 0, head = 0;
        while(size<nums.length&&head<nums.length){
            if(nums[head]!=nums[size]){
                nums[++size] = nums[head];
            }
            head++;
        }
        return size+1;
    }
}
