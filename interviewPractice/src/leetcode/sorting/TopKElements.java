package leetcode.sorting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/*
Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note: 
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the 
array's size.

Solution: O(nlogn) comparison based sorting is at least O(nlogN) so this is at least 
bucket sort.

http://stackoverflow.com/questions/185697/the-most-efficient-way-to-find-top-k-frequent-words-in-a-big-word-sequence


1. build a frequency map
    key[frequency] = 1[12],2[12],5[3],99[1]
2. Notice the max(frequency)<=n
    so we can build an List of frequency-key
    1[9],3[5],12[1,2]
3.  Go from the end of the frequency-key array and backwards

*/
public class TopKElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        
        // 1. get key-freq map
        Map<Integer,Integer> keyFreqMap = getKeyFreqMap(nums);
    
        // 2. get frequency-key Array
        List<List<Integer>> freqKeyList = generateFreqKeyList(keyFreqMap, nums.length);
        
        // 3. lookup the top k
        return findTopK(freqKeyList,k);
    }

    public Map<Integer,Integer> getKeyFreqMap(int[] nums){
    
        Map<Integer,Integer> freqMap = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(freqMap.containsKey(nums[i])){
                freqMap.put(nums[i],freqMap.get(nums[i])+1);
            }
            else{
                freqMap.put(nums[i],1);
            }
        }
        return freqMap;
    }
    
    public List<List<Integer>> generateFreqKeyList(Map<Integer,Integer> inputMap, int size){
    
        List<List<Integer>> resulstList = new ArrayList<>();

        // 1. create a array with the size of the input elements
        for(int i=0;i<size;i++){				
            resulstList.add(new ArrayList<Integer>());
        }
        
        // 2. bucket sort population
        for (Entry<Integer, Integer> entry : inputMap.entrySet()) {

            // value is the index (e.g. 4 times, in the 3rd(4-1) slot)
            List<Integer> keyList = resulstList.get(entry.getValue()-1);
            // key is the value in the content
            keyList.add(entry.getKey());
        }
        return resulstList;
    }
    
    public List<Integer> findTopK(List<List<Integer>> freqKeyList, int top){
        List<Integer> result = new ArrayList<>();
        for(int i=freqKeyList.size()-1;i>=0;i--){
            result.addAll(freqKeyList.get(i));
            if(result.size()>=top){
                break;
            }
        }
        return result;
    }
        
     public static void main(String[] args){
	 TopKElements test = new TopKElements();
	 int[] nums = {1,1,1,2,2,3}; // 1
	 int k = 1;
	 System.out.println(test.topKFrequent(nums, k));
     }
}
