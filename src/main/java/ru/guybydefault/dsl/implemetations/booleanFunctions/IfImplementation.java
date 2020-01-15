package ru.guybydefault.dsl.implemetations.booleanFunctions;

import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.BooleanFunctions;
import ru.guybydefault.dsl.implemetations.AbstractFunctionImplementation;

public class IfImplementation extends AbstractFunctionImplementation {

    private static final StringSymbol[] names = new StringSymbol[] {BooleanFunctions.If};

    public IfImplementation() {
        super(names);
    }

    @Override
    protected Symbol evaluate(Expression expression) {
        if (expression.getArguments().get(0).equals(BooleanFunctions.True)) {
            return expression.getArguments().get(1);
        }

        if (expression.getArguments().get(0).equals(BooleanFunctions.False)) {
            return expression.getArguments().get(2);
        }

        return expression;
    }
}
