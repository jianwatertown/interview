package design;

import java.util.Collections;
import java.util.List;


/**
 * Check Trie.java, that is written better
 * 
 * @author jian.wang
 *
 */
public class TrieService {
    private TrieNode root = null;

    public TrieService() {
        root = new TrieNode();
    }

    public TrieNode getRoot() {
        return root;
    }

    public void insert(String word, int frequency) {
    	insertOnNode(word,frequency, getRoot());
    }
    
    public void insertOnNode(String word,int frequency, TrieNode p){

    	// 1. edge case
    	if(word==null||word.length()==0||p==null){
    		return;
    	}
    	
    	// 2. create first Node if needed
		Character first = word.charAt(0);
		if(!p.children.containsKey(first)){
			p.children.put(first,new TrieNode());
		}
		TrieNode next = p.children.get(first);

		// 3. maintain to p10 list
    	List<Integer> top10 = next.top10;
    	top10.add(frequency);
    	Collections.sort(top10, Collections.reverseOrder());
    	if(top10.size()>10) top10 = top10.subList(0, 10);
    	
    	// 4. next level if needed
		insertOnNode(word.substring(1),frequency,next);
    }
}
