package dynamicProgramming;

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
    	
    	// 1. O(NLogN) sort
    	Arrays.sort(nums);
    	
    	// 2. O(N) find the first element<=0
    	int middle = indexOfFirstNoBiggerThanKey(nums,0);

    	if(middle==-1 || 				// nothing smaller than 0
    			middle>=nums.length-1){	// fewer than 1 bigger than 0 
    		return result;
    	}
    	
    	// 3. from middle and expand left and right
    	int left = middle -1;
    	int right = middle +1;
    	while(left>=0 && right<=nums.length-1){
    		// right on 
    		if(nums[left]+nums[middle]+nums[right]==0){
    			List<Integer> oneResult = new ArrayList<Integer>();
    			oneResult.add(nums[left]);
    			oneResult.add(nums[middle]);
    			oneResult.add(nums[right]);
    			result.add(oneResult);
    			left --;
    			right ++;
    		}
    		else if(nums[left]+nums[middle]+nums[right]>0){
    			left --;
    		}
    		else{	// nums[left]+nums[middle]+nums[right]<0
    			right ++;
    		}
    		// skip the same thing
    		while(left>0 && nums[left-1]==nums[left]){
    			left--;
    		}
    		while(right<nums.length-1 && nums[right+1]==nums[right]){
    			right++;
    		}
    	}
    	return result;
    }
    
    public static int indexOfFirstNoBiggerThanKey(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        int mid = lo + (hi - lo) / 2;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            mid = lo + (hi - lo) / 2;
            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;			// exact match
        }
        // now a[mid] is just smaller than or bigger than key
        if(a[mid]<key) {
        	return mid;
        }
        else{
        	return mid-1;
        }
    }
    
    public static void main(String[] args){
    //	System.out.println(indexOfFirstNoBiggerThanKey(new int[] {1,2,3,4,5,6},0));
    	System.out.println(new ThreeSum().threeSum(new int[] {-1,0,1,2,-1,-4}));
    }
}
