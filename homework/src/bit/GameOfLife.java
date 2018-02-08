package bit;


/**
 * https://discuss.leetcode.com/topic/29054/easiest-java-solution-with-explanation
 * 
 * To solve it in place, we use 2 bits to store 2 states:

        [2nd bit, 1st bit] = [next state, current state]
        
        - 00  dead (next) <- dead (current)
        - 01  dead (next) <- live (current)  
        - 10  live (next) <- dead (current)  
        - 11  live (next) <- live (current) 
        
        In the beginning, every cell is either 00 or 01.
        
        Notice that 1st state is independent of 2nd state.
        
        Imagine all cells are instantly changing from the 1st to the 2nd state, at the same time.
        
        Let's count # of neighbors from 1st state and set 2nd state bit.
        
        Since every 2nd state is by default dead, no need to consider transition 01 -> 00.
        In the end, delete every cell's 1st state by doing >> 1.
        For each cell's 1st bit, check the 8 pixels around itself, and set the cell's 2nd bit.
        
        Transition 01 -> 11: when board == 1 and lives >= 2 && lives <= 3.
        Transition 00 -> 10: when board == 0 and lives == 3.
        To get the current state, simply do
        
        board[i][j] & 1
        To get the next state, simply do
        
        board[i][j] >> 1
        
        
         *             Bit-wise operation
  *
- *     1. Integer is a 4-byte (32 bit)
- *     2. range -2 power 31 to 2 power 31 (first bit as sign)
- *     
- *             In this case
- *     1.       matrix[x][y] = v, v == either 
- *                     00000000 00000000 00000000 0000 0000 - 0
- *                     00000000 00000000 00000000 0000 0001 - 1
- *
- *     Operations
- *             00000000 00000000 00000000 0000 1000 - row
- *             00000000 00000000 00000000 0000 0100 - column
- *             
- *             29-30 two bits (row,column) determines if the row, column needs to be reset
- *     

 * 
 * @author jian.wang
 *
 */
public class GameOfLife {
	
    public void gameOfLife(int[][] board) {
        for(int i=0;i<board.length;i++){
        	for(int j=0;j<board[0].length;j++){
        		// live now
        		if((board[i][j]&1)==1){
        			// 2-3 neigbors, live to next round
        			if(2<=getNeighborsCount(i,j,board)&&
        					3>=getNeighborsCount(i,j,board)){
        				board[i][j] = 3; // 11
        			}
        		}
        		// dead now
        		if((board[i][j]&1)==0){
        			if(3==getNeighborsCount(i,j,board)){
        				board[i][j] = 2; //10
        			}
        		}

        	}
        }
        
        // move to next board
        for(int i=0;i<board.length;i++){
        	for(int j=0;j<board[0].length;j++){
        		board[i][j] = board[i][j]>>1;
        	}
        }
    }
     
    public int getNeighborsCount(int row, int column, int[][]board){
    	int count=0;
    	if(isAlive(row-1,column-1,board)) count++;
    	if(isAlive(row,column-1,board)) count++;
    	if(isAlive(row+1,column-1,board)) count++;
    	if(isAlive(row+1,column,board)) count++;
    	if(isAlive(row+1,column+1,board)) count++;
    	if(isAlive(row,column+1,board)) count++;
    	if(isAlive(row-1,column+1,board)) count++;
    	if(isAlive(row-1,column,board)) count++;
    	return count;    	
    }
    
    public boolean isAlive(int row, int column, int[][] board){
    	if(row<0||row>=board.length||column<0||column>=board[0].length){
    		return false;
    	}
		return (board[row][column]&1)==1;
    }
}
