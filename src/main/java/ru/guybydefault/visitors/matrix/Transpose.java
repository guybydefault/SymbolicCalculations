package ru.guybydefault.visitors.matrix;

import ru.guybydefault.domain.Expression;
import ru.guybydefault.visitors.Evaluator;

public class Transpose implements Evaluator {
    @Override
    public Exception visit(Expression expression) {
        return null;
    }
}
