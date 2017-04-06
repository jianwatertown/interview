package recursion;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Given a collection of distinct numbers, return all possible permutations.
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result,new LinkedList<>(),nums,0);
        return result;

    }

    public void backtrack(List<List<Integer>> result, List<Integer> soFar, int[] nums, int start){
        if(start==nums.length){result.add(new LinkedList<>(soFar));return;}
        // 0,1,2 (4 indexes to insert, 0 to 3)
        for(int i=0;i<=soFar.size();i++){
            soFar.add(i,nums[start]);
            backtrack(result,soFar,nums,start+1);
            soFar.remove(i);
        }
    }
}
