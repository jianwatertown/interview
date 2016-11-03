package tree;

public class LowestCommonAncestor {

	// works
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		return containNodeCount(root, p, q).parent;
    }
	
	public Pair containNodeCount(TreeNode root, TreeNode p, TreeNode q){

		// 1. empty
		if(root==null) {return new Pair(0,null);}
		
		if(root==p && root==q) {return new Pair(2, root);}

		// 2. left
		Pair leftPair = containNodeCount(root.left,p,q);
		Pair rightPair = containNodeCount(root.right,p,q);
		
		if(leftPair.count==2) return leftPair;
		if(rightPair.count==2) return rightPair;
		if(leftPair.count==1 && rightPair.count==1) {return new Pair(2,root);}
		
		if(root==p || root==q) {
			// root is the parent
			if(leftPair.count==1||rightPair.count==1){
				return new Pair(2,root);
			}
			// neither, only this node match
			else{
				return new Pair(1,root);
			}
			
		}
		// only look at subtree
		else{
		    if(leftPair.count==1) {return leftPair;}
		    if(rightPair.count==1) {return rightPair;}
			return new Pair(0,null);
		}
	}
	
	public class Pair{
		int count;
		TreeNode parent;
		public Pair(int count, TreeNode parent){
			this.count = count;
			this.parent = parent;
		}
	}
	
	public static void main(String[] args){
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		n1.left = n2;
		n1.right = n3;
		LowestCommonAncestor a = new LowestCommonAncestor();
		TreeNode n = a.lowestCommonAncestor(n1, n2, n3);
		System.out.println(n!=null?n.val:"null");
	}
}
