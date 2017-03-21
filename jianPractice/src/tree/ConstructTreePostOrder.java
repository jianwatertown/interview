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
    	int index = findIndex(inStart,root.val,inorder);

    	// ---------------------------------------------------------------------------------------------
    	//
    	//		Key to this question is the index manipulation, pay attention to the "-1" part
    	//
    	// ---------------------------------------------------------------------------------------------
    	// 4. get this recursively
    	root.left = buildTree(postorder,inorder,
    			postStart,
				postStart + (index-inStart) - 1, // index-inStart is the size of left tree
    			inStart,
				index-1);
    	root.right = buildTree(postorder,inorder,
    			postStart + (index-inStart),
    			postEnd-1,
    			index+1,
    			inEnd);
    
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
