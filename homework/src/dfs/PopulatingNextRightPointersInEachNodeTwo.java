package dfs;

public class PopulatingNextRightPointersInEachNodeTwo {

    public void connect(TreeLinkNode root) {
        if(root==null) {return;}

        // 1. find what right.next
        TreeLinkNode rightNext = findFirstLeft(root.next);

        // 2. right first
        if(root.right!=null){
            root.right.next = rightNext;
            rightNext = root.right; // save some typing.
        }

        // 3. now left
        if (root.left!=null){
            root.left.next = rightNext;
        }

        // 3. move on
        connect(root.right);
        connect(root.left);
    }

    TreeLinkNode findFirstLeft(TreeLinkNode root){
        while(root!=null){
            if(root.left!=null) {return root.left;}
            if(root.right!=null) {return root.right;}
            root = root.next;
        }
        return null;
    }

    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }
}

