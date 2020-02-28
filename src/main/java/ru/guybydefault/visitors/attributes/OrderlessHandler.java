package ru.guybydefault.visitors.attributes;

import ru.guybydefault.dsl.library.Attributes;
import ru.guybydefault.visitors.ISymbolVisitor;
import ru.guybydefault.SymbolComparer;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;

import java.util.Collections;
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
