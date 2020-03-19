package ru.symbolic.visitors;

import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;

public interface ISymbolVisitor<T> {
    T visitExpression(Expression expression);
    T visitSymbol(StringSymbol symbol);
    T visitConstant(Constant constant);
}
