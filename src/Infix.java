import java.util.Stack;

public class Infix {

    public static void main(String[] args) {
        String input = "a+b*(c^d-e)^(f+g*h)-i";
        System.out.println(infixToPostfix(input));
    }

    public static int precedence(char op) {
        if (op == '^') return 3;
        if (op == '*' || op == '/') return 2;
        if (op == '+' || op == '-') return 1;
        return -1;
    }

    public static String infixToPostfix(String exp) {

    Stack<Character> stack = new Stack<>();
    StringBuilder output = new StringBuilder();

    for (int i = 0; i < exp.length(); i++) {

        char ch = exp.charAt(i);

        if (Character.isLetterOrDigit(ch)) {
            output.append(ch);
        } else if (ch == '(') {
            stack.push(ch);
        } else if (ch == ')') {
            while (!stack.isEmpty() && stack.peek() != '(') {
                output.append(stack.pop());
            }
            stack.pop();
        } else {
            while (!stack.isEmpty() &&
                    Infix.precedence(stack.peek()) >= Infix.precedence(ch)) {
                output.append(stack.pop());
            }
            stack.push(ch);
        }
    }

        while (!stack.isEmpty()) {
        output.append(stack.pop());
        }

        return output.toString();
    }
}
