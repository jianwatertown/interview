package tree;

public class DoublyListNode {
    public int val;
    public DoublyListNode next, prev;
    public DoublyListNode(int val) {
        this.val = val;
        this.next = this.prev = null;
    }
}
