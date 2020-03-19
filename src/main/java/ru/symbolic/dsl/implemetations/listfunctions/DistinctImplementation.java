package ru.symbolic.dsl.implemetations.listfunctions;

import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.functions.ListFunctions;

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
