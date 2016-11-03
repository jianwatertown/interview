package tree;


// Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding 
// up all the values along the path equals the given sum.

public class PathSum {

    public boolean hasPathSum(TreeNode root, int sum) {
        return helper(root,sum,0);
    }
    
    public boolean helper(TreeNode root, int target, int sumsofar){
    	if(root==null) return false;
    	if(root.left==null && root.right==null && root.val+sumsofar==target) return true;
    	if(root.left!=null && helper(root.left,target,sumsofar+root.val)) 
    		return true;
    	return (root.right!=null && helper(root.right,target,sumsofar+root.val));
    }
    
    public static void main(String[] args){
    	TreeNode n = new TreeNode(1);
    	System.out.println(new PathSum().hasPathSum(n,0));
    }
}