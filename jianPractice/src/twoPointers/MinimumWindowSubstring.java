package twoPointers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jianwang on 4/10/17.
 *
 *
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

     For example,
     S = "ADOBECODEBANC"
     T = "ABC"
     Minimum window is "BANC".

     Note:
     If there is no such window in S that covers all characters in T, return the empty string "".

     If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.

     // source = c,a,b,c,d,b,
     // target = c,d
     // when found = [c,a,b,c,d] need to move first "c" to the 2nd "c"

 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {

        if(s==null||t==null||s.length()<t.length()) {return "";}

        // 1. put t into a map
        Map<Character,Integer> map = new HashMap<>();
        for(Character c:t.toCharArray()){
            int count = map.containsKey(c)?map.get(c):0;
            map.put(c,count+1);
        }

        // 2. go through source string, stop with all matched
        int matchNeeded = t.length();
        int begin = 0, end = 0, distance=Integer.MAX_VALUE;

        while(end<s.length()){
            Character endChar = s.charAt(end);
            Character beginChar = s.charAt(begin);

            // a. expand window, move end, until valid
            if(map.containsKey(endChar)){

                int count = map.get(endChar);
                if(count>0) {matchNeeded--;}

                count--;end++;
                map.put(endChar,count);
            }
            // b. shrink window, move begin, until no longer valid
           while(matchNeeded==0 /*valid*/){
                // update min window size
                if(distance>(end-begin+1)) {distance = end-begin+1;}

                if(map.containsKey(beginChar)){
                    int count = map.get(beginChar);
                    if(count==0){matchNeeded++; /*no longer valid*/}
                    count++;begin++;
                    map.put(beginChar,count);
                }
            }
        }

        return matchNeeded==0?s.substring(begin,end+1):"";
    }
}







