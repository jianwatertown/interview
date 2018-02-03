package tree;

public class ClosestBinarySearchTreeValue {

    public int closestValue(TreeNode root, double target) {
        return helper(root,target,root.val);
    }

    public int helper(TreeNode root, double target, int soFar){
        // 1. edge
        if(root==null) {return soFar;}

        // 2. so far
        if(root.val==target) {return soFar;}

        // 3. in case current is closer than previous
        if(Math.abs(root.val-target)<Math.abs(soFar-target)){soFar=root.val;}

        // 4. left
        if(target<root.val)
        {
            soFar=helper(root.left,target,soFar);
        }

        // 5. right
        else if(target>root.val) {
            soFar=helper(root.right,target,soFar);
        }

        return soFar;
    }
}
