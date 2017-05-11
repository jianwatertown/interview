package recursion;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jianwang on 4/29/17.
 *
 * Given an integer n, generate all structurally unique BST's (binary search trees)
 *  that store values 1...n.

     For example,
     Given n = 3, your program should return all 5 unique BST's shown below.
 */
public class UniqueBinarySearchTreesTwo {



    public List<TreeNode> generateTrees(int n) {
        if(n==0) return new LinkedList<TreeNode>();
        return generateSubtrees(1, n);
    }

    private List<TreeNode> generateSubtrees(int s, int e) {
        List<TreeNode> res = new LinkedList<TreeNode>();
        if (s > e) {
            res.add(null); // empty tree
            return res;
        }

        // use i as root
        for (int i = s; i <= e; ++i) {
            List<TreeNode> leftSubtrees = generateSubtrees(s, i - 1);
            List<TreeNode> rightSubtrees = generateSubtrees(i + 1, e);

            for (TreeNode left : leftSubtrees) {
                for (TreeNode right : rightSubtrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }

    public static List<TreeNode> generateTreesDP(int n) {
        List<TreeNode>[] dp = new List[n + 1];
        dp[0] = new ArrayList<TreeNode>();
        if (n == 0) {
            return dp[0];
        }

        dp[0].add(null);

        // left to right DP
        for (int len = 1; len <= n; len++) {
            dp[len] = new ArrayList<TreeNode>();

            // use previous trees
            for (int j = 0; j < len; j++) {
                for (TreeNode nodeL : dp[j]) {
                    // each nodeR needs to be shifted
                    for (TreeNode nodeR : dp[len - j - 1]) {
                        TreeNode node = new TreeNode(j + 1);
                        node.left = nodeL;
                        node.right = createNodeWithOffset(nodeR, j + 1);
                        dp[len].add(node);
                    }
                }
            }
        }
        return dp[n];
    }

    private static TreeNode createNodeWithOffset(TreeNode n, int offset) {
        if (n == null) {
            return null;
        }
        TreeNode node = new TreeNode(n.val + offset);
        node.left = createNodeWithOffset(n.left, offset);
        node.right = createNodeWithOffset(n.right, offset);
        return node;
    }
}
