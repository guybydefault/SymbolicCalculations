package ru.symbolic.dsl.implemetations.booleanFunctions;

import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.functions.BooleanFunctions;
import ru.symbolic.dsl.functions.CastingFunctions;
import ru.symbolic.dsl.implemetations.AbstractFunctionImplementation;

public class EqImplementation extends AbstractFunctionImplementation {
    private static final StringSymbol[] names = new StringSymbol[] {BooleanFunctions.Eq};

    public EqImplementation() {
        super(names);
    }

    @Override
    protected Symbol evaluate(Expression expression) {
        Symbol x = expression.getArguments().get(0);
        Symbol y = expression.getArguments().get(1);

        if (x.getClass() != y.getClass() && !x.equals(CastingFunctions.Null) && !y.equals(CastingFunctions.Null)) {
            return expression;
        }

        return x.equals(y) ? BooleanFunctions.True : BooleanFunctions.False;
    }
}
