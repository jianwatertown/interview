package sortAndSearch;

public class BinarySearch {
	
	public static int findTargetRecursive(int[] nums, int s, int e, int target){
		if(e<s || 0>nums.length || s<0){return s-1;}
		int index = (s+e)/2;
		if(nums[index]==target){
			return index;
		}
		else if(nums[index]>target){
			return findTargetRecursive(nums,s,index-1,target);
		}
		else{ 	//nums[index]<target
			return findTargetRecursive(nums,index+1,e,target);
		}
	}
	
	// find the index of matching value
	// or the index of element that's just smaller than the element
	public static int findTargetIterative(int[] nums,int target){
		int s = 0;
		int e = nums.length-1;
		while(s>=0 && e<nums.length && s<=e){
			int i = (s+e)/2;
			if(nums[i]==target) {return i;}
			else if(nums[i]>target) {e=i-1;}
			else {s=i+1;}
		}
		return s-1;
	}
	
	
	public static void main(String[] args){
		//			  0,1,2,3,4,5,6,7,8, 9 ,10,11,12
		int[] nums = {1,2,3,4,5,6,7,8,11,34,56,78,90};
		int target = 56;
		
		// iteratively
		System.out.println(BinarySearch.findTargetIterative(nums, target));
		System.out.println(BinarySearch.findTargetIterative(nums, 0));
		System.out.println(BinarySearch.findTargetIterative(nums, 91));
		System.out.println(BinarySearch.findTargetIterative(nums, 60));
		
		// recursively
		System.out.println(BinarySearch.findTargetRecursive(nums, 0,nums.length-1,target));
		System.out.println(BinarySearch.findTargetRecursive(nums, 0,nums.length-1,0));
		System.out.println(BinarySearch.findTargetRecursive(nums, 0,nums.length-1,91));
		System.out.println(BinarySearch.findTargetRecursive(nums, 0,nums.length-1,60));
	}
}
