package sortAndSearch;

/**
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, 
 * 	with the colors in the order red, white and blue.

	Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

    Note:
    You are not suppose to use the library's sort function for this problem.
    
    Follow up:
    A rather straight forward solution is a two-pass algorithm using counting sort.
    First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, 
    	then 1's and followed by 2's.
    
    Could you come up with an one-pass algorithm using only constant space?
     * @author jian.wang
     * 
     * Solution
     *  
     *  Given: 1111111
     *  
     *  1. Invariant:
     *  	p0 points to the right-most 0 that has been examined, init p0=-1
     *  	p2 points to the left-most 2 that has been examined, init p2=len(num)
     *  	p0+1<=pScan<=p2-1
     *  
     *   
     *  2. Goal: scan from p0 to p2 and push the elements to either side
     *  
     *  3. Algorithm:
     *  	init p0=-1
     *  	p2=len(num)
     *  	init pScan=0
     * 		
     * 		when p0+1<=pScan<=p2-1 stays true 
     *  		check what pScan is pointing at
         *  		find 1, move on, pScan++
         *  		find 0, 
         *  			pScan swap with p0's next
         *  			p0++
         * 			find 2, pScan swap with p2's left 
         *  			p2--
     *  
     * 		(move pScan to be bigger than p0+1)
     * 
     *
 */

public class ColorSort {
    
    public void sortColors(int[] nums) {
	
	int p0=-1;
	int pScan=0;
	int p2=nums.length;
	
	while(pScan<=p2-1){
	    	    
//	    System.out.println("before one iteration: pScan="+pScan+" p0="+p0+" p2="+p2);
	    // 2
	    if(nums[pScan]==2){
		swap(nums,p2-1,pScan);
		p2--;
	    }
	    // 0
	    else if(nums[pScan]==0){
		swap(nums,p0+1,pScan);
		p0++;
	    }
	    // 1
	    else{
		pScan++;
	    }
	    pScan = pScan<=p0?p0+1:pScan;
//	    System.out.println("after one iteration: pScan="+pScan+" p0="+p0+" p2="+p2);
//	    printNums(nums);
	}
    }

    public void swap(int[] nums, int i, int j){
	int temp = nums[i];
	nums[i] = nums[j];
	nums[j] = temp;
    }

    public static void printNums(int[] nums){
	for(int i=0;i<nums.length;i++){
	    System.out.print(nums[i]+" ");
	}
	System.out.println();
    }
    
    public static void main(String[] args){
	
//	int[] input = {0,1,2,0,1,2,1,2,1,2};
//	int[] input = {1,0};
	int[] input = {0};
	
	System.out.println("input");
	printNums(input);
	
	ColorSort test = new ColorSort();
	test.sortColors(input);
	System.out.println("output");
	printNums(input);
    }
}
