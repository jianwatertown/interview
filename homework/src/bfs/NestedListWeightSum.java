package bfs;

import java.util.List;

public class NestedListWeightSum {

    public int depthSum(List<NestedInteger> nestedList) {
        return depthSum(nestedList,1);
    }

    public int depthSum(List<NestedInteger> nestedList, int level) {
        int sum=0;
        for(NestedInteger nest: nestedList){
            sum+= nest.isInteger()?nest.getInteger()*level:
                    depthSum(nest.getList(),level+1);
        }
        return sum;
    }
}
