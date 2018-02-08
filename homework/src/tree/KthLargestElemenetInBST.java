package tree;

public class KthLargestElemenetInBST {
    private static int number = 0;
    private static int count = 0;

    public int kthSmallest(TreeNode root, int k) {
        count = k;
        helper(root);
        return number;
    }

    public void helper(TreeNode n) {

        // 1. left
        if (n.left != null) helper(n.left);

        // 2. root
        count--;
        if (count == 0) {
            number = n.val;
            return;
        }

        // 3. right
        if (n.right != null) helper(n.right);
    }
}
