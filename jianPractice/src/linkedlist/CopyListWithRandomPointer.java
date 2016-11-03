package linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * A linked list is given such that each node contains an additional random 
 * pointer which could point to any node in the list or null. Return a deep 
 * copy of the list.
 * @author jian.wang
 * public class RandomListNode {
	 int label;
	 RandomListNode next, random;
	 RandomListNode(int x) { this.label = x; }
 };
 *   0 -> 1 -> 2 -> 3 -> 4
 *   1. map<original,copy> 
 *   2. copy as you go
 */
public class CopyListWithRandomPointer {
	
    public DirtyListNode copyRandomList(DirtyListNode head) {
        if(head==null) return null;
    	Map<DirtyListNode,DirtyListNode> map = new HashMap<DirtyListNode,DirtyListNode>();
    	copyRandomList(head,map);
    	return map.get(head);
    }
    
    public void copyRandomList(DirtyListNode head, Map<DirtyListNode,DirtyListNode> map) {

    	if(head==null) return;
    	
    	// 1. create node and put into cache
    	DirtyListNode copy = new DirtyListNode(head.value);
    	map.put(head,copy);
    
    	// 2. copy next
    	if(head.next!=null && !map.containsKey(head.next)){
    		copyRandomList(head.next, map);
    	}
    	copy.next = map.get(head.next);
    	
    	// 3. copy random
    	if(head.dirty!=null && !map.containsKey(head.dirty)){
    		copyRandomList(head.dirty, map);
    	}    	
    	copy.dirty = map.get(head.dirty);
    }
}
