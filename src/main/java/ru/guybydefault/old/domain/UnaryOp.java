package ru.guybydefault.old.domain;

import ru.guybydefault.old.services.Serializer;
import ru.guybydefault.old.visitors.Evaluator;

public abstract class UnaryOp implements OrdinaryExpression {

    private final OrdinaryExpression arg;

    public UnaryOp(OrdinaryExpression arg) {
        this.arg = arg;
    }

    public OrdinaryExpression getArg1() {
        return arg;
    }

}
