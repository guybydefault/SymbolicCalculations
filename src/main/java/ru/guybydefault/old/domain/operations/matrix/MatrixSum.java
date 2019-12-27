package ru.guybydefault.old.domain.operations.matrix;

import ru.guybydefault.old.domain.MatrixExpression;
import ru.guybydefault.old.domain.OrdinaryExpression;
import ru.guybydefault.old.services.Serializer;
import ru.guybydefault.old.visitors.Evaluator;

public class MatrixSum extends MatrixBinaryOperation {
    public MatrixSum(MatrixExpression arg1, MatrixExpression arg2) {
        super(arg1, arg2);
    }


    @Override
    public MatrixExpression evaluate(Evaluator visitor) {
        return visitor.evaluate(this);
    }

    @Override
    public String serialize(Serializer serializer) {
        return serializer.serialize(this);
    }

    @Override
    public MatrixExpression evaluate() {
        return null;
    }

    @Override
    public OrdinaryExpression getEvaluatedCell(int row, int column) {
        return null;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }
}
