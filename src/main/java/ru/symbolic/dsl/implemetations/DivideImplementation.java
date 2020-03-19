package ru.symbolic.dsl.implemetations;

import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.functions.ArithmeticFunctions;
import ru.symbolic.visitors.cast.AsConstantVisitor;

public class DivideImplementation extends AbstractFunctionImplementation {

    private static final StringSymbol[] names = new StringSymbol[]{ArithmeticFunctions.Divide};

    public DivideImplementation() {
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
