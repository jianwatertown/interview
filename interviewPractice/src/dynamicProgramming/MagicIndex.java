package dynamicProgramming;

public class MagicIndex {

/**
 * Tricky when the numbers are not unique	
 * 
 * Great base case break down
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
			
			// search left and right
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
