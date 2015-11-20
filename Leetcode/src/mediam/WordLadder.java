package mediam;

import java.util.*;

public class WordLadder {
	
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
    	
    	// elements can be "found" - return it
    	// or "visited" (weather or not it's found) - do not visit again
    	// or "to be checked"
        Map<String,Integer> found = new HashMap<String,Integer>();
        Set<String> visited = new HashSet<String>();
        
        found.put(beginWord,0);
        return getEndWith(endWord, wordList,found,visited);
    }
    
    public int getEndWith(String current, Set<String> dict, Map<String,Integer> found, Set<String> visited){

    	if(found.containsKey(current)){
            return found.get(current);
        }
        else{
        	visited.add(current);
        	
            int result = Integer.MAX_VALUE;
            List<String> buddies = findBuddies(current,dict,visited);
            for(String bud:buddies){
                result = Math.min(getEndWith(bud,dict,found,visited),result);
            }
            found.put(current,result+1);
            return result+1;
        }
    }
    
    public List<String> findBuddies(String s,Set<String> dict, Set<String> visited){
        List<String> result = new ArrayList<String>();
        for(int i=0;i<s.length();i++){
            for(int j=0;j<26;j++){
                StringBuffer b =  new StringBuffer(s);
                char c = (char)('a'+j);
                b.setCharAt(i,c);
                if(b.toString()=="hit"){
                System.out.println(b);}
                // if new node and valid
                if(!visited.contains(b.toString())&&dict.contains(b.toString())){
                    result.add(b.toString());
                }
            }
        }
        return result;
    }
    
    public static void main(String[] args){
    	WordLadder w = new WordLadder();
    	System.out.println(w.ladderLength("hit","hot",  new HashSet<String>(Arrays.asList("hit", "hot","dot","dog","cog"))));
    }
}
