package ru.guybydefault.dsl.implemetations;

import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.ArithmeticFunctions;
import ru.guybydefault.visitors.cast.AsConstantVisitor;

public class DivideImplementation extends AbstractFunctionImplementation {

    private static final StringSymbol[] names = new StringSymbol[]{ArithmeticFunctions.Divide};

    public DivideImplementation(StringSymbol[] names) {
        super(names);
    }

    @Override
    protected Symbol evaluate(Expression expression) {
        Constant c1 = expression.getArguments().get(0).visit(AsConstantVisitor.getInstance());
        Constant c2 = expression.getArguments().get(1).visit(AsConstantVisitor.getInstance());

        if (c1 != null && c2 != null) {
            return new Constant(c1.getValue() / c2.getValue());
        }

        return expression;
    }
}
