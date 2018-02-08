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


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isPali(String str, int l, int h){
        while(l<h){
            if(str.charAt(l++)!=str.charAt(h--)){return false;}
        }
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<String>> partition2(String s) {
        List<List<String>> result = new LinkedList<List<String>>();
        dfs(s,0,new LinkedList<String>(),result);
        return result;
    }

    public void dfs(String s, int start, List<String>pathSoFar, List<List<String>> result){
        if(start==s.length()) {result.add(new ArrayList<>(pathSoFar));return;}
        for(int i=start;i<s.length();i++){
            if(isParlindrom(s,start,i)) {
                pathSoFar.add(s.substring(start,i+1));
                dfs(s,i+1,pathSoFar,result);
                pathSoFar.remove(pathSoFar.size()-1);
            }
        }
    }

    public boolean isParlindrom(String s, int start, int end){
        if(end>start||start<0||end>=s.length()) return false;
        while(start<end){
            if(s.charAt(start++)!=s.charAt(end--)){
                return false;
            }
        }
        return true;
    }
}
