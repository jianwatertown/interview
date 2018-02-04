package dynamicProgramming;

public class ValidParenthesisString {

    public boolean checkValidString(String s) {
        return helper(0, s,0);
    }

    public boolean helper(int start,String s, int count) {

        for(int i=start;i<s.length();i++){
            char c = s.charAt(i);
            if(c=='(') {count++;}
            else if(c==')') {
                count--;
                if(count<0) {return false;}
            }
            else if(c=='*'){
                return ((helper(i+1,s,count+1)||helper(i+1,s,count-1)||helper(i+1,s,count)));
            }
        }
        return count==0;
    }
}
