package tree;

public class ConstructTreePreOrder {

    public int findIndex(int start,int target, int[] search){
    	for(int i=start;i<search.length;i++){
    		if(target==search[i]){return i;}
    	}
    	return -1;
    }


    // ------------------------------------------------------------------------------------------
	//	use base case tree to figure out the indexes
	// practise 03/06/2017
	// ------------------------------------------------------------------------------------------
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return null;
	}

	public TreeNode helper(int[] pre, int[] in, int pS, int pE, int iS, int iE){
    	// 1. out bound then, null
    	if(pS>pE||iS>iE||pS<0||pE>=pre.length||iS<0||iE>=in.length){return null;}
		// 2. find root index
		int rootIndex = findIndex(iS,pre[pS],in);
    	TreeNode root = new TreeNode(pre[pS]);
    	// 3. split the range
    	root.left = helper(pre,in,pS+1,pS+(rootIndex-iS),iS,rootIndex-1);
		root.right = helper(pre,in,pS+(rootIndex-iS)+1,pE,rootIndex+1,iE);
    	return root;
	}


    
    public static void main(String[] args){
		// construct
		BTreePrinter printer = new BTreePrinter();
		Node root = printer.constructATree();
		printer.printNode(root);

		int[] preOrder = {1,2};
		int[] inOrder = {1,2};
		
		ConstructTreePreOrder cons = new ConstructTreePreOrder();
		cons.buildTree(preOrder, inOrder);
    }
}
