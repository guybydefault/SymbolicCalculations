package ru.guybydefault.domain.operations;

import ru.guybydefault.domain.MatrixExpression;
import ru.guybydefault.domain.OrdinaryExpression;
import ru.guybydefault.services.Serializer;
import ru.guybydefault.visitors.Evaluator;

public class Divide extends BinaryOp {
    public Divide(String type, OrdinaryExpression arg1, OrdinaryExpression arg2) {
        super(type, arg1, arg2);
    }

    @Override
    public OrdinaryExpression evaluate(Evaluator visitor) {
        return visitor.evaluate(this);
    }

    public String serialize(Serializer serializer) {
        return serializer.serialize(this);
    }
}
