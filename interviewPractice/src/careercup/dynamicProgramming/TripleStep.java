package careercup.dynamicProgramming;

import java.util.Arrays;

public class TripleStep {

	public int countWays(int n){
		int[] steps = new int[n+1];			// n+1, 0 step is also a solution
		Arrays.fill(steps, -1);
		return countWays(n, steps);
	}
	
	public int countWays(int n, int[] steps){
		if(n<0){return 0;}
		else if(n==0){return 1;}		// found a way
		else{
			if(steps[n]!=-1){return steps[n];}
			else{
				int computed = countWays(n-1,steps)+
						countWays(n-2,steps)+
						countWays(n-3,steps);
				steps[n]=computed;
				return computed;
			}
		}
	}
}
