package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by jianwang on 2/4/17.
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BinaryTreeRightSideView {


    public List<Integer> rightSideView(TreeNode root) {
        // setup
        List<Integer> result = new ArrayList<>();
        if(root==null) { return result;}

        // classic bfs + counter setup
        int currentLevel = 1;
        int nextLevel = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        // until the end of the queue
        while (!q.isEmpty()){

            // 1. get first node
            TreeNode n = q.poll();
            currentLevel--;

            // 2. enqueue left and right
            if(n.left!=null){
                q.add(n.left);
                nextLevel++;
            }
            if(n.right!=null){
                q.add(n.right);
                nextLevel++;
            }

            // 3. level end
            if(currentLevel==0){
                currentLevel = nextLevel;
                nextLevel=0;
                result.add(n.val);
            }
        }
        return result;
    }


    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> view = new ArrayList<>();
        if(root==null) {return view;}

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                TreeNode node = q.poll();
                if(i==0) {view.add(node.val);}
                if(node.right!=null) {q.add(node.right);size++;}
                if(node.left!=null) {q.add(node.left);size++;}
            }
        }
        return view;
    }
}
