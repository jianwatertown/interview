package dfs;

import tree.TreeNode;

public class BinaryTreeMaximumPathSum {

    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        pathMax(root);
        return max;
    }

    public int pathMax(TreeNode root) {
        if(root==null) {return 0;}

        int left = Math.max(pathMax(root.left),0); // use the left path or not
        int right = Math.max(pathMax(root.right),0); // use the left path or not

        max = Math.max(max, left+right+root.val);
        return root.val + Math.max(left,right);

    }
}
