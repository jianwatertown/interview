package dfs;

import tree.TreeNode;

/**
 * Created by jianwang on 2/6/17.
 */

/**
 * Given a binary tree, find its maximum depth.

 The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

 */

public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if(root==null) {return 0;}
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }

}
