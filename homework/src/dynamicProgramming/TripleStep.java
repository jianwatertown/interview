package dynamicProgramming;

import java.util.Arrays;

public class TripleStep {

	public static int countWays(int n){
		int[] steps = new int[n+1];			// n+1, 0 step is also a solution
		Arrays.fill(steps, -1);
		return countWays(n, steps);
	}
	
	public static int countWays(int n, int[] steps){
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

	public static int countWaysDP(int n){
		int[] steps = new int[n+1];
		Arrays.fill(steps,0);
		steps[0] = 1;
		for(int i=0;i<=n;i++){
			if(i+1<=n) {steps[i+1]+=steps[i];}
			if(i+2<=n) {steps[i+2]+=steps[i];}
			if(i+3<=n) {steps[i+3]+=steps[i];}
		}
		return steps[n];
	}

	public static void main(String[] args) {
		for (int i = 5; i < 20; i++) {
			System.out.println(i+" "+countWays(i)+" "+countWaysDP(i));
		}
	}
}
