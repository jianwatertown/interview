package dfs;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {
        return root==null? new LinkedList<>(): binaryTreePaths(root,"");
    }

    public List<String> binaryTreePaths(TreeNode root,String pathSoFar) {
        List<String> result = new LinkedList<>();
        pathSoFar = (pathSoFar.length()==0)? String.valueOf(root.val): (pathSoFar+"->"+root.val);
        if(root.left==null&&root.right==null) {result.add(pathSoFar);return result;}
        if(root.left!=null) {result.addAll(binaryTreePaths(root.left,pathSoFar));}
        if(root.right!=null) {result.addAll(binaryTreePaths(root.right,pathSoFar));}
        return result;
    }
}
