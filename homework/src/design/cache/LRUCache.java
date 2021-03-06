package design.cache;

import java.util.HashMap;
import java.util.Map;


/*
* 		2 most important primitives are:
* 		1) removeNode(Node n) removes any node
* 				used in deleting when reaching max count
	* 			used in updating existing node by removing then adding
* 		2) addNodeToTheHead
*
* **/
public class LRUCache {
    int capacity = 0;
    Map<Integer,CacheNode> lookup = new HashMap<>();
    CacheNode fakeHead = new CacheNode(null,null,0,0);
    CacheNode fakeTail = new CacheNode(null,null,0,0);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        fakeHead.next = fakeTail;
        fakeTail.prev = fakeHead;
    }
    
    public void set(int key, int value) {
    	if(this.capacity==0) {
    		return;
    	}
    	
    	// new item
    	if(!lookup.containsKey(key)){
    		    		
    		// 1. add to head
    		CacheNode cacheNode = new CacheNode(null/*previous*/,null /*next*/,key,value);
    		addNodeToHead(cacheNode);

    		// 2. remove the tail
    		if(lookup.size()>capacity){
    			removeNode(fakeTail.prev);
    		}
    	}
		// existing item
    	else{
    		// reset value if needed
    		CacheNode cacheNode = lookup.get(key);
    		cacheNode.value = value;
			removeNode(lookup.get(key));
			addNodeToHead(lookup.get(key));
    	}
    }

	public int get(int key) {
		if(!lookup.containsKey(key)){return -1;}
		else{
			removeNode(lookup.get(key));
			addNodeToHead(lookup.get(key));
			return lookup.get(key).value;
		}
	}

    public void removeNode(CacheNode cacheNode){
    	
    	if(cacheNode==fakeTail || cacheNode == fakeHead) {return;}

		// 0. setup
		CacheNode oldPrev = cacheNode.prev;
		CacheNode oldNext = cacheNode.next;

		// 1. connect prev with next
		oldPrev.next = oldNext;
		oldNext.prev = oldPrev;
		lookup.remove(cacheNode.key);
    }
    
    public void addNodeToHead(CacheNode cacheNode){
    	if(fakeHead.next==cacheNode) {return;}
		CacheNode oldHead = fakeHead.next;
		cacheNode.next = oldHead;
		oldHead.prev = cacheNode;
		fakeHead.next = cacheNode;
		lookup.put(cacheNode.key, cacheNode);
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
    	LRUCache cache = new LRUCache(3);
    	cache.set(1, 1);
    	cache.set(2, 2);
    	cache.set(3, 3);
    	cache.set(4, 4);
    	System.out.println(cache.get(4));
    	System.out.println(cache.get(3));
    	System.out.println(cache.get(2));
    	System.out.println(cache.get(1));
    	cache.set(5, 5);
    	System.out.println(cache.get(1));
    }
}