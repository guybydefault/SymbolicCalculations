package ru.guybydefault.visitors;

import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.StringSymbol;

public interface ISymbolVisitor<T> {
    T visitExpression(Expression expression);
    T visitSymbol(StringSymbol symbol);
    T visitConstant(Constant constant);
}
