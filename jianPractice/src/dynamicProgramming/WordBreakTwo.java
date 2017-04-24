package dynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by jianwang on 4/23/17.
 */
public class WordBreakTwo {

    public List<String> wordBreak(String s, List<String> wordDict) {

        if(s==null||s.length()==0) {return new LinkedList<>();}

        // to skip ["aa..(lots of 'a').b", "a","aaaaa"...so on] case
        boolean[] breakable = new boolean[s.length()];
        Arrays.fill(breakable,false);
        for(int i=0;i<s.length();i++){
            for(int j=0;j<=i;j++){
                breakable[i] = (j>0?breakable[j-1]:true /*previous always breakable*/) &&
                        wordDict.contains(s.substring(j,i+1));
                if(breakable[i]) {break;}
            }
        }

        if(!breakable[s.length()-1]){return new LinkedList<>();}


        Map<Integer,List<String>> endAt = new HashMap<>();

        for(int i=0;i<s.length();i++){
            for(int j=0;j<=i;j++){

                List<String> jList = new LinkedList<>();
                String second = s.substring(j,i+1);

                if(wordDict.contains(second)) {

                    // [0...i]
                    if (j == 0) {
                        jList = new LinkedList<>();
                        jList.add("");
                    }
                    // [0,j-1,j,...]
                    else if (endAt.containsKey(j - 1)){
                            jList = endAt.get(j-1);
                    }

                    // add to existing result for i
                    List<String> existing = endAt.containsKey(i)?endAt.get(i):new LinkedList<>();
                    for(String str:jList){
                        existing.add(str.equals("")?second:(str+" "+second));
                        endAt.put(i,existing);
                    }
                }
            }
        }
        return endAt.containsKey(s.length()-1)?endAt.get(s.length()-1): new LinkedList<>();
    }
}
