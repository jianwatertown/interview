package dynamicProgramming;

/**
 * Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' 
 * (the number zero), return the maximum enemies you can kill using one bomb.
 * The bomb kills all the enemies in the same row and column from the planted
 *  point until it hits the wall since the wall is too strong to be destroyed.
 *  
 *  Note that you can only put the bomb at an empty cell.
 *  
 *  
 *  For the given grid

    0 E 0 0
    E 0 W E
    0 E 0 0

	return 3. (Placing a bomb at (1,1) kills 3 enemies)


 * @author jian.wang
 *
 */
public class BombEnemy {
    public int maxKilledEnemies(char[][] grid) {
    	
    	if(grid.length==0 || grid[0].length==0){return 0;}
    	
    	// 0. 
    	int max = 0;
    	int x = grid.length;		// easier to use x,y
    	int y = grid[0].length;		//
    	Point[][] cache = new Point[x][y];
    	for(int i=0;i<x;i++){
    		for(int j=0;j<y;j++){
    			cache[i][j] = new Point();
    		}
    	}
    	
    	// 1. n^2 traversal from top left to bottom right. 
    	// populate leftToHere[] and upToHere[]
    	for(int i=0;i<x;i++){
    		for(int j=0;j<y;j++){
    			char c = grid[i][j];
    			int left = i>0?cache[i-1][j].left:0;	// use left
    			int up = j>0?cache[i][j-1].up:0;		// use up
    			// 1.1 'W' -> nothing can be reached from here, keep 0
    			// 1.2 '0' -> place a bomb
    			if(c=='0'){
        			// update current
        			cache[i][j].left = left;
        			cache[i][j].up = up; 
    			}
    			// 1.3  'E' -> cannot place a bomb, but can update all the values
    			else if(c=='E'){
        			// update current
        			cache[i][j].left = left+1;
        			cache[i][j].up = up+1;
    			}
    		}
    	}
    	
    	
    	// 2. n^2 traversal from bottom right to upper right. 
    	// populate rightToHere[] and downToHere[]
    	// also keep the global max here
    	for(int i=x-1;i>=0;i--){
    		for(int j=y-1;j>=0;j--){
    			char c = grid[i][j];
    			int right = i<x-1?cache[i+1][j].right:0;	// use right
    			int down = j<y-1?cache[i][j+1].down:0;		// use down
    			// 2.1 'W' -> nothing can be reached from here, keep 0
    			// 2.2 '0' -> place a bomb
    			if(c=='0'){
        			// update current
        			cache[i][j].right = right;
        			cache[i][j].down = down;
        			max = Math.max(max, cache[i][j].getSum());
    			}
    			// 2.3  'E' -> cannot place a bomb, but can update all the values
    			else if(c=='E'){
        			// update current
        			cache[i][j].right = right+1;
        			cache[i][j].down = down+1;
    			}
    		}
    	}
    	return max;
    }

    public static class Point{
    	public int left=0;			// enemy to the left
    	public int right=0;		// enemy to the right 
    	public int up=0;			// enemy from top
    	public int down=0;			// enemy from bottom
		public int getLeft() {
			return left;
		}
		public int getRight() {
			return right;
		}
		public int getUp() {
			return up;
		}
		public int getDown() {
			return down;
		}
		
		public int getSum(){
			return left+right+up+down;
		}
    }
    
    public int maxKilledEnemiesArray(char[][] grid) {
    	
    	if(grid.length==0 || grid[0].length==0){return 0;}
    	
    	// 0. 
    	int max = 0;
    	int x = grid.length;		// easier to use x,y
    	int y = grid[0].length;		//
    	int[][][] cache = new int[x][y][4];		// left,up,right,down
    	for(int i=0;i<x;i++){
    		for(int j=0;j<y;j++){
    			cache[i][j] = new int[4];
    		}
    	}
    	
    	// 1. n^2 traversal from top left to bottom right. 
    	// populate leftToHere[] and upToHere[]
    	for(int i=0;i<x;i++){
    		for(int j=0;j<y;j++){
    			char c = grid[i][j];
    			int left = i>0?cache[i-1][j][0]:0;	// use left
    			int up = j>0?cache[i][j-1][1]:0;		// use up
    			// 1.1 'W' -> nothing can be reached from here, keep 0
    			// 1.2 '0' -> place a bomb
    			if(c=='0'){
        			// update current
        			cache[i][j][0] = left;
        			cache[i][j][1] = up; 
    			}
    			// 1.3  'E' -> cannot place a bomb, but can update all the values
    			else if(c=='E'){
        			// update current
        			cache[i][j][0] = left+1;
        			cache[i][j][1] = up+1;
    			}
    		}
    	}
    	
    	
    	// 2. n^2 traversal from bottom right to upper right. 
    	// populate rightToHere[] and downToHere[]
    	// also keep the global max here
    	for(int i=x-1;i>=0;i--){
    		for(int j=y-1;j>=0;j--){
    			char c = grid[i][j];
    			int right = i<x-1?cache[i+1][j][2]:0;	// use right
    			int down = j<y-1?cache[i][j+1][3]:0;		// use down
    			// 2.1 'W' -> nothing can be reached from here, keep 0
    			// 2.2 '0' -> place a bomb
    			if(c=='0'){
        			// update current
        			cache[i][j][2] = right;
        			cache[i][j][3] = down;
        			max = Math.max(max, cache[i][j][0]+cache[i][j][1]+cache[i][j][2]+cache[i][j][3]);
    			}
    			// 2.3  'E' -> cannot place a bomb, but can update all the values
    			else if(c=='E'){
        			// update current
        			cache[i][j][2] = right+1;
        			cache[i][j][3] = down+1;
    			}
    		}
    	}
    	return max;
    }

    
    
    // "0E00","E0WE","0E00"
    public static void main(String[] args){
    	char[][] input = {
    			{'0','E'},
    	};
    	System.out.println(new BombEnemy().maxKilledEnemies(input));
    }
}
