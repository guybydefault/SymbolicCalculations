package ru.symbolic.dsl.implemetations.casting;

import ru.symbolic.visitors.cast.AsConstantVisitor;
import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.functions.CastingFunctions;
import ru.symbolic.dsl.implemetations.AbstractFunctionImplementation;

public class AsConstantImplementation extends AbstractFunctionImplementation {

    private static final StringSymbol[] names = new StringSymbol[] {CastingFunctions.AsConstant};

    public AsConstantImplementation() {
        super(names);
    }

    @Override
    protected Symbol evaluate(Expression expression) {
        Symbol argument = expression.getArguments().get(0);
        Constant constant = (Constant) argument.visit(new AsConstantVisitor());

        return (constant == null) ? constant : CastingFunctions.Null;
    }
}
