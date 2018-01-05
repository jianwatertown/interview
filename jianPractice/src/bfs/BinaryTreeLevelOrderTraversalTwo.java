package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by jianwang on 2/4/17.
 */
public class BinaryTreeLevelOrderTraversalTwo {

    public List<List<Integer>> levelOrderBottom2(TreeNode root) {

        // set up
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Queue<TreeNode> q = new LinkedList<>();
        if(root==null) {return result;}
        q.add(root);

        while(!q.isEmpty()){
            List<Integer> oneLevel = new ArrayList<>();
            int nextLevelSize = q.size();
            for(int i=0;i<nextLevelSize;i++){
                TreeNode n = q.poll();
                oneLevel.add(n.val);
                if(n.left!=null) {q.add(n.left);}
                if(n.right!=null) {q.add(n.right);}
            }
            result.add(0,oneLevel);
        }
        return result;
    }
}
