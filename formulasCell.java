import java.util.Stack;

import parser.AbstractFactory;
import parser.ExpressionBuilder;

import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

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
        AbstractFactory factory;
        ExpressionBuilder builder;

        String express = getContent().substring(1);

        factory = new Factory();
        builder = new ExpressionBuilder(factory);

        builder.buildExpression(express);

        // Returns the expression
        Node expression = (Node)builder.getExpression();

        if (expression != null) {
            return Double.toString(expression.getValue());
        } else {
            throw new IllegalStateException("Expression not yet built");
        }

        // Returns the list of cell references... in the case empty
        //List<String> references = builder.getCellReferences();
    }

}