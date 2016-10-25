package design;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    int capacity = 0;
    Map<Integer,CacheNode> lookup = new HashMap<>();
    CacheNode head = null;
    CacheNode tail = null;
    int size = 0;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public void set(int key, int value) {
    	if(this.capacity==0) {
    		return;
    	}
    	
    	// new item
    	if(!lookup.containsKey(key)){
    		// 1. add to head
    		CacheNode cacheNode = new CacheNode(null/*previous*/,null /*next*/,key,value);
    		lookup.put(key,cacheNode);
    		size++;

    		//  first element
    		if(head==null){
    			head = cacheNode;
    			tail = cacheNode;
    		}
    		//  not first, update pointers of old head
    		else{
    			CacheNode oldHead = head;
    			oldHead.prev = cacheNode;
    			cacheNode.next = oldHead;
    			head = cacheNode;
    		}

    		// 2. remove the tail
    		if(size>capacity){
    			// remove tail element
    			CacheNode oldTail = tail;
    			int oldKey = oldTail.key;
    			lookup.remove(oldKey);
    			size--;
    			
    			// reset tail
    			tail = tail.prev;
    		}
    	}
		// existing item
    	else{
    		// reset value if needed
    		CacheNode cacheNode = lookup.get(key);
    		cacheNode.value = value;
    		moveNodeToHead(cacheNode);
    	}
    }
    
    public void moveNodeToHead(CacheNode cacheNode){
    	
		if(head!=cacheNode){
			// connect the neighbours
			CacheNode oldPrev = cacheNode.prev;
			CacheNode oldNext = cacheNode.next;
			oldPrev.next = oldNext;
			
			// cacheNode is the last one
			if(oldNext==null){
				tail = oldPrev;
			}
			// not last one
			else{
    			oldNext.prev = oldPrev;
			}
			// move cacheNode to the front
			CacheNode oldHead = head;
			cacheNode.next = oldHead;
			oldHead.prev = cacheNode;
			head = cacheNode;
		}
    }

	public int get(int key) {
        if(!lookup.containsKey(key)){
        	return -1;
        }
        else{
        	moveNodeToHead(lookup.get(key));
        	return lookup.get(key).value;
        }
    }

    public class CacheNode{
    	int value;
    	int key;
    	CacheNode next;
    	CacheNode prev;
    	public CacheNode(CacheNode prev,CacheNode next, int key, int value){
    		this.prev = prev;
    		this.next = next;
    		this.value = value;
    		this.key = key;
    	}
    }
    
    public static void main(String[] args){
    	LRUCache cache = new LRUCache(1);
    	cache.set(2, 1);
    	cache.set(3, 2);
    	
    }
}