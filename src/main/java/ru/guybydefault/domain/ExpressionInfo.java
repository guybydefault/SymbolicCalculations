package ru.guybydefault.domain;

public class ExpressionInfo {

    private final Expression expression;

    public ExpressionInfo(Expression expression) {
        this.expression = expression;
    }

    public MatrixExpression(Expression e) {
        this.expression = e;
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

    public MatrixExpression simplify() {
        return new MatrixExpression(expression.simplify());
    }
}
