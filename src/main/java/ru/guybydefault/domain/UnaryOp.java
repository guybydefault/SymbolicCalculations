package ru.guybydefault.domain;

public class UnaryOp implements Expression {

    private final Expression arg;

    private final UnaryOpType type;

    public UnaryOp(String type, Expression arg) {
        this.arg = arg;
        this.type = UnaryOpType.findByName(type);
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
    @Override
    public Expression simplify() {
        return null;
    }
}
