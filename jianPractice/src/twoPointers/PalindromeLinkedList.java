package twoPointers;

import linkedlist.ListNode;

import java.util.Stack;

/**
 * Created by jianwang on 4/8/17.
 *
 *          1->2->null
 *              fast end on null, slow end on 2
 *          1->2->3->null
 *              fast end on 3, slow end on 2
 *          1->2->3->4->null
 *              fast end on null, slow end on 3
 */
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if(head==null||head.next==null) {return true;} // OJ says so, whatever
        ListNode fast=head,slow=head,halfHead=head;

        // 1. find the mid point
        while(fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        halfHead = (fast==null)?slow:slow.next;    // even/odd number of nodes

        // 2. reverse order
        Stack<ListNode> stack = new Stack<>();
        ListNode runner = head;
        while(runner!=slow){
            stack.add(runner); runner=runner.next;
        }

        // 3. compare
        while(!stack.empty()){
            if(stack.pop().val!=halfHead.val){return false;}
            halfHead = halfHead.next;
        }
        return true;
    }

    public static void main(String[] args){
        PalindromeLinkedList list = new PalindromeLinkedList();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(0);
        ListNode three = new ListNode(0);
        one.next = two;
        two.next = three;
        System.out.println(list.isPalindrome(one));
    }
}
