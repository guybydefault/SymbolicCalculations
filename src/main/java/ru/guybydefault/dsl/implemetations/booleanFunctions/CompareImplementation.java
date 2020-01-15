package ru.guybydefault.dsl.implemetations.booleanFunctions;

import ru.guybydefault.SymbolComparer;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.BooleanFunctions;
import ru.guybydefault.dsl.implemetations.AbstractFunctionImplementation;

public class CompareImplementation extends AbstractFunctionImplementation {
    private static final SymbolComparer symbolComparer = new SymbolComparer();

    private static final StringSymbol[] names = new StringSymbol[] {BooleanFunctions.Compare};

    public CompareImplementation() {
        super(names);
    }

    @Override
    protected Symbol evaluate(Expression expression) {
        Symbol x = expression.getArguments().get(0);
        Symbol y = expression.getArguments().get(1);

        if (x.getClass() != y.getClass()) {
            return expression;
        }

        return new Constant(symbolComparer.compare(x, y));
    }
}
