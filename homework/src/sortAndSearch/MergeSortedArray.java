package sortAndSearch;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * @author jian.wang
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. 
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * 
 * Input case:
 * 	[0]	0
	[1] 1
 * 
 * 
 */
public class MergeSortedArray {
	
		
    public void merge(int[] nums1, int m, int[] nums2, int n) {
    
    	// 0. edge case
    	if(nums1==null || nums1.length<m || nums2==null || nums2.length<n || n<=0){
    		return;
    	}
    	
    	// 0.0 crazy case, nums1 is not initialized
    	if(m<=0){
    		for(int i=n-1;i>=0;i--){
    			nums1[i] = nums2[i];
    		}
    	}
    	  
    	// 1. 0<=i=m-1, and 0<=j=n-1, targetIndex [0,m+n-1]
    	int i = m-1;
    	int j = n-1;
    	int targetIndex = m+n-1;

    	// 2. loop from the end of nums1 and nums2
    	// 3. pick the bigger one and assign it to targetIndex, targetIndex--
    	// 4. until either nums1 or nums2 is empty, or both
    	while(i>=0 && j>=0){
    		
    		if(nums1[i]>=nums2[j]){
    			nums1[targetIndex--] = nums1[i--];
    		}
    		else{
    			nums1[targetIndex--] = nums2[j--];
    		}
    	}// i and j stops at the next element to be inserted, and end in -1

    	// 5. append the remaining elements
    	while(i>=0){
    		nums1[targetIndex--] = nums1[i--];
    	}
    	while(j>=0){
    		nums1[targetIndex--] = nums2[j--];
    	}
    }    
}
