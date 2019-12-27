package ru.guybydefault.old.domain.operations.matrix;


import ru.guybydefault.old.domain.MatrixExpression;

public abstract class MatrixBinaryOperation implements MatrixExpression {

    private final MatrixExpression arg1;
    private final MatrixExpression arg2;

    public MatrixBinaryOperation(MatrixExpression arg1, MatrixExpression arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public MatrixExpression getArg1() {
        return arg1;
    }

    public MatrixExpression getArg2() {
        return arg2;
    }
}
