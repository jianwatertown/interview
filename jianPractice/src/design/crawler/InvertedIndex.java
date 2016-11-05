package design.crawler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Given a list of documents with id and content. (class Document)

    [
      {
        "id": 1,
        "content": "This is the content of document 1 it is very short"
      },
      {
        "id": 2,
        "content": "This is the content of document 2 it is very long bilabial bilabial heheh hahaha ..."
      },
    ]
    Return an inverted index (HashMap with key is the word and value is a list of document ids).
    
    {
       "This": [1, 2],
       "is": [1, 2],
       ...
    }

 * @author jian.wang
 *
 */
public class InvertedIndex {
    public Map<String, List<Integer>> invertedIndex(List<Document> docs) {
    	Map<String,Set<Integer>> wordToIndex = new HashMap<>();
    	
    	// every doc
    	for(Document d: docs){
    		
    		String[] words = d.content.split(" +");
    		
    		// every word
    		for(String word:words){
    			// skip empty
    			if(word.length()==0){ continue;}
    			addIndexToMap(wordToIndex,word,d.id);
    		}
    	}
    	
    	// return set to a list
    	Map<String,List<Integer>> result = new HashMap<>();
    	for(String key: wordToIndex.keySet()){
    		List resultList = new ArrayList(wordToIndex.get(key));
    		Collections.sort(resultList);
    		result.put(key, resultList);
    	}
    	return result;
    }
    
    public void addIndexToMap(Map<String,Set<Integer>> wordToIndex, String word, int index){
    	// 1. add to existing 
    	if(wordToIndex.containsKey(word)){
    		wordToIndex.get(word).add(index);
    	}
    	// 2. add to new
    	else{
    		Set<Integer> indexSet = new HashSet<>();
    		indexSet.add(index);
    		wordToIndex.put(word, indexSet);
    	}
    }
}
