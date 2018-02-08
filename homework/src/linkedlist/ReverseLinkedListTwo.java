package linkedlist;

public class ReverseLinkedListTwo {

    public ListNode reverseBetween(ListNode head, int m, int n) {

        // init and dummy head
        if(head==null||head.next==null||m>=n){return head;}
        ListNode dummyHead= new ListNode(0);
        dummyHead.next = head;

        // 1 - 2 - 3 - 4 (swapping 2, 3)
        ListNode before = dummyHead;        // 1
        ListNode end = dummyHead;           // 3

        for(int i=1;i<=n;i++){
            if(i<m){
                before = before.next;
            }
            end = end.next;
        }
        ListNode remainStart = end.next; // 4

        // reverse
        ListNode newHead = reverseBetween(before.next,end);

        // swapped + remain
        before.next.next = remainStart;   // 2.next = 4
        // begin + swapped
        before.next = newHead;
        return dummyHead.next;
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


    public static void main(String[] args){
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
//        ListNode five = new ListNode(5);
        one.next = two;
        two.next = three;
        System.out.println(new ReverseLinkedListTwo().reverseBetween(one,2,3));
    }
}
