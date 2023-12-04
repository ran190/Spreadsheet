import parser.AbstractNode;

public class FunctionCall implements Node {
    protected String name;
    protected AbstractNode[] parameters;

    public FunctionCall(String name, AbstractNode[] parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    public String toString() {
        return "FUNCTION: "+name;
    }

    @Override
    public double getValue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getValue'");
    }
}
