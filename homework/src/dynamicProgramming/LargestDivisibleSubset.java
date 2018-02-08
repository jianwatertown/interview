package dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jianwang on 1/7/17.
 */
public class LargestDivisibleSubset {

    public List<Integer> largestDivisibleSubset(int[] nums) {

        List<Integer> result = new ArrayList<>();

        if(nums==null||nums.length==0) {
            return result;
        }


        boolean[][] dp = new boolean[nums.length][nums.length];
        int[]size = new int[nums.length];
        int indexOfMax = 0;
        Arrays.fill(size,0);

        for(int i = 0;i<nums.length;i++){
            for(int j=0;j<nums.length;j++){
                dp[i][j] = ((nums[i] % nums[j]) == 0 );
                if(dp[i][j]){
                    size[i] ++;
                }
            }
            if(size[i]>size[indexOfMax]){
                indexOfMax = i;
            }
        }

        // assemble the list of sum
        for(int i=0;i<nums.length;i++){
            result.add(nums[i]);
        }
        
        return result;
    }


    public List<Integer> largestDivisibleSubset2(int[] nums) {

        if(nums==null||nums.length==0) {return new ArrayList<>();}

        // s[i]: largest divisible set, whose biggest element is i
        Map<Integer,Set<Integer>> map = new HashMap<>();

        Arrays.sort(nums);
        // core code: DP by input num from small to big
        // e.g. 2,4,10
        for(int num:nums){
            Set<Integer> largestSet = new HashSet<>();
            for(int divisor:nums){
                // if 4%2==0, then s[4] can be s[2]+{4}
                if(divisor<num && num%divisor==0 &&
                        map.containsKey(divisor) && map.get(divisor).size()>largestSet.size()){
                    largestSet = new HashSet<>(map.get(divisor));
                }
            }
            largestSet.add(num);
            map.put(num,new HashSet<>(largestSet));
        }

        // 3. find biggest set
        Set<Integer> largestSet = new HashSet<>();
        for(Set<Integer> set:map.values()){
            if(set.size()>largestSet.size()){
                largestSet = set;
            }
        }
        return new ArrayList(largestSet);
    }

    public static void main(String[] args){
        LargestDivisibleSubset test = new LargestDivisibleSubset();
        System.out.println(test.largestDivisibleSubset2(new int[] {20000}));
    }
}
