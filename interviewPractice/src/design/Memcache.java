package design;

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