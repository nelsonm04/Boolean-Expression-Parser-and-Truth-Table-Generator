//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a Boolean expression: ");
        String expression = scanner.nextLine().replaceAll("\\s+", ""); // Remove spaces

        InfixToPostfixConverter converter = new InfixToPostfixConverter();
        String postfix = converter.infixToPostfix(expression);
        System.out.println("Postfix Expression: " + postfix);

        TruthTableGenerator generator = new TruthTableGenerator();
        generator.generateTruthTable(expression, postfix);
        scanner.close();
    }
}