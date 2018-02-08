package twoPointers;

import linkedlist.ListNode;

/**
 * Created by jianwang on 4/9/17.
 *
     1)
     l - length of the loop
     m: distance from beginning of the list to start of the loop
     k: distance from the beginning of the loop to the meeting point

     When they meet:

     distance_slow = m+P*l+k
     distance_fast = m+Q*l+k

     m+k = (Q-2P)*l -> m+k is the integer multiple of th length of the loop

     2) F,S meet at k place
     3) now move S to the beginning
     move both S and F m steps forward,
     S is on m
     F is on (m+k), the beginning of the loop, and they meet
 *
 *
 */
public class LinkedListCycleTwo {

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow){      // fast is thrown away once the first meeting point is found
                                    // slow2 replaced slow, slow replaced fast
                ListNode slow2 = head;
                while (slow2 != slow){
                    slow = slow.next;
                    slow2 = slow2.next;
                }
                return slow;
            }
        }
        return null;
    }

}
