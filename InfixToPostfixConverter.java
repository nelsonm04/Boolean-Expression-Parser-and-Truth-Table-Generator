import java.util.*;

public class InfixToPostfixConverter {

    // Get operator precedence
    private int precedence(char operator) {
        switch (operator) {
            case '!': return 3;
            case '*': return 2; // AND operator
            case '+': return 1; // OR operator
            default: return -1;
        }
    }

    // Convert infix to postfix
    public String infixToPostfix(String expression) {
        CustomStack<Character> stack = new CustomStack<>();
        StringBuilder postfix = new StringBuilder();

        for (char ch : expression.toCharArray()) {
            if (Character.isLetter(ch)) {
                postfix.append(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(ch)) {
                    postfix.append(stack.pop());
                }
                stack.push(ch);
            }
        }
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }
}
