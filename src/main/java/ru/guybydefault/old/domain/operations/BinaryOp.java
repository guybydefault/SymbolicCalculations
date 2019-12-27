package ru.guybydefault.old.domain.operations;

import ru.guybydefault.old.domain.MatrixExpression;
import ru.guybydefault.old.domain.OrdinaryExpression;

public abstract class BinaryOp implements OrdinaryExpression {

    private final OrdinaryExpression arg1;
    private final OrdinaryExpression arg2;

    public BinaryOp(String type, OrdinaryExpression arg1, OrdinaryExpression arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public OrdinaryExpression getArg2() {
        return arg2;
    }

    public OrdinaryExpression getArg1() {
        return arg1;
    }

}
