package tree;

public class CommonAncestorInBST {
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}

		return LCA(root, p, q);
	}

	private TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
		if ((p.val < root.val) && (q.val > root.val)) {
			return root;
		} else if ((p.val < root.val) && (q.val < root.val)) {
			return LCA(root.left, p, q);
		} else if ((p.val > root.val) && (q.val > root.val)) {
			return LCA(root.right, p, q);
		} else
			return root;
	}
}
