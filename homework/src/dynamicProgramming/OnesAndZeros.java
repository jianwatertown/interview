package dynamicProgramming;

import java.util.ArrayList;

/**
 * Created by jianwang on 1/27/17.
 *
 * Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
     Output: 4

     Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”


     Example 2:
     Input: Array = {"10", "0", "1"}, m = 1, n = 1
     Output: 2

     Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
     Show Company Tags
     Show Tags

 */
public class OnesAndZeros {


    // simply regression on the word, add one word at a time to the next
    public int findMaxForm(String[] strs, int m, int n) {
        ArrayList<Pair> inputPairs = new ArrayList<>();
        for(String str:strs){
            inputPairs.add(new Pair(str));
        }


        // count[i][j]  (1<=i<=m,1<=j<=n) means, given (i,j), max words it can cover
        int[][] count = new int[m+1][n+1];
        for(Pair word:inputPairs){

            // loop from outside in to make sure each word is only used once
            for(int zero=m;zero>=word.zero;zero--)
                for(int one=n;one>=word.one;one--) // everything in this range can construct "word"
                {
                    count[zero][one] = Math.max(count[zero][one],count[zero-word.zero][one-word.one]+1);
                }
        }

        return count[m][n];
    }



    public static class Pair{
        public int zero=0;
        public int one=0;

        public Pair(String str){

            for(Character c: str.toCharArray()){
                if(c=='0') {
                    this.zero++;
                }
                else{
                    this.one++;
                }
            }
        }
    }
}
