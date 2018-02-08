package tree;


import java.util.ArrayList;

/**
 *
 *
 * You are given a binary tree in which each node contains an integer value.

     Find the number of paths that sum to a given value.

     The path does not need to start or end at the root or a leaf,
     but it must go downwards (traveling only from parent nodes to child nodes).

     The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.


 */
public class PathSumThree {

	int count = 0;

    public int pathSum(TreeNode root, int sum) {
        helper(root,sum,new ArrayList<>());
        return count;
    }

    public void helper(TreeNode root, int sum, ArrayList<Integer> possible){

        if(root==null) {return;}

        // 1. all possible paths
        possible.add(0);        //  not using anything before
        for(Integer v:possible){
            v = v+root.val;
            if(v==sum) {count++;}
        }

        // 2. downstream
        helper(root.left,sum,new ArrayList<>(possible));
        helper(root.right,sum,new ArrayList<>(possible));
    }
}
