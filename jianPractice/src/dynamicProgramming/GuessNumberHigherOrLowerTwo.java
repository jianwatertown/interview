package dynamicProgramming;

/**
 * Created by jianwang on 1/1/17.
 *
 * We are playing the Guess Game. The game is as follows:

 I pick a number from 1 to n. You have to guess which number I picked.

 Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

 However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.

 Example:

 n = 10, I pick 8.

 First round:  You guess 5, I tell you that it's higher. You pay $5.
 Second round: You guess 7, I tell you that it's higher. You pay $7.
 Third round:  You guess 9, I tell you that it's lower. You pay $9.

 Game over. 8 is the number I picked.

 You end up paying $5 + $7 + $9 = $21.
 Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.


 https://leetcode.com/problems/guess-number-higher-or-lower-ii/

 Definition of dp[i][j]: minimum number of money to guarantee win for subproblem [i, j].

 Target: dp[1][n]

 Corner case: dp[i][i] = 0 (because the only element must be correct)

 Equation: we can choose k (i<=k<=j) as our guess, and pay price k. After our guess, the problem is divided into two subproblems.
 Notice we do not need to pay the money for both subproblems.

 We only need to pay the worst case (because the system will tell us which side we should go) to guarantee win.
 So dp[i][j] = min (i<=k<=j) { k + max(dp[i][k-1], dp[k+1][j]) }
 */

public class GuessNumberHigherOrLowerTwo {
    public int getMoneyAmount(int n) {

        // 1. edge
        if(n<=1) {return 0;}

        // 2. problem(n) -> problem(1,n) -> generlize problem(i,j) when i=1 and j=n
        // dp[i][j] get money between i and j
        // n+1 to be easier for index computation
        int[][] dp = new int[n+1][n+1];

        // 3. get i, j from previous
        for(int i=1;i<=n;i++){
            for(int j=i;j<=n;j++){
//                if(i==j) {
//                    dp[i][j] = 0;
//                }
//                else{
                    dp[i][j] = Integer.MAX_VALUE;
                    for(int k = i;k<=j;k++){
                        dp[i][j] = Math.min(
                                k + Math.max(/*i to k-1 */(k-1>=i?dp[i][k-1]:0),/*k+1 to j*/(k+1<=j?dp[k+1][j]:0)),
                                dp[i][j]);
//                    }
                }
            }
        }

        // 4. a specific case
        return dp[1][n];
    }

    public static void main(String[] args){
        GuessNumberHigherOrLowerTwo t = new GuessNumberHigherOrLowerTwo();
        System.out.println(t.getMoneyAmount(3));
    }
}














