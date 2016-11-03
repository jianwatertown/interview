package tree;

public class ValidateBinarySearchTree {

	public boolean isValidBST(TreeNode root) {
        return isValidBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE,true,true);
    }

    public boolean isValidBST(TreeNode root, Integer lower, Integer upper, boolean allowMax,boolean allowMin){
    	if(root==null) {return true;}
    	
    	if(root.val>=upper||root.val<=lower) {
    		// reaching upper bound
    		if(root.val==upper && root.val==Integer.MAX_VALUE && allowMax){
    			allowMax = false;
    		}
    		else if(root.val==lower && root.val==Integer.MIN_VALUE && allowMin){
    			allowMin = false;
    		}
    		else{
    			return false;
    		}
    	}
		return isValidBST(root.left,lower,root.val,allowMax,allowMin) && 
				isValidBST(root.right,root.val,upper,allowMax,allowMin);
    }
}
