package dynamicProgramming;

/**
 * Created by jianwang on 1/8/17.
 *
 */
public class WiggleSubsequence {

    public int wiggleMaxLength(int[] nums) {

        // 1. boundary
        if(nums==null || nums.length==0) {return 0;}

        int b = 0;
        int a = -1;
        int maxLength = 1;

        // when a new sequence is possible
        //      a<b>c or a>b<c, move b->c, and a->b
        // always, extend the end, b
        for(int c=0;c<nums.length;c++){

            if(nums[c]==nums[b]) { continue;}

            // a<b>c or a>b<c
            if( (a==-1) // first time
                    || (nums[a]<nums[b] && nums[b]>nums[c]) || (nums[a]>nums[b] && nums[b]<nums[c]) /* a wiggle sequece*/){
                maxLength++;
                a = b;
            }
            b = c;
        }

        return maxLength;
    }
}
