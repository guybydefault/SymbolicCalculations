package ru.guybydefault.dsl.implemetations.listfunctions;

import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.ListFunctions;
import ru.guybydefault.dsl.implemetations.VariableAssigner;
import ru.guybydefault.dsl.library.Functions;
import ru.guybydefault.visitors.cast.AsExpressionVisitor;
import ru.guybydefault.visitors.evaluation.VariableReplacer;

import java.util.LinkedList;
import java.util.List;

public class FMapImplementation extends AbstractListFunctionImplementation {
    private static final StringSymbol[] names = new StringSymbol[] {ListFunctions.FMap};

    public FMapImplementation() {
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
