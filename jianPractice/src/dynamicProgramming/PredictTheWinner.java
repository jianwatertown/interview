package dynamicProgramming;

import java.util.Map;

/**
 * Created by jianwang on 1/29/17.
 *
 *
 *
 * Did it myself!
 *
 *
 *  1. find next player's best moves
 *  2. my best move is the to Max(my move - his best moves)
 *
 */
public class PredictTheWinner {

    public boolean PredictTheWinner(int[] nums) {

        if(nums==null||nums.length==0){
            return false;
        }
        return helper(nums,new Integer[nums.length][nums.length],0,nums.length-1)>=0;

    }

    public int helper(int[] nums, Integer[][] cache, int start, int end){

        if(cache[start][end]==null){
            cache[start][end] = (start==end)? nums[start]:                                        // degenerated case of returning itself
                    Math.max(nums[start]-helper(nums,cache,start+1,end),                   // pick left
                    nums[end]-helper(nums,cache,start,end-1));                              // pick right
        }
        return cache[start][end];
    }


    public static void main(String[] args){
        PredictTheWinner tester = new PredictTheWinner();
        System.out.println(tester.PredictTheWinner(new int[]{1,100,1}));
        System.out.println(tester.PredictTheWinner(new int[]{1}));
    }
}
