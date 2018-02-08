package tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.

 For example:
     Given binary tree [1,null,2,3],
    1
     \
      2
     /
    3
 return [1,2,3].
 */
public class BinaryTreePreorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> result = new LinkedList<>();
        if(root==null) {return result;}

        // use stack and reverse the insert order
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            TreeNode top = stack.pop();
            result.add(top.val);
            if(top.right!=null) {stack.push(top.right);}
            if(top.left!=null) {stack.push(top.left);}
        }
        return result;
    }
}
