package dynamicProgramming;

import java.util.Arrays;

// idea: basically a decision tree
// data structure: use a int[x]=y to denote point(x,y) has something
// 					level says which level we are on, right now
// use int[].clone
// 92 solutions in total

public class EightQueens {

	public void printEightQueens(int size){
		int[] board = new int[size];
		Arrays.fill(board, -1);
		printEightQueens(board, 0);
	}
	
	public void printEightQueens(int[] board, int row){
		
		// 1. base case, max level
		if(row>=8){
			for(int i=0;i<8;i++){
				//System.out.println("x="+i+" y="+board[i]);
			}
			System.out.println("hey");
		}
		
		// 2. now we need to place a queen
		else{
			// for each row, try on each column
			for(int column=0;column<8;column++){
				if(isValid(board,row,column)){
					board[row] = column;
					// go to next level
					printEightQueens(board.clone(), row+1);
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
	
	public static void main(String[] args){
		EightQueens queen = new EightQueens();
		queen.printEightQueens(8);
	}
}