package stack;

import java.util.Stack;

public class LargestRectangleInHistogram {

    // to keep the distance , we need to put the indexes, not the height in the stack
	public int largestRectangleArea(int[] heights) {

    	Stack<Integer> stack = new Stack();
		int index = 0;
		int max = 0;

		while(index<=heights.length){

			int height = (index==heights.length)?-1:heights[index]; // put -1 dummy index to pop everything in the stack

			if(index==4){
				System.out.println("hi");
			}
			// 1. if height is higher, put into the stack
			if(stack.isEmpty()||height>=heights[stack.peek()]){
				stack.push(index++);
			}
			// 2. something in the stack is bigger, current height cannot be used to compute the size
			// not wa don't need to change index here
			else{
				int popUpIndex = stack.pop();

                // stack.peek() is the one *left* to the one getting popped, that's as far as it can go
                int width = (stack.isEmpty() ? index : index - stack.peek() - 1);
				max = Math.max(max, heights[popUpIndex] * width);
			}
		}
    	return max;
	}


	public static void main(String[] args){
    	
 //   	int[] heights0 = {3,5,5,2,5,5,6,6,4,4};	// 24
 //   	int[] heights1 = {100,4};				// 100
 //   	int[] heights2 = {33,1,1,1,21};			// 33
 //   	int[] heights3 = {2,1,5,6,2,3};			// 10
    	
    	LargestRectangleInHistogram nn = new LargestRectangleInHistogram();
    	
    	int[][] heightsList = new int[1][];
//    	heightsList[0] = heights0;
//    	heightsList[1] = heights1;
//    	heightsList[2] = heights2;
//    	heightsList[3] = heights3;
//

		heightsList[0] = new int[]{2,1,5,6,2,3};
    	for(int[] heights:heightsList){
           	System.out.println("---------------------------------------------------");
           	nn.largestRectangleArea(heights);
    	}
    }

}
