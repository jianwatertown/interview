package dynamicProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers 
 * sums to T. The same repeated number may be chosen from C unlimited number of times.
 * 
 * For example, given candidate set [2, 3, 6, 7] and target 7, 
 * @author jian.wang
 *
 */
public class CombinationSum {

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		Set<List<Integer>> setResult = combinationSum(candidates, target, new HashMap<Integer,Set<List<Integer>>>());
		return new ArrayList(setResult);
	}
	
	public Set<List<Integer>> combinationSum(int[] candidates, int target, Map<Integer,Set<List<Integer>>> cache) {
		
		// 1. cache
		if(cache.containsKey(target)) {return cache.get(target);}
		
		// 2. base case
		if(target==0){
			Set<List<Integer>> result = new HashSet<List<Integer>>();
			result.add(new ArrayList<Integer>());
			cache.put(0, result);
			return result;
		}
		else if(target<-1){
			return null;
		}
		// 3. normal case
		else{
			Set<List<Integer>> result = new HashSet<List<Integer>>();
			for(int can:candidates){
				Set<List<Integer>> subResults = combinationSum(candidates, target-can, cache);
				// valid array only
				if(subResults!=null){
					for(List<Integer> oneResult:subResults){
						List<Integer> copiedResult =  new ArrayList<>();
						copiedResult.addAll(oneResult);
						copiedResult.add(can);
						Collections.sort(copiedResult);
						result.add(copiedResult);
					}
				}
			}
			cache.put(target, result.size()!=0?result:null);
			return result;
		}
    }
}
