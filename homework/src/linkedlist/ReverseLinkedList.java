package linkedlist;

public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if(head==null||head.next==null) {return head;}
        ListNode nextNode = head.next;
        ListNode newHead = reverseList(nextNode);
        nextNode.next = head;
        head.next = null;
        return newHead;
    }

    ////////////////////////////////////////////////////////////
    public ListNode reverseList(ListNode head, ListNode end) {
        if(head==null||head.next==null||head==end) {return head;}
        ListNode nextNode = head.next;
        ListNode newHead = reverseList(nextNode,end);
        nextNode.next = head;
        head.next = null;
        return newHead;
    }

    public ListNode reverseList2(ListNode head) {
        if(head==null||head.next==null) {return head;}
        ListNode runner = head;
        while(runner.next!=null){
            runner = runner.next;
        }
        return reverseList(head,runner);
    }
}
