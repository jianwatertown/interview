package tree;

/**
 *
 *
 *
 * 给一棵二叉树，找到有最大平均值的子树。返回子树的根结点
 *
 *
 *
 *
 */
public class SubtreeWithMaximumAverage {

    int maxAvg = Integer.MIN_VALUE;
    TreeNode maxAvgRoot = null;

    static class Result{
        int count;
        int sum;
        public Result(int c, int s){
            count = c;
            sum = s;
        }
    }

    public Result helper(TreeNode root){

        // 1. null
        if(root==null){
            new Result(0,0);
        }

        // 2. base case
        Result l = helper(root.left);
        Result r = helper(root.left);

        int sumRoot = l.sum + r.sum + root.val;
        int countRoot = (1+l.count+r.count);
        int avg = sumRoot/countRoot;

        // 3. update if necessary
        if(avg>=maxAvg){
            maxAvg = avg;
            maxAvgRoot = root;
        }

        return new Result(countRoot,sumRoot);
    }
}
