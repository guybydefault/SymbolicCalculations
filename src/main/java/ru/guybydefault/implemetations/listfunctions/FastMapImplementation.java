package ru.guybydefault.implemetations.listfunctions;

import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.ListFunctions;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FastMapImplementation extends AbstractListFunctionImplementation {
    private static final StringSymbol[] names = new StringSymbol[] {ListFunctions.FastMap};

    public FastMapImplementation() {
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
