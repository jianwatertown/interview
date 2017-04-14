package twoPointers;

import java.util.HashMap;
import java.util.Map;

/**
 * the basic idea is, keep a hashmap which stores the characters in string as keys and their positions as values
 * Given "abcabcbb", the answer is "abc", which the length is 3.

 Given "bbbbb", the answer is "b", with the length of 1.

 Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring,
 "pwke" is a subsequence and not a


 the basic idea is, keep a hashmap which stores the characters in string as keys and
 their positions as values, and keep two pointers which define the max substring.
 move the right pointer to scan through the string , and meanwhile update the hashmap.
 If the character is already in the hashmap, then move the left pointer to the right of
 the same character last found.

 *Note that the two pointers can only move forward.*
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if(s.length()<=1) {return s.length();}
        Map<Character,Integer> map = new HashMap<>();
        int begin = 0,end = 0, max=1;
        boolean isDupe = false;
        while (end<s.length()){
            // 1.shrink window when distinctCount>2
            if(isDupe){
                Character c = s.charAt(begin);
                int count = map.get(c);
                if(count==2){
                    isDupe=false;
                }
                map.put(c,count-1);
                begin++;
            }
            // 2. expand window
            else{
                Character c = s.charAt(end);
                int count = map.containsKey(c)?map.get(c):0;
                if(count>0){isDupe=true;}
                if(!isDupe) {max = Math.max(max,end-begin+1);}
                map.put(c,count+1);
                end++;
            }
        }
        return max;
    }

    public static void main(String[] args){
        LongestSubstringWithoutRepeatingCharacters tester = new LongestSubstringWithoutRepeatingCharacters();
        tester.lengthOfLongestSubstring("abba");

    }
}
