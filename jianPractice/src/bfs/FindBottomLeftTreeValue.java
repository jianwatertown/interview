package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by jianwang on 2/21/17.
 */
public class FindBottomLeftTreeValue {

    public int findBottomLeftValue(TreeNode root) {

        // set up
        Queue<TreeNode> q = new LinkedList<>();
        if(root==null) {return 0;}
        int result=root.val;
        boolean shouldUpdateResult = false;

        int currentLevelCount = 1;
        int nextLevelCount = 0;
        q.add(root);

        while(!q.isEmpty()){

            // 1. add to current level
            TreeNode n = q.poll();
            if(shouldUpdateResult){
                result = n.val;
                shouldUpdateResult = false;
            }
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
                shouldUpdateResult = true;
                currentLevelCount = nextLevelCount;
                nextLevelCount = 0;
            }
        }
        return result;
    }
}
