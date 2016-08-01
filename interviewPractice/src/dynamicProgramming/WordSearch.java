package dynamicProgramming;
public class WordSearch {

/**
 * The key in this question is:
 *     		// reset used anyways
    		used[currentX][currentY] = false;
 * always reset
 * 	
 * @param board
 * @param word
 * @return
 */
	
	
    public boolean exist(char[][] board, String word) {
    	boolean[][] used = new boolean[board.length][board[0].length];
    	for(int i=0;i<board.length;i++){
    		for(int j=0;j<board[0].length;j++){
    			if(exist(board,used,word,i,j,0)){return true;}
    		}
    	}
    	return false;
    }
    
    public boolean exist(char[][] board, 
    		boolean[][] used,
    		String word, 
    		int currentX, int currentY, int wordIndex){
    	
    	// last character, found it!
    	if(wordIndex==word.length()){return true;}
    	
    	// out of bound search
    	if(currentX>=board.length || currentX<0 || 
    			currentY>=board[0].length ||  currentY<0){
    		return false;
    	}
    	
    	// have seen in cache, then we cannot use it again
    	if(used[currentX][currentY]){return false;}
    		
    	// actual finding
    	if(word.charAt(wordIndex)==board[currentX][currentY]){
    		used[currentX][currentY] = true;
    		boolean found = 
    				(exist(board,used,word,currentX-1,currentY,wordIndex+1)||		// left 
    						 exist(board,used,word,currentX+1,currentY,wordIndex+1)||		// right
    						 exist(board,used,word,currentX,currentY+1,wordIndex+1)||		// down
    						 exist(board,used,word,currentX,currentY-1,wordIndex+1));		// up
    		// reset used anyways
    		used[currentX][currentY] = false;
    		if(found) {return true;}
    	}
    	return false;
    }
}
