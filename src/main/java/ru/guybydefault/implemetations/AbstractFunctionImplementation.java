package ru.guybydefault.implemetations;

import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.domain.Symbol;

public abstract class AbstractFunctionImplementation {
    private final StringSymbol[] names;
    protected AbstractFunctionImplementation(StringSymbol[] names) {
        this.names = names;
    }

    public Symbol VisitExpression(Expression expression) {
        for (StringSymbol s : names) {
            if (expression.getHead().equals(s)) {
                return Evaluate(expression);
            }
        }
        return expression;
    }

    public Symbol VisitSymbol(StringSymbol symbol) {
        return symbol;
    }
    public Symbol VisitConstant(Constant constant) {
        return constant;
    }

    protected abstract Symbol Evaluate(Expression expression);
}
