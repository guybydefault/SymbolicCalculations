package ru.guybydefault.domain;

import ru.guybydefault.services.Serializer;
import ru.guybydefault.visitors.Evaluator;

public interface MatrixExpression extends Expression {
    MatrixExpression evaluate(Evaluator visitor);
    OrdinaryExpression getEvaluatedCell(int row, int column);
}
