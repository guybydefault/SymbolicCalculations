package ru.guybydefault.visitors.cast;

import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;
import ru.guybydefault.visitors.ISymbolVisitor;

public class AsExpressionVisitor implements ISymbolVisitor<Expression> {

    private static final AsExpressionVisitor instance = new AsExpressionVisitor();

    @Override
    public Expression visitExpression(Expression expression) {
        return expression;
    }

    @Override
    public Expression visitSymbol(StringSymbol symbol) {
        return null;
    }

    @Override
    public Expression visitConstant(Constant constant) {
        return null;
    }

    public static AsExpressionVisitor getInstance() {
        return instance;
    }
}
