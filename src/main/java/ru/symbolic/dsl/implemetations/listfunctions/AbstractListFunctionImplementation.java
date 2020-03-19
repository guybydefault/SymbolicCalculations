package ru.symbolic.dsl.implemetations.listfunctions;

import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.implemetations.AbstractFunctionImplementation;
import ru.symbolic.visitors.cast.AsExpressionVisitor;

import java.util.List;
import java.util.Objects;

import static ru.symbolic.dsl.functions.ListFunctions.List;

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
