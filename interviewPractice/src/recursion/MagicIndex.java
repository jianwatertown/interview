package recursion;

public class MagicIndex {

/**
 * Tricky when the numbers are not unique	
 * 
 * Great base case break down
 * 	index	[0,1,2,3,4,5,6,7,8]
 * 	input	[3,4,5,6,6,6,7,8,8]
 *  look at index = 4, input = 6 -> right: at least starting at 6(input), left starts at 3(4-1)
 * 
 *  index	[ 0, 1, 2, 3,4,5,6,7,8]
 * 	input	[-5,-4,-3, 0,0,1,5,5,5]
 *  look at index index 4, input 0 -> right: 4+1, left at least 0 (index[4])
 *  
 *  So:
 *  index<=input[index] left = index-1, right = input[index]
 *  index>input[index] left = input[index], right = index+1
 *		
 *		left = min(index-1,input[index])
 *		right = max(index+1, input[index])
 *
 *	Combine the two:
 *		
 * 
 * @param input
 */
	
	public void findMagicIndex(int[] input){
		findMagicIndex(input,0,input.length-1);
	}
	
	public void findMagicIndex(int[] input, int b, int e){

		// 1. end condition
		if(b>e){
			return;
		}
		// 2. b<=e
		else{
			int index = (b+e)/2;
			if(input[index]==index){
				System.out.println(index);
			}
			
			// search left *and* right
			int leftIndex = Math.min(index-1, input[index]);
			int rightIndex = Math.max(index+1, input[index]);
			
			findMagicIndex(input,b,leftIndex);
			findMagicIndex(input,rightIndex,e);
		}
	}
	
	public static void main(String[] args){
		MagicIndex instance = new MagicIndex();
		int[] input = {1,2,3,4,5,5};
		instance.findMagicIndex(input);
	}
	
}
