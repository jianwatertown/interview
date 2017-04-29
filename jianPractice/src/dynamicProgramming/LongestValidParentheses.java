package dynamicProgramming;

/**
 * Created by jianwang on 4/28/17.
 *
 *  Note: the question is looking for "substring", which is continuous
 *
 */
public class LongestValidParentheses {

    // stack
    // use the "substring" attribute

    /**
     * Scan the string from beginning to end.
         If current character is '(',
         push its index to the stack. If current character is ')' and the
         character at the index of the top of stack is '(', we just find a
         matching pair so pop from the stack. Otherwise, we push the index of
         ')' to the stack.
         After the scan is done, the stack will only
         contain the indices of characters which cannot be matched. Then
         let's use the opposite side - substring between adjacent indices
         should be valid parentheses.
         If the stack is empty, the whole input
         string is valid. Otherwise, we can scan the stack to get longest
         valid substring as described in step 3.
     * @param s
     * @return
     */
//    int longestValidParentheses(string s) {
//        int n = s.length(), longest = 0;
//        stack<int> st;
//        for (int i = 0; i < n; i++) {
//            if (s[i] == '(') st.push(i);
//            else {
//                if (!st.empty()) {
//                    if (s[st.top()] == '(') st.pop();
//                    else st.push(i);
//                }
//                else st.push(i);
//            }
//        }
//        if (st.empty()) longest = n;
//        else {
//            int a = n, b = 0;
//            while (!st.empty()) {
//                b = st.top(); st.pop();
//                longest = max(longest, a-b-1);
//                a = b;
//            }
//            longest = max(longest, a);
//        }
//        return longest;
//    }

    /**
     My one pass DP solution runs in 3ms.

     There are two key insights behind this code.

         1) dp[i] is the longest valid parentheses ends at string array[i - 1].

         2) for every ), we need to check one character left. If the character left is a (, it is a match.

     If the character before is a ), we calculate the index of last unmatched character j.

     If j is a (, then it is a match. We can calculate the longest valid parentheses with function dp[i] = dp[j - 1] + dp[i - 1] + 2;

     To void index out of bound, the indexing is little bit tricky. Hope the code looks intuitive with the explanations above.
     */
    public int longestValidParenthesesDP(String s) {
        int[] dp = new int[s.length() + 1];
        char[] array = s.toCharArray();
        int max = 0;
        for(int i = 2, j = 0; i < dp.length; i++){
            if(array[i - 1] == ')'){
                if(array[i - 2] == '('){
                    dp[i] = dp[i - 2] + 2;
                // j is the furthest index, i can reach.  (when i, j is only 0 character apart, it's OK too)
                }else if((j = i - dp[i - 1] - 1) >= 1 && array[j - 1] == '('){
                    dp[i] = dp[j - 1] + dp[i - 1] + 2;
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

}
