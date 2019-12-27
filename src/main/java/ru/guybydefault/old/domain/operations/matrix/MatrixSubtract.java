package ru.guybydefault.old.domain.operations.matrix;

import ru.guybydefault.old.domain.Matrix;
import ru.guybydefault.old.domain.MatrixExpression;
import ru.guybydefault.old.services.Serializer;
import ru.guybydefault.old.visitors.Evaluator;

import java.util.List;

public class MatrixSubtract extends MatrixBinaryOperation {
    public MatrixSubtract(MatrixBinaryOperation arg1, MatrixBinaryOperation arg2) {
        super(arg1, arg2);
    }

    @Override
    public MatrixExpression evaluate(List<Evaluator> evaluators) {
        return new MatrixSum(getArg1(), Matrix.getZeroMatrix(getArg1().getHeight(), getArg1().getWidth())).evaluate(evaluators);
    }

    @Override
    public String serialize(Serializer serializer) {
        return serializer.serialize(this);
    }
}
