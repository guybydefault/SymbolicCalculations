package ru.guybydefault.implemetations.casting;

import ru.guybydefault.cast.AsConstantVisitor;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.CastingFunctions;
import ru.guybydefault.implemetations.AbstractFunctionImplementation;

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
