package dfs;

import tree.TreeNode;

/**
 * Created by jianwang on 3/6/17.
 */
public class PathSum {

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null) {return sum==0;}
        return hasPathSum(root.left,sum-root.val) ||  hasPathSum(root.right,sum-root.val);
    }
}
