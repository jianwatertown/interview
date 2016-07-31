package dynamicProgramming;
import java.util.*;

public class WordLadder {
	
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {

    	/**
    	 * 1. 	Words are organized Graph, so this is Graph traversal problem
    	 * 2.	Graph needs a "visited" set (graph traversal always needs this)
    	 * 
    	 * 		DFS approach does not work. Because different travel preferences give you different visiting paths,
    	 * 		DFS generates local optimal, not necessarily global optimal.
    	 * 
    	 *		Algorithm: BFS by level order
    	 *
    	 *		1. Iterative: left Queue, right Queue
    	 *  	2. Recursion
    	 *  
    	 *  	Undirected graph
    	 *  	1. Set "visited" has node seen so far
    	 *  
    	 *  	Utility:
    	 *  	1.	function to find "friends"
    	 *  
    	 */
        return ladderLengthWithTwoQueues(endWord, wordList);
    }
    
    
    public int ladderLengthWithTwoQueues(String head, Set<String> dict){

    	// init
    	Queue<String> leftQ = new PriorityQueue<String>();
    	Queue<String> rightQ = new PriorityQueue<String>();
    	Set<String> seen = new HashSet<String>();
    	seen.add(head);
    	leftQ.add(head);
    	
    	int level=0;
    	
    	// when either of them is empty
    	while((!leftQ.isEmpty())|| (!rightQ.isEmpty())){
    		
    		// scan left queue
    		while(!leftQ.isEmpty()){
    			String current = leftQ.poll();
    			if(current.equals(head)){
    				return level;
    			}
    			else{
    				seen.add(current);
    				for(String child: findBuddies(current,dict,seen)){
    					rightQ.add(child);
    				}
    			}
    		}
    		
    		// going to switch level
    		level++;
    		
    		// right is empty
    		// scan right queue
    		while(!rightQ.isEmpty()){
    			String current = rightQ.poll();
    			if(current.equals(head)){
    				return level;
    			}
    			else{
    				seen.add(current);
    				for(String child: findBuddies(current,dict,seen)){
    					leftQ.add(child);
    				}
    			}
    		}
    		
    		// going to switch level
    		level++;
    	}
    	
    	// made out of the loop, then it's a dead loop
    	return -1;
    }

    // Exclude from this set
    public List<String> findBuddies(String myself, Set<String> wordList, Set<String> excludeSet){
    	
        List<String> result = new ArrayList<String>();
        
        // for every character in myself
        for(int i=0;i<myself.length();i++){
        	
        	// change it to the 26 character
            for(int j=0;j<26;j++){

            	StringBuffer b =  new StringBuffer(myself);
            	char c = (char)('a'+j);
            	b.setCharAt(i,c);
                
                // if new node and valid
                if(!myself.equals(b.toString()) 				// not itself
                		&& !excludeSet.contains(b.toString())	// not exclude
                		&& wordList.contains(b.toString())){	// valid
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
