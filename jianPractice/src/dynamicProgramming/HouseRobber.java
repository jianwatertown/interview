package dynamicProgramming;

/**
 * Created by jianwang on 12/30/16.
 *
 * Dynamic Programming,
 *
 *  knowing i, i+1, how to figure out i+2
 *
 *      i       i+1     i+2
 *      yes     no      yes
 *      no      yes     no
 *      no      no      yes
 */
public class HouseRobber {
    public int rob(int[] nums) {
        int i_yes = 0;
        int i_no = 0;
        int i_1_yes = 0;
        int i_1_no = 0;
        int i_2_yes = 0;
        int i_2_no = 0;

        for(int num:nums){

            // current level
            i_2_yes = Math.max(i_yes,i_no)+num;
            i_2_no = i_1_yes;

            // i -> i+1
            i_yes = i_1_yes;
            i_no = i_1_no;
            // i+1 -> i+2
            i_1_yes = i_2_yes;
            i_1_no = i_2_no;


        }
        return Math.max(i_2_yes,i_2_no);
    }


    // notice maxExcludeNow is needed
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
}
