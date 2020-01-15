package ru.guybydefault.dsl.implemetations.casting;

import ru.guybydefault.visitors.cast.AsExpressionVisitor;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.CastingFunctions;
import ru.guybydefault.dsl.functions.ListFunctions;
import ru.guybydefault.dsl.implemetations.AbstractFunctionImplementation;

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
