package dynamicProgramming;

/**
 * Created by jianwang on 4/29/17.
 *
 * Big Idea: Given any n, we make a guess k. Then we break the interval [1,n] into [1,k - 1] and [k + 1,n].
 *  The min of worst case cost can be calculated recursively as

     cost[1,n] = k + max{cost[1,k - 1] + cost[k+1,n]}
     Also, it takes a while for me to wrap my head around "min of max cost".
    My understand is that: you strategy is the best, but your luck is the worst.
    You only guess right when there is no possibilities to guess wrong.
 */
public class GuessNumberLowerOrHigherTwo {

    public int getMoneyAmount(int n) {
        // all intervals are inclusive
        // uninitialized cells are assured to be zero
        // the zero column and row will be uninitialized
        // the illegal cells will also be uninitialized
        // add 1 to the length just to make the index the same as numbers used
        int[][] dp = new int[n + 1][n + 1]; // dp[i][j] means the min cost in the worst case for numbers (i...j)

        // iterate the lengths of the intervals since the calculations of longer intervals rely on shorter ones
        for (int l = 2; l <= n; l++) {
            // iterate all the intervals with length l, the start of which is i. Hence the interval will be [i, i + (l - 1)]
            for (int i = 1; i <= n - (l - 1); i++) {
                dp[i][i + (l - 1)] = Integer.MAX_VALUE;
                // iterate all the first guesses g
                for (int g = i; g <= i + (l - 1); g++) {
                    int costForThisGuess;
                    // since if g is the last integer, g + 1 does not exist, we have to separate this case
                    // cost for [i, i + (l - 1)]: g (first guess) + max{the cost of left part [i, g - 1], the cost of right part [g + 1, i + (l - 1)]}
                    if (g == n) {
                        costForThisGuess = dp[i][g - 1] + g;
                    } else {
                        costForThisGuess = g + Math.max(dp[i][g - 1], dp[g + 1][i + (l - 1)]);
                    }
                    dp[i][i + (l - 1)] = Math.min(dp[i][i + (l - 1)], costForThisGuess); // keep track of the min cost among all first guesses
                }
            }
        }
        return dp[1][n];
    }
}
