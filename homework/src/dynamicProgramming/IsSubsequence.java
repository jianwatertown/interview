package dynamicProgramming;

/**
 * Created by jianwang on 1/23/17.
 */
public class IsSubsequence {

    // a
    // ab
    public boolean isSubsequence(String s, String t) {

        // 1. boundary case
        if(s==null||t==null||s.length()>t.length()){
            return false;
        }

        // s[0...i] is a substring of t[0...j]
        int i = -1;
        int j = -1;

        // until reaching the end of s or t
        while((i<s.length()-1)&&(j<t.length()-1)){

            // t[j+1] == s[i+1]
            if(t.charAt(j+1)==s.charAt(i+1)){
               i++;
               j++;
            }
            else{
                j++;
            }
        }

        return i==s.length()-1;
    }
}
