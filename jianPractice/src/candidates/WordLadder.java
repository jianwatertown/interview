package candidates;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


/***
 *
 *
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest
 * transformation sequen  ce from beginWord to endWord, such that:

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

 */
public class WordLadder {
	
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
		return 0;
    }
    
    public static void main(String[] args){
    	WordLadder w = new WordLadder();

    	// test 1
		int steps1 =  w.ladderLength("hot","dog",
				new HashSet<String>(Arrays.asList("hot", "dog","dot")));
		assert 3==steps1:"Expected steps1 = 3, but got " + steps1;

		// test 2
		int steps2 =  w.ladderLength("hit","cog",
				new HashSet<String>(Arrays.asList("hot","dot","dog","lot","log","cog")));
		assert 6==steps2:"Expected steps2 = 5, but got " + steps2;

		System.out.println("All pass!");
	}
}
