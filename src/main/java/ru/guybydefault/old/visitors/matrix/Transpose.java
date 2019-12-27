package ru.guybydefault.old.visitors.matrix;

import ru.guybydefault.old.domain.MatrixExpression;
import ru.guybydefault.old.visitors.Evaluator;

public class Transpose implements Evaluator {
    @Override
    public Exception visit(MatrixExpression expression) {
        return null;
    }
}
