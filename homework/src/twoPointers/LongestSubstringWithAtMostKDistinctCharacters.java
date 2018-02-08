package twoPointers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jianwang on 4/11/17.
 */
public class LongestSubstringWithAtMostKDistinctCharacters {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(k==0) {return 0;}
        if(s.length()<=1) {return s.length();}
        Map<Character,Integer> map = new HashMap<>();
        int begin = 0,end =0, max=0;
        int distinctCount = 0;
        while (end<s.length()){
            // 1.shrink window when distinctCount>k
            if(distinctCount>k){
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
                if(distinctCount<=k) {max = Math.max(max,end-begin+1);}
                map.put(c,count+1);
                end++;
            }
        }
        return max;
    }
}
