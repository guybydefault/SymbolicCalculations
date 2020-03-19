package ru.symbolic.dsl.implemetations;

import ru.symbolic.visitors.cast.AsExpressionVisitor;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.functions.ListFunctions;
import ru.symbolic.dsl.library.Functions;


public class ApplyListImplementation extends AbstractFunctionImplementation {

    private static final StringSymbol[] names = new StringSymbol[] {Functions.ApplyList};

    public ApplyListImplementation(){
        super(names);
    }

    @Override
    protected Symbol evaluate(Expression expression) {
        Symbol func = expression.getArguments().get(0);
        Expression list = expression.getArguments().get(1).visit(new AsExpressionVisitor());

        if (list != null && (list.getHead() != ListFunctions.List)){
            return expression;
        }

        try {
            return new Expression(func, list.getArguments());
        } catch (Exception e) {
            return null;
        }
    }
}
