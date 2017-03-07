package dfs;

import tree.TreeNode;

/**
 * Created by jianwang on 3/6/17.
 */
public class BinaryTreeMaximumPathSum {

    public int maxPathSum(TreeNode root) {
        return helper(root).max();
    }

    // with/without
    public Sum helper(TreeNode root){
        if(root==null) {return new Sum(Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE);}
        Sum left = helper(root.left);
        Sum right = helper(root.right);
        int without = Math.max(left.max(),right.max());
        int withOnePath = Math.max(left.withOnePath,right.withOnePath) + root.val;
        int withAsRoot = left.withOnePath + right.withOnePath + root.val;
        return new Sum(without,withOnePath,withAsRoot);
    }

    public static class Sum{
        int without;
        int withAsRoot;
        int withOnePath;
        public Sum(int a, int b, int c){
            this.without = a;
            this.withAsRoot = b;
            this.withOnePath = c;
        }

        public int max(){
            return Math.max(Math.max(without,withAsRoot),withOnePath);
        }
    }

}
