package bfs;

/**
 * Created by jianwang on 3/26/17.
 */
public class SumRootToLeafNumbers {

    public int sumNumbers(TreeNode root) {
        return root==null?0:sumNumbers(root,0);
    }

    public int sumNumbers(TreeNode root, int sumSoFar) {
        int nodeSum = sumSoFar*10+root.val;
        if(root.left==null&&root.right==null){
            return nodeSum;
        }
        int sum = 0;
        if(root.left!=null){
            sum+=sumNumbers(root.left,nodeSum);
        }
        if(root.right!=null){
            sum+=sumNumbers(root.right,nodeSum);
        }
        return sum;
    }
}
