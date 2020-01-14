package ru.guybydefault.implemetations;

import ru.guybydefault.ISymbolVisitor;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;

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
