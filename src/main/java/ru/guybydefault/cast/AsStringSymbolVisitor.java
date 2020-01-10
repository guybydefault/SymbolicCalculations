package ru.guybydefault.cast;

import ru.guybydefault.*;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;

public class AsStringSymbolVisitor implements ISymbolVisitor {
    @Override
    public Object visitExpression(Expression expression) {
        return null;
    }

    @Override
    public Object visitSymbol(StringSymbol symbol) {
        return symbol;
    }

    @Override
    public Object visitConstant(Constant constant) {
        return null;
    }
}
