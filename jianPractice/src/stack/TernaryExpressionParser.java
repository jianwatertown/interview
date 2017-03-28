package stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by jianwang on 3/28/17.
 */
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

}
