package matrix;

/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. 
 * 	Do it in place.

    click to show follow up.
    
    Follow up:
    Did you use extra space?
    A straight forward solution using O(mn) space is probably a bad idea.
    A simple improvement uses O(m + n) space, but still not the best solution.
    Could you devise a constant space solution?
 * 
 * 	@author jian.wang
 * 
 *	My idea is simple: store states of each row in the first of that row, 
 *	and store states of each column in the first of that column. 
 *
 *	Because the state of row0 and the state of column0 would occupy the same cell, 
 *	I let it be the state of row0, and use another variable "col0" for column0. 
 *	In the first phase, use matrix elements to set states in a top-down way. 
 *	In the second phase, use states to set matrix elements in a bottom-up way.
 *
 *	Smart of this algorithm:
 *		1) first iteration: row: 0->row, column: 1->column
 *		2) second iteration: row: row-1->0, column: column -> 1
 *
 *
 */

public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {

    	int col0 = 1; // special field for first column
    	int rows = matrix.length;
    	int columns = matrix[0].length;
    	
    	// first iteration, set the top edge and left edge
        for(int i=0;i<rows;i++){
        	// 1. first column
        	if(matrix[i][0]==0){col0=0;}
        	// 2. the rest columns
    		for(int j=1;j<columns;j++){
        		if(matrix[i][j]==0){
        			matrix[i][0] = 0; 
        			matrix[0][j] = 0;
        		}
        	}
        }
    	
    	// second iteration, set sub matrix
        for(int i=rows-1;i>=0;i--){
        	// everything except first column
        	for(int j=columns-1;j>=1;j--){
        		if(matrix[i][0]==0 || matrix[0][j]==0){
        			matrix[i][j] = 0;
        		}
        	}
        	// first column
        	if(col0==0){
        		matrix[i][0] = 0;
        	}        	
        }
    }
}