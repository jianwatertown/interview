package twoPointers;

import leetcode.dataStructures.ListNode;

/**
 * Created by jianwang on 4/4/17.
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if(head==null) return false;
        ListNode walker = head;
        ListNode runner = head;
        while(runner.next!=null && runner.next.next!=null) {
            walker = walker.next;
            runner = runner.next.next;
            if(walker==runner) return true;
        }
        return false;
    }
}
