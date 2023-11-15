import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class formulasCell extends Cell {
    protected String value;

    public formulasCell() {
    }

    public formulasCell(String value) {
        this.value = value;
    }

    public String getContent() {
        return value;
    }

    public String getValue() {
        return convertToRPN(value);
    }

    private String convertToRPN(String expression) {
        StringBuilder outputQueue = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char token = expression.charAt(i);

            if (Character.isDigit(token)) {
                outputQueue.append(token);
            } else if (token == '(') {
                operatorStack.push(token);
            } else if (token == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    outputQueue.append(operatorStack.pop());
                }
                if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
                    operatorStack.pop(); // Discard the '('
                }
            } else if (isOperator(token)) {
                while (!operatorStack.isEmpty() && !isLeftParenthesis(operatorStack.peek())
                        && (getPrecedence(operatorStack.peek()) > getPrecedence(token)
                                || (getPrecedence(operatorStack.peek()) == getPrecedence(token)
                                        && isLeftAssociative(token)))) {
                    outputQueue.append(operatorStack.pop());
                }
                operatorStack.push(token);
            } else if (token == ',') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    outputQueue.append(operatorStack.pop());
                }
            }
        }

        while (!operatorStack.isEmpty()) {
            outputQueue.append(operatorStack.pop());
        }

        return outputQueue.toString();
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private boolean isLeftAssociative(char operator) {
        return operator == '+' || operator == '-';
    }

    private boolean isLeftParenthesis(char c) {
        return c == '(';
    }

    private int getPrecedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        }
        return 0; // Default precedence for non-operators
    }
}
