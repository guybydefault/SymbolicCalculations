package ru.symbolic.dsl.implemetations;

import ru.symbolic.visitors.ISymbolVisitor;
import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;

public abstract class AbstractFunctionImplementation implements ISymbolVisitor<Symbol> {
    private final StringSymbol[] names;
    protected AbstractFunctionImplementation(StringSymbol[] names) {
        this.names = names;
    }

    public Symbol visitExpression(Expression expression) {
        for (StringSymbol s : names) {
            if (expression.getHead().equals(s)) {
                return evaluate(expression);
            }
        }
        return expression;
    }

    public Symbol visitSymbol(StringSymbol symbol) {
        return symbol;
    }
    public Symbol visitConstant(Constant constant) {
        return constant;
    }

    protected abstract Symbol evaluate(Expression expression);
}
