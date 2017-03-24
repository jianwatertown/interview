package dfs;

/**
 * Created by jianwang on 3/6/17.
 */
public class PopulatingNextRightPointersInEachNodeTwo {

    public void connect(TreeLinkNode root) {
        if(root==null) {return;}

        // 1. connect immediate children first
        if(root.right!=null){
            
        }


        if(root.left!=null){

            if(root.right)

            root.left.next = root.right==null? findFirstLeft(root):right.n;
            // 2. connect right child to my sibling's left
            if(root.next!=null && root.right!=null /*protected by root.left!=null already*/){
                root.right.next = root.next.left;
            }
        }

        // 2. move on
        connect(root.left);
        connect(root.right);
    }

    TreeLinkNode findFirstLeft(TreeLinkNode root){
        while(root!=null){
            if(root.left!=null) {return root.left;}
            if(root.right!=null) {return root.right;}
            root = root.right;
        }
        return null;
    }

    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }
}

