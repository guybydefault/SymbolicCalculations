package ru.guybydefault.dsl.implemetations.listfunctions;

import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.ListFunctions;

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
        for (Symbol item : items) {
            mappedItems.add(Expression.newInstance(expression.getArguments().get(1), item));
        }
        return new Expression(ListFunctions.List, mappedItems);
    }

}
