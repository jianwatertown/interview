package recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new LinkedList<>();
        backtrack(result,new ArrayList<>(), s,0);
        return result;
    }

    public void backtrack(List<List<String>> result, List<String> soFar, String str, int start){
        if(start==str.length()){ result.add(new ArrayList<>(soFar));}
        else{
            for(int i=start;i<str.length();i++){
                if(isPali(str,start,i)){
                    soFar.add(str.substring(start,i+1));
                    backtrack(result,soFar,str,i+1);
                    soFar.remove(soFar.size()-1);
                }
            }

        }
    }

    public boolean isPali(String str, int l, int h){
        while(l<h){
            if(str.charAt(l)!=str.charAt(h)){return false;}
            l++;h--;
        }
        return true;
    }
}
