package ru.guybydefault.visitors;

import ru.guybydefault.domain.Expression;

public interface Visitor {
    public Exception visit(Expression expression);
}
