package ru.symbolic.visitors.cast;

import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.visitors.ISymbolVisitor;

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
