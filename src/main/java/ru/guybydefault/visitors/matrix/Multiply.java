package ru.guybydefault.visitors.matrix;

import ru.guybydefault.domain.Expression;
import ru.guybydefault.visitors.Evaluator;

public class Multiply implements Evaluator {
    @Override
    public Exception visit(Expression expression) {
        return null;
    }
}
