package ru.guybydefault.dsl.implemetations.listfunctions;

import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.ArithmeticFunctions;
import ru.guybydefault.dsl.functions.ListFunctions;

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
