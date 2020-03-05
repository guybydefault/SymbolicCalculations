package ru.guybydefault.dsl.implemetations.listfunctions;

import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.implemetations.AbstractFunctionImplementation;
import ru.guybydefault.visitors.cast.AsExpressionVisitor;

import java.util.List;
import java.util.Objects;

import static ru.guybydefault.dsl.functions.ListFunctions.List;

public abstract class AbstractListFunctionImplementation extends AbstractFunctionImplementation {
    protected AbstractListFunctionImplementation(StringSymbol[] names) {
        super(names);
    }

    protected abstract Symbol evaluateList(Expression expression, List<Symbol> items);

    protected Symbol evaluate(Expression expression) {
        Expression list = expression.getArguments().get(0).visit(AsExpressionVisitor.getInstance());

        if (list == null || !Objects.equals(list.getHead(), List)) {
//            throw new IllegalArgumentException("Argument is not a list");
            return expression;
        }

        return evaluateList(expression, list.getArguments());
    }

}
