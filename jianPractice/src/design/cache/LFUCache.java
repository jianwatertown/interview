package design.cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;


/**
 * At first I didn’t understand why we didn’t need to care about nextMin and we just could add 1 to min.
 * Now it makes sense that min will always reset to 1 when adding a new item. And also, min can never jump
 * forward more than 1 since updating an item only increments it’s count by 1.
 *
 *
 *
 * Leetcode's answer has issues,
 *
 * Input:
 ["LFUCache","put","put","get","put","get","get","put","get","get","get"]
 [[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
 Output:
 [null,null,null,1,null,-1,3,null,1,3,4]
 Expected:
 [null,null,null,1,null,-1,3,null,-1,3,4]


    previous visting *metrics* should not be evicted
 */
public class LFUCache {

    int capcity;
    Map<Integer,Integer> cache = new HashMap<>();
    Map<Integer,Integer> access = new HashMap<>();
    Map<Integer, LinkedHashSet<Integer>> freqMap = new HashMap<>();

    public LFUCache(int c) {capcity = c;}

    public int get(int key) {
        if(cache.containsKey(key)){
            increaseFreqFor(key);
            return cache.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        cache.put(key,value);
        increaseFreqFor(key);
        if(cache.size()>capcity){removeElementAccessedOnce();}
    }

    public void increaseFreqFor(int key){

        // 1. update frequency map
        int freq = access.getOrDefault(key,0);
        access.put(key,freq+1);

        // 2. update new order map
        LinkedHashSet<Integer> newOrder = freqMap.getOrDefault(freq+1,new LinkedHashSet<>());
        newOrder.add(key);
        freqMap.put(freq+1,newOrder);

        // 3. delete from old map
        LinkedHashSet<Integer> oldOrder = freqMap.getOrDefault(freq,new LinkedHashSet<>());
        if(oldOrder.size()>=1) {oldOrder.remove(key);}
    }

    public void removeElementAccessedOnce(){
        if(freqMap.containsKey(1) && freqMap.get(1).size()>0){
            int key = freqMap.get(1).iterator().next();

            // 1. take this out of the cache map
            cache.remove(key);

            // 2. take this out from frequency map
            access.remove(key);

            // 3. take this out from order map
            freqMap.get(1).remove(key);
        }
    }
}
