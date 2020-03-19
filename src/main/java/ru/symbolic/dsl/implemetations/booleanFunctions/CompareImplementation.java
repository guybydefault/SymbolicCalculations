package ru.symbolic.dsl.implemetations.booleanFunctions;

import ru.symbolic.SymbolComparer;
import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.functions.BooleanFunctions;
import ru.symbolic.dsl.implemetations.AbstractFunctionImplementation;

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
