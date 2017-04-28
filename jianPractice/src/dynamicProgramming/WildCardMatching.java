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
        boolean[][] match=new boolean[s.length()+1][p.length()+1];
        match[0][0] = true;

        for(int i=1;i<=p.length();i++){
            for(int j=1;i<=s.length();j++){
                match[][]

            }
        }

        return match[s.length()][p.length()];
    }

}
