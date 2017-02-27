package dfs;

import tree.TreeNode;

/**
 * Created by jianwang on 2/27/17.
 */
public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        return getHeight(root)!=-1;
    }

    // -1 unbalanced, or height
    public int getHeight(TreeNode root){
        if(root==null) {return 0;}
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        if(left==-1||right==-1||Math.abs(left-right)>1) {
            return -1;
        }
        else{
            return Math.max(left,right);
        }
    }
}
