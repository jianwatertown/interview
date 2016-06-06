package leetcode.heap;

import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, 
 * not the kth distinct element.
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 * Note: 
 * 
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 * @author jian.wang
 *
 */
public class KthLargestElement {
    
    public int findKthLargest(int[] nums, int k) {
	PriorityQueue<Integer> heap = new PriorityQueue<>(nums.length, new Comparator<Integer>(){
	    public int compare(Integer a, Integer b) {
	        if (a < b) return +1;
	        if (a.equals(b)) return 0;
	        return -1;
	    }
	});
	for(int i=0;i<nums.length;i++){
	    heap.add(nums[i]);
	}
	int largest = 0;
	for(int i=0;i<k;i++){
	    largest = (int) heap.poll();
	}
	return largest;
    }
    
    public static void main(String[] args){
	KthLargestElement test = new KthLargestElement();
	int[] nums = {1,1,1,2,2,3}; // 1
    	int k = 1;
    	System.out.println(test.findKthLargest(nums, k));
    }
}
