package dynamicProgramming;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class RobotInGrid {
	
/**
 * 
 * 	Key 1: recursive structure, the LinkedList<Point> as return type
 * 
 * 	Key 2: use a visited set
 * 
 *  
 * @param r
 * @param c
 * @return
 */
	
	public LinkedList<Point> findPath(int r, int c){
		return findPath(r,c,0,0, new HashSet<Point>());
	} 
	

	// x in [0,c-1], y in [0,r-1]
	public LinkedList<Point> findPath(int r, int c, int x, int y, Set<Point> visited){
		
		Point p = new Point(x,y);
		
		// 0. found the end
		if((x==c-1)&&(y==r-1)){
			LinkedList<Point> path = new LinkedList<Point>();
			path.add(p);
			return path;
		}
		
		// 1. visited? then somebody else must have taken care of this
		if(visited.contains(p)){
			return null;
		}		
		
		// 2. exceeds
		if((x>c-1)||(y>r-1)||(isOffLimit(x,y))){
			visited.add(p);
			return null;
		}
		
		// 3. looking for a path
		else{
			visited.add(p);
			LinkedList<Point> pathRight = findPath(r,c,x+1,y,visited);
			
			if(pathRight!=null){
				pathRight.add(p);
				return pathRight;
			}
			else{
				LinkedList<Point> pathDown = findPath(r,c,x,y+1,visited);
				if(pathDown!=null){
					pathDown.add(p);
					return pathDown;
				}
				else{
					return null;
				}
			}
		}
	}
	
	
	public boolean isOffLimit(int x, int y){
		if(x==1 &&y==0){
			return true;
		}
		return false;
	}
	
	public class Point{
		int x;
		int y;
		
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString(){
			return "x = "+x+" y = "+y;
		}
	}
	
	public static void main(String[] args){
		RobotInGrid robot = new RobotInGrid();
		LinkedList<Point> path = robot.findPath(3, 3);
		for(Point p:path){
			System.out.println(p);
		}
	}
}
