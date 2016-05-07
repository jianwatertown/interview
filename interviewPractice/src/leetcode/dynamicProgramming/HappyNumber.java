package leetcode.dynamicProgramming;

import java.util.HashSet;
import java.util.Set;


/**
 * Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, 
replace the number by the sum of the squares of its digits, and repeat the process until the number 
equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers 
for which this process ends in 1 are happy numbers.
 * @author jian.wang
 *
 */
public class HappyNumber {
    
    public boolean isHappy(int n) {
	
	Set<Integer> seen = new HashSet<>();
		
	return isHappy(n,seen);
    }
    
    public boolean isHappy(int n, Set<Integer> seen ){

	System.out.println(n);
	
	// 1. the only successful path
	if(n==1)
	    return true;
	// 2. if we have explorer the path
	else{
	    if(seen.contains(n)){
		return false;
	    }
	    // 3. mark visited before visit because we might never return
	    else{
		seen.add(n);
		int result = getSum(n);
		return isHappy(result,seen);
	    }
	}
    }
    
    
    public int getSum(int n){
	System.out.println("computing for "+n);
	int sum = 0;
	while(n>0){
	    // 123 -> power(3,2) -> 6
	    int digit  = (int) Math.pow(n%10,2);
	    
	    sum+=digit;
	    
	    // 123 -> 12, 12->1
	    n = n/10;
	}
	System.out.println("sum="+sum);
	return sum;
    }    
    
    public static void main(String[] args){
	new HappyNumber().isHappy(7);
    }
}
