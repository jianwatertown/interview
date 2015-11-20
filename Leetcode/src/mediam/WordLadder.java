package mediam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Map<String,Integer> found = new HashMap<String,Integer>();
        found.put(beginWord,0);
        return getEndWith(endWord, wordList,found);
    }
    
    public int getEndWith(String end, Set<String> dict, Map<String,Integer> found){
        if(found.containsKey(end)){
            return found.get(end);
        }
        else{
            int result = Integer.MAX_VALUE;
            List<String> buddies = findBuddies(end,dict);
            for(String bud:buddies){
                result = Math.min(getEndWith(bud,dict,found),result);
            }
            found.put(end,result+1);
            return result+1;
        }
    }
    
    public List<String> findBuddies(String s,Set<String> dict){
        List<String> result = new ArrayList<String>();
        for(int i=0;i<s.length();i++){
            for(int j=0;j<26;j++){
                StringBuffer b =  new StringBuffer(s);
                char c = (char)('a'+j);
                b.setCharAt(i,c);
                if(dict.contains(b)){
                    result.add(b.toString());
                }
            }
        }
        return result;
    }
    
    public static void main(String[] args){
    	
    }
}
