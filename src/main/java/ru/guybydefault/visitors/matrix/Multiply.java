package ru.guybydefault.visitors.matrix;

import ru.guybydefault.domain.MatrixExpression;
import ru.guybydefault.visitors.Evaluator;

public class Multiply implements Evaluator {
    @Override
    public Exception visit(MatrixExpression expression) {
        return null;
    }
}
