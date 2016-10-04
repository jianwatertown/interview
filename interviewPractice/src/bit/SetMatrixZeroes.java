package bit;
/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. 
 * 	Do it in place.

    click to show follow up.
    
    Follow up:
    Did you use extra space?
    A straight forward solution using O(mn) space is probably a bad idea.
    A simple improvement uses O(m + n) space, but still not the best solution.
    Could you devise a constant space solution?
 * @author jian.wang
 *
 *	Solution:
 *
 *		Bit-wise operation
 *
 *	1. Integer is a 4-byte (32 bit)
 *	2. range -2 power 31 to 2 power 31 (first bit as sign)
 *	
 *		In this case
 *	1.	 matrix[x][y] = v, v == either 
 *			00000000 00000000 00000000 0000 0000 - 0
 *			00000000 00000000 00000000 0000 0001 - 1
 *
 *	Operations
 *		00000000 00000000 00000000 0000 1000 - row
 *		00000000 00000000 00000000 0000 0100 - column
 *		
 *		29-30 two bits (row,column) determines if the row, column needs to be reset
 *	
 * 
 * 	1.	set bit 29 to 0-> 1 operation (matrix[x][y]|8)
 * 		set bit 30 to 0-> 1 operation (matrix[x][y]|4)
 * 
 *  2. 	check if 29 is 1, row, (matrix[x][y]&8==8)?
 * 		check if 30 is 1, column, (matrix[x][y]&4==4)?
 * 
 *  Algorithm
 *  
 *	2.	Iteration One, 
 *			if matrix[i][j] == 0, 
 * 				mark 2 edge's row to 1
 *					matrix[0][j]
 *					matrix[x-1][j]
 *				mark 2 edge's column to 1
 *					matrix[i][0]
 *					matrix[i][y-1]
 *
 *	3.	Iteration Two,
 *			except the edges, use the edges to set each cell accordingly
 *	3.	Iteration Three, fix the edges, except the 4 corners
 *	4.	Iteration Four, fix the 4 corners
 *
 *  http://stackoverflow.com/questions/3312853/how-does-bitshifting-work-in-java
 */
public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
    	int x = matrix.length;
    	int y = matrix[0].length;
    	
    	// 1. first iteration, set the edges
        for(int i=0;i<x;i++){
        	for(int j=0;j<y;j++){
        		if(matrix[i][j]==0){
        			setEdgesOne(i,j,matrix);
        		}
        	}
        }
    	
    	// 2. second iteration, set sub matrix
        for(int i=1;i<x-1;i++){
        	for(int j=1;i<y-1;j++){
        		if(isEdgeOne(i,j,matrix)){
        			matrix[i][j] = 0;
        		}
        	}
        }

    	// 3. third iteration, edges, except corners
        for(int i=0;i<x;i++){
        	for(int j=0;j<y;j++){
        		if(isCorner(i,j,matrix)){
        			continue;
        		}
        		else{
            		if(isEdgeOne(i,j,matrix)){
            			matrix[i][j] = 0;
            		}
            		// reset edges
            		else{
            			matrix[i][j] = 1;
            		}
        		}
        	}
        } 
        
        // 4. forth iteration, corners
        if(matrix[0][0]!=1) {matrix[0][0]=0;}
        if(matrix[0][y-1]!=1) {matrix[0][y-1]=0;}
        if(matrix[x-1][0]!=1) {matrix[x-1][0]=0;}
        if(matrix[x-1][y-1]!=1) {matrix[x-1][y-1]=0;}
    }
    
    public boolean isCorner(int i, int j, int[][] matrix){
    	int x = matrix.length;
    	int y = matrix[0].length;
    	return
    			(i==0) &&((j==0)||(j==y-1))
    			||
    			(i==x-1) &&((j==0)||(j==y-1))
    			;
    }

    public boolean isEdgeOne(int i, int j, int[][] matrix){
    	int x = matrix.length;
    	int y = matrix[0].length;
    	return (
    			(matrix[0][j]&8)==8||
    			(matrix[x-1][j]&8)==8||
    			(matrix[i][0]&4)==4||
    			(matrix[i][y-1]&4)==4
    			);
    }
    
    public void setEdgesOne(int i, int j, int[][] matrix){
    	int x = matrix.length;
    	int y = matrix[0].length;
    	
    	// 1. row
    	matrix[0][j] = matrix[0][j]|8;
    	matrix[x-1][j] = matrix[x-1][j]|8;
    	
    	// 2. column
    	matrix[i][0] = matrix[i][0]|4;
    	matrix[i][y-1] = matrix[i][y-1]|4;
    }
    
    
    public int getRowOne(){
    	return (1>>0);
    }
    
    public int getColumnOne(){
    	int column = (1>>0);
    }
}