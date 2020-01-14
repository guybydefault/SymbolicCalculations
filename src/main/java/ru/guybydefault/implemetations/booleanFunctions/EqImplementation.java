package ru.guybydefault.implemetations.booleanFunctions;

import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.BooleanFunctions;
import ru.guybydefault.dsl.functions.CastingFunctions;
import ru.guybydefault.implemetations.AbstractFunctionImplementation;

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
