package ru.guybydefault.domain;


import ru.guybydefault.visitors.Evaluator;

public class Function implements Expression {

    private final Expression arg1;
    private final Expression arg2;

    private final FunctionType type;

    public Function(String type, Expression arg1) {
        this.arg1 = arg1;
        this.arg2 = null;
        this.type = FunctionType.findByName(type);
    }

    public Function(String type, Expression arg1, Expression arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.type = FunctionType.findByName(type);
    }

    public Expression getArg1() {
        return arg1;
    }

    public Expression getArg2() {
        return arg2;
    }

    private FunctionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString(arg1, arg2);
    }

    @Override
    public void evaluate(Evaluator visitor) {
        visitor.visit(this);
    }
}
