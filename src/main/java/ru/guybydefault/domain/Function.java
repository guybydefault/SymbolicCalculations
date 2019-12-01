package ru.guybydefault.domain;

public class Function extends Expression {

    private final Expression arg1;
    private final Expression arg2;

    private final FunctionType type;

    public Function(String type, Expression arg1, Expression arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.type = FunctionType.valueOf(type);
    }

    public Expression getArg1() {
        return arg1;
    }

    public Expression getArg2() {
        return arg2;
    }

    public FunctionType getType() {
        return type;
    }

    @Override
    public String toString() {
        String result = "";

        return result;
    }
}