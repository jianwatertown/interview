package tree;

import java.util.ArrayList;
import java.util.List;


// Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
public class PathSumTwo {
	
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> pathSoFar = new ArrayList<Integer>();
        addToList2(result, root, sum, pathSoFar);
        return result;
    }

	public void addToList2(List<List<Integer>> result, TreeNode root, int sum, List<Integer> pathSoFar){

		if(root==null) {return;}
		sum = sum-root.val;
		pathSoFar.add(root.val);

		// 1. leaf node
		if(root.left==null && root.right==null && sum==0){
			result.add(pathSoFar);
		}

		// 2. in-node
		addToList2(result,root.left,sum,new ArrayList<Integer>(pathSoFar));
		addToList2(result,root.right,sum,new ArrayList<Integer>(pathSoFar));
	}

    public void addToList(List<List<Integer>> result, TreeNode root, int target, int sumsofar, List<Integer> pathSoFar){

    	if(root==null) {return;}
    	
    	// 1. leaf node
    	if(root.left==null && root.right==null){
    		// 1.1 matches
    		if(root.val+sumsofar==target){
    			List<Integer> copy = new ArrayList<Integer>(pathSoFar);
    			copy.add(root.val);
    			result.add(copy);
    		}
    		// 1.2 do nothing
    	}
    	// 2. in-node
    	else{
    		pathSoFar.add(root.val);
    		sumsofar+=root.val;
    		if(root.left!=null)
    			addToList(result,root.left,target,sumsofar,pathSoFar);
    		if(root.right!=null)
    			addToList(result,root.right,target,sumsofar,pathSoFar);
    		pathSoFar.remove(pathSoFar.size()-1);
    	}
    }
}
