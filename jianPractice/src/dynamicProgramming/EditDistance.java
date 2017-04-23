package dynamicProgramming;

import java.util.Arrays;

public class EditDistance {

	// 4.23.2017
	public int minDistance(String word1 /*from*/, String word2/*to*/) {

		// min[i][j] the distance between word1[i-1] and word2[j-1]
		// note: in word[index], when index=0, it's an empty string
		int[][] min = new int[word1.length()+1][word2.length()+1];

		// "" -> "something", insert in every step
		for(int i=0;i<=word2.length();i++){min[0][i] = i;}

		// "something" -> "", delete in every step
		for(int i=0;i<=word1.length();i++){min[i][0] = i;}

		for(int i=1;i<=word1.length();i++){
			for(int j=1;j<=	word2.length();j++){
				int distance= Integer.MAX_VALUE;
				if(i>0&&j>0) {
					if (word1.charAt(i-1) == word2.charAt(j-1)) {	// same
						distance = Math.min(distance, min[i - 1][j - 1]);
					} else {									// replace
						distance = Math.min(distance, min[i - 1][j - 1] + 1);
					}
				}
				// delete
				if(i>0){distance = Math.min(distance,min[i-1][j]+1);}
				// insert
				if(j>0){distance = Math.min(distance,min[i][j-1]+1);}
				min[i][j] = distance;
			}
		}
		return min[word1.length()][word2.length()];
	}

	////////////////////////////////////////////////////////
    public int minDistanceRecursion(String word1, String word2) {
    	
    	if(word1.length()==0) {return word2.length();}
    	if(word2.length()==0) {return word1.length();}
    		
    	int[][] previous = new int[word1.length()][word2.length()];
    	for(int i=0;i<word1.length();i++){
    		Arrays.fill(previous[i], -1);			// has not visited
    	}
    	return minDistanceRecursion(word1,word2,0,0,previous);
    }
    
    public int minDistanceRecursion(String source, String target, int sIdx, int tIdx, int[][] previous){

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
    		minDistance = minDistanceRecursion(source, target, sIdx+1,tIdx+1,previous);
    	}
    	else{
    		// 2.2 insert into source to match current target.charAt(tIdx)
    		int minInsertion = minDistanceRecursion(source, target, sIdx,tIdx+1,previous)+1;
    		// 2.3 replace
    		int minReplace =  minDistanceRecursion(source, target, sIdx+1,tIdx+1,previous)+1;
    		// 2.4 deletion
    		int minDeletion =  minDistanceRecursion(source, target, sIdx+1,tIdx,previous)+1;
    		minDistance = Math.min(Math.min(minInsertion,minReplace),minDeletion);
    	}
    	
    	// 5. Return
    	previous[sIdx][tIdx] = minDistance;
		return minDistance;
	}

	public static void main(String[] args){
    	EditDistance edit = new EditDistance();
    	System.out.println(edit.minDistanceRecursion("a", "ab"));
    }
}
