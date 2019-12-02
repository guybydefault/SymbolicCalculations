package ru.guybydefault.domain;

public class BinaryOp extends Expression {

    private final Expression arg1;
    private final Expression arg2;

    private final BinaryOpType type;

    public BinaryOp(String type, Expression arg1, Expression arg2) {
        this.type = BinaryOpType.valueOf(type);
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public Expression getArg2() {
        return arg2;
    }

    public Expression getArg1() {
        return arg1;
    }

    public BinaryOpType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString(arg1, arg2);
    }
}
