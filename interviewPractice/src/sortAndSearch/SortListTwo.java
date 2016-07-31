package sortAndSearch;

import leetcode.dataStructures.ListNode;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 
 * Reasoning: 
 * 		1. Comparison based sorting requires nLogN. 
 * 		2. Use the head to be the pivot value  --- Quick Sort
 * 		3. for all the elements from 1 to N-1, go to either S(<=head)
 * or B(>head) 4. Sort S and B 5. Merge S,B into one
 * 
 * Complexity 1. n first pass 2. n/2 second pass 3. n/4.... so it's nLogN
 * 
 * Algorithm 1. Take the head out as the starting value P 2. sHead is a subList
 * whose ListNode of node<=head.value sEnd is the end of the List bHead is a
 * subList whose ListNode of node>head.value bEnd is the end of the List 3. R
 * from head+1 until the end a) if R.value<head, extend sEnd to R b) if
 * R.value>=head, extend bEnd to R R points to the next element 4. Merge S,P,
 * and B
 * 
 * Base cases: 1. only 1 element, return right away
 * 
 * @author jian.wang
 *
 *
 *  ***Always make the pointer points to a legit node ****
 */
public class SortListTwo {
	
	public ListNode sortList(ListNode head){
		
		// return head when null
		if(head==null||head.next==null){
			return head;
		}
		
		ListNode dummy1 = new ListNode(0);   // <---- dummy nodes are very cool
		ListNode dummy2 = new ListNode(0);
		
		ListNode sEnd=dummy1;
		ListNode bEnd=dummy2;
		ListNode runner = head.next;	
		ListNode mEnd=head;
		
		while(runner!=null){
			// go to middle
			if(runner.val==head.val){
				mEnd.next = runner;  // extend end 
				mEnd = runner;
			}			
			// go to small list
			else if(runner.val<head.val){
				sEnd.next = runner;  // extend end 
				sEnd = runner;
			}
			// go to big list
			else{
				bEnd.next = runner;
				bEnd = runner;
			}
			runner = runner.next;
		}

		// bEnd,mEnd,sEnd all points to legit nodes
		bEnd.next = mEnd.next = sEnd.next = null;
		dummy1.next = sortList(dummy1.next);
		dummy2.next = sortList(dummy2.next);
		
		// beautiful merging
		ListNode newEnd = dummy1;
		while(newEnd.next!=null){
			newEnd = newEnd.next;
		}
		newEnd.next = head;		// head is a legit node too
		mEnd.next=dummy2.next;	
		
		return dummy1.next;
	}


	public void printAList(String surfix, ListNode r){
		System.out.println(surfix);
		while(r!=null){
			System.out.print(r.val+" ");
			r= r.next;
		}
		System.out.println();
	}
	
	
	public ListNode getTail(ListNode head){
		
		if(head==null){
			return head;
		}
		while(head.next!=null){
			head=head.next;
		}
		return head;
	}
	
	public static void main(String[] args) {
		ListNode n0 = new ListNode(4);
		ListNode n1 = new ListNode(19);
		ListNode n2 = new ListNode(14);
		ListNode n3 = new ListNode(5);
		ListNode n4 = new ListNode(-3);
		ListNode n5 = new ListNode(1);
		ListNode n6 = new ListNode(8);
		ListNode n7 = new ListNode(5);
		ListNode n8 = new ListNode(11);
		ListNode n9 = new ListNode(15);
		

		n0.next = n1;
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		n7.next = n8;
		n8.next = n9;

		ListNode newHead = new SortListTwo().sortList(n0);
		
		ListNode r = newHead;
		while (r != null) {
			System.out.println(r.val);
			r = r.next;
		}
	}
}
