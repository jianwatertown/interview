package dynamicProgramming;

public class ClimbingStairs {

    public int climbStairs(int n) {
        int[] steps = new int[n+1];
        steps[0] = 1;
        for(int i=0;i<=n;i++){
            if(i+1<=n) {steps[i+1]+=steps[i];}
            if(i+2<=n) {steps[i+2]+=steps[i];}
        }
        return steps[n];
    }
}
