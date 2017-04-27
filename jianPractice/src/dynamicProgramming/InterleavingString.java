package dynamicProgramming;

/**
 * Created by jianwang on 4/26/17.
 *
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

     For example,
         Given:
         s1 = "aabcc",
         s2 = "dbbca",

         When s3 = "aadbbcbcac", return true.
         When s3 = "aadbbbaccc", return false.
 */
public class InterleavingString {

    // recursive
    public boolean isInterleave(String s1, String s2, String s3) {
        return helper(0,0,s1,s2,s3,new int[s1.length()][s2.length()]);
    }

    // int[][] cache, 1 yes, -1 no, 0 unknown
    public boolean helper(int i, int j, String s1, String s2, String s3, int[][] cache) {

        if (i >= cache.length) {
            return s2.substring(j).equals(s3.substring(i + j));
        }
        if (j >= cache[0].length) {
            return s1.substring(i).equals(s3.substring(i + j));
        }

        if (cache[i][j] != 0) {
            return cache[i][j] == 1;
        }

        char c3 = s3.charAt(i + j), c1 = s1.charAt(i), c2 = s2.charAt(j);
        boolean isInter = false;

        if (c3 != c1 && c3 != c2) {
            cache[i][j] = -1;
            return false;
        }
        if (c3 == c1) { // use c1
            isInter = helper(i + 1, j, s1, s2, s3, cache);
        }
        if (c3 == c2 && !isInter) {
            isInter = helper(i, j + 1, s1, s2, s3, cache);
        }
        cache[i][j] = isInter ? 1 : -1;
        return isInter;
    }


    // DP
    // Draw the diagram then you will understand
    public boolean isInterleaveDP(String s1, String s2, String s3) {

        if(s1.length()+s2.length()!=s3.length()) { return false;}

        // standard DP setting
        boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
        dp[0][0] = true;

        for(int i=0;i<=s1.length();i++){
            for(int j=0;j<=s2.length();j++){
                if(i==0&&j==0) {dp[i][j]=true;}
                else if(i==0) {// first row, ""+s2
                    dp[i][j] = dp[i][j-1] && s3.charAt(i+j-1)==s2.charAt(j-1);
                }
                else if(j==0) { // first column, s1+""
                    dp[i][j] = dp[i-1][j] && s3.charAt(i+j-1)==s1.charAt(i-1);
                }
                else{
                    dp[i][j] = dp[i-1][j] && s3.charAt(i+j-1)==s1.charAt(i-1) || dp[i][j-1] && s3.charAt(i+j-1)==s2.charAt(j-1);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }


    public static void main(String[] args){
        InterleavingString t = new InterleavingString();
        System.out.println(t.isInterleave("aa","ab","aaba"));
    }
}