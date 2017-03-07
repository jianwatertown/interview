package tree;

public class ConstructTreePreOrder {
	
    public TreeNode buildTree(int[] preorder, int[] inorder) {
    	return buildTree(preorder, inorder, 0, preorder.length-1, 0, inorder.length-1);
    }
    
    public TreeNode buildTree(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd){

    	// make sure we are in bound
    	if(preStart>preEnd || preStart<0 || preEnd>preorder.length-1) {return null;}
    	if(inStart>inEnd || inStart<0 || inEnd>inorder.length-1) {return null;}
    	
    	// 1. create root
    	TreeNode root = new TreeNode(preorder[preStart]);

    	// 2. find root index in inorder
    	int inOrderRootIndex = findIndex(inStart,root.val,inorder);
    	
    	// 3. get the size of each size
    	int inOrderLeftSize = inOrderRootIndex - inStart;
    	int inOrderRightSize = inEnd - inOrderRootIndex;
    	
    	
    	// ---------------------------------------------------------------------------------------------
    	//
    	//		Key to this question is the index manipulation, pay attention to the "-1" part
    	//
    	// ---------------------------------------------------------------------------------------------
    	// 4. get this recursively
    	root.left = buildTree(preorder,inorder,
    			preStart+1,
    			preStart+1+inOrderLeftSize-1,
    			inOrderRootIndex-inOrderLeftSize,
    			inOrderRootIndex-1);
    	root.right = buildTree(preorder,inorder,
    			preStart+1+inOrderLeftSize,
    			preStart+1+inOrderLeftSize+inOrderRightSize-1,
    			inOrderRootIndex+1,
    			inOrderRootIndex+1+inOrderRightSize-1);
    
    	return root;
    }
    
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
	public TreeNode buildTree2(int[] preorder, int[] inorder) {
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
