package ru.guybydefault.implemetations.casting;

import ru.guybydefault.cast.AsStringSymbolVisitor;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.CastingFunctions;
import ru.guybydefault.implemetations.AbstractFunctionImplementation;

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
