package ru.guybydefault.domain;

public class UnaryOp extends Expression {

    private final Expression arg;

    private final UnaryOpType type;

    public UnaryOp(String type, Expression arg) {
        this.arg = arg;
        this.type = UnaryOpType.valueOf(type);
    }

    public Expression getArg1() {
        return arg;
    }

    public UnaryOpType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString(arg);
    }
}
