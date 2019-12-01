package ru.guybydefault.domain;

public class BinaryOp extends Expression {

    Expression arg1;
    Expression arg2;

    BinaryOpType type;

    public BinaryOp(String type, Expression arg1, Expression arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.type = BinaryOpType.valueOf(type);
    }

    @Override
    public String toString() {
        String result = "";

        return result;
    }
}
