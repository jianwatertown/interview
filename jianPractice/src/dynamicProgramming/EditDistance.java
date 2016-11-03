package dynamicProgramming;

import java.util.Arrays;

public class EditDistance {

    public int minDistance(String word1, String word2) {
    	
    	if(word1.length()==0) {return word2.length();}
    	if(word2.length()==0) {return word1.length();}
    		
    	int[][] previous = new int[word1.length()][word2.length()];
    	for(int i=0;i<word1.length();i++){
    		Arrays.fill(previous[i], -1);			// has not visited
    	}
    	return minDistance(word1,word2,0,0,previous);
    }
    
    public int minDistance(String source, String target, int sIdx, int tIdx, int[][] previous){

    	// 1. Edge
    	if(sIdx==source.length() && tIdx==target.length()) {return 0;}
    	if(sIdx==source.length()) {return target.length()-tIdx;}
    	if(tIdx==target.length()) {return source.length()-sIdx;}
    	
    	// 2. Multi-value cache 
    	if(previous[sIdx][tIdx]>=0){
    		return previous[sIdx][tIdx];
    	}

    	// 3. Init
    	int minDistance=0;
    	
    	// 4. Recursion
    	if(source.charAt(sIdx)==target.charAt(tIdx)){
    		minDistance = minDistance(source, target, sIdx+1,tIdx+1,previous);
    	}
    	else{
    		// 2.2 insert into source to match current target.charAt(tIdx)
    		int minInsertion = minDistance(source, target, sIdx,tIdx+1,previous)+1;
    		// 2.3 replace
    		int minReplace =  minDistance(source, target, sIdx+1,tIdx+1,previous)+1; 
    		// 2.4 deletion
    		int minDeletion =  minDistance(source, target, sIdx+1,tIdx,previous)+1;
    		minDistance = Math.min(Math.min(minInsertion,minReplace),minDeletion);
    	}
    	
    	// 5. Return
    	previous[sIdx][tIdx] = minDistance;
		return minDistance;
	}
    
    public static void main(String[] args){
    	EditDistance edit = new EditDistance();
    	System.out.println(edit.minDistance("a", "ab"));
    }
}
