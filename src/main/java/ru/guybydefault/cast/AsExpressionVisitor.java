package ru.guybydefault.cast;

import ru.guybydefault.*;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;

public class AsExpressionVisitor implements ISymbolVisitor {
    @Override
    public Object visitExpression(Expression expression) {
        return expression;
    }

    @Override
    public Object visitSymbol(StringSymbol symbol) {
        return null;
    }

    @Override
    public Object visitConstant(Constant constant) {
        return null;
    }
}
