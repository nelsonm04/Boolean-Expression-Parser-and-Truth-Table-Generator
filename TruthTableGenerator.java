import java.util.*;

public class TruthTableGenerator {

    // Evaluate postfix expression for given truth values
    private boolean evaluatePostfix(String postfix, Map<Character, Boolean> values) {
        CustomStack<Boolean> stack = new CustomStack<>();

        for (char ch : postfix.toCharArray()) {
            if (Character.isLetter(ch)) {
                stack.push(values.get(ch));
            } else if (ch == '!') {
                stack.push(!stack.pop());
            } else {
                boolean b = stack.pop();
                boolean a = stack.pop();
                switch (ch) {
                    case '+': stack.push(a || b); break;
                    case '*': stack.push(a && b); break;
                }
            }
        }
        return stack.pop();
    }

    // Generate and print truth table for the expression
    public void generateTruthTable(String expression, String postfix) {
        Set<Character> variables = new TreeSet<>();
        for (char ch : expression.toCharArray()) {
            if (Character.isLetter(ch)) {
                variables.add(ch);
            }
        }

        System.out.print("Truth Table:\n");
        for (char var : variables) {
            System.out.print(var + " | ");
        }
        System.out.println(expression);
        System.out.println("-".repeat(variables.size() * 4 + expression.length()));

        int rows = (int) Math.pow(2, variables.size());
        List<Character> variableList = new ArrayList<>(variables);

        for (int i = 0; i < rows; i++) {
            Map<Character, Boolean> values = new HashMap<>();
            for (int j = 0; j < variableList.size(); j++) {
                values.put(variableList.get(j), (i & (1 << j)) != 0);
            }

            for (char var : variableList) {
                System.out.print((values.get(var) ? "T" : "F") + " | ");
            }

            boolean result = evaluatePostfix(postfix, values);
            System.out.println(result ? "T" : "F");
        }
    }
}
