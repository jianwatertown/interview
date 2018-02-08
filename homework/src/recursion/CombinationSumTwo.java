package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where
 * the candidate numbers sums to T.

     Each number in C may only be used once in the combination.

     Note:
     All numbers (including target) will be positive integers.
     The solution set must not contain duplicate combinations.
     For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,

     [
     [1, 7],
     [1, 2, 5],
     [2, 6],
     [1, 1, 6]
     ]
 */
public class CombinationSumTwo {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(list, new ArrayList<>(), candidates, target,0);
        return list;
    }


    public void backtrack(List<List<Integer>> result, List<Integer> soFar, int[] candidates, int remain, int start){
        if(remain<0)    {return;}
        else if(remain==0)  {result.add(new ArrayList<>(soFar));}
        else{
            for(int i=start;i<candidates.length ;i++){
                // skip 1,1,2,3
                if(i>start&&candidates[i-1]==candidates[i]){continue;}
                soFar.add(candidates[i]);
                backtrack(result,soFar,candidates,remain-candidates[i],i+1);
                soFar.remove(soFar.size()-1);
            }
        }
    }
}
