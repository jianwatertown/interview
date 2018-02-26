package sortAndSearch;

/**
 * 	[1,3,5,7,9]
 * 
 *   [7,9,1,3,5]
 * 
 * 	What we know
 * 	The array has 2 parts 1) sorted and 2) unsorted	
 * 	* in the unsorted part, it also has 1) sorted and unsorted portion
 *  so algorithm
 *  
 *  	1) slit array
 *  	2) find in normal when possible
 * 		3) recurse on abnormal
 * 
 * Test
 * 	1) null/[] , 3
 *  2) [3], 3
 *  3) [3], 4
 *  4) [2,3,4,-1,1] 1
 *  5) [3,1] 1
 * @author jian.wang
 *
 */
public class SearchInRotatedArray {

	public int search(int[] nums, int target) {
		int minIdx = findMinIdx(nums);
		if (target == nums[minIdx]) return minIdx;
		int m = nums.length;
		int start = (target <= nums[m - 1]) ? minIdx : 0;
		int end = (target > nums[m - 1]) ? minIdx : m - 1;

		while (start <= end) {
			int mid = start + (end - start) / 2;
			if (nums[mid] == target) return mid;
			else if (target > nums[mid]) start = mid + 1;
			else end = mid - 1;
		}
		return -1;
	}

	public int findMinIdx(int[] nums) {
		int start = 0, end = nums.length - 1;
		while (start < end) {
			int mid = start + (end -  start) / 2;
			if (nums[mid] > nums[end]) start = mid + 1;
			else end = mid;
		}
		return start;
	}
//
//	int search(int A[], int n, int target) {
//		int lo=0,hi=n-1;
//		// find the index of the smallest value using binary search.
//		// Loop will terminate since mid < hi, and lo or hi will shrink by at least 1.
//		// Proof by contradiction that mid < hi: if mid==hi, then lo==hi and loop would have been terminated.
//		while(lo<hi){
//			int mid=(lo+hi)/2;
//			if(A[mid]>A[hi]) lo=mid+1;
//			else hi=mid;
//		}
//		// lo==hi is the index of the smallest value and also the number of places rotated.
//		int rot=lo;
//		lo=0;hi=n-1;
//		// The usual binary search and accounting for rotation.
//		while(lo<=hi){
//			int mid=(lo+hi)/2;
//			int realmid=(mid+rot)%n;
//			if(A[realmid]==target)return realmid;
//			if(A[realmid]<target)lo=mid+1;
//			else hi=mid-1;
//		}
//		return -1;
//	}
//};
	
//    public int search(int[] nums, int target) {
//
//    	if(nums==null||nums.length==0) {return -1;}
//
//    	return search(nums,0,nums.length-1,target);		// ([3,1],0,1,1)
//    }
//
//    public int search(int[] nums, int start, int end, int target){
//
//    	// 0. out of bound
//    	if(nums==null|| nums.length==0|| start>end || start<0 || end>nums.length-1){
//    		return -1;
//    	}
//
//    	// 1. base case
//    	int mid = (start+end)/2;				//  0 = (0+1)/2
//    	if(target==nums[mid]) {return mid;}		// nums[0]= 3 != 1
//
//
//    	// 2. left is sorted [left,mid-1]
//    	if(nums[start]<nums[mid]){				//
//    		// 2.1  if we are in the normal range, search there
//    		if(nums[start]<=target && target<nums[mid]){		// [1,2,3], 0,0, 1
//    			return bindarySearch(nums,start,mid-1,target);
//    		}
//    		else{
//    		// 2.2 bad range
//    			return search(nums,mid+1,end,target);		//  ([2,3,4,-1,1],3,4,1)
//    		}
//    	}
//
//    	// 2. right normal [mid+1,end] if it exists
//    	else{
//    		if(mid+1<=end){
//           		// 3.1  if we are in the normal range, search there
//        		if(nums[mid+1]<=target && target<=nums[end]){
//        			return bindarySearch(nums,mid+1,end,target);
//        		}
//        		else{
//        		// 3.2 bad range
//        			return search(nums,start,mid-1,target);
//        		}
//        	}
//    		else{
//    			return -1; // there is no right side
//    		}
//    	}
//    }
//
//    // assume we can find it
//    public int bindarySearch(int[] nums, int start, int end, int target){
//
//    	if(nums==null || nums.length==0 || start>end || start<0 || end>nums.length-1){
//    		return -1;
//    	}
//    	int mid = (start+end)/2;
//    	if(target==nums[mid]) {return mid;};
//    	return nums[mid]>target?
//    			bindarySearch(nums,start,mid-1,target):
//    			bindarySearch(nums,mid+1,end,target);
//    }
}
