package bfs;
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
 *
 * //////////////////////////////////////////////////////////////////////////////////////////////////

		while(...){
			int size = q.size()
			for( i<size){
				q.poll();					// <--------- inside for loop
 			}
 		}


 * //////////////////////////////////////////////////////////////////////////////////////////////////
 *
 */

public class WordLadder {


	public int ladderLength(String beginWord, String endWord, List<String> wordList) {

		Set<String> wordSet = new HashSet<>(wordList);
		Queue<String> q = new LinkedList<>();
		q.add(beginWord);

		int levelCount=1;
		while (!q.isEmpty()){
			levelCount++;
			int size = q.size();
			for(int i=0;i<size;i++){
				String word = q.poll();
				Set<String> children = getNextLevelUnvisited(word, wordSet);
				if(children.contains(endWord)){ return levelCount;}
				if(children.size()!=0) {q.addAll(children);}
			}
		}
		return 0;
	}

	// get all friends with distance one
	public Set<String> getNextLevelUnvisited(String myself, Set<String> wordList){

		Set<String> result = new HashSet<String>();
		char[] myChars = myself.toCharArray();
		for(int i=0;i<myChars.length;i++){
			// replace each character
			for(char newChar='a'; newChar<='z';newChar++){
				myChars[i] = newChar;
				String newWord=String.valueOf(myChars);
				if(wordList.contains(newWord)){
					result.add(newWord);
					wordList.remove(newWord);
				}
			}
			// reset character
			myChars = myself.toCharArray();
		}
		return result;
	}


	public static void main(String[] args){
		WordLadder tester = new WordLadder();
		List<String> wordList = new ArrayList<>();
		wordList.add("hot");
		wordList.add("dot");
		wordList.add("dog");
		wordList.add("lot");
		wordList.add("log");
		wordList.add("cog");
		String begin = "hit";
		String end = "cog";
		System.out.println(tester.ladderLength(begin,end,wordList));
	}
}
