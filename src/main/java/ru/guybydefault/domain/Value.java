package ru.guybydefault.domain;

public class Value implements Expression {

    private final String value;
    private final ValueType type;

    public Value(String type, String val) {
        this.type = ValueType.valueOf(type);
        this.value = val;
    }

    public String getValue() {
        return value;
    }

    public ValueType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString(value);
    }

    @Override
    public Expression simplify() {
        return null;
    }
}
