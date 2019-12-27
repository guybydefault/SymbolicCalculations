package ru.guybydefault.old.domain.operations.matrix;

import ru.guybydefault.old.domain.MatrixExpression;
import ru.guybydefault.old.domain.OrdinaryExpression;
import ru.guybydefault.old.services.Serializer;
import ru.guybydefault.old.visitors.Evaluator;

public class MatrixMultiplicate extends MatrixBinaryOperation {
    public MatrixMultiplicate(MatrixBinaryOperation arg1, MatrixBinaryOperation arg2) {
        super(arg1, arg2);
    }

    @Override
    public MatrixExpression evaluate(Evaluator visitor) {
        return visitor.evaluate(this);
    }

    @Override
    public OrdinaryExpression getEvaluatedCell(int row, int column) {
        return null;
    }

    @Override
    public String serialize(Serializer serializer) {
        return serializer.serialize(this);
    }
}
