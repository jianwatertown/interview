package tree;

// Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
// TODO needs to revisit the algorithm, not generic question
// https://leetcode.com/problems/inorder-successor-in-bst/

public class InoderSuccessorBST {
   
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		
		TreeNode ret = null;
		while (root != null) {
			// 1. current root is smaller or equal, definitely not in this branch
			if (root.val <= p.val) {
				root = root.right;
			// 2. current root is smaller, definitely not in this branch				
			} else {
				// 2.1 root is 1 of the successors, although might not be the one
				ret = root;
				// 2.2 maybe we can find one in the left branch
				root = root.left;
			}
		}
		return ret;
	}
	
	// not easier, but good to get familiar with the technics here
	public Node inorderSuccessorWithParentWithoutRoot(Node p){
		
		// 1. right subtree not null, find the left most child
		if(p.right!=null){
			return findLeftMostChild(p.right);
		}
		// 2. right subtree null, find the parent that is left child of some node
		else{
			// go up until no parent, or p is parent's left child
			while(p.parent!=null && p.parent.left!=p){
				p = p.parent;
			}
			return p.parent;
		}
	}
	
	public Node findLeftMostChild(Node root){
		while(root!=null){
			root = root.left;
		}
		return root;
	}
}
