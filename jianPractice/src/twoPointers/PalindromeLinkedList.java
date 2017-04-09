package twoPointers;

import linkedlist.ListNode;

import java.util.Stack;

/**
 * Created by jianwang on 4/8/17.
 *
 *          1->2->3->null
 *              fast end on 3, slow end on 2
 *          1->2->3->4->null
 *              fast end on null, slow end on 3
 */
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if(head==null) {return false;}
        ListNode fast=head,slow=head;

        // 1. find the mid point
        while(fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        slow = (fast!=null)?slow.next:slow;    // even/odd number of nodes

        // 2. reverse order
        Stack<ListNode> stack = new Stack<>();
        ListNode runner = head;
        while(runner!=null){
            stack.add(runner); runner=runner.next;
        }

        // 3. compare
        while(!stack.empty()){
            if(stack.pop()!=slow){return false;}
            slow = slow.next;
        }
        return true;
    }

}
