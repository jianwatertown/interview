package recursion;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
    
    'A' -> 1
    'B' -> 2
    ...
    'Z' -> 26
    Given an encoded message containing digits, determine the total number of ways to decode it.
    
    For example,
    Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
    
    The number of ways decoding "12" is 2.
    
 * @author jian.wang
 *
 */
public class DecodeWays {
	
     public int numDecodings(String s) {
            if( s.compareTo("") == 0) return 0;
            
            int[] ways = new int[s.length()+1];
            ways[s.length()] = 1;
            int prev = -1;
            
            for (int i = s.length()-1;i >= 0;i--) {
                int cur = Character.getNumericValue(s.charAt(i));
                if (cur != 0) {
                    ways[i] = (prev !=-1 && cur*10+prev<=26) ? ways[i+2]+ways[i+1] : ways[i+1];
                }
                prev = cur;
            }
            
            return ways[0];
	}

	public static void main(String[] argss){
		DecodeWays w = new DecodeWays();
    	System.out.println(w.numDecodings("111"));
	}

}
