package dynamicProgramming;

import java.util.Arrays;
import java.util.List;


// cannot rotate, so no total order, this becomes a 
// Longest Increasing Sequence problem + Directional Graph problem
public class StackBoxes {
	
	public int maxHeight(List<Box> boxList){

		// for all Bi < Bj
		// MHj = Max(MHi+Bi's height)
		int mx[] = new int[boxList.size()+1];
		boxList.add(new Box(0,0,0));
		Arrays.fill(mx, -1);
		mx[mx.length-1] = 0;
		return 0;
	}

	
	
	public class Box{
		int w;
		int l;
		int height;
		public Box(int w, int l, int height){
			this.w = w;
			this.l = l;
			this.height = height;
		}
		
		public boolean canSupportBox(Box other){
			if(other.w<=this.w&&other.l<=this.l){
				return true;
			}
			else{
				return false;
			}
		}
	}
}
