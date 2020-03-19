package ru.symbolic.dsl.implemetations.listfunctions;

import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.functions.ArithmeticFunctions;
import ru.symbolic.dsl.functions.ListFunctions;

import java.util.List;

public class ListSumImplementation extends AbstractListFunctionImplementation {
    private static final StringSymbol[] names = new StringSymbol[] {ListFunctions.ListSum};

    public ListSumImplementation() {
        super(names);
    }

    @Override
    public Symbol evaluateList(Expression expression, List<Symbol> items) {
        return new Expression(ArithmeticFunctions.Plus, items);
    }
}
