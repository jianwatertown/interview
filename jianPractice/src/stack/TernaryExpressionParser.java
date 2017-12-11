package stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by jianwang on 3/28/17.
 *
     * Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression.
     * You can always assume that the given expression is valid and only consists of digits 0-9,
     * ?, :, T and F (T and F represent True and False respectively).

     Note:

     The length of the given string is ≤ 10000.
     Each number will contain only one digit.
     The conditional expressions group right-to-left (as usual in most languages).
     The condition will always be either T or F. That is, the condition will never be a digit.
     The result of the expression will always evaluate to either a digit 0-9, T or F.

     Example 1:

     Input: "T?2:3"

     Output: "2"

     Explanation: If true, then result is 2; otherwise result is 3.
     Example 2:

     Input: "F?1:T?4:5"

     Output: "4"

     Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:

     "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
     -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
     -> "4"                                    -> "4"
     Example 3:

     Input: "T?T?F:5:3"

     Output: "F"

     Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:

     "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
     -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
     -> "F"                                    -> "F"

 */

/*
*
*   O(n) with stack
    Collect chars from back to front on a stack, evaluate ternary sub-expressions as soon as possible:

    def parseTernary(self, expression):
        stack = []
        for c in reversed(expression):
            stack.append(c)
            if stack[-2:-1] == ['?']:
                stack[-5:] = stack[-3 if stack[-1] == 'T' else -5]
        return stack[0]
*
*
*
* */
public class TernaryExpressionParser {

    // find the right most '?' then construct the expression carefully
    public String parseTernary(String expression) {
        while (expression.length() != 1) {
            int i = expression.lastIndexOf("?");            // get the last shown '?'
            char tmp = expression.charAt(i-1) == 'T'? expression.charAt(i+1):expression.charAt(i+3);
            expression = expression.substring(0,i-1)+tmp+expression.substring(i+4);
        }
        return expression;
    }

    public String parseTernaryStack(String expression) {
        if (expression == null || expression.length() == 0) return "";
        Deque<Character> stack = new LinkedList<>();

        for (int i = expression.length() - 1; i >= 0; i--) {
            char c = expression.charAt(i);
            if (!stack.isEmpty() && stack.peek() == '?') {

                stack.pop(); //pop '?'
                char first = stack.pop();
                stack.pop(); //pop ':'
                char second = stack.pop();

                if (c == 'T') stack.push(first);
                else stack.push(second);
            } else {
                stack.push(c);
            }
        }

        return String.valueOf(stack.peek());
    }
    // find the right most '?' then construct the expression carefully
    // the following is incorrect
    public String parseTernaryDec2017(String expression) {
        char right = expression.charAt(expression.length()-1);
        // from right to left
        for(int i=expression.length()-1;i>=4;i=i-4){
            if(expression.charAt(i-4)=='T'){
                // update right to left only when it's "T", otherwise use the value in the right branch
                right = expression.charAt(i-2) /*lef value*/;
            }
        }
        return String.valueOf(right);
    }
}
