package ru.guybydefault.implemetations.booleanFunctions;

import ru.guybydefault.SymbolComparer;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.BooleanFunctions;
import ru.guybydefault.implemetations.AbstractFunctionImplementation;

public class CompareImplementation extends AbstractFunctionImplementation {
    private static final SymbolComparer symbolComparer = new SymbolComparer();

    private final StringSymbol[] names = new StringSymbol[] {BooleanFunctions.Compare};

    public CompareImplementation(StringSymbol[] names) {
        super(names);
    }

    @Override
    protected Symbol Evaluate(Expression expression) {
        Symbol x = expression.getArguments().get(0);
        Symbol y = expression.getArguments().get(1);

        if (x.getClass() != y.getClass()) {
            return expression;
        }

        return new Constant(symbolComparer.compare(x, y));
    }
}
