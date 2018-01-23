package tree;

import java.util.Stack;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

     Calling next() will return the next smallest number in the BST.

    Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 */

public class BinarySearchTreeIterator {


    Stack<TreeNode> stack = new Stack<>();
    TreeNode current;

    // BSTIterator
    public BinarySearchTreeIterator(TreeNode root) {
        current = root;
    }


    // follow the left branch and add all the left nodes
    public void addLeftMostBranchFromNode(TreeNode node){
        while (node!=null){
            stack.push(node);
            node = node.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        addLeftMostBranchFromNode(current);
        current = null;
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        if(!hasNext()) {return -1;}
        TreeNode top = stack.pop();
        // now it's right child's turn
        current = top.right;
        return top.val;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */