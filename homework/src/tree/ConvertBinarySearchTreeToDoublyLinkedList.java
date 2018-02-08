package tree;

public class ConvertBinarySearchTreeToDoublyLinkedList {
    DoublyListNode fakeHead = new DoublyListNode(0);
    DoublyListNode head = fakeHead;

    public DoublyListNode bstToDoublyList(TreeNode root) {
        if(root==null) return null;
        visit(root);
        fakeHead.next.prev = null;
        return fakeHead.next;
    }

    public void visit(TreeNode root){
        if(root!=null){
            visit(root.left);
            DoublyListNode newNode = new DoublyListNode(root.val);
            head.next = newNode;
            newNode.prev = head;
            head = newNode;
            visit(root.right);
        }
    }
}
