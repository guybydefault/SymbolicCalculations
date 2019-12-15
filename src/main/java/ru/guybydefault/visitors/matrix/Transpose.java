package ru.guybydefault.visitors.matrix;

import ru.guybydefault.domain.MatrixExpression;
import ru.guybydefault.visitors.Evaluator;

public class Transpose implements Evaluator {
    @Override
    public Exception visit(MatrixExpression expression) {
        return null;
    }
}
