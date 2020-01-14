package ru.guybydefault.implemetations.listfunctions;

import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.CastingFunctions;
import ru.guybydefault.dsl.functions.ListFunctions;
import ru.guybydefault.implemetations.AbstractFunctionImplementation;

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
        List<Symbol> newItems = new LinkedList<>(items);
        items.addAll(expression.getArguments().stream().skip(1).collect(Collectors.toList()));
        return new Expression(ListFunctions.List, items);
    }

}
