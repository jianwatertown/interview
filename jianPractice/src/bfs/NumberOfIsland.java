package bfs;

import java.util.LinkedList;
import java.util.Queue;


/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * How many distinct connected components are there in this map?
 *
 * @author jian.wang
 *
 * Solution:
 * 		bfs all inner nodes, get number of connected parts
 */
public class NumberOfIsland {


	public int numIslands(char[][] grid) {

			int result = 0;
			Queue<Point> q = new LinkedList<>();

			// visit each node
			// 0 water
			// 1 island
			// 2 visited island
			for (int i = 0;i < grid.length; i++) {
				for (int j = 0; j< grid[0].length; j++) {

					// once a new land detected, find all adjcent land
					if (grid[i][j] == 1) {
						Point p = new Point(i,j);
						q.add(p);
						while(q.size()>0){
							Point head = q.poll();
							addNewLandToQueue(head.x,head.y-1,grid,q);
							addNewLandToQueue(head.x,head.y+1,grid,q);
							addNewLandToQueue(head.x-1,head.y,grid,q);
							addNewLandToQueue(head.x+1,head.y,grid,q);
						}
						result++;
					}

				}
			}
			return result;
		}

	public void addNewLandToQueue(int i, int j, char[][] grid, Queue q){
		if(i>=0&&i<grid.length&&j>=0&&j<grid[0].length&&grid[i][j]=='1'){
			grid[i][j] = '2';
			q.add(new Point(i,j));
		}
	}

	public class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}



	public static void main(String[] args) {
//		char[][] multi = new char[][]{
//				  { '0','0', '0', '0', '0', '0', '0','0', '0', '0' },
//				  { '0','0', '0', '0', '0', '0', '0','0', '0', '0' },
//				  { '0','0', '0', '0', '0', '1', '0','0', '0', '0' },
//				  { '0','0', '0', '0', '0', '0', '0','0', '0', '0' },
//				  { '0','0', '0', '0', '0', '0', '0','0', '0', '0' },
//				};

		char[][] multi = new char[][]{
				{'1', '1', '0', '0', '0',},
				{'1', '1', '0', '0', '0',},
				{'0', '0', '1', '0', '0',},
				{'0', '0', '0', '1', '1',},
		};
		System.out.println(new NumberOfIsland().numIslands(multi));
	}
}
