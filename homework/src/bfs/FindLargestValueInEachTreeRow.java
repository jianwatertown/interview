package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by jianwang on 2/21/17.
 */
public class FindLargestValueInEachTreeRow {

    public List<Integer> largestValues(TreeNode root) {

        // set up
        List<Integer> result = new ArrayList<Integer>();
        Queue<TreeNode> q = new LinkedList<>();
        if(root==null) {return result;}

        int currentLevelCount = 1;
        int nextLevelCount = 0;
        q.add(root);
        int levelMax = Integer.MIN_VALUE;

        while(!q.isEmpty()){

            // 1. add to current level
            TreeNode n = q.poll();
            levelMax = Math.max(levelMax,n.val);
            currentLevelCount --;

            // 2. handle children
            if(n.left!=null){
                q.add(n.left);
                nextLevelCount ++;
            }
            if(n.right!=null){
                q.add(n.right);
                nextLevelCount ++;
            }

            // 3. finish one level
            if(currentLevelCount==0){
                result.add(levelMax);
                levelMax = Integer.MIN_VALUE;
                currentLevelCount = nextLevelCount;
                nextLevelCount = 0;
            }
        }
        return result;
    }
}
