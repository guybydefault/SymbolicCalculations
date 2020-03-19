package ru.symbolic.dsl.implemetations.casting;

import ru.symbolic.visitors.cast.AsExpressionVisitor;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.functions.CastingFunctions;
import ru.symbolic.dsl.functions.ListFunctions;
import ru.symbolic.dsl.implemetations.AbstractFunctionImplementation;

public class AsExpressionArgsImplementation extends AbstractFunctionImplementation {
    private static final StringSymbol[] names = new StringSymbol[] {CastingFunctions.AsExpressionArgs};

    public AsExpressionArgsImplementation() {
        super(names);
    }

    @Override
    protected Symbol evaluate(Expression expression) {
        Symbol head = expression.getArguments().get(0);
        Symbol argument = expression.getArguments().get(1);

        Expression ex = (Expression) argument.visit(new AsExpressionVisitor());

        if (ex == null) {
            return CastingFunctions.Null;
        }

        if (!(ex.getHead().equals(head))) {
            return CastingFunctions.Null;
        }

        return new Expression(ListFunctions.List, ex.getArguments());
    }
}
