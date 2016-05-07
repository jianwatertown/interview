package leetcode.dynamicProgramming;

public class CoinChange {
	
    public static int minCoins (int n, int[] coins) {
        //mins[i] holds minimum number of coins needed to make i dollars
        int[] mins = new int[n+1];
        mins[0] = 0;
        
        //smallest coin in 'coins' array
        int minCoinSize = 0;
        
        //mins[coin] for each coin should be 1 (just use that coin)
        //and compute minCoinSize
        for (int coin : coins) {
            if (coin < minCoinSize) minCoinSize = coin;
            if (coin > n) break;
        }
        
        //set unattainable values to n+1 so they won't affect the next calculations
        for (int i = 1; i < minCoinSize; i++) mins[i] = n+1;
        
        // compute remaining mins[i]
        for (int i = minCoinSize; i < n+1; i++) {
            int minCoins = n+1;
            for (int coin : coins) {
                int rem = i - coin;
                if (rem >= 0 && mins[i - coin] < minCoins) minCoins = mins[i - coin] + 1;
            }
            mins[i] = minCoins;
        }
        
        int finalMin = mins[n];
        return (finalMin > n) ?  -1 : finalMin;
    }
    
    public static void main (String[] args) {
        System.out.println(minCoins(1, new int[] {2}));
    }
}
