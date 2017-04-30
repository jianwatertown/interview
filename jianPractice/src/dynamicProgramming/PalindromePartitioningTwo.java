package dynamicProgramming;

/**
 * Created by jianwang on 4/29/17.
 *
 *
     Given a string s, partition s such that every substring of the partition is a palindrome.

     Return the minimum cuts needed for a palindrome partitioning of s.

     For example, given s = "aab",
     Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

     "keep updating the unknown(right) with known information (left)" is similar to coin change
    iterative solution
 */
public class PalindromePartitioningTwo {

    public int minCut(String s) {
        if(s==null||s.length()<=1) {return 0;}

        // number of cuts for the first i characters in String, at least i-1
        int[] cuts = new int[s.length()+1];
        for(int i=0;i<=s.length();i++){ cuts[i] = i-1;}

        // use the known last one, cuts[i], as the center to extend rightwards
        for(int i=0;i<s.length();i++){
            // keep updating based on known value cuts[i]
            for (int j = 0; i-j >= 0 && i+j < s.length() && s.charAt(i-j)==s.charAt(i+j) ; j++) // odd length palindrome
                cuts[i+j+1] = Math.min(cuts[i+j+1],1+cuts[i-j]);

            for (int j = 1; i-j+1 >= 0 && i+j < s.length() && s.charAt(i-j+1) == s.charAt(i+j); j++) // even length palindrome
                cuts[i+j+1] = Math.min(cuts[i+j+1],1+cuts[i-j+1]);
        }


        return cuts[s.length()];
    }
}
