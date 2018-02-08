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

    // ************************************************************************************
    // the following is different, it use "contains" to dedupe
    // ************************************************************************************

    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(tempList.contains(nums[i])) continue; // element already exists, skip
                tempList.add(nums[i]);
                backtrack(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }


    // ************************************************************************************
    // practise 10/09/2017
    // ************************************************************************************

    public List<List<Integer>> permutePractice(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackPractise(result,new ArrayList<>(),nums,0);
        return result;
    }

    public void backtrackPractise(List<List<Integer>> result, List<Integer> soFar, int[]nums, int currentIndex){
        if(currentIndex==nums.length) {result.add(new LinkedList<>(soFar));  return;}
        for(int i=0;i<=soFar.size();i++){
            soFar.add(i,nums[currentIndex]);
            backtrack(result,soFar,nums,currentIndex+1);
            soFar.remove(i);
        }
    }
}
