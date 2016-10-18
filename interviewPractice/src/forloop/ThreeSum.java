package forloop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
 * Find all unique triplets in the array which gives the sum of zero.

        Note: The solution set must not contain duplicate triplets.
        
        For example, given array S = [-1, 0, 1, 2, -1, -4],
        
        A solution set is:
        [
          [-1, 0, 1],
          [-1, -1, 2]
        ]
 * @author jian.wang
 *
 */
public class ThreeSum {
	
	   public List<List<Integer>> threeSum(int[] nums) {
	    	
	    	List<List<Integer>> result = new ArrayList<List<Integer>>();
	    	
	    	if(nums==null||nums.length<3) {return result;}
	    	
	    	// O(NLogN) sort
	    	Arrays.sort(nums);
	    	
	    	// from 1st to end-2
	    	for(int i=0;i<nums.length-2;i++){
	    		// 1. skip current if it is the same
	    		if(i>0 && nums[i-1]==nums[i]) continue;
	    		
	    		// 2. left starts from next, right starts from end
	    		int left = i+1;
	    		int right = nums.length-1;
	   
	    		while(left<right){
	    			int sum = nums[i]+nums[left]+nums[right];
	    			if(sum==0) {
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(nums[i]);
						tmp.add(nums[left]);
						tmp.add(nums[right]);
						result.add(tmp);
						left++;
						right--;
						while (left < right && nums[left] == nums[left - 1]) { // to skip duplicates
							left++;
						}
						while (left < right && nums[right] == nums[right + 1]) { // to skip duplicates
							right--;
						}
	    			}
	    		    else if(sum<0) {left++;}
	    			else {	right--;}
	    		}
	    	}
	        return result;
	    }
    
    public static void main(String[] args){
    //	System.out.println(indexOfFirstNoBiggerThanKey(new int[] {1,2,3,4,5,6},0));
    	System.out.println(new ThreeSum().threeSum(new int[] {-1,0,1,2,-1,-4}));
    }
}
