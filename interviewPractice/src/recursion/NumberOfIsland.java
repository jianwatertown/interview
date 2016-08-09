package recursion;

import java.util.LinkedList;
import java.util.Queue;


/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
 * You may assume all four edges of the grid are all surrounded by water.

 * @author jian.wang
 *
 */
public class NumberOfIsland {

	
	public int numIslands(char[][] grid) {

		if(grid.length==0 || grid[0].length==0) return 0;
		
		int island = 0;
		int xSize = grid.length;
		int ySize = grid[0].length;
		for(int i=0;i<xSize;i++){			
			for(int j=0;j<ySize;j++){
				
				// 1. skip all the non-land
				if(grid[i][j]!='1'){continue;}
				
				// 2. make it # to show this point is visited
				grid[i][j] = '#';
					
				// 3. DFS each node BFS
				Queue<Point> queue = new LinkedList<Point>();
				queue.add(new Point(i,j));
				
				while(!queue.isEmpty()){
					Point c = queue.poll();
					int x = c.x;
					int y = c.y;
					if(isNewLand(x-1,y,xSize,ySize,grid)){
						queue.add(new Point(x-1,y));
						grid[x-1][y] = '#';
					}
					if(isNewLand(x,y-1,xSize,ySize,grid)){
						queue.add(new Point(x,y-1));
						grid[x][y-1] = '#';
					}
					if(isNewLand(x+1,y,xSize,ySize,grid)){
						queue.add(new Point(x+1,y));
						grid[x+1][y] = '#';
					}
					if(isNewLand(x,y+1,xSize,ySize,grid)){
						queue.add(new Point(x,y+1));
						grid[x][y+1] = '#';
					}
				}
				island++;
			}
		}
		return island;
    }
	
	public boolean isNewLand(int x, int y, int xSize, int ySize, char[][] grid){
		if(x>=xSize || x<0 || y>= ySize || y<0) {
			return false;
		}
		return (grid[x][y]=='1');
	}
	
	public class Point{
		int x;
		int y;
		
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args){
//		char[][] multi = new char[][]{
//				  { '0','0', '0', '0', '0', '0', '0','0', '0', '0' },
//				  { '0','0', '0', '0', '0', '0', '0','0', '0', '0' },
//				  { '0','0', '0', '0', '0', '1', '0','0', '0', '0' },
//				  { '0','0', '0', '0', '0', '0', '0','0', '0', '0' },
//				  { '0','0', '0', '0', '0', '0', '0','0', '0', '0' },
//				};
		
		char[][] multi = new char[][]{
		  { '1','1','0','0','0',},
		  { '1','1','0','0','0',},
		  { '0','0','1','0','0',},
		  { '0','0','0','1','1',},
		};
		System.out.println(new NumberOfIsland().numIslands(multi));
	}
}
