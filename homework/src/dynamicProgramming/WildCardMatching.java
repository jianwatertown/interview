package dynamicProgramming;

/**
 * '
 * ?' Matches any single character.
 '*' Matches any sequence of characters (including the empty sequence).

     The matching should cover the entire input string (not partial).

     The function prototype should be:
     bool isMatch(const char *s, const char *p)

     Some examples:
     isMatch("aa","a") → false
     isMatch("aa","aa") → true
     isMatch("aaa","aa") → false
     isMatch("aa", "*") → true
     isMatch("aa", "a*") → true
     isMatch("ab", "?*") → true
     isMatch("aab", "c*a*b") → false
 */
public class WildCardMatching {

    public boolean isMatch(String s, String p) {

        // defaults are False "pattern*source"
        // index(i,j) value are saved in match[i+1][j+1]
        boolean[][] match=new boolean[p.length()+1][s.length()+1];

        // "","" matches; "" matches to nothing else
        match[0][0] = true;

        // first column, only extend when pattern is *
        for(int i=0;i<p.length();i++){
            if(match[i][0] && p.charAt(i)=='*' /*used as empty here*/){
                match[i+1][0] = true;
            }
            else{break;}
        }

        for(int i=0;i<p.length();i++){
            boolean rowContainsTrue = match[i][0];
            for(int j=0;j<s.length();j++){
                if(p.charAt(i)==s.charAt(j)||p.charAt(i)=='?'){
                    match[i+1][j+1] = match[i][j];
                }
                else if(p.charAt(i)=='*'){
                    match[i+1][j+1] =
                            match[i][j]             // (up,left) * becomes up character
                            || match[i][j+1]        // (up) * becomes "" (empty)
                            || match[i+1][j];       // (left) * can be expand to anything, so if the left matches, this matches
                }
                rowContainsTrue = rowContainsTrue||match[i+1][j+1];
            }
            if(!rowContainsTrue){return false;}
        }
        return match[p.length()][s.length()];
    }
}
