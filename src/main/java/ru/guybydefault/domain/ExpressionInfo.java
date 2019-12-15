package ru.guybydefault.domain;

public class ExpressionInfo {

    private final MatrixExpression expression;

    public ExpressionInfo(MatrixExpression expression) {
        this.expression = expression;
    }

    public MatrixExpression getExpression() {
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

    public ExpressionInfo simplify() {
        return new ExpressionInfo(expression.evaluate());
    }
}
