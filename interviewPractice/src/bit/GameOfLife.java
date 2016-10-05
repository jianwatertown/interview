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
        
    }
    
    public static void main(String[] args){
    	StringBuffer sb = new StringBuffer();
    	System.out.println(sb.insert(0,"a").insert(0, "b").toString());
    }

}
