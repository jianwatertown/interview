package stack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class LargestRectangleInHistogram {
	
	// https://discuss.leetcode.com/topic/39151/5ms-o-n-java-solution-explained-beats-96
	// 1. N*N
    public int largestRectangleAreaNN(int[] heights) {
    	int max_size = 0;
    	for(int i=0;i<heights.length;i++){
    		int width = (getLastFromRight(heights,i)-getFirstFromLeft(heights,i))+1;
    		max_size = Math.max(max_size, heights[i] *width);
    	}
    	return max_size;    	
    }
    
    public int getFirstFromLeft(int[] nums, int i){
    	
    	int result = 0;
    	
		for(int j=i-1;j>=0;j--){
			if(nums[j]<nums[i]){		// find first smaller
				return j+1;
			}
		}
    	return result;
    }
    
    public int getLastFromRight(int[] nums, int i){
    	
    	int result = nums.length-1;

		for(int j=i+1;j<nums.length;j++){
			if(nums[j]<nums[i]){		// find first bigger
				return j-1;
			}
    	}
    	return result;
    }
    
    public int largestRectangleAreaJumpToLeftSmall(int[] heights) {

    	// say: indexOfLessFromLeft[13] == 7
    	// means the first element whose height < heights[13] is the 7th element
    	int[] indexOfLessFromLeft = new int[heights.length];
    	Arrays.fill(indexOfLessFromLeft, -1);
    	
    	// say: indexOfLessFromRight[8] == 17
    	// means the last element whose height < heights[8] is the 17th element
    	int[] indexOfLessFromRight = new int[heights.length];
    	Arrays.fill(indexOfLessFromRight, heights.length);
    	
    	int max_area = 0;
    	
    	// 1. find left index
    	for(int i=1;i<heights.length;i++){
    		
    		int previousSmallIndex = i-1;
    		
    		// previous is taller, then its at least as far as
    		// previous' indexOfLessFromLeft
    		
    		// until find a smaller height or reached to the front
    		while(previousSmallIndex>=0 && heights[previousSmallIndex]>=heights[i]){
    			previousSmallIndex = indexOfLessFromLeft[previousSmallIndex];
    		}
    		
    		indexOfLessFromLeft[i] = previousSmallIndex;
    	}
    	
    	// 2. find right index
    	for(int i=heights.length-2;i>=0;i--){
    		
    		int nextSmallIndex = i+1;
    		// until find a smaller height or reached to the end
    		while(nextSmallIndex<=heights.length-1 && heights[nextSmallIndex]>=heights[i]){
    			nextSmallIndex = indexOfLessFromRight[nextSmallIndex];
    		}
    		
    		indexOfLessFromRight[i] = nextSmallIndex;
    	}
    	
    	// 3. compute the area
    	for(int i=0;i<heights.length;i++){
    		// 1,2,2,2,1
    		// indexOfLessFromRight=4 while indexOfLessFromLeft=0
    		int area = (indexOfLessFromRight[i]-indexOfLessFromLeft[i]-1)*heights[i];
    		max_area = Math.max(max_area, area);
    	}
    	return max_area;
    }
    
    // the classic stack implementation O(N)
    // http://fisherlei.blogspot.com/2012/12/leetcode-largest-rectangle-in-histogram.html
    // 
    //	stack saves the *index* of the elements that is *possible* to form an rectangle with current value
    // 
    public int largestRectangleAreaClassicStack(int[] heights) {
    	
    	Stack<Integer> reacheableFromCurrent = new Stack<Integer>();
    	int max_area = 0;
    	int i = 0;
    	
    	/**
    	 * Key points:
    	 * 1. only compute the area, when 
    	 * 			a) reached to the end, or 
    	 * 			b) smaller than left - that means it can use the left bar, or more to form a rectangle

    	 * 2. the stack at any given point saves all the elements that's *reachable*, 
    	 * 		because all the unreachable (caused by very small bar) has been popped()
    	 * * 
    	 */
    	while(i<=heights.length){
    		
    		// 0. fake node in the end 
        	int heightI = (i != heights.length) ? heights[i] : -1; //Dummy node after the end item to dequeue all.
    		
    		// 1. Forming a rectangle, not time to compute the size yet 
    		if(reacheableFromCurrent.size()==0 || heightI>=heights[reacheableFromCurrent.peek()]){
    			reacheableFromCurrent.push(i++);							// notice i++
    		}
    		
    		// 2. CurrentHight is smaller then left, now we can check
    		//  	*ALL* the bar sizes in [left,current-1], pop() until it's empty
    		else{
    			int left = reacheableFromCurrent.pop();
    			int width = reacheableFromCurrent.isEmpty()?i:  // "left" was the only one in the stack, which is the smallest one of the
    															// entire list
    															// all reachable to the left most
    				i-reacheableFromCurrent.peek()-1;			// [left,current-1] area
    															// reacheableFromCurrent.peek() is *not* part of the bar
    			int area = heights[left]*width;
    			max_area = Math.max(area,max_area);
    		}
    	}
    	
    	return max_area;
    }
    
    // copied from internet
    // https://discuss.leetcode.com/topic/26070/simple-35ms-o-n-java-solution-with-stack
    public int largestRectangleAreaReading(int[] heights) {
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0;
        int max = 0;
        while(i <= heights.length){
        	int heightI = (i != heights.length) ? heights[i] : -1;//Dummy node after the end item.
        	
            if(stack.isEmpty() || heights[stack.peek()] <= heightI){
                stack.push(i++);
            }else {//Height[i] is lower than height[top] of the stack.
                int last = stack.pop();
                max = Math.max(max, heights[last] * (stack.isEmpty() ? i : i - stack.peek() - 1));
            }
        }
        return max;
    }
    
    
    // divide and conqure 
    public int largestRectangleAreaDivideAndConque(int[] heights){
    	return helper(heights,0,heights.length-1);
    }
    
    public int helper(int[] heights, int start, int end){

    	// 1.base cases
    	// out of bound
    	if(start>end || start<0 || end>=heights.length){return 0;}
    	// only 1 bar
    	if(start==end) {return heights[start];}

    	// 2. compute the left and right part
    	int mid = (start+end)/2;
    	int leftPart = helper(heights,start,mid);				// make sure  *mid* is not ignored
    	int rightPart = helper(heights,mid+1,end);				

    	// 3. compute the area with middle
    	int leftStart = mid;
    	int rightEnd = mid+1;
    	        	
    	int middleHeight = Math.min(heights[mid],heights[mid+1]);
    	int middleArea = middleHeight * 2;
    	
    	
    	while(leftStart>=start && rightEnd<=end){
    		// TODO finish the complex logic here
    	}
    	
    	return Math.max(Math.max(leftPart, rightPart),middleArea);
    }
    
    public static void main(String[] args){
    	
    	int[] heights1 = {100,4};
    	int[] heights2 = {33,1,1,1,21};
    	int[] heights3 = {2,1,5,6,2,3};
    	int[] heights0 = {3,5,5,2,5,5,6,6,4,4};
    	
    	LargestRectangleInHistogram nn = new LargestRectangleInHistogram();
    	
    	int[][] heightsList = new int[1][];
    	heightsList[0] = heights0;
//    	heightsList[1] = heights1;
//    	heightsList[2] = heights2;
//    	heightsList[3] = heights3;
    	

    	for(int[] heights:heightsList){
    		
        	// 1. n*n    	
        	System.out.println("naive"+nn.largestRectangleAreaNN(heights));
        	
        	// 2. O(n) but few passes
        	System.out.println("classic-stack"+nn.largestRectangleAreaClassicStack(heights));
        	System.out.println("stack-others"+nn.largestRectangleAreaReading(heights));
        	System.out.println("stack-mine"+nn.largestRectangleAreaJumpToLeftSmall(heights));  
        	
        	
        	// 3. O(nLogN)
        	System.out.println("divide-and-conque"+nn.largestRectangleAreaDivideAndConque(heights));
        	System.out.println("---------------------------------------------------");
    	}
    }

}
