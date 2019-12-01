package ru.guybydefault.domain;

public class Value extends Expression {

    private final String val;

    public Value(String val) {
        this.val = val;
    }

    public String getVal() {
        return this.val;
    }

    @Override
    public String toString() {
        return "" + this.val + "";
    }
}
