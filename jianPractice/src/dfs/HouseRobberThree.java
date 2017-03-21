package dfs;

import tree.TreeNode;

/**
 * Created by jianwang on 3/20/17.
 */
public class HouseRobberThree {

    public int rob(TreeNode root) {
        int[] max = helper(root);
        return Math.max(max[0],max[1]);
    }

    // with, without
    public int[] helper(TreeNode r){
        int[] max = new int[] {0,0};
        if(r!=null){
            int[] left = helper(r.left);
            int[] right = helper(r.right);
            max[0] = left[1] + right[1] + r.val;
            max[1] = Math.max(left[0],left[1]) + Math.max(right[0],right[1]);
        }
        return max;
    }
}
