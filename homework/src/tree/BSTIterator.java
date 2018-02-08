package tree;

public class BSTIterator {

    /*
    * @param root: The root of binary tree.
    */
    LinkedList head = new LinkedList(null, new TreeNode(0));
    LinkedList current = head;

    public BSTIterator(TreeNode root) {
            // do initialization if necessary
            visit(root);
            current = head;
        }

        public void visit(TreeNode root){
            if(root==null) {return;}
            visit(root.left);
            current.next = new LinkedList(null,root);
            current = current.next;
            visit(root.right);
        }

        /*
         * @return: True if there has next node, or false
         */
        public boolean hasNext() {
            return current.next!=null;
        }

        /*
         * @return: return next node
         */
        public TreeNode next() {
            current = current.next;
            return current.node;
        }

        public static class LinkedList{
            LinkedList next;
            TreeNode node;
            public LinkedList(LinkedList n, TreeNode r){
                next = n;
                node = r;
            }
        }
}
