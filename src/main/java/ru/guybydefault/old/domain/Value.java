package ru.guybydefault.old.domain;

import ru.guybydefault.old.services.Serializer;
import ru.guybydefault.old.visitors.Evaluator;

public class Value implements OrdinaryExpression {

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

    @Override
    public OrdinaryExpression evaluate(Evaluator visitor) {
        return visitor.evaluate(this);
    }

    @Override
    public String serialize(Serializer serializer) {
        return serializer.serialize(this);
    }
}
