package dynamicProgramming;
import java.util.*;

public class WordLadder {
	
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
    	Map<String,Integer> visited = new HashMap<>();
    	Set<String> visiting = new HashSet<>();
        return find(beginWord,endWord, wordList,visited,visiting)+1;
    }
    
    
    public int find(String beginWord, String endWord, Set<String> wordList, Map<String,Integer> visited, Set<String> visiting) {

    	if(beginWord.equals(endWord)){
    		return 0;
    	}

    	int length = Integer.MAX_VALUE;

    	// 1. find all friends
    	Set<String> friends = findFriends(beginWord,wordList);
    	visiting.add(beginWord);

    	for(String frd:friends){
    		if(visiting.contains(frd)){
    			continue;
    		}
    		// 2. visited
    		if(visited.containsKey(frd)){
    			if(visited.get(frd)!=-1)
    				length = Math.min(length,visited.get(frd)+1);
    		}
    		// 3. unvisited friend
    		else{

    			int distance = find(frd, endWord, wordList, visited, visiting);
    			if(distance!=-1){
    				length = Math.min(distance+1,length);
    			}
    		}
    	}
    	// not found
    	if(length==Integer.MAX_VALUE){
    		length = -1;
    	}
    	
    	visiting.remove(beginWord);
    	visited.put(beginWord,length);
    	return length;
    }

    // Exclude from this set
    public Set<String> findFriends(String myself, Set<String> wordList){
    	
    	Set<String> result = new HashSet<String>();
    	for(String other: wordList){
    		if(isFriend(myself,other)){
    		result.add(other);}
    	}
        return result;
    }
    
    
    public boolean isFriend(String a, String b){
    	if(a==null||b==null||a.length()!=b.length()||a.equals(b)) {return false;}
    	int diffCount = 0;
    	for(int i=0;i<a.length();i++){
    		if(a.charAt(i)!=b.charAt(i)){
    			diffCount++;}
    		if(diffCount>1) return false;
    	}
    	return true;
    }
    
    public static void main(String[] args){
    	WordLadder w = new WordLadder();
    	System.out.println(w.ladderLength("hit","cog", 
    			new HashSet<String>(Arrays.asList("hit", "hot","dot","dog","cog","cit","cot"))));
    }
}
