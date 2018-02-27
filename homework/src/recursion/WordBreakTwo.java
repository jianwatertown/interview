package recursion;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class WordBreakTwo {
    HashMap<String,List<String>> map = new HashMap<String,List<String>>();


    public List<String> wordBreak(String s, List<String> wordDict) {

        // 1. empty
        List<String> result = new LinkedList<>();
        if(s==null||s.length()==0) {return result;}

        // 2. cache
        if(map.containsKey(s)) {return map.get(s);}

        // 3. whole word
        if(wordDict.contains(s)) {result.add(s);}

        // 4. check s.substring(0,i) && s.substring(i,s.length())
        for(int i=0;i<s.length();i++){
            String second = s.substring(i,s.length());
            if(wordDict.contains(second)){
                List<String> firstList = wordBreak(s.substring(0,i),wordDict);
                if(firstList.size()!=0) {
                    for(String first:firstList){
                        result.add(first+" "+second);
                    }
                }
            }
        }

        // 5. result handling
        map.put(s,result);
        return result;
    }

}
