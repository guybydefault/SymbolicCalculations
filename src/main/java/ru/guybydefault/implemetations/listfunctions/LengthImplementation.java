package ru.guybydefault.implemetations.listfunctions;

import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.ListFunctions;

import java.util.LinkedList;
import java.util.List;

public class LengthImplementation extends AbstractListFunctionImplementation {
    private static final StringSymbol[] names = new StringSymbol[] {ListFunctions.FastMap};

    public LengthImplementation() {
        super(names);
    }

    @Override
    protected Symbol evaluateList(Expression expression, List<Symbol> items) {
        return new Constant(items.size());
    }

}
