package bfs;

import java.util.*;

/**
 * Created by jianwang on 2/10/17.
 */
public class BinaryTreeZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentLevel = new ArrayList<>();
        Stack<TreeNode> l = new Stack<>();
        Stack<TreeNode> r = new Stack<>();

        r.add(root);

        while (!l.isEmpty()|| !r.isEmpty()){
            // visit each, prepare right to left
            while (!l.isEmpty()){
                TreeNode n = l.pop();
                currentLevel.add(n.val);
                if(n.right!=null) {r.add(n.right);}
                if(n.left!=null) {r.add(n.left);}
            }
            if(!currentLevel.isEmpty()){
                result.add(currentLevel);
                currentLevel = new ArrayList<>();
            }

            while (!r.isEmpty()){
                TreeNode n = r.pop();
                currentLevel.add(n.val);
                if(n.left!=null) {l.add(n.left);}
                if(n.right!=null) {l.add(n.right);}
            }
            if(!currentLevel.isEmpty()){
                result.add(currentLevel);
                currentLevel = new ArrayList<>();
            }
        }
        return result;
    }
}
