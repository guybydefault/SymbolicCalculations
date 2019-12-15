package ru.guybydefault.domain;

import ru.guybydefault.visitors.Evaluator;

public interface OrdinaryExpression extends Expression {
    OrdinaryExpression evaluate(Evaluator visitor);
}
