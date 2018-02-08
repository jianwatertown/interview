package dynamicProgramming;

/**
 * Created by jianwang on 1/5/17.
 *
 * https://leetcode.com/problems/house-robber-ii/
 *
 *
 */
public class HouseRobberTwo {

    public int rob(int[] num, int start, int end){
        int maxInclude = 0;
        int maxExclude = 0;

        for(int i=start;i<=end;i++){
            int maxExcludeNow = Math.max(maxExclude,maxInclude);
            int maxIncludeNow = Math.max(maxExclude+num[i] /*add current one*/,maxInclude /*previous max*/);
            maxExclude = maxExcludeNow;
            maxInclude = maxIncludeNow;
        }
        return Math.max(maxExclude,maxInclude);
    }


    /**
     *  for any houses i, i+1, there are 4 situations (yes as robbed)
     *          i,      i+1
     *  1)      yes     no
     *  2)      no      yes
     *  3)      no      no
     *
     *  which falls into 2 category a) i no 2) i+1 no
     *
     *  also, when any given house j is not robbed, it then does not effect j+1, nor j-1. So when j is not robbed,
     *  then circule = j+1 ---> j-1
     *
     * @param nums
     * @return
     */

    public int rob(int[] nums){
        if(nums.length==1) {return nums[0];}
        return Math.max(
                (/*not rob first*/ rob(nums,1,nums.length-1)),
                (/*not rob last*/ rob(nums,0,nums.length-2))
        );
    }
}
