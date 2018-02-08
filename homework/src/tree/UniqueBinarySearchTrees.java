package tree;

import java.util.HashMap;
import java.util.Map;


/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 * @author jian.wang
 *
 */
public class UniqueBinarySearchTrees {

	public int numTrees(int n) {
		if(n==0) return 1;
		Map<Integer,Integer> cache = new HashMap<Integer,Integer>();
		cache.put(0, 1);
		return numTree(n, cache);
    }
	
	public int numTree(int n, Map<Integer,Integer> cache){
		
		// 1. memory
		if(cache.containsKey(n)) {return cache.get(n);}
		
		// 2. recursion
		int current = 0;
		for(int i=1;i<=n;i++){
			int left = numTree(i-1,cache);
			int right = numTree(n-i,cache);
			current += left*right;
		}
		cache.put(n, current);
		return current;
	}
}
