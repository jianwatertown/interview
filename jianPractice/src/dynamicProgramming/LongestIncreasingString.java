package dynamicProgramming;

import sortAndSearch.BinarySearch;

/**
 * For example,	Given [10, 9, 2, 5, 3, 7, 101, 18],
	The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. 
	Note that there may be more than one LIS combination, it is only necessary for you to return the length.

	Your algorithm should run in O(n2) complexity.

 */
// called "Longest Increasing Subsequence" on LeetCode
public class LongestIncreasingString {


	// Power(n,2) solution
	public int lengthOfLISNN(int[] nums) {

		int max_sequence_length = 0;

		for(int begin=0;begin<nums.length;begin++){
			int last = nums[begin];
			int max = 1;
			for(int end=begin+1;end<nums.length;end++){
				if(last<nums[end]){
					last = nums[end];
					max++;
				}
			}
			max_sequence_length = Math.max(max_sequence_length,max);
		}
		return max_sequence_length;
    }

/**
 * tails is an array storing the smallest tail of all increasing subsequences with length i+1 in tails[i].
    For example, say we have nums = [4,5,6,3], then all the available increasing subsequences are:
    
    len = 1   :      [4], [5], [6], [3]   => tails[0] = 3
    len = 2   :      [4, 5], [5, 6]       => tails[1] = 5
    len = 3   :      [4, 5, 6]            => tails[2] = 6
    
    We can easily prove that tails is a increasing array. Therefore it is possible to do a binary search in tails 
    array to find the one needs update.
    
    Each time we only do one of the two:
    
    (1) if x is larger than all tails, append it, increase the size by 1
    (2) if tails[i-1] < x <= tails[i], update tails[i]
    
    always
    1) binary search find an "i" so that either
    	(a) tails[i-1] < x <= tails[i]
    	or 
    	(b) last index (when it's largest than all trails)
    2) so tails[i] = x, in both (a), (b) cases
    3) in case (a), max_length++
    
    Doing so will maintain the tails invariant. The the final answer is just the size.

 * @param nums
 * @return
 */
	// O(nLogN) solution
	public int lengthOfLISFromWeb(int[] nums) {
	    int[] tails = new int[nums.length];
	    int size = 0;
	    for (int x : nums) {
	        int i = 0, j = size;
	        while (i != j) {
	            int m = (i + j) / 2;
	            if (tails[m] < x)
	                i = m + 1;
	            else
	                j = m;
	        }
	        tails[i] = x;
	        if (i == size) ++size;
	    }
	    for(int i=0;i<tails.length;i++){
	    	System.out.println("i="+i+" "+tails[i]);
	    }
	    return size;
	}
	
	// and of the new element element does 2 things
	// 1) if it is bigger than all, then it can add to the longest and make it even longer. 
	//	max_length ++;  // can use an index to represent
	//	largest_element_of_length[max_length+1] = largest_element_of_length[max_length].append()
	// 2) otherwise, it must be smaller than one of tail, thus that tail can be updated
	
	public int lengthOfLIS(int[] nums){
		
		int max_length_index = 0;
		int[] largest_of_length = new int[nums.length];
		
		for(int e:nums){
			
			// binary search to find the equal index or the immediately smaller one
			// largest_of_length[i-1]<nums[i]<=largest_of_length[i] or last element
			// set for both cases
			int indexOfSmallOrEqual = BinarySearch.findTargetIterative(nums, e);
			largest_of_length[indexOfSmallOrEqual] = e;
			
			// last element
			if(indexOfSmallOrEqual==max_length_index){
				max_length_index++;
			}
		}
		return max_length_index;	// is both the index and the length
	}
	
	public static void main(String [] args){
		LongestIncreasingString test = new LongestIncreasingString();
		System.out.println(test.lengthOfLISNN(new int[]{10,9,2,5,3,7,101,18}));
		System.out.println(test.lengthOfLISFromWeb(new int[]{10,9,2,5,3,7,101,18}));
	}
}
