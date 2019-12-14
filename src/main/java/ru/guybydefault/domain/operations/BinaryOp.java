package ru.guybydefault.domain.operations;

import ru.guybydefault.domain.BinaryOpType;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.visitors.Evaluator;

public abstract class BinaryOp implements Expression {

    private final Expression arg1;
    private final Expression arg2;

    public BinaryOp(String type, Expression arg1, Expression arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public Expression getArg2() {
        return arg2;
    }

    public Expression getArg1() {
        return arg1;
    }

}
