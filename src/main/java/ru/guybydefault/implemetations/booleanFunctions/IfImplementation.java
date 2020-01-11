package ru.guybydefault.implemetations.booleanFunctions;

import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.BooleanFunctions;
import ru.guybydefault.implemetations.AbstractFunctionImplementation;

public class IfImplementation extends AbstractFunctionImplementation {

    private final StringSymbol[] names = new StringSymbol[] {BooleanFunctions.If};

    public IfImplementation(StringSymbol[] names) {
        super(names);
    }

    @Override
    protected Symbol Evaluate(Expression expression) {
        if (expression.getArguments().get(0).equals(BooleanFunctions.True)) {
            return expression.getArguments().get(1);
        }

        if (expression.getArguments().get(0).equals(BooleanFunctions.False)) {
            return expression.getArguments().get(2);
        }

        return expression;
    }
}
