package ru.guybydefault.dsl.implemetations.listfunctions;

import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.ListFunctions;
import ru.guybydefault.visitors.cast.AsConstantVisitor;

import java.util.List;

public class PartImplementation extends AbstractListFunctionImplementation {
    private static final StringSymbol[] names = new StringSymbol[] {ListFunctions.Part};

    public PartImplementation() {
        super(names);
    }

    @Override
    protected Symbol evaluateList(Expression expression, List<Symbol> items) {
        Constant constant = expression.getArguments().get(1).visit(AsConstantVisitor.getInstance());
        if (constant == null) {
            throw new IllegalArgumentException("Only constant arg is supported as a param in ListFunctions.Part");
        }

        return items.get((int) constant.getValue());
    }

}
