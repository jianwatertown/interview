package twoPointers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jianwang on 4/11/17.
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {
    public int longestSubstring(String s, int k) {
        // 1. find char count for s
        int max = 0;
        int[] charCount = new int[26];
        for(char c:s.toCharArray()){charCount[c-'a']++;}

        // 2. use the 1st character hole, to split string, pick longest one
        for(int i=0;i<26;i++){
            if(charCount[i-'a']>0 && charCount[i-'a']<k){
                for(String subString:s.split(String.valueOf('a'+i))){
                    max= Math.max(longestSubstring(subString,k),max);
                }
                break;

            }
        }
        return max;
    }
}
