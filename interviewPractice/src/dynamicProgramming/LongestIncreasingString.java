package dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

import sortAndSearch.BinarySearch;

public class LongestIncreasingString {

	
	// Power(n,2) solution
	public int lengthOfLIS2(int[] nums) {
		List<ArrayList<Integer>> allLIS = new ArrayList<ArrayList<Integer>>();
		
		for(int i=0;i<nums.length;i++){
			int maxLengthAtI = 0;
			ArrayList<Integer> current = null;
			for(ArrayList<Integer> previousList: allLIS){
				if(previousList.size()>maxLengthAtI && 				// longest one
					previousList.get(previousList.size()-1)>nums[i]){	// smaller so we can append
					current = previousList;
					maxLengthAtI = previousList.size() + 1;
				}
			}
		}
		return allLIS.get(allLIS.size()-1).size();
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
}
