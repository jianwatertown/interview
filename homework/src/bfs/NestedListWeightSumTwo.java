package bfs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NestedListWeightSumTwo {

    int maxLevel = 0;
    Map<Integer,Integer> map = new HashMap<>();

    public int depthSumInverse(List<NestedInteger> nestedList) {
        depthSumInverse(nestedList,1);
        int sum = 0;
        for(int i=1;i<=maxLevel;i++){
            sum+= map.get(i)*(maxLevel-i+1);
        }
        return sum;
    }

    public void depthSumInverse(List<NestedInteger> nestedList, int level) {

        int levelSum = map.containsKey(level)?map.get(level):0;
        maxLevel = Math.max(maxLevel,level);
        for(NestedInteger nest: nestedList){
            if(!nest.isInteger()) {
                depthSumInverse(nest.getList(),level+1);
            }
            else{
                levelSum += nest.getInteger();
            }
        }
        map.put(level,levelSum);
    }

    public static void main(String[] args){
        NestedListWeightSumTwo nest = new NestedListWeightSumTwo();
    }

}
