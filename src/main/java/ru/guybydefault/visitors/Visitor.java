package ru.guybydefault.visitors;

import ru.guybydefault.domain.Expression;

public interface Visitor {
    public VisitResult visit(Expression expression);
}
