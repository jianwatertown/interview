package dynamicProgramming;

import java.util.*;

public class ConcatenatedWords {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {

        List<String> result = new LinkedList<>();

        // 1. sort the words
        List<String> list = new ArrayList<>();
        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int diff = (o1.length()-o2.length());
                return diff==0?0:diff>0?1:-1;
            }
        };
        SortedSet<String> set = new TreeSet(comp);
        set.addAll(Arrays.asList(words));

        // 2. dfs on each word
        Map<String,Boolean> map = new HashMap<>();
        for(String word:words){
            if(dfs(set,word,map)) {result.add(word);}
        }

        return result;
    }

    public boolean dfs(SortedSet<String> words, String target, Map<String,Boolean> cache){
        // 1. previously computed
        if(cache.containsKey(target)) {return cache.get(target);}

        // starting from 1, at least 2 words
        for(int i=1;i<=target.length();i++){
            cache.put(target.substring(0,i),false);
            for(int j=0;j<i;j++){
                if(words.contains(target.substring(i,target.length()))&&dfs(words,target.substring(j,i),cache)){
                    cache.put(target.substring(0,i),true);
                    break;
                }
            }
        }

        return cache.get(target);
    }
}
