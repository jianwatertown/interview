package design.cache;


/**
 * Implement a memcache which support the following features:

    get(curtTime, key). Get the key's value, return 2147483647 if key does not exist.
    set(curtTime, key, value, ttl). Set the key-value pair in memcache with a time to live (ttl). 
    The key will be valid from curtTime to curtTime + ttl - 1 and it will be expired after ttl seconds. 
    if ttl is 0, the key lives forever until out of memory.
    delete(curtTime, key). Delete the key.
    
    incr(curtTime, key, delta). Increase the key's value by delta return the new value. 
    Return 2147483647 if key does not exist.
    
    decr(curtTime, key, delta). Decrease the key's value by delta return the new value. 
    Return 2147483647 if key does not exist.
    
    It's guaranteed that the input is given with increasingcurtTime.
    
    Actually, a real memcache server will evict keys if memory is not sufficient, 
    and it also supports variety of value types like string and integer. In our case, let's make it simple,
    we can assume that we have enough memory and all of the values are integers.

    Search "LRU" & "LFU" on google to get more information about how memcache evict data.
    
    Try the following problem to learn LRU cache:
    http://www.lintcode.com/problem/lru-cache
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Memcache {

	SortedSet<CacheNode> nodeSet = new TreeSet<>();
	Map<Integer,CacheNode> nodeMap = new HashMap<>();
    public Memcache() {}

    public int get(int curtTime, int key) {
        evitExpireBefore(curtTime);
    	if(nodeMap.containsKey(key)){
        	return nodeMap.get(key).value;
        }
    	else{
    		return Integer.MAX_VALUE;
        }
    }

    public void set(int curtTime, int key, int value, int ttl) {
    	evitExpireBefore(curtTime);
        delete(curtTime,key);
        addNode(new CacheNode(key,value,curtTime,ttl));
    }

    public void delete(int curtTime, int key) {
    	evitExpireBefore(curtTime);
    	removeNodeByKey(key);
    }
    
    public int incr(int curtTime, int key, int delta) {
    	evitExpireBefore(curtTime);
    	CacheNode n = getNodeByKey(key);
    	if(n!=null){
    		n.value +=delta;
    	}
    	return get(curtTime,key);
    }

    public int decr(int curtTime, int key, int delta) {
    	evitExpireBefore(curtTime);
    	CacheNode n = getNodeByKey(key);
    	if(n!=null){
    		n.value -=delta;
    	}
    	return get(curtTime,key);

    }
    
    public void evitExpireBefore(int curtTime){
    	CacheNode lookupNode = new CacheNode(curtTime);
    	Set<CacheNode> nodeExpired = nodeSet.headSet(lookupNode);
    	for(CacheNode n:nodeExpired){
    		removeNodeByKey(n.key);
    	}
    }
    
    public CacheNode getNodeByKey(int key){
    	if(nodeMap.containsKey(key)){
    		return nodeMap.get(key);
    	}
    	else{
    		return null;
    	}
    }
    
    public void removeNodeByKey(int key){ 
    	if(nodeMap.containsKey(key)){
    		CacheNode n = nodeMap.get(key);
    		nodeSet.remove(n);
    		nodeMap.remove(key);
    	}
    }
    
    public void addNode(CacheNode cacheNode){
    	nodeMap.put(cacheNode.key,cacheNode);
    	nodeSet.add(cacheNode);
    }
    
    public class CacheNode implements Comparable<CacheNode>{
    	int value;
    	int key;
    	Integer liveTil;				// use Integer nicely here!
    	
    	public CacheNode(int key, int value, int curTime, int ttl){
    		this.value = value;
    		this.key = key;
    		this.liveTil = ttl==0?Integer.MAX_VALUE:(curTime + ttl -1);
    	}
    	
    	public CacheNode(int curTime){
    		this.liveTil = curTime;
    	}

		@Override
		public int compareTo(CacheNode o) {
			return this.liveTil.compareTo(o.liveTil);
		}
    }
    
    public static void main(String[] args){
    	Memcache cache = new Memcache();
       	System.out.println(cache.get(1, 0));
    	cache.set(2, 1, 1, 2);
    	System.out.println(cache.get(3, 1));
 
    }
}