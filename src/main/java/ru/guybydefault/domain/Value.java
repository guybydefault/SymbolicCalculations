package ru.guybydefault.domain;

public class Value extends Expression {

    private final String value;
    private final ValueType type;

    public Value(ValueType type, String val) {
        this.type = type;
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
}
