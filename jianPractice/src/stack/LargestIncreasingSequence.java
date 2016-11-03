package stack;

import java.util.Stack;

/**
 * Similar to LargestRectangleInHistogram
 * @author jian.wang
 *
 */
public class LargestIncreasingSequence {

    public int lengthOfLIS(int[] nums) {
        if(nums.length==0) {return 0;}
        
    	Stack<Integer> lisEndingTop = new Stack<Integer>();
    	int i = 0;
    	int maxSizeSoFar = 0;
    	while(i<nums.length){
    		if(lisEndingTop.isEmpty()||nums[i]>nums[lisEndingTop.peek()]){
    			lisEndingTop.add(i);
    			i++;
    		}
    		else{
    			maxSizeSoFar = Math.max(maxSizeSoFar, lisEndingTop.size());
    			lisEndingTop.pop(); // evit smaller element
    		}
    	}
		maxSizeSoFar = Math.max(maxSizeSoFar, lisEndingTop.size());
    	return maxSizeSoFar;
    }
    
    public static void main(String[] args){
    	
   // 	System.out.println(new LargestIncreasingSequence().lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    	System.out.println(new LargestIncreasingSequence().lengthOfLIS(new int[]{1,3,6,7,9,4,10,5,6}));
    }
}
