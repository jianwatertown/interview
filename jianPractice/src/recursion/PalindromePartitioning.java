package recursion;

import java.util.LinkedList;
import java.util.List;

public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new LinkedList<>();


        return result;
    }

    public void backtrack(List<List<String>> result, List<String> soFar, int){

    }


    public boolean isPali(String str, int l, int h){
        while(l<h){
            if(str.charAt(l)!=str.charAt(h)){return false;}
            l++;h++;
        }
        return true;
    }
}
