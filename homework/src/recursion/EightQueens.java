package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// idea: row level recursion,
// since each row can have only 1 queen
//
// 		use a int[x]=y to denote point(x,y) has a queen
//
// *use int[].clone* or reset the board
// 92 solutions in total

public class EightQueens {

	public void printEightQueens(int size){
		int[] board = new int[size];
		Arrays.fill(board, -1);
		printEightQueens(board, 0);
	}
	
	public void printEightQueens(int[] board, int row){
		
		// 1. base case, max level
		if(row==8){
			for(int i=0;i<8;i++){
				System.out.println("x="+i+" y="+board[i]);
			}
		}
		
		// 2. now we need to place a queen
		else{
			// for each row, try on each column
			for(int column=0;column<8;column++){
				if(isValid(board,row,column)){
					board[row] = column;
					// go to next level
					printEightQueens(board.clone(), row+1);
					// reset the board
					board[row] = -1;
				}
			}
		}
	}
	
	public boolean isValid(int[] board, int row, int column){
		
		for(int r=0;r<7;r++){
			// 1. check same column
			if(board[r]==column){
				return false;
			}
			
			// 2. check diagonal
			if(board[r]!=-1 && 
					Math.abs(r-row)==Math.abs(board[r]-column)){
				return false;
			}
		}		
		return true;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<List<String>> solveNQueens(int size){
		int[] columns = new int[size];
		Arrays.fill(columns,-1);	// -1 not visited
		List<List<String>> result = new ArrayList<>();
		dfs(columns,0,size,result);
		return result;
	}

	public void dfs(int[] columns, int level, int size, List<List<String>> result){
		// 1. base case
		if(level==size) { addToResult(columns,result);}
		// 2.
		else{
			for(int c=0;c<size;c++){
				if(columns[c]==-1 && isOK(size,columns,level,c)){
					columns[c] = level;
					dfs(columns,level+1,size,result);
					columns[c] = -1;
				}
			}
		}
	}

	public void addToResult(int[] columns,List<List<String>> result){
		Map<Integer,String> oneSolution = new HashMap<>();
		for(int c=0;c<columns.length;c++){
			int r = columns[c];
			String str = "";
			for(int print=0;print<columns.length;print++){
				str+=(print==c?"Q":".");
			}
			oneSolution.put(r,str);
		}
		List<String> solution = new LinkedList<>();
		for(int i=0;i<columns.length;i++){
			solution.add(oneSolution.get(i));
		}
		result.add(solution);
	}

	public boolean isOK(int size, int[] columns, int newRow, int newColum){
		for(int oldColumn=0;oldColumn<size;oldColumn++){
			if(columns[oldColumn]!=-1&&Math.abs(oldColumn-newColum)==Math.abs(columns[oldColumn]-newRow)){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args){
		EightQueens queen = new EightQueens();
//		queen.printEightQueens(8);
		int count=0;
		for(List<String> oneSolution:queen.solveNQueens(4)){
			System.out.println("Solution "+count++);
			for(String str:oneSolution){
				System.out.println(str);
			}
			System.out.println("===============");
		}
	}
}
