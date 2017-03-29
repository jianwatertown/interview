package twoPointers;

import java.util.HashMap;

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
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max=0;
        for (int i=0, j=0; i<s.length(); ++i){
            if (map.containsKey(s.charAt(i))){
                j = Math.max(j,map.get(s.charAt(i))+1);
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-j+1);
        }
        return max;
    }

    public int lengthOfLongestSubstring2(String s) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int length = 0;
        for(int begin=0,end=0;end<s.length();end++){
            char c = s.charAt(end);
            if (map.containsKey(c)){        // seen character before

                // starting right of the character
                // or keep its original place, which might have skipped
                // c already
                begin = Math.max(begin,map.get(c)+1);


            }
            map.put(c,end);
            length = Math.max(length,end-begin+1);
        }
        return length;
    }
    public static void main(String[] args){
        LongestSubstringWithoutRepeatingCharacters tester = new LongestSubstringWithoutRepeatingCharacters();
        tester.lengthOfLongestSubstring2("abba");

    }
}
