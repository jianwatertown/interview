package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by jianwang on 2/4/17.
 */
public class BinaryTreeLevelOrderTraversalTwo {

    public List<List<Integer>> levelOrder(TreeNode root) {

        // set up
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Queue<TreeNode> q = new LinkedList<>();
        if(root==null) {return result;}

        int currentLevelMax = 1;
        int nextLevelMax = 0;
        q.add(root);
        List<Integer> onLevel = new LinkedList<>();

        while(!q.isEmpty()){

            // 1. add to current level
            TreeNode n = q.poll();
            onLevel.add(n.val);

            // 2. handle children
            if(n.left!=null){
                q.add(n.left);
                nextLevelMax ++;
            }
            if(n.right!=null){
                q.add(n.right);
                nextLevelMax ++;
            }

            // 3. finish one level
            if(onLevel.size()==currentLevelMax){
                result.add(onLevel);
                onLevel = new LinkedList<>();
                currentLevelMax = nextLevelMax;
                nextLevelMax = 0;
            }
        }
        return result;
    }
}
