package dfs;

import linkedlist.ListNode;
import tree.TreeNode;

/**
 * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/#/solutions
 */
public class ConvertSortedListToBinarySearchTree {

    public TreeNode sortedListToBST(ListNode head) {
        if(head==null) {return null;}
        return helper(head,null);
    }

    // notice this helper does not handle null cases
    public TreeNode helper(ListNode head, ListNode exTail)
    {
        if(head==exTail) {return null;}
        ListNode slow = head;
        ListNode fast = head;
        // end on exTail or its parent
        while(fast!=exTail&&fast.next!=exTail){
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode n = new TreeNode(slow.val);
        n.left = helper(head,slow);
        n.right = helper(slow.next,exTail);
        return n;
    }
}
