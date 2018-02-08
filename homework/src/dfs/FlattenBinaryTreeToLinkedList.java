package dfs;

import tree.TreeNode;

import java.util.Stack;


/**
*
* 将一棵二叉树按照前序遍历拆解成为一个假链表。所谓的假链表是说，用二叉树的 right 指针，来表示链表中的 next 指针。
*
 * Given a binary tree, flatten it to a linked list in-place.

     For example,
     Given

      1
     / \
    2   5
   / \   \
  3   4   6
 The flattened tree should look like:
 1
  \
   2
    \
     3
      \
       4
        \
         5
          \
           6
 */
public class FlattenBinaryTreeToLinkedList {

    private TreeNode lastNode = null;

    public void flatten1(TreeNode root) {
        if (root == null) {
            return;
        }

        // 1. manage upper level
        if (lastNode != null) {
            lastNode.left = null;
            lastNode.right = root;
        }

        // 2. manage subtree
        lastNode = root;
        TreeNode right = root.right;    // root.right will be changed in the "root.left" recursion
        flatten1(root.left);
        flatten1(right);
    }

    // version 2: Divide & Conquer
    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten2(TreeNode root) {
        helper2(root);
    }

    // flatten root and return the last node
    private TreeNode helper2(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode leftLast = helper2(root.left);
        TreeNode rightLast = helper2(root.right);

        // connect leftLast to root.right
        if (leftLast != null) {
            leftLast.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        // easy to miss these returns: right, left, root
        if (rightLast != null) {
            return rightLast;
        }

        if (leftLast != null) {
            return leftLast;
        }

        return root;
    }

    // version 3: Non-Recursion
    /**
     * Definition of TreeNode:
     * public class TreeNode {
     *     public int val;
     *     public TreeNode left, right;
     *     public TreeNode(int val) {
     *         this.val = val;
     *         this.left = this.right = null;
     *     }
     * }
     */

    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten3(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }

            // connect
            node.left = null;
            if (stack.empty()) {
                node.right = null;
            } else {
                node.right = stack.peek();
            }
        }
    }
}
