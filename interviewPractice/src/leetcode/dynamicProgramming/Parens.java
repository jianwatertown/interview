package leetcode.dynamicProgramming;

public class Parens {

	
	public void printParens(int p){
		printParens(p,p,"");
	}
	
	public void printParens(int left, int right, String str){
	
		// 1. base case, nothing left
		if(left==0 && right ==0){
			System.out.println(str);
		}
		
		// 2. print left
		if(left>0){
			printParens(left-1,right,str+"(");
		}
		
		// 3. print right
		if(right>0&&right>left){
			printParens(left,right-1,str+")");
		}
		
	}
	
	public static void main(String[] args){
		Parens p = new Parens();
		p.printParens(3);
	}
}

