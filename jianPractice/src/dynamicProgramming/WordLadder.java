package dynamicProgramming;
import java.util.*;



/***
 *
 *
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation
 * sequence from beginWord to endWord, such that:

	 Only one letter can be changed at a time.
	 Each transformed word must exist in the word list. Note that beginWord is not a transformed word.


 For example,

 Given:

	 beginWord = "hit"
	 endWord = "cog"
	 wordList = ["hot","dot","dog","lot","log","cog"]

	 As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
	 return its length 5.

 Note:
	 Return 0 if there is no such transformation sequence.
	 All words have the same length.
	 All words contain only lowercase alphabetic characters.
	 You may assume no duplicates in the word list.
	 You may assume beginWord and endWord are non-empty and are not the same.

 * 	If using DFS to compute the shortest path, the order of visiting nodes should first be topologically sorted. 
 * 	If it is not, there may be loops in the DFS forest (recursive tree). 
 *  For BFS, the visiting order is naturally topologically sorted.
 *  
 *   topologically sorted -> how far it is from the root
 * 	
 * 	BFS + Active pruning
 *
 *
 */

public class WordLadder {
	
//    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
//
//    	if(beginWord.equals(endWord)){ return 0;}
//
//    	// BFS with 2 queues
//    	Queue<String> currentLevel = new LinkedList<>();
//    	int currentLevelCount = 1;
//    	currentLevel.add(beginWord);
//    	wordList.remove(beginWord);
//    	int level = 1;
//
//    	while(!currentLevel.isEmpty()){
//
//			// 2. for node every level
//			String current = currentLevel.poll();
//			currentLevelCount--;
//
//			// 2.1 found it, return
//			if(current.equals(endWord)) {
//				return level;
//			}
//
//			// 2.2 nope, add friends to next level
//			currentLevel.addAll(getNextLevelUnvisited(current,wordList));
//
//    		if(currentLevelCount==0){
//        		currentLevelCount = currentLevel.size();
//        		level++;
//    		}
//    	}
//    	return 0;
//    }
//
//    // get all friends with distance one
//    public Set<String> getNextLevelUnvisited(String myself, Set<String> wordList){
//
//    	Set<String> result = new HashSet<String>();
//    	char[] myChars = myself.toCharArray();
//    	for(int i=0;i<myChars.length;i++){
//    		// replace each character
//    		for(char newChar='a'; newChar<='z';newChar++){
//    			myChars[i] = newChar;
//    			String newWord=String.valueOf(myChars);
//    			if(wordList.contains(newWord)){
//    				result.add(newWord);
//    				wordList.remove(newWord);
//    			}
//    		}
//    		// reset character
//    		myChars = myself.toCharArray();
//    	}
//    	return result;
//    }
//
//    public static void main(String[] args){
//    	WordLadder w = new WordLadder();
//		int steps1 =  w.ladderLength("hot","dog",
//				new HashSet<String>(Arrays.asList("hot", "dog","dot")));
//
//		assert 1==steps1:"Expected steps1 = 3, but got " + steps1;
//	}
}
