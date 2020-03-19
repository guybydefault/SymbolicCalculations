package ru.symbolic.dsl.implemetations.listfunctions;

import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.functions.ListFunctions;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AppendImplementation extends AbstractListFunctionImplementation {
    private static final StringSymbol[] names = new StringSymbol[] {ListFunctions.Append};

    public AppendImplementation() {
        super(names);
    }

    @Override
    protected Symbol evaluateList(Expression expression, List<Symbol> items) {
        //TODO
        List<Symbol> newItems = new LinkedList<>(items);
        items.addAll(expression.getArguments().stream().skip(1).collect(Collectors.toList()));
        return new Expression(ListFunctions.List, items);
    }

}
