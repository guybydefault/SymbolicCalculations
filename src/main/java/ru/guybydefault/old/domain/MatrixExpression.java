package ru.guybydefault.old.domain;

import ru.guybydefault.old.visitors.Evaluator;

import java.util.List;

public interface MatrixExpression extends Expression {
    MatrixExpression evaluate(List<Evaluator> evaluators);
    OrdinaryExpression getEvaluatedCell(int row, int column);
    int getHeight();
    int getWidth();
}
