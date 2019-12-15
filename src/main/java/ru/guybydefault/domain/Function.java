package ru.guybydefault.domain;


import ru.guybydefault.services.Serializer;
import ru.guybydefault.visitors.Evaluator;

public class Function implements OrdinaryExpression {

    private final OrdinaryExpression arg1;
    private final OrdinaryExpression arg2;

    private final FunctionType type;

    public Function(String type, OrdinaryExpression arg1) {
        this.arg1 = arg1;
        this.arg2 = null;
        this.type = FunctionType.findByName(type);
    }

    public Function(String type, OrdinaryExpression arg1, OrdinaryExpression arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.type = FunctionType.findByName(type);
    }

    public OrdinaryExpression getArg1() {
        return arg1;
    }

    public OrdinaryExpression getArg2() {
        return arg2;
    }

    public FunctionType getType() {
        return type;
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
