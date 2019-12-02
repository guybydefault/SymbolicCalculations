package ru.guybydefault.visitors.matrix;

import ru.guybydefault.domain.Expression;
import ru.guybydefault.visitors.Visitor;

public class Add implements Visitor {

    @Override
    public Exception visit(Expression expression) {
        return null;
    }
}
