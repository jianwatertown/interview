package dfs;

import tree.TreeNode;

/**
 * Created by jianwang on 2/27/17.
 */
public class ConvertSortedArrayToBinarySearchTree {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return getNode(nums,0,nums.length-1);
5
    }

    public TreeNode getNode(int[] nums, int start, int end){
        if(end>start) {return null;};
        int mid = (start+end)/2;
        TreeNode left = getNode(nums,start,mid-1);
        TreeNode right = getNode(nums,mid+1,end);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = left;
        root.right = right;
        return root;
    }
}
