package ru.guybydefault.old.domain;

import ru.guybydefault.old.visitors.Evaluator;

import java.util.List;

public interface OrdinaryExpression extends Expression {
    OrdinaryExpression evaluate(List<Evaluator> evaluators);
}
