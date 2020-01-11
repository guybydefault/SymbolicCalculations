package ru.guybydefault.implemetations;

import ru.guybydefault.cast.AsExpressionVisitor;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.ListFunctions;
import ru.guybydefault.dsl.library.Functions;


public class ApplyListImplementation extends AbstractFunctionImplementation {

    private final StringSymbol[] names = new StringSymbol[] {Functions.ApplyList};

    public ApplyListImplementation(StringSymbol[] names){
        super(names);
    }

    @Override
    protected Symbol Evaluate(Expression expression) {
        Symbol func = expression.getArguments().get(0);
        Expression list = (Expression) expression.getArguments().get(1).visit(new AsExpressionVisitor());

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
