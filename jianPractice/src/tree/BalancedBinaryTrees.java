package tree;

// same as K-balcned Node tree
public class BalancedBinaryTrees {
	
    public boolean isBalanced(TreeNode root) {
    	return isBalancedInt(root)!=-1;        
    }
    
    // -1 means not balanced
    // or return the value of the longest height
    public int isBalancedInt(TreeNode root){
    	if(root==null) {return 0;}
    	int left = isBalancedInt(root.left);
    	if(left==-1) return -1;
    	int right = isBalancedInt(root.right);
    	if(right==-1) return -1;
    	if(Math.abs(left-right)>1){return -1;}
    	else {return Math.max(left, right)+1;}
    }
}
