package recursion;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers 
 * sums to T. The same repeated number may be chosen from C unlimited number of times.
 * 
 * For example, given candidate set [2, 3, 6, 7] and target 7,
 *
 *
 * A general approach to backtracking questions in Java (Subsets, Permutations, Combination Sum, Palindrome Partitioning)
 */
public class CombinationSum {


	public List<List<Integer>> combinationSum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		backtrack(result,new ArrayList<>(), nums,target,0);
		return result;
	}

	// backtrack means: It is however possible that during construction, you realize that the solution is not successful
	// (does not satisfy certain constraints), then you backtrack: you undo certain assignments of values to variables in order to reassign them.
	public void backtrack(List<List<Integer>> result, List<Integer> soFar, int[] nums, int remain, int start){

		if(remain<0){ return;}												// backtrack
		else if(remain==0){ result.add(new LinkedList<>(soFar));}			// found answer, remember to copy
		else{
			for(int i=start;i<nums.length;i++){								// can only use start and after
				soFar.add(nums[i]);
				backtrack(result,soFar,nums,remain-nums[i],i);		// i and onwards
				soFar.remove(soFar.size()-1);
			}
		}
	}
}

