package ru.guybydefault.domain.operations.matrix;


import ru.guybydefault.domain.MatrixExpression;

public abstract class MatrixBinaryOperation implements MatrixExpression {

    private final MatrixBinaryOperation arg1;
    private final MatrixBinaryOperation arg2;

    public MatrixBinaryOperation(MatrixBinaryOperation arg1, MatrixBinaryOperation arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public MatrixBinaryOperation getArg1() {
        return arg1;
    }

    public MatrixBinaryOperation getArg2() {
        return arg2;
    }
}
