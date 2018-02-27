package tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestWordInDictionary {


    public String longestWord(String[] words) {
        Arrays.sort(words);
        String maxString = "";
        TrieNode root = new TrieNode();

        for(String word:words){
            System.out.println(word);
            TrieNode current = root;
            for(int i=0;i<word.length();i++){
                Character c = word.charAt(i);
                // one character at a time
                if(!current.map.containsKey(c)){
                    // last character, that's OK, remember it; otherwise just pretend nothing happens
                    if(i==word.length()-1){
                        current.map.put(c,new TrieNode());
                        if(word.length()>maxString.length() || (word.length()==maxString.length()&&(word.compareTo(maxString)<0))){
                            maxString = word;
                        }
                    }
                    // not OK, skip word
                    break;
                }
                else{ current = current.map.get(c);}
            }
        }
        return maxString;
    }

    public static class TrieNode{
        public Map<Character,TrieNode> map = new HashMap<>();

    }

    public static void main(String[] args){
        LongestWordInDictionary test = new LongestWordInDictionary();
        System.out.println(test.longestWord(new String[] {"w","wo","wor","worl","world"}));
    }
}
