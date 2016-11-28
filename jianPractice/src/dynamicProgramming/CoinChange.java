package dynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 
	Check input: [1,2147483647]
	Line 20: java.lang.ArrayIndexOutOfBoundsException: -2147483648
	
	check this line:
		if(i+coin<=n && i<Integer.MAX_VALUE-coin){			// coin + previous value is within boundary
	why not?
		if(i+coin<=n && i+coin<Integer.MAX_VALUE){			// because 1+2147483647=-2147483648
	or you can use 
		i+coin >0
	
 */
public class CoinChange {
	
	// needs to  rename to "coinChange" to run
	// iterative approach goes from left to right, so that each slot has more than 1 chances
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
    				if(i+coin<=n && i<Integer.MAX_VALUE-coin){			// coin + previous value is within boundary
        				coinsNeeded[i+coin] = coinsNeeded[i+coin]==-1?	// not set before?
        						(coinsNeeded[i]+1):						// new value
        							Math.min(coinsNeeded[i]+1, coinsNeeded[i+coin]);	// new value v.s. previously set
    				}
    			}
    		}
    	}
    	return coinsNeeded[n];
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