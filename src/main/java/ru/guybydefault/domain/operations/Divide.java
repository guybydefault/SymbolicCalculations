package ru.guybydefault.domain.operations;

import ru.guybydefault.domain.Expression;
import ru.guybydefault.services.Serializer;
import ru.guybydefault.visitors.Evaluator;

public class Divide extends BinaryOp {
    public Divide(String type, Expression arg1, Expression arg2) {
        super(type, arg1, arg2);
    }

    @Override
    public Expression evaluate(Evaluator visitor) {
        return visitor.evaluate(this);
    }

    public String serialize(Serializer serializer) {
        return serializer.serialize(this);
    }
}
