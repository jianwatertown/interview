package dynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
	Check input: [1,2147483647]
	Line 20: java.lang.ArrayIndexOutOfBoundsException: -2147483648
	
 */
public class CoinChange {
	
	// needs to  rename to "coinChange" to run
    public int coinChangeBottomUpIterative (int[] coins, int n) {
    	if(n<=0) return 0;
    	
    	// 1.init
    	int[] coinsNeeded = new int[n+1];
    	Arrays.fill(coinsNeeded, -1);
    	coinsNeeded[0] = 0;
    	
    	// 2. for all reachable ones
    	for(int i=0;i<n;i++){
    		// cannot reach here
    		if(coinsNeeded[i]==-1){
    			continue;
    		}
    		else{
    			// 3. move to the next coin place
    			for(int coin:coins){
    				coinsNeeded[i+coin] = coinsNeeded[i+coin]==-1?	// not set before?
    						(coinsNeeded[i]+1):						// new value
    							Math.min(coinsNeeded[i]+1, coinsNeeded[i+coin]);	// new value v.s. previously set
    			}
    		}
    	}
    	return coinsNeeded[n];
    }
	
    public int minCoinsIterative (int n, int[] coins) {
    	
        // 1. mins[i] holds minimum number of coins needed to make i dollars
        int[] mins = new int[n+1];
        
        // 2. set unattainable values to n+1 so they won't affect the next calculations
        Arrays.fill(mins,n+1);

        // 3. set the mins
        mins[0] = 0;
        for(int coin:coins){
        	if(coin<=n){
        		mins[coin] = 1;
        	}
        }
        
        // 4. now build up the change, from target value 0 to n
        for(int target=1;target<=n;target++){
        	// look forward
        	for(int coin:coins){
        		int nextTarget = coin+target;
        		if(nextTarget>0&&nextTarget<=n){
        			mins[nextTarget] = Math.min(mins[nextTarget], mins[target]+1);
        		}
        	}
        }

        return (mins[n] > n) ?  -1 : mins[n];
    }

    
    // recursive, top-down
    public int coinChange (int[] coins, int n) {
    	Map<Integer,Integer> cache = new HashMap<Integer,Integer>();
    	cache.put(0, 0);
    	return coinChange(n,coins,cache);
    }
    
    public int coinChange (int n, int[] coins, Map<Integer,Integer> cache) {
    	
    	// 1. memory, boundary
    	if(n<0){ return -1;} // unreachable
    	if(cache.containsKey(n)) {return cache.get(n);}
    	    	int minCoin=Integer.MAX_VALUE;

    	// 2. recursion
    	for(int coin:coins){
    		int subCoin = coinChange(n-coin,coins,cache);
    		if(subCoin>=0){
    			minCoin = Math.min(subCoin+1, minCoin);
    		}
    	}
    	
    	// 3. return
    	minCoin= minCoin==Integer.MAX_VALUE? -1:minCoin;
    	cache.put(n,minCoin);
    	return minCoin;
    }

    
    public static void main (String[] args) {
    	CoinChange change = new CoinChange();
    	System.out.println(change.coinChangeBottomUpIterative( new int[] {1},1));
    }
}












