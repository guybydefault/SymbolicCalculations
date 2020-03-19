package ru.symbolic.visitors.attributes;

import ru.symbolic.dsl.library.Attributes;
import ru.symbolic.visitors.ISymbolVisitor;
import ru.symbolic.SymbolComparer;
import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class OrderlessHandler implements ISymbolVisitor {
    private static final Comparator comparer = new SymbolComparer();

    private static final HasAttributeChecker orderlessChecker = new HasAttributeChecker(Attributes.Orderless);

    @Override
    public Object visitExpression(Expression expression) {
        List<Symbol> arguments = new LinkedList<>(expression.getArguments());
        arguments.sort(comparer);
        return ((boolean) expression.getHead().visit(orderlessChecker))
                ? new Expression(expression.getHead(), arguments)
                : expression;
    }

    @Override
    public Object visitSymbol(StringSymbol symbol) {
        return symbol;
    }

    @Override
    public Object visitConstant(Constant constant) {
        return constant;
    }
}
