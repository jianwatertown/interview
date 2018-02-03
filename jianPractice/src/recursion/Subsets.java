package recursion;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Given a set of distinct integers, nums, return all possible subsets.
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));

        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    // power set with cache which is not working well
    public List<List<Integer>> subsets2(int[] nums) {
        Map<Integer,Set<List<Integer>>> cache = new HashMap<>();
        List<List<Integer>> result = new LinkedList<>();
        dfs(result,cache, new ArrayList<>(), nums, 0);
        return result;
    }

    // x...y....a,b,c
    // [previous string] a,b,c
    private void dfs(List<List<Integer>> result, Map<Integer,Set<List<Integer>>> cache,
                                            List<Integer> soFar, int [] nums, int start){

        // 1. without "start"
        result.add(new LinkedList<>(soFar));

        // 2. with "start", but we have cache whose first element is "start"
        if(cache.containsKey(start)){
            for(List<Integer> subSetsFromStart: cache.get(start)){
                LinkedList<Integer> soFarPlus = new LinkedList<>(soFar);
                soFarPlus.addAll(subSetsFromStart);
                result.add(soFarPlus);
            }
        }
        // 3. with "start", we need to compute all the subset starting with "start" and update cache
        else{
            cache.put(start, new HashSet<>());

            // 3.1 we use one of the next level elements
            for(int i = start; i < nums.length; i++){

                soFar.add(nums[i]);
                dfs(result,cache,soFar,nums,i+1);
                soFar.remove(soFar.size() - 1);
                // update the cache
                if(cache.containsKey(i+1)&&cache.get(i+1).size()>0){
                    for(List<Integer> oneLevel:cache.get(i+1)){
                        cache.get(start).add(new LinkedList<>(oneLevel));
                        List<Integer> oneLevelPlus = new LinkedList<>(oneLevel);
                        oneLevelPlus.add(start);
                        cache.get(start).add(oneLevelPlus);
                    }
                }else {
                    List<Integer> soFarPlus = new LinkedList<>(soFar);
                    soFarPlus.add(nums[i]);
                    cache.get(i).add(soFarPlus);
                }
            }
        }
    }

    public static void main(String[] args){
        Subsets tester = new Subsets();
        System.out.println(tester.subsets(new int[]{11,12,13}));
    }
}
