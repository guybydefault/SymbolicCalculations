package ru.guybydefault.old.domain.operations;

import ru.guybydefault.old.domain.OrdinaryExpression;
import ru.guybydefault.old.domain.UnaryOp;
import ru.guybydefault.old.services.Serializer;
import ru.guybydefault.old.visitors.Evaluator;

import java.util.List;

public class Minus extends UnaryOp {
    public Minus(OrdinaryExpression arg) {
        super(arg);
    }

    @Override
    public OrdinaryExpression evaluate(List<Evaluator> evaluators) {
        OrdinaryExpression expression = this;
        for (Evaluator evaluator : evaluators) {
         expression = evaluator.evaluate(this);
        }
        return expression;
    }

    @Override
    public String serialize(Serializer serializer) {
        return serializer.serialize(this);
    }
}
