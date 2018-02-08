package dfs;

/**
 * Created by jianwang on 3/6/17.
 */
public class PopulatingNextRightPointersInEachNode {

    public void connect(TreeLinkNode root) {
        if(root==null) {return;}

        // 1. connect immediate children first
        if(root.left!=null){
            root.left.next = root.right;
            // 2. connect right child to my sibling's left
            if(root.next!=null){
                root.right.next = root.next.left;
            }
        }

        // 2. move on
        connect(root.left);
        connect(root.right);
    }

    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }
}

