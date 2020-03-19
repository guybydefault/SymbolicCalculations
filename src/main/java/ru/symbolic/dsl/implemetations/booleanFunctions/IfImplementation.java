package ru.symbolic.dsl.implemetations.booleanFunctions;

import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.functions.BooleanFunctions;
import ru.symbolic.dsl.implemetations.AbstractFunctionImplementation;

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
