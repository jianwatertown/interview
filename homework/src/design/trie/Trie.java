package design.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;


/**
 * Your Trie object will be instantiated and called as such:
 * Trie trie = new Trie();
 * trie.insert("lintcode");
 * trie.search("lint"); will return false
 * trie.startsWith("lint"); will return true
 */
	//insert("lintcode")
	//search("code") // return false
	//startsWith("lint") // return true
	//startsWith("linterror") // return false
	//insert("linterror")
	//search("lintcode) // return true
	//startsWith("linterror") // return true

/**
 * 
 * 
 * 	Big note on function(++i) vs function(i++)
 * 	THe latter calls function first before  doing stuff
 * 
 * @author jian.wang
 *
 */
public class Trie {
	
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }


    public void insert(String word) {
    	TrieNode r = root;
    	// Insert is easier to be implemented in an iterative way    	
    	for(int i=0;i<word.length();i++){
    		Character c = word.charAt(i);
    		// No there? create one!
    		if(!r.children.containsKey(c)){
    			r.children.put(c, new TrieNode());
    		}
    		r = r.children.get(c);
    	}
    	r.endHere = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
    	return searchHelper(root,word,false /*notEndHereOk*/, 0);
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
    	return searchHelper(root,prefix,true /*notEndHereOk*/, 0);
    }
    
    // search is easier with recursion
    public boolean searchHelper(TrieNode n, String word, boolean notEndHereOk, int i){
    	Character c = word.charAt(i);
    	// 1. key is there
    	if(n.children.containsKey(c)){
    		// 2. end
    		if(i==word.length()-1) {return notEndHereOk||n.children.get(c).endHere;}
    		// 3. more
    		else{
    			return searchHelper(n.children.get(c), word, notEndHereOk, ++i);
    		}
    	}
    	// not here
    	else{
    		return false;
    	}
    }

    // find the node iteratively
    public List<String> suggest(String prefix){
    	
    	TrieNode r = root;
    	List result = new ArrayList<String>();
    	
    	// 1. find the node first
    	for(int i=0;i<prefix.length();i++){
    		Character c = prefix.charAt(i);
    		if(r.children.containsKey(c)){
    			r = r.children.get(c);
    		}
    		else{
    			return result;
    		}
    	}
    	// 2. now assemble the result
    	for(String oneSuffix:findSuggestionFromNode(r)){
    		result.add(prefix+oneSuffix);
    	}
    	return result;    	
    }
    
    public List<String> findSuggestionFromNode(TrieNode r){

    	List<String> result = new ArrayList();
    	// 1. base case nothing at this level
    	result.add("");
    	
    	// 2. some character points to here
		for(Character c: r.children.keySet()){
			List<String> childrenStringList = findSuggestionFromNode(r.children.get(c));
			for(String childStr:childrenStringList){
    			result.add(c+childStr);
			}
    	}
   		return result;
    }
    
    
    public static void main(String[] args){
    	Trie t = new Trie();
    	t.insert("redfin");
    	t.insert("red");
    	t.insert("ron");
    	t.insert("ronaldo");
//    	System.out.println(t.search("red"));
//    	System.out.println(t.startsWith("red"));
//    	t.insert("a");
//    	t.insert("b");
//    	t.insert("c");
    	System.out.println(t.suggest("r"));
    	System.out.println(t.suggest("d"));
    }
}
