package dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

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
Doing so will maintain the tails invariant. The the final answer is just the size.
 * @param nums
 * @return
 */
	// O(nLogN) solution
	public int lengthOfLIS(int[] nums) {
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
}
