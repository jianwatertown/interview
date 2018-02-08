package twoPointers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jianwang on 4/10/17.
 *
 * Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

     For example, Given s = “eceba”,

     T is "ece" which its length is 3.
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s.length()<=1) {return s.length();}
        Map<Character,Integer> map = new HashMap<>();
        int begin = 0,end =0, max=0;
        int distinctCount = 0;
        while (end<s.length()){
            // 1.shrink window when distinctCount>2
            if(distinctCount>2){
                Character c = s.charAt(begin);
                int count = map.get(c);
                if(count==1){
                    distinctCount--;
                }
                map.put(c,count-1);
                begin++;
            }
            // 2. expand window
            else{
                Character c = s.charAt(end);
                int count = map.containsKey(c)?map.get(c):0;
                if(count==0){distinctCount++;}
                if(distinctCount<=2) {max = Math.max(max,end-begin+1);}
                map.put(c,count+1);
                end++;
            }
        }
        return max;
    }

    public static void main(String[] args){
        LongestSubstringWithAtMostTwoDistinctCharacters test =
                new LongestSubstringWithAtMostTwoDistinctCharacters();
        System.out.println(test.lengthOfLongestSubstringTwoDistinct("eceba"));
    }
}
