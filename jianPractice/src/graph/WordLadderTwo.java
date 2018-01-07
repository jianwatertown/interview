package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *   beginWord = "hit"
     endWord = "cog"
     wordList = ["hot","dot","dog","lot","log","cog"]
     Return
     [
     ["hit","hot","dot","dog","cog"],
     ["hit","hot","lot","log","cog"]
     ]

        https://discuss.leetcode.com/topic/6809/java-code-based-on-dijkstra-s-algorithm-accepted
        https://discuss.leetcode.com/topic/6794/accepted-java-solution-based-on-dijkstra-s-algorithm/2

     Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s)
     from beginWord to endWord, such that:

     Only one letter can be changed at a time
     Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
     For example,

     Given:
     beginWord = "hit"
     endWord = "cog"
     wordList = ["hot","dot","dog","lot","log","cog"]
     Return
     [
     ["hit","hot","dot","dog","cog"],
     ["hit","hot","lot","log","cog"]
     ]

 */
public class WordLadderTwo {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        // 0. overall dedupe and state dedupe
        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> visitedPrevious = new HashSet<>();

        // 1. visit begining node
        Map<String,Set<String>> parentMap = new HashMap<>();
        parentMap.put(beginWord,new HashSet<>());
        visitedPrevious.add(beginWord);

        // 2. BFS
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        while (!q.isEmpty()){
            int size = q.size();
            Set<String> visitedCurrentLevel = new HashSet<>();

            for(int i=0;i<size;i++){
                String word = q.poll();
                if(word.equals(endWord)) {
                    return bfsPaths(parentMap,endWord);}
//                    if("log".equals(word)||"dog".equals(word)){System.out.println("log/dog");}
                Set<String> children = getNextLevelUnvisited(word, wordSet,visitedPrevious,visitedCurrentLevel);
                visitedCurrentLevel.addAll(children);
                for(String child:children){
                    if(!parentMap.containsKey(child)) {parentMap.put(child,new HashSet<>());}
                    parentMap.get(child).add(word);
                    q.add(child);
                }
            }

            visitedPrevious.addAll(visitedCurrentLevel);
        }
        return new LinkedList<>();
    }

    public List<List<String>> bfsPaths(Map<String,Set<String>> map, String root){
        List<List<String>> result = new ArrayList<>();
        dfsHelper(result,new LinkedList<>(),map,root);
        return result;
    }

    public void dfsHelper(List<List<String>> result, List<String> pathSoFar, Map<String,Set<String>> map, String root){
        List<String> extention = new LinkedList<>(pathSoFar);
        extention.add(0,root);

        // this is the end
        if(map.get(root).size()==0){result.add(extention);return;}
        for(String nextLevel: map.get(root)){
            dfsHelper(result,extention,map,nextLevel);
        }
    }


    // get all friends with distance one
    public Set<String> getNextLevelUnvisited(String myself, Set<String> wordList,
        Set<String> visitedPrevious, Set<String> visitedCurrentLevel){

        Set<String> result = new HashSet<>();
        char[] myChars = myself.toCharArray();
        for(int i=0;i<myChars.length;i++){
            // replace each character
            for(char newChar='a'; newChar<='z';newChar++){
                myChars[i] = newChar;
                String newWord=String.valueOf(myChars);
                if(wordList.contains(newWord)&&!visitedPrevious.contains(newWord)){
                    result.add(newWord);
                    visitedCurrentLevel.add(newWord);
                }
            }
            // reset character
            myChars = myself.toCharArray();
        }
        return result;
    }

    public static void main(String[] args){
        WordLadderTwo tester = new WordLadderTwo();
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");
        String begin = "hit";
        String end = "cog";
        for(List<String> path: tester.findLadders(begin,end,wordList)){
            for(String w: path){
                System.out.print(w+" ");
            }
            System.out.println();
        }
    }

}
