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
 */
public class SortList {
	
	public ListNode sortList(ListNode head){
		return head!=null?sortListAndReturnEnds(head).first:null;
	}
	
	// given a LinkedList start at head,
	// sort the list and return a Pair(first element, last element)
	// return null if head is null
	public Pair sortListAndReturnEnds(ListNode head){
		
		// return null
		if(head==null){
			return null;
		}
//		printAList("--------current head "+head.val, head);
		ListNode smallListHead=null;
		ListNode smallListEnd=null;
		ListNode bigListHead=null;
		ListNode bigListEnd=null;

		// until runner points to null
		ListNode runner = head.next;		
		while(runner!=null){
			// go to small list
			if(runner.val<=head.val){
				if(smallListHead==null){
					smallListHead = runner; // head points to runner and stays there
					smallListEnd = runner;
				}
				else{
					smallListEnd.next = runner;  // extend end 
					smallListEnd = runner;
				}
			}
			// go to big list
			else{
				if(bigListHead==null){
					bigListHead = runner;
					bigListEnd = runner;
				}
				else{
					bigListEnd.next = runner;
					bigListEnd = runner;
				}
			}
			runner = runner.next;
		}
		
		if(smallListEnd!=null) smallListEnd.next=null;
		if(bigListEnd!=null) bigListEnd.next=null;
		
//		System.out.println("current smallListHead="+(smallListHead!=null?smallListHead.val:"null"));
//		System.out.println("current smallListEnd="+(smallListEnd!=null?smallListEnd.val:"null"));
//		System.out.println("current bigListHead="+(bigListHead!=null?bigListHead.val:"null"));
		Pair smallPair = sortListAndReturnEnds(smallListHead);
//		System.out.println(smallPair);
		Pair bigPair = sortListAndReturnEnds(bigListHead);
//		System.out.println(bigPair);

		return merge(smallPair,bigPair,head);
	}
	
	public Pair merge(Pair smallPair, Pair bigPair, ListNode head){

		ListNode newHead=null;
		ListNode newEnd = null;
		
		if(smallPair==null||smallPair.first==null||smallPair.last==null){
			newHead = head;
		}
		else{
			newHead = smallPair.first;
			smallPair.last.next = head;			
		}
		head.next = bigPair!=null?bigPair.first:null;
		newEnd = bigPair!=null?bigPair.last:head;
		return new Pair(newHead,newEnd);
	}


	public void printAList(String surfix, ListNode r){
		System.out.println(surfix);
		while(r!=null){
			System.out.print(r.val+" ");
			r= r.next;
		}
		System.out.println();
	}
	
	public class Pair{
		ListNode first;
		ListNode last;
		public Pair(ListNode first, ListNode last) {
			this.first = first;
			this.last = last;
		}
		
		@Override
		public String toString(){
			return ("Pair value = " +(first!=null?first.val:"null")+" "+(last!=null?last.val:"null"));
		}
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
		ListNode n0 = new ListNode(3);
		ListNode n1 = new ListNode(2);
		ListNode n2 = new ListNode(4);

		n0.next = n1;
		n1.next = n2;

		ListNode newHead = new SortList().sortList(n0);
		
		ListNode r = newHead;
		while (r != null) {
			System.out.println(r.val);
			r = r.next;
		}
	}
}
