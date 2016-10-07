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
	
		// the following works!
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
	
	// not working!!!
    public int numDecodings2(String s) {
    	Map<String,Integer> cache = new HashMap<String,Integer>();
    	return numDecodings(s,cache);
    }
    
    public int numDecodings(String s, Map<String,Integer> cache){
    	if(s==null || s.length()==0 ){ return 0;}
    	if(cache.containsKey(s)) return cache.get(s);
    	int ways = 0;
    	if(s.length()<=2) { ways = getSizeTwoCount(s);}
    	else{
    		// *Note* how the substring is working here
        	for( int i=1;i<s.length();i++){
        		// classic slide
        		int leftWays = numDecodings(s.substring(0,i));
        		int rightWays = numDecodings(s.substring(i,s.length()));
        		ways += leftWays*rightWays;
        	}    		
    	}
    	cache.put(s, ways);
    	return ways;
    }
    
    public int getSizeTwoCount(String s){
    	if(s==null| s.length()==0 || s.length()>2) {return 0;}
    	
    	// '03'
    	if(s.length()==2 && s.charAt(0)=='0') {
    		return 0;
    	}
    	
    	Integer v = Integer.parseInt(s);
 
    	if(v<=0) {return 0;}
    	else if(v>0 && v<=10){ return 1;}
    	else if(v>=11 && v<=26) {return 2;} // 11 or 1,1
    	else{	// v>26
    		return (v%10==0)?0:1;		// 30,40 -> 0. 31->1
    	}
    }
}
