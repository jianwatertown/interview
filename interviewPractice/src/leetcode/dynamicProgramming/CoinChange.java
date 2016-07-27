package leetcode.dynamicProgramming;

import java.util.Arrays;


/**
 * Key to iterative approach
 * 	1. use min[i] to hold minimum number of coins needed to make i dollars
 *  2. use min[i] = n+1 for unattainable values, better than Integer.max
 *  3. check index out of bound: 
 *  		a)	int nextTarget = coin+target;
        		e.g. 2147483647 + 1 -> -2147483648, so we need to check if nextTarget>0
        		if(nextTarget>0&&nextTarget<=n)
        	b) single coin might be bigger than n
        	
        	
   TODO: recursive approach still has bug
 */
public class CoinChange {
	
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
        for(int target=0;target<=n;target++){
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
    
    public int minCoinsRecursive(int n, int[] coins, int mins[]){

    	
    	// -1 cannot reach
    	// -2 have not checked
    	if(mins[n]!=-2){
    		return mins[n];
    	}

    	if(n==0){
    		mins[0]=0;
    		return 0;
    	}
    	
    	// set to unattainable value
    	mins[n] = n+1;
    	for(int coin:coins){
    		
    		int remain = n-coin;

    		// 1. base case, one of them match
    		if(remain==0){
    			mins[n] = 1;
    			return 1;
    		}
    		// 2. find this recursively
    		else if(remain>0){
    			int minForUsingThisCoin = minCoinsRecursive(remain,coins,mins);
    			// if find anything
    			if(minForUsingThisCoin>=0){
    				mins[n] = Math.min(mins[n],minForUsingThisCoin+1);
    			}
    		}
    		// 3. if remain<0, then cannot use this coin, skip
    	}
    	return (mins[n] > n) ?  -1 : mins[n];
    }
    
    
    
    public int coinChange(int[] coins, int amount) {
    	int[] mins = new int[amount+1];
    	Arrays.fill(mins, -2);
    	return minCoinsRecursive(amount,coins,mins);
    }
    
    public static void main (String[] args) {
    	CoinChange change = new CoinChange();
//        System.out.println(change.minCoinsIterative(12, new int[] {1,2,6,7}));
        System.out.println(change.coinChange(new int[] {186,419,83,408},6249));
    }
}












