package tree;

public class ConstructTreePostOrder {
	
    public TreeNode buildTree(int[] postorder, int[] inorder) {
    	return buildTree(postorder, inorder, 0, postorder.length-1, 0, inorder.length-1);
    }
    
    public TreeNode buildTree(int[] postorder, int[] inorder, int postStart, int postEnd, int inStart, int inEnd){

    	// make sure we are in bound
    	if(postStart>postEnd || postStart<0 || postEnd>postorder.length-1) {return null;}
    	if(inStart>inEnd || inStart<0 || inEnd>inorder.length-1) {return null;}
    	
    	// 1. create root
    	TreeNode root = new TreeNode(postorder[postEnd]);

    	// 2. find root index in inorder
    	int inOrderRootIndex = findIndex(inStart,root.val,inorder);
    	
    	// 3. use in-order sequence to get the size of each size
    	int inOrderLeftSize = inOrderRootIndex - inStart;
    	int inOrderRightSize = inEnd - inOrderRootIndex;
    	
    	
    	// ---------------------------------------------------------------------------------------------
    	//
    	//		Key to this question is the index manipulation, pay attention to the "-1" part
    	//
    	// ---------------------------------------------------------------------------------------------
    	// 4. get this recursively
    	root.left = buildTree(postorder,inorder,
    			postEnd-1-inOrderLeftSize-inOrderRightSize+1,
    			postEnd-1-inOrderRightSize,
    			inOrderRootIndex-inOrderLeftSize,
    			inOrderRootIndex-1);
    	root.right = buildTree(postorder,inorder,
    			postEnd-1-inOrderLeftSize+1,
    			postEnd-1,
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
    
    public static void main(String[] args){

		int[] postOrder = {1,2,3,4};
		int[] inOrder = {1,3,4,2};
		
		ConstructTreePostOrder cons = new ConstructTreePostOrder();
		System.out.println(cons.buildTree(postOrder, inOrder).val);
    }

}
