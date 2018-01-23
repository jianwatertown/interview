package tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        List<Integer> result = new LinkedList<>();

        TreeNode leftMost = root;
        while(leftMost!=null||!stack.isEmpty()){

            // 1. go to the left most node and visit
            while (leftMost!=null){
                stack.add(leftMost);
                leftMost = leftMost.left;
            }
            TreeNode top = stack.pop();
            result.add(top.val);

            // 2. to leftMost's right
            leftMost = leftMost.right;
        }
        return result;
    }
}
