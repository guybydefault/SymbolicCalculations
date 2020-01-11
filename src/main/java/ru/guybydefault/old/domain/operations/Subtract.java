package ru.guybydefault.old.domain.operations;

import ru.guybydefault.old.domain.OrdinaryExpression;
import ru.guybydefault.old.services.Serializer;
import ru.guybydefault.old.visitors.Evaluator;

public class Subtract extends BinaryOp {
    public Subtract(String type, OrdinaryExpression arg1, OrdinaryExpression arg2) {
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