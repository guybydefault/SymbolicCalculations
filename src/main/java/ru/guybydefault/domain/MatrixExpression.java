package ru.guybydefault.domain;

public class MatrixExpression {

    private final Expression expression;

    public MatrixExpression(String me) {
        String expr = "";
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<math xmlns=\"http://www.w3.org/1998/Math/MathML\">\n"
                + "<mrow>\n"
                + expression.toString()
                + "</mrow>\n"
                + "</math>\n";
    }
}
