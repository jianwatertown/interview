package dfs;

import tree.TreeNode;

/**
 * Created by jianwang on 2/6/17.
 */
public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if(root==null) {return 0;}
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }

}
