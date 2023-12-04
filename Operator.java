public class Operator implements Node {
    protected char op;
    private Node left;
    private Node right;

    public Operator(char op, Node left, Node right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    public String toString() {
        return "OP: "+op;
    }

    public Node getLeft(){
        return left;
    }

    public Node getRight(){
        return right;
    }

    public double getValue() {
        double leftValue = left.getValue();
        double rightValue = right.getValue();
    
        switch (op) {
            case '+':
                return  leftValue + rightValue;
            case '-':
                return  leftValue - rightValue;
            case '*':
                return leftValue * rightValue;
            case '/':
                if (rightValue != 0) {
                    return leftValue / rightValue;
                } else {
                    throw new ArithmeticException("Cannot divide by zero");
                }
            default:
                throw new UnsupportedOperationException("Unsupported operator: " + op);
        }
    }
}
