package dynamicProgramming;

/**
 * Created by jianwang on 11/23/17.
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

     Example:

     Input: "babad"

     Output: "bab"

     Note: "aba" is also a valid answer.
     Example:

     Input: "cbbd"

     Output: "bb"
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {

        int l = s.length();
        int[][] matrix = new int[l][l];
        String maxString = "";

        // 1. build 1, 2 elements
        for(int i=0;i<l;i++){
            // 1 element
            matrix[i][i] = 1;
            maxString = maxString.length()==0?s.substring(i,i+1):maxString;

            // 2 elements
            if((i+1<l)&&(s.charAt(i)==s.charAt(i+1))){
                matrix[i][i+1] = 1;
                maxString = s.substring(i,i+2);
            }
        }
        // 2. size grows to length
        for(int size=2;size<l;size++){
            for(int i=0;i+size<l /*until max index*/;i++){
                // check substring(i,i+size): i,i+1,.....,i+size-1,i+size
                if((matrix[i+1][i+size-1]>0)            // make sure [i+1,i+size-1]  is palindrom
                && (s.charAt(i)==s.charAt(i+size))){    // make sure s(i)==s(i+size)
                    matrix[i][i+size] = 1;
                    System.out.println(s.substring(i,i+size+1));
                    maxString = maxString.length()<size+1?s.substring(i,i+size+1):maxString;
                }
            }
        }
        return maxString;
    }

    public static void main(String[] args){
        LongestPalindromicSubstring tester = new LongestPalindromicSubstring();
//        System.out.println(tester.longestPalindrome("cbbd"));
        System.out.println(tester.longestPalindrome("ccc"));
    }
}
