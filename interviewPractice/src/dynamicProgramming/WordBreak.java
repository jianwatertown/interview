package dynamicProgramming;

import java.util.Arrays;
import java.util.Set;

// for any 0<=i<=j<0 s.length
// if a[i..j] is a word, then the return wordBreak
// if i==s.length-1, then it's all found out
// use cache a[i] to remember if a[i...n] is breakable
// 0 unset, 1 yes, -1 no
public class WordBreak {
	
    public boolean wordBreak(String s, Set<String> wordDict) {
    	if(s.length()==0) {return false;}
    	int[] cache = new int[s.length()];
    	Arrays.fill(cache, 0);
    	return wordBreak(s,0,wordDict,cache);
    }
    
    public boolean wordBreak(String s, int head, Set<String> wordDict, int[] cache){
    	
    	if(head>=s.length()) {return true;}
    	if(cache[head]!=0) {return cache[head]==1;}
    	
    	for(int i=head;i<=s.length();i++){
    		/// if a[i...j] is in
    		if(wordDict.contains(s.substring(head, i)) &&
    				wordBreak(s,i,wordDict,cache)){
    			cache[head] = 1;
    			return true;
    		}
    	}
    	cache[head] = -1;
    	return false;
    }
}
