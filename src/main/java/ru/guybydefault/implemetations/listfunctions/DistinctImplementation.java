package ru.guybydefault.implemetations.listfunctions;

import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.ListFunctions;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class DistinctImplementation extends AbstractListFunctionImplementation {
    private static final StringSymbol[] names = new StringSymbol[] {ListFunctions.Distinct};

    public DistinctImplementation() {
        super(names);
    }

    @Override
    protected Symbol evaluateList(Expression expression, List<Symbol> items) {
        return new Expression(ListFunctions.List, items.stream().distinct().collect(Collectors.toList()));
    }

}
