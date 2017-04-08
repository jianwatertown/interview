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
        // with/without
        int[] pre = new int[]{0,0};
        int[] now = new int[2];

        for(int num:nums){
            // yes
            now[0] = pre[1] + num;
            // no
            now[1] = Math.max(pre[1],pre[0]);
            pre = now.clone();
        }
        return Math.max(now[0],now[1]);
    }

    public static void main(String[] args){
        System.out.println(new HouseRobber().rob(new int[]{2,1}));
    }
}
