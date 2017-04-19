package dynamicProgramming;

import java.util.Arrays;

/**
 * Created by jianwang on 4/19/17.
 */
public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        if(s==null||t==null||s.length()<t.length()||t.length()==0) {return 0;}

        int[][] match = new int[t.length()+1][s.length()+1];

        // s[0] matches with t
        Arrays.fill(match[0],1);

        for(int i=0;i<t.length();i++){
            for(int j=0;j<s.length();j++) {
                // normal case
                if (t.charAt(i) == s.charAt(j)) {
                    match[i+1][j+1] = match[i+1][j] /*left*/ +  match[i][j] /*upper left*/;
                } else {
                    match[i+1][j+1] = match[i+1][j];    // j not contributing
                }
            }
        }
        return match[t.length()][s.length()];
    }

    public static void main(String[] args){
        DistinctSubsequences t = new DistinctSubsequences();
        System.out.println(t.numDistinct("bbb","bb"));
    }
}
