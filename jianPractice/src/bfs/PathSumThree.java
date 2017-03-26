package bfs;

import java.util.LinkedList;
import java.util.List;


public class PathSumThree {

    List<List<Integer>> result = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root!=null){
            pathSum(root,sum,new LinkedList<>());
        }
        return result;
    }

    public void pathSum(TreeNode root, int target, List<Integer> path) {

        List<Integer> pathSoFar = new LinkedList<>(path);
        pathSoFar.add(root.val);
        int remain = target - root.val;

        // 1. leaf node
        if(root.left==null&root.right==null){
            if(remain==0) {result.add(pathSoFar);}
        }

        // 2. go down the non-null paths
        if(root.left!=null) {pathSum(root.left,remain,pathSoFar);}
        if(root.right!=null) {pathSum(root.right,remain,pathSoFar);}
    }
}
