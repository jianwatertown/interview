package design;


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
 * 	THe latter calls function first before doing stuff
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
    
    public static void main(String[] args){
    	Trie t = new Trie();
//    	t.insert("redfin");
//    	System.out.println(t.search("red"));
//    	System.out.println(t.startsWith("red"));
    	t.insert("a");
    	t.insert("b");
    	t.insert("c");
    	System.out.println(t.search("b"));
    }
}
