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
 */
public class CombinationSumThree {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(),k,1,n);
        return result;
    }


    public void backtrack(List<List<Integer>> result, List<Integer> soFar, int k, int start, int remain){

        if(start>10||soFar.size()>k || remain<0) {return;}

        if(soFar.size()==k && remain==0){
            result.add(new ArrayList<>(soFar)); return;
        }

        for(int j=start;j<=9;j++){
            soFar.add(j);
            backtrack(result,soFar,k,j+1,remain-j);
            soFar.remove(soFar.size()-1);
        }
    }


    public List<List<Integer>> combinationSum3Jian(int k, int n) {

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
}