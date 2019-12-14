package ru.guybydefault.domain;

import ru.guybydefault.services.Serializer;
import ru.guybydefault.visitors.Evaluator;

public interface Expression {
    Expression evaluate(Evaluator visitor);
    String serialize(Serializer serializer);
}
