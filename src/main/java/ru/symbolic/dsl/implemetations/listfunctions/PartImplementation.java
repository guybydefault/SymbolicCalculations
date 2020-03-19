package ru.symbolic.dsl.implemetations.listfunctions;

import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.functions.ListFunctions;
import ru.symbolic.visitors.cast.AsConstantVisitor;

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
