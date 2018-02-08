package recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jianwang on 5/8/17.
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be
 * used and each combination should be a unique set of numbers.

     Example 1:

     Input: k = 3, n = 7

     Output:

     [[1,2,4]]

     Example 2:

     Input: k = 3, n = 9

     Output:

     [[1,2,6], [1,3,5], [2,3,4]]
 *
 */
public class CombinationSumThree {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(),k,1,n);
        return result;
    }


    // only 3 branches
    public void backtrack(List<List<Integer>> result, List<Integer> soFar, int k, int start, int remain){

        // 1. branch 1, negative
        if(start>10||soFar.size()>k || remain<0) {return;}

        // 2. branch 2, 0
        if(soFar.size()==k && remain==0){
            result.add(new ArrayList<>(soFar)); return;
        }

        // 3. branch 3, positive
        // "soFar" uses J or not use J
        for(int j=start;j<=9;j++){
            soFar.add(j);
            backtrack(result,soFar,k,j+1,remain-j);
            soFar.remove(soFar.size()-1);
        }
    }


    public List<List<Integer>> combinationSum3JPractice1(int k, int n) {

        if(k==0||n<1) {return new LinkedList<>();}

        Map<Integer,Set<Set<Integer>>> map = new HashMap();
        Set<Set<Integer>> zeroList = new HashSet<>();
        zeroList.add(new HashSet<>());
        map.put(0,zeroList);

        // 1 ... n
        for(int i=1;i<=n;i++){
            Set<Set<Integer>> iResult = new HashSet<>();
            for(int j=1;j<=9;j++){
                if(i-j<0) {break;} // no use increasing
                if(map.containsKey(i-j)){
                    for (Set<Integer> oneCom:map.get(i-j)){
                        if(oneCom.size()<k&& !oneCom.contains(j)){
                            Set<Integer> newCom = new HashSet<>(oneCom);
                            newCom.add(j);
                            iResult.add(newCom);
                        }
                    }
                }
            }
            map.put(i,iResult);
        }

        List<List<Integer>> result = new LinkedList<>();
        for(Set<Integer> oneResult:map.get(n)){
            if(oneResult.size()==k) {result.add(new ArrayList<>(oneResult));}
        }
        return result;
    }


    public List<List<Integer>> combinationSum3JPractice2(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        addToResult(result,new ArrayList<>(),1,k,n);
        return result;
    }

    public void addToResult(List<List<Integer>> result, List<Integer> soFar, int start, final int k, final int n){

        if(n<0 /*out of bound of target*/ || start>k /*exceeds count*/) {return;}
        if(n==0/*just right*/) {result.add(new ArrayList<>(soFar));}

        for(int i=start;i<9;i++){
            soFar.add(start);
            addToResult(result,soFar,start+1,k,n-start);
            soFar.remove(soFar.size()-1);   // soFar.remove(start);
        }
    }
}
