package ru.guybydefault.domain;

import ru.guybydefault.services.Serializer;
import ru.guybydefault.visitors.Evaluator;

public class UnaryOp implements OrdinaryExpression {

    private final OrdinaryExpression arg;

    private final UnaryOpType type;

    public UnaryOp(String type, OrdinaryExpression arg) {
        this.arg = arg;
        this.type = UnaryOpType.findByName(type);
    }

    public OrdinaryExpression getArg1() {
        return arg;
    }

    public UnaryOpType getType() {
        return type;
    }


    @Override
    public OrdinaryExpression evaluate(Evaluator visitor) {
        return visitor.evaluate(this);
    }

    @Override
    public String serialize(Serializer serializer) {
        return serializer.serialize(this);
    }
}
