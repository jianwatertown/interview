package twoPointers;

/**
 * Created by jianwang on 4/4/17.
 */
public class RemoveElement {

    public int removeElement(int[] nums, int val) {

        // size is the slower pointer here that points
        // to the index that's golden
        int size = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] !=val){  // value to keep
                nums[size] = nums[i];
                size++;
            }
        }
        return size;
    }
}
