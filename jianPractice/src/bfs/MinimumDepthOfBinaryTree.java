package bfs;

/**
 * Created by jianwang on 2/4/17.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class MinimumDepthOfBinaryTree {
    public int minDepth2(TreeNode root) {

        if(root==null) {return 0;}
        // set up
        int result = 0;
        Queue<TreeNode> q = new LinkedList<>();
        if(root==null) {return result;}

        int currentLevel = 1;
        int nextLevel = 0;
        q.add(root);

        while(!q.isEmpty()){

            // 1. add to current level
            TreeNode n = q.poll();
            currentLevel--;

            if(n.left==null&&n.right==null){
                break;
            }

            q.add(n.left);
            q.add(n.right);
            nextLevel +=2;

            // 3. finish one level
            if(currentLevel==0){
                currentLevel = nextLevel;
                nextLevel = 0;
                result++;
            }
        }
        return result;
    }

    public int minDepth(TreeNode root) {
        if(root==null) { return 0;}

        // null, null
        if(root.left==null && root.right==null){
            return 1;
        }
        // yes, yes
        else if (root.left!=null && root.right!=null) {
            return Math.min((minDepth(root.left)),minDepth(root.right))+1;
        }
        // yes, none
        else if (root.left!=null){
            return 1+ minDepth(root.left);
        }
        // no, none
        else{
            return 1+ minDepth(root.right);
        }
    }
}
