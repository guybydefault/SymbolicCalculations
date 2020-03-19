package ru.symbolic.dsl.implemetations.casting;

import ru.symbolic.visitors.cast.AsStringSymbolVisitor;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.functions.CastingFunctions;
import ru.symbolic.dsl.implemetations.AbstractFunctionImplementation;

public class AsStringSymbolImplementation extends AbstractFunctionImplementation {

    private static final StringSymbol[] names = new StringSymbol[] {CastingFunctions.AsStringSymbol};

    public AsStringSymbolImplementation() {
        super(names);
    }

    @Override
    protected Symbol evaluate(Expression expression) {
        Symbol argument = expression.getArguments().get(0);
        StringSymbol stringSymbol = (StringSymbol) argument.visit(new AsStringSymbolVisitor());

        return (stringSymbol == null) ? stringSymbol : CastingFunctions.Null;
    }
}
