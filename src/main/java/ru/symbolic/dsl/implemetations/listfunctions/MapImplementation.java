package ru.symbolic.dsl.implemetations.listfunctions;

import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.functions.ListFunctions;
import ru.symbolic.dsl.library.Functions;
import ru.symbolic.visitors.cast.AsExpressionVisitor;

import java.util.LinkedList;
import java.util.List;

public class MapImplementation extends AbstractListFunctionImplementation {
    private static final StringSymbol[] names = new StringSymbol[] {ListFunctions.Map};

    public MapImplementation() {
        super(names);
    }

    @Override
    protected Symbol evaluateList(Expression expression, List<Symbol> items) {
        List<Symbol> mappedItems = new LinkedList<>();
        Expression funcExpr = expression.getArguments().get(1).visit(AsExpressionVisitor.getInstance());
        if (funcExpr == null || !funcExpr.getHead().equals(Functions.Fun)) {
            throw new IllegalArgumentException("The last arg in FMap should be Function");
        }
        Symbol funcParameter = funcExpr.getArguments().get(0);
        Expression funcBody = funcExpr.getArguments().get(1).visit(AsExpressionVisitor.getInstance());
        if (funcBody == null) {
            throw new IllegalArgumentException("Function body is not an expression");
        }
        for (Symbol item : items) {
            mappedItems.add(new Expression(new Expression(Functions.Fun, funcParameter, funcBody), item));
        }
        return new Expression(ListFunctions.List, mappedItems);
    }

}
