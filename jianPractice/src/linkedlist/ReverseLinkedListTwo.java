package linkedlist;

public class ReverseLinkedListTwo {

    public ListNode reverseBetween(ListNode head, int m, int n) {

        if(head==null||head.next==null||m>=n){return head;}

        ListNode before = head;
        ListNode end = head;
        for(int i=1;i<n;i++){
            if(i<m){
                before = before.next;
            }
            end = end.next;
        }
        ListNode remainStart = end.next;
        ListNode swapStart = before.next;
        ListNode newHead = reverseBetween(swapStart,end);

        // 1->2->[...]->3->4
        swapStart.next = remainStart;

        // fix head if needed
        if(m>1) {
            before.next = newHead;
            newHead = head;
        }
        return newHead;
    }

    // same as the algorithm in ReverseLinkedList
    public ListNode reverseBetween(ListNode head, ListNode end) {
        if (head == null || head.next == null || head == end) {
            return head;
        }
        ListNode nextNode = head.next;
        ListNode newHead = reverseBetween(nextNode, end);
        nextNode.next = head;
        head.next = null;
        return newHead;
    }

}
