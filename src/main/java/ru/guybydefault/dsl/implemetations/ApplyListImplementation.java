package ru.guybydefault.dsl.implemetations;

import ru.guybydefault.visitors.cast.AsExpressionVisitor;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.ListFunctions;
import ru.guybydefault.dsl.library.Functions;


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
